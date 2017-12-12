package com.tt.ttbry.mybbs.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tt.ttbry.mybbs.R;
import com.tt.ttbry.mybbs.config.API;
import com.tt.ttbry.mybbs.view.MyWebView;

/**
 * Created by TTBry on 2017/12/8.
 */

public class MeijuFragment extends BaseFragment {
    private MyWebView webView;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.meiju_fragment_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        webView = view.findViewById(R.id.web_view);

        if(webView.getX5WebViewExtension() != null) {
            Bundle data = new Bundle();
            data.putBoolean("standardFullScreen", false);
            data.putBoolean("supportLiteWnd", false);
            data.putInt("DefaultVideoScreen", 1);
            webView.getX5WebViewExtension().invokeMiscMethod("setVideoParams", data);
        }
        webView.loadUrl(API.NEETS_CC);
    }

    private long lastPressedTime = 0;
    public void goBack(){
        if(webView != null && webView.canGoBack()){
            webView.goBack();
        }else{
            long now = System.currentTimeMillis();
            if ((now - lastPressedTime) < 2 * 1000) {
                getActivity().finish();
            } else {
                showToast(getString(R.string.quit_app_tip));
                lastPressedTime = now;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(webView != null){
            webView.removeAllViews();
            webView.destroy();
        }
    }
}
