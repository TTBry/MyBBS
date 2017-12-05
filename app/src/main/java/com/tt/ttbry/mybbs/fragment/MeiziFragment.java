package com.tt.ttbry.mybbs.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tt.ttbry.mybbs.R;
import com.tt.ttbry.mybbs.adapter.MeiziAdapter;
import com.tt.ttbry.mybbs.config.API;
import com.tt.ttbry.mybbs.model.Meizi;
import com.tt.ttbry.mybbs.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TTBry on 2017/12/5.
 */

public class MeiziFragment extends BaseFragment {
    private static final int MEIZI_COUNT = 20;
    private int page = 1;
    private List<Meizi> meiziList = new ArrayList<>();

    private RecyclerView recyclerView;
    private MeiziAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meizi_fragment_layout, container, false);
        initView(view);
        getMeizi();
        return view;
    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.recycler_view_meizi);
        //LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        //manager.setOrientation(LinearLayoutManager.VERTICAL);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new MeiziAdapter(this, meiziList);
        recyclerView.setAdapter(adapter);

        //滑动到底部后加载更多
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //用来标记是否正在向最后一个滑动，既是否向下滑动
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    int[] lastVisiblePositions = manager.findLastVisibleItemPositions(new int[manager.getSpanCount()]);
                    int lastVisiblePos = getMaxElem(lastVisiblePositions);
                    int totalItemCount = manager.getItemCount();

                    // 判断是否滚动到底部
                    if (lastVisiblePos == (totalItemCount -1) && isSlidingToLast) {
                        page ++;
                        getMeizi();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                if(dy > 0){
                    //大于0表示，正在向下滚动
                    isSlidingToLast = true;
                }else{
                    //小于等于0 表示停止或向上滚动
                    isSlidingToLast = false;
                }
            }
        });
    }

    private void getMeizi(){
        httpUtil.get(getApi(), new HttpUtil.OnResponseHandler() {
            @Override
            public void onSuccess(String content) {
                if(!TextUtils.isEmpty(content)){
                    JSONObject object = JSON.parseObject(content);
                    if(!object.getBoolean("error")){
                        JSONArray array = object.getJSONArray("results");
                        meiziList.addAll(JSON.parseArray(array.toJSONString(), Meizi.class));
                        adapter.notifyDataSetChanged();
                    }else{
                        showToast(getString(R.string.server_error));
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

    /**
     * 获取API地址,每获取一次页数+1，也就是按顺序获取，以后可随机获取
     */
    private String getApi(){
        StringBuilder builder = new StringBuilder(API.GANHUO_FULI);
        builder.append("/" + MEIZI_COUNT).append("/" + page);
        page ++;
        return builder.toString();
    }

    private int getMaxElem(int[] arr) {
        int size = arr.length;
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            if (arr[i]>maxVal)
                maxVal = arr[i];
        }
        return maxVal;
    }
}