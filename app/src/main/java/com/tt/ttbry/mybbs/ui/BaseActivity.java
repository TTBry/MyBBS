package com.tt.ttbry.mybbs.ui;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by TTBry on 2017/11/7.
 */

public class BaseActivity extends AppCompatActivity {

    public void showToast(String content){
        if(!TextUtils.isEmpty(content)) {
            Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
        }
    }
}
