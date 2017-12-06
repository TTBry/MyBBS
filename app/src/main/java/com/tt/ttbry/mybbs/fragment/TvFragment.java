package com.tt.ttbry.mybbs.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tt.ttbry.mybbs.R;
import com.tt.ttbry.mybbs.adapter.ChannelAdapter;
import com.tt.ttbry.mybbs.config.API;
import com.tt.ttbry.mybbs.model.Channel;
import com.tt.ttbry.mybbs.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

public class TvFragment extends BaseFragment {
    private List<Channel> channelList = new ArrayList<>();

    private RecyclerView recyclerView;
    private ChannelAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tv_fragment_layout, container, false);
        initView(view);
        getChannels();
        return view;
    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ChannelAdapter(channelList);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                channelList.clear();
                getChannels();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getChannels(){
        httpUtil.get(API.TTTV_GET_CHANNEL, new HttpUtil.OnResponseHandler() {
            @Override
            public void onSuccess(String content) {
                if(!TextUtils.isEmpty(content)){
                    JSONObject object = JSON.parseObject(content);
                    if(object.getBoolean("isSuccess")){
                        JSONArray array = object.getJSONArray("data");
                        channelList.addAll(JSON.parseArray(array.toJSONString(), Channel.class));
                        adapter.notifyDataSetChanged();
                    }else{
                        showToast(getString(R.string.error) + object.getString("result"));
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
}
