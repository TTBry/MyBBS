package com.tt.ttbry.mybbs.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.tt.ttbry.mybbs.util.HttpUtil;

/**
 * Created by TTBry on 2017/11/7.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public HttpUtil httpUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        httpUtil = HttpUtil.getInstance(this);
    }

    public void showToast(String content){
        if(!TextUtils.isEmpty(content)) {
            Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
        }
    }

}
