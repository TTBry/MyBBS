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
import com.tt.ttbry.mybbs.adapter.DuanziAdapter;
import com.tt.ttbry.mybbs.config.API;
import com.tt.ttbry.mybbs.model.Duanzi;
import com.tt.ttbry.mybbs.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

public class DuanziFragment extends BaseFragment {

    private RecyclerView mRvShowDuanzi;
    private SwipeRefreshLayout mRefresh;
    private DuanziAdapter adapter;

    private List<Duanzi> duanziList = new ArrayList<>();

    public static DuanziFragment newInstance() {
        return new DuanziFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.duanzi_fragment_layout, container, false);
        initView(view);
        getDuanzi();
        return view;
    }
    private void initView(View view){
        mRvShowDuanzi = view.findViewById(R.id.duanzi_rv_show_duanzi);
        mRvShowDuanzi.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DuanziAdapter(this, duanziList, new DuanziAdapter.OnItemClickCallback() {
            @Override
            public void onItemClick(int position) {

            }
        });
        mRvShowDuanzi.setAdapter(adapter);

        mRefresh = view.findViewById(R.id.duanzi_refresh);
        mRefresh.setColorSchemeResources(R.color.colorPrimary);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDuanzi();
                mRefresh.setRefreshing(false);
            }
        });
    }

    /**
     * 获取段子列表
     */
    private void getDuanzi(){
        httpUtil.get(API.GET_DUANZI, new HttpUtil.OnResponseHandler() {
            @Override
            public void onSuccess(String content) {
                if(!TextUtils.isEmpty(content)){
                    JSONObject object = JSON.parseObject(content);
                    if("success".equals(object.getString("message"))){
                        JSONObject obj = object.getJSONObject("data");
                        JSONArray array = obj.getJSONArray("data");
                        duanziList.clear();
                        duanziList.addAll(JSON.parseArray(array.toJSONString(), Duanzi.class));
                        adapter.notifyDataSetChanged();
                    }else{
                        showToast(getString(R.string.server_error));
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
