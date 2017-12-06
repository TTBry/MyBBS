package com.tt.ttbry.mybbs.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.tt.ttbry.mybbs.R;
import com.tt.ttbry.mybbs.fragment.DuanziFragment;
import com.tt.ttbry.mybbs.fragment.MeiziFragment;
import com.tt.ttbry.mybbs.fragment.NewsFragment;
import com.tt.ttbry.mybbs.fragment.TvFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private DrawerLayout drawerLayout;
    private ImageView navHeaderImage;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFragment();
        showFragment(0);

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
                        showFragment(0);
                        break;
                    case R.id.nav_duanzi:
                        showFragment(1);
                        break;
                    case R.id.nav_tv:
                        showFragment(2);
                        break;
                    case R.id.nav_meizi:
                        showFragment(3);
                        break;
                    default:
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new NewsFragment());
        fragments.add(new DuanziFragment());
        fragments.add(new TvFragment());
        fragments.add(new MeiziFragment());


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for(Fragment fragment : fragments) {
            transaction.add(R.id.fragment_layout, fragment);
        }
        hideAllFragments(transaction);
        transaction.commit();
    }

    private void hideAllFragments(FragmentTransaction transaction){
        for(Fragment fragment : fragments){
            if(fragment != null){
                transaction.hide(fragment);
            }
        }
    }

    private void showFragment(int position){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragments(transaction);
        transaction.show(fragments.get(position));
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private long lastPressedTime = 0;
    @Override
    public void onBackPressed() {
        long now = System.currentTimeMillis();
        if((now - lastPressedTime ) < 2 * 1000){
            finish();
        }else{
            showToast(getString(R.string.quit_app_tip));
            lastPressedTime = now;
        }
    }

}
