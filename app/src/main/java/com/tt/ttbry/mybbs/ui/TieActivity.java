package com.tt.ttbry.mybbs.ui;

import android.os.Bundle;
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

    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if(webView != null){
                if(urlHistories.size() > 0){
                    urlHistories.remove(urlHistories.size() - 1);
                    if(urlHistories.size() > 0) {
                        webView.loadUrl(urlHistories.get(urlHistories.size() - 1));
                    }
                }
            }else{
                finish();
            }
        }
        return true;
    }*/
}
