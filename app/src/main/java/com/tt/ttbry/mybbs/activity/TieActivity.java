package com.tt.ttbry.mybbs.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tt.ttbry.mybbs.R;
import com.tt.ttbry.mybbs.view.MyWebView;

/**
 * Created by TTBry on 2017/11/8.
 */

public class TieActivity extends BaseActivity {
    private MyWebView webView;
    private String url;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tie_layout);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null) {
            showToast("数据获取异常");
            finish();
        }
        url = bundle.getString("url");

        initView();
    }

    private void initView(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        webView = findViewById(R.id.web_view);
        webView.loadUrl(url);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(webView != null && webView.canGoBack()){
            webView.goBack();
        }else{
            finish();
        }
    }
}
