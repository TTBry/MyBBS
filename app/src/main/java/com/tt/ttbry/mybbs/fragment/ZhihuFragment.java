package com.tt.ttbry.mybbs.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.tt.ttbry.mybbs.R;
import com.tt.ttbry.mybbs.activity.ZhihuArticleActivity;
import com.tt.ttbry.mybbs.adapter.StoryAdapter;
import com.tt.ttbry.mybbs.config.API;
import com.tt.ttbry.mybbs.model.zhihu.ZhihuNews;
import com.tt.ttbry.mybbs.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by TTBry on 2017/12/7.
 */

public class ZhihuFragment extends BaseFragment {
    private List<ZhihuNews.StoriesBean> stories = new ArrayList<>();
    private List<ZhihuNews.TopStoriesBean> topStories = new ArrayList<>();

    private RecyclerView recyclerView;
    private StoryAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ViewPager viewPager;
    private int index = 0;
    private boolean isContinue = true;
    private Timer timer;
    private List<View> views = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhihu_fragment_layout, container, false);
        initView(view);
        getNews();
        return view;
    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //解决recyclerView滑动不流畅，没有滚动效果的问题
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new StoryAdapter(stories);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                stories.clear();
                topStories.clear();
                getNews();
                swipeRefreshLayout.setRefreshing(false);
                if(timer != null){
                    timer.cancel();
                    timer = null;
                }
            }
        });

        viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(onPageChangeListener);
        viewPager.setOnTouchListener(onTouchListener);
    }

    private void startTimer(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(isContinue){
                    handler.sendEmptyMessage(1);
                }
            }
        }, 3000, 3000);
    }

    private void getNews(){
        httpUtil.get(API.ZHIHU_GET_NEWS_LATEST, new HttpUtil.OnResponseHandler() {
            @Override
            public void onSuccess(String content) {
                if(!TextUtils.isEmpty(content)){
                    ZhihuNews news = JSON.parseObject(content, ZhihuNews.class);
                    if(news != null){
                        stories.addAll(news.getStories());
                        topStories.addAll(news.getTop_stories());
                        adapter.notifyDataSetChanged();
                        pagerAdapter.notifyDataSetChanged();
                        startTimer();
                    }
                }else{
                    showToast(getString(R.string.no_data));
                }
            }

            @Override
            public void onFailure(Throwable error) {
                error.printStackTrace();
                showToast(getString(R.string.error) + error.getMessage());
            }
        });
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    index ++;
                    if(index >= topStories.size()){
                        index = 0;
                    }
                    viewPager.setCurrentItem(index);
                    break;
                default:
                    break;
            }
        }
    };

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {

        private long downTime;
        private int downX;
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()){
                //手指按下和划动的时候停止图片的轮播
                case MotionEvent.ACTION_MOVE:
                    isContinue = false;
                    break;
                case MotionEvent.ACTION_DOWN:
                    isContinue = false;

                    downX = (int)event.getX();
                    downTime = System.currentTimeMillis();
                    break;
                case MotionEvent.ACTION_UP:
                    long now = System.currentTimeMillis();
                    int nowX = (int) event.getX();
                    if(now - downTime < 500 && Math.abs(downX - nowX) < 30){
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", topStories.get(index).getId());
                        Intent intent = new Intent(getActivity(), ZhihuArticleActivity.class);
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
                    }
                    isContinue = true;
                    break;
                default:
                    isContinue = true;
                    break;
            }
            return true;
        }
    };

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            index = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return topStories.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.top_story_item_layout, container, false);
            ImageView imageView = view.findViewById(R.id.image_view);
            Glide.with(getActivity()).load(topStories.get(position).getImage()).into(imageView);
            TextView tvTitle = view.findViewById(R.id.tv_title);
            tvTitle.setText(topStories.get(position).getTitle());
            container.addView(view);
            views.add(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }
}
