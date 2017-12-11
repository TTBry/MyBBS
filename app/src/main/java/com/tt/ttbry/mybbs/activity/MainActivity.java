package com.tt.ttbry.mybbs.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.github.lazylibrary.util.DataCleanManager;
import com.tt.ttbry.mybbs.R;
import com.tt.ttbry.mybbs.adapter.MyFragmentPagerAdapter;
import com.tt.ttbry.mybbs.fragment.DuanziFragment;
import com.tt.ttbry.mybbs.fragment.MeijuFragment;
import com.tt.ttbry.mybbs.fragment.MeiziFragment;
import com.tt.ttbry.mybbs.fragment.NewsFragment;
import com.tt.ttbry.mybbs.fragment.TvFragment;
import com.tt.ttbry.mybbs.fragment.ZhihuFragment;
import com.tt.ttbry.mybbs.util.CacheUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    public static final int PAGE_NEWS = 0;//新闻
    public static final int PAGE_DUANZI = 1;//段子
    public static final int PAGE_TV = 2;//TV
    public static final int PAGE_ZHIHU = 3;//知乎日报
    public static final int PAGE_MEIJU = 4;//看剧
    public static final int PAGE_MEIZI = 5;//妹子

    private DrawerLayout drawerLayout;
    private ImageView navHeaderImage;
    private List<Fragment> fragments;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFragment();
    }

    private void initView(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
        }

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.nav_news);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_news:
                        viewPager.setCurrentItem(PAGE_NEWS);
                        break;
                    case R.id.nav_duanzi:
                        viewPager.setCurrentItem(PAGE_DUANZI);
                        break;
                    case R.id.nav_tv:
                        viewPager.setCurrentItem(PAGE_TV);
                        break;
                    case R.id.nav_zhihu:
                        viewPager.setCurrentItem(PAGE_ZHIHU);
                        break;
                    case R.id.nav_meiju:
                        viewPager.setCurrentItem(PAGE_MEIJU);
                        break;
                    case R.id.nav_meizi:
                        viewPager.setCurrentItem(PAGE_MEIZI);
                        break;
                    default:
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initFragment(){
        viewPager = findViewById(R.id.view_pager);
        fragments = new ArrayList<>();
        fragments.add(new NewsFragment());
        fragments.add(new DuanziFragment());
        fragments.add(new TvFragment());
        fragments.add(new ZhihuFragment());
        fragments.add(new MeijuFragment());
        fragments.add(new MeiziFragment());

        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.setCurrentItem(0);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.clean_cache:
                clearCache();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private long lastPressedTime = 0;
    @Override
    public void onBackPressed() {
        switch (viewPager.getCurrentItem()){
            case PAGE_MEIJU:
                MeijuFragment fragment = (MeijuFragment) fragments.get(4);
                fragment.goBack();
                break;
            default:
                long now = System.currentTimeMillis();
                if ((now - lastPressedTime) < 2 * 1000) {
                    finish();
                } else {
                    showToast(getString(R.string.quit_app_tip));
                    lastPressedTime = now;
                }
                break;
        }
    }

    private void clearCache(){
        try {
            String cacheSize = CacheUtil.getTotalCacheSize(this);
            DataCleanManager.cleanApplicationData(getApplication());
            CacheUtil.clearAllCache(this);
            showToast(getString(R.string.cache_cleared) + cacheSize);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
