package com.tt.ttbry.mybbs.fragment;

import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by TTBry on 2017/11/7.
 */

public class BaseFragment extends Fragment {

    public void showToast(String content){
        if(!TextUtils.isEmpty(content)){
            Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
        }
    }
}
