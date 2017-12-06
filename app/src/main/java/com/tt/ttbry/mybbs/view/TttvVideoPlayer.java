package com.tt.ttbry.mybbs.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.tt.ttbry.mybbs.R;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by TTBry on 2017/12/6.
 */

public class TttvVideoPlayer extends JZVideoPlayerStandard {
    ImageView ivPre;
    ImageView ivNext;

    private OnChangeChannelListener onChangeChannelListener;

    public TttvVideoPlayer(Context context) {
        super(context);
    }

    public TttvVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        super.init(context);
        //移除最低下的进度条
        bottomProgressBar.setVisibility(View.GONE);
        bottomProgressBar = new ProgressBar(context);

        ivPre = findViewById(R.id.iv_pre_channel);
        ivNext = findViewById(R.id.iv_next_channel);
        ivPre.setOnClickListener(this);
        ivNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_pre_channel:
                if(onChangeChannelListener != null){
                    onChangeChannelListener.pre();
                }
                break;
            case R.id.iv_next_channel:
                if(onChangeChannelListener != null){
                    onChangeChannelListener.next();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.tttv_video_player_layout;
    }

    public void setOnChangeChannelListener(OnChangeChannelListener onChangeChannelListener) {
        this.onChangeChannelListener = onChangeChannelListener;
    }

    public interface OnChangeChannelListener{
        void next();
        void pre();
    }

    @Override
    public void setAllControlsVisiblity(int topCon, int bottomCon, int startBtn, int loadingPro, int thumbImg, int bottomPro, int retryLayout) {
        super.setAllControlsVisiblity(topCon, bottomCon, startBtn, loadingPro, thumbImg, bottomPro, retryLayout);
        bottomProgressBar.setVisibility(View.INVISIBLE);
        ivPre.setVisibility(startBtn);
        ivNext.setVisibility(startBtn);
    }

    @Override
    public void dissmissControlView() {
        if (currentState != CURRENT_STATE_NORMAL
                && currentState != CURRENT_STATE_ERROR
                && currentState != CURRENT_STATE_AUTO_COMPLETE) {
            if (getContext() != null && getContext() instanceof Activity) {
                ((Activity) getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bottomContainer.setVisibility(View.INVISIBLE);
                        topContainer.setVisibility(View.INVISIBLE);
                        startButton.setVisibility(View.INVISIBLE);
                        ivPre.setVisibility(View.INVISIBLE);
                        ivNext.setVisibility(INVISIBLE);
                        if (clarityPopWindow != null) {
                            clarityPopWindow.dismiss();
                        }
                        if (currentScreen != SCREEN_WINDOW_TINY) {
                            bottomProgressBar.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        }
    }

    @Override
    public void onStateError() {
        super.onStateError();
        startButton.setVisibility(View.GONE);
    }
}
