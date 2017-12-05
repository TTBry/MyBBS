package com.tt.ttbry.mybbs.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tt.ttbry.mybbs.R;
import com.tt.ttbry.mybbs.adapter.CommonPagerAdapter;
import com.tt.ttbry.mybbs.fragment.DetailFragment;
import com.tt.ttbry.mybbs.util.Check;
import com.tt.ttbry.mybbs.util.GlideHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by TTBry on 2017/12/5.
 */

public class DetailActivity extends BaseActivity {
    /**
     * 保存图片网络地址的 List
     */
    private List<String> mImageUrlList;

    /**
     * ViewPager 中所有的 Fragment
     */
    private List<Fragment> mFragments;

    /**
     * 保存图片缓存地址的 List
     */
    private List<String> mCachePathList;

    private static final String IMAGE_URL_LIST = "imageUrlList";
    private static final String POSITION = "position";

    private ViewPager mVpShowPhoto;

    public static void startActivity(Context context, ArrayList<String> imageUrlList, int position) {
        Intent intent = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(IMAGE_URL_LIST, imageUrlList);
        bundle.putInt(POSITION, position);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mImageUrlList = new ArrayList<>();
        mFragments = new ArrayList<>();
        mCachePathList = new ArrayList<>();
        int position = getIntent().getIntExtra(POSITION, -1);
        mImageUrlList = getIntent().getStringArrayListExtra(IMAGE_URL_LIST);
        mVpShowPhoto = findViewById(R.id.detail_vp_show_photo);
        initViewWithCache(position);
    }

    /**
     * 通过图片的缓存地址来初始化界面
     */
    private void initViewWithCache(final int position) {
        Task.call(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                for (String imageUrl : mImageUrlList) {
                    mCachePathList.add(GlideHelper.getImagePathFromCache(imageUrl, DetailActivity.this));
                }
                return mCachePathList;
            }
        }, Task.BACKGROUND_EXECUTOR).continueWith(new Continuation<List<String>, Object>() {
            @Override
            public Object then(Task<List<String>> task) throws Exception {
                List<String> mCachePathList = task.getResult();
                if(!Check.isEmpty(mCachePathList)){
                    for (String cachePath : mCachePathList) {
                        DetailFragment fragment = DetailFragment.newInstance(cachePath);
                        mFragments.add(fragment);
                    }
                    CommonPagerAdapter adapter = new CommonPagerAdapter(getSupportFragmentManager(), mFragments);
                    mVpShowPhoto.setAdapter(adapter);
                    mVpShowPhoto.setCurrentItem(position);
                }
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
