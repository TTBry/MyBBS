package com.tt.ttbry.mybbs.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.alibaba.fastjson.JSON;
import com.tt.ttbry.mybbs.R;
import com.tt.ttbry.mybbs.config.API;
import com.tt.ttbry.mybbs.model.zhihu.ZhihuArticle;
import com.tt.ttbry.mybbs.util.Check;
import com.tt.ttbry.mybbs.util.HttpUtil;
import com.tt.ttbry.mybbs.view.MyWebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by TTBry on 2017/12/7.
 */

public class ZhihuArticleActivity extends BaseActivity {
    private ZhihuArticle article;
    private Toolbar toolbar;
    private MyWebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tie_layout);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            finish();
            return;
        }
        int articleId = bundle.getInt("id");

        initView();
        getArticleById(articleId);
    }

    private void initView(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        webView = findViewById(R.id.web_view);
    }

    private void getArticleById(int id){
        httpUtil.get(API.ZHIHU_GET_NEWS_CONTENT + id, new HttpUtil.OnResponseHandler() {
            @Override
            public void onSuccess(String content) {
                if(!Check.isEmpty(content)){
                    article = JSON.parseObject(content, ZhihuArticle.class);
                    if(article != null){
                        Document document = Jsoup.parse(article.getBody());
                        Elements elements = document.select("img");
                        for(int i = 1; i < elements.size(); i++){
                            elements.get(i).attr("width", "100%");
                        }
                        webView.loadData(document.html(), "text/html; charset=UTF-8", null);
                    }else{
                        showToast(getString(R.string.no_data));
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(webView != null){
            webView.removeAllViews();
            webView.destroy();
        }
    }
}
