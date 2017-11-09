package com.tt.ttbry.mybbs.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tt.ttbry.mybbs.Config;
import com.tt.ttbry.mybbs.R;
import com.tt.ttbry.mybbs.adapter.ArticleAdapter;
import com.tt.ttbry.mybbs.model.Article;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {
    public static final int GET_DATA_SUCCESS = 1;

    private List<Article> articles = new ArrayList<>();
    private int currentPage = 1;

    private RecyclerView recyclerView;
    private ArticleAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        initView(view);
        getArticles();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        adapter = new ArticleAdapter(articles);
        recyclerView.setAdapter(adapter);
        //滑动到底部后加载更多
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = recyclerView.getAdapter().getItemCount();
                int lastVisibleItemPosition = lm.findLastVisibleItemPosition();
                int visibleItemCount = recyclerView.getChildCount();
                if(newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItemPosition == totalItemCount - 1
                        && visibleItemCount > 0){
                    //加载更多
                    currentPage ++;
                    getArticles();
                }
            }
        });
    }

    private void getArticles(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Document document = Jsoup.connect(getUrl()).get();
                    Elements elements = document.select("article.excerpt");
                    Log.d("FirstFragment", elements.html());
                    for(int i = 0; i < elements.size(); i++){
                        Element h2 = elements.get(i).select("h2").get(0);
                        String title = h2.select("a").get(0).text();
                        String url = h2.select("a").get(0).attr("href");
                        String thumb = elements.get(i).select("img").attr("src");
                        String time = elements.get(i).select("time.new").text();
                        String subTitle = elements.get(i).select("p.note").text();
                        Article article = new Article(title, thumb, time, subTitle, url);
                        articles.add(article);
                    }
                    handler.sendEmptyMessage(GET_DATA_SUCCESS);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String getUrl(){

        if(currentPage == 1){
            return Config.CCNOVEL_TRANSLATE + "index.html";
        }
        return Config.CCNOVEL_TRANSLATE + "index_" + currentPage + ".html";
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GET_DATA_SUCCESS:
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };
}
