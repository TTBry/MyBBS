package com.tt.ttbry.mybbs.activity;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tt.ttbry.mybbs.R;
import com.tt.ttbry.mybbs.model.Channel;
import com.tt.ttbry.mybbs.view.TttvVideoPlayer;

import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by TTBry on 2017/12/6.
 */

public class TvActivity extends BaseActivity {
    private TttvVideoPlayer videoPlayer;
    private List<Channel> channelList;
    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_layout);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            finish();
            return;
        }
        channelList = (List<Channel>) bundle.getSerializable("channels");
        position = bundle.getInt("position");

        initVideoPlayer();
    }

    private void initVideoPlayer(){
        videoPlayer = findViewById(R.id.video_player);
        videoPlayer.setOnChangeChannelListener(new TttvVideoPlayer.OnChangeChannelListener() {
            @Override
            public void next() {
                position++;
                if(position == channelList.size()){
                    position = 0;
                }
                setUpVideoPlayer();
            }

            @Override
            public void pre() {
                position--;
                if(position < 0){
                    position = channelList.size() - 1;
                }
                setUpVideoPlayer();
            }
        });

        setUpVideoPlayer();
    }

    private void setUpVideoPlayer(){
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        videoPlayer.setUp(channelList.get(position).getChannelAddress()
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL
                , channelList.get(position).getChannelName());
        videoPlayer.startVideo();
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
