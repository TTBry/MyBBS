package com.tt.ttbry.mybbs.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tt.ttbry.mybbs.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TTBry on 2017/11/8.
 */

public class TieActivity extends BaseActivity {
    private WebView webView;
    private String url;
    private List<String> urlHistories = new ArrayList<>();
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
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(false);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url1) {
                urlHistories.add(url1);
                view.loadUrl(url1);
                return true;
            }
        });
        webView.loadUrl(url);
        urlHistories.add(url);
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
}
