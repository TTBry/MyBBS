package com.tt.ttbry.mybbs.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tt.ttbry.mybbs.R;
import com.tt.ttbry.mybbs.config.API;

/**
 * Created by TTBry on 2017/12/8.
 */

public class MeijuFragment extends BaseFragment {
    private WebView webView;
    private FloatingActionButton btnMain;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meiju_fragment_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        webView = view.findViewById(R.id.web_view);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setMediaPlaybackRequiresUserGesture(true);
        settings.setUseWideViewPort(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setPluginState(WebSettings.PluginState.ON);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url1) {
                view.loadUrl(url1);
                return true;
            }
        });
        webView.loadUrl(API.NEETS_CC);

        btnMain = view.findViewById(R.id.btn_main);
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl(API.NEETS_CC);
            }
        });
    }

}
