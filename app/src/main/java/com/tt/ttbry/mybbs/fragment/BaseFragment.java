package com.tt.ttbry.mybbs.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.Toast;

import com.tt.ttbry.mybbs.util.HttpUtil;

/**
 * Created by TTBry on 2017/11/7.
 */

public class BaseFragment extends Fragment {
    public HttpUtil httpUtil;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        httpUtil = HttpUtil.getInstance(getActivity());
    }

    public void showToast(String content){
        if(!TextUtils.isEmpty(content)){
            Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
        }
    }
}
