package com.tt.ttbry.mybbs.config;

import android.app.Application;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;
import com.tt.ttbry.mybbs.event.QbSdkInitFinishedEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by TTBry on 2017/12/11.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化X5内核
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                //x5内核初始化完成回调接口，此接口回调并表示已经加载起来了x5，有可能特殊情况下x5内核加载失败
                EventBus.getDefault().post(new QbSdkInitFinishedEvent());
            }

            @Override
            public void onViewInitFinished(boolean b) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.e("MyApplication","加载内核是否成功:" + b);
            }
        });
    }
}
