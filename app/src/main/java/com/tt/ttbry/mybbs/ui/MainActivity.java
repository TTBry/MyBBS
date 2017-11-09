package com.tt.ttbry.mybbs.ui;

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
import com.tt.ttbry.mybbs.fragment.FirstFragment;
import com.tt.ttbry.mybbs.fragment.SecondFragment;
import com.tt.ttbry.mybbs.fragment.ThirdFragment;

public class MainActivity extends BaseActivity {
    private DrawerLayout drawerLayout;
    private ImageView navHeaderImage;
    private Fragment[] fragments = new Fragment[3];

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
        navView.setCheckedItem(R.id.nav_first);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_first:
                        showFragment(0);
                        break;
                    case R.id.nav_second:
                        showFragment(1);
                        break;
                    case R.id.nav_third:
                        showFragment(2);
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
        fragments[0] = new FirstFragment();
        fragments[1] = new SecondFragment();
        fragments[2] = new ThirdFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_layout, fragments[0]);
        transaction.add(R.id.fragment_layout, fragments[1]);
        transaction.add(R.id.fragment_layout, fragments[2]);
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
        transaction.show(fragments[position]);
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

}
