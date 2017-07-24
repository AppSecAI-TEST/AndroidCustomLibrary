package com.sds.dslibrary.lib.view.video;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by sds on 2017-07-07.
 */

/**
 * < 연결 관계 >
 * VideoView <-> MediaController <-> CustomMediaControllerView
 *
 * < 호출 순서 >
 * 1. DsVideoView.setMediaController() 을 호출
 * 2. 내부에서 DsSimpleMediaController.setAnchorView() 가 호출 됨
 * 3. 내부에서 DsSimpleMediaController.makeControllerView() 가 호출 됨
 * 4. ControllerView 가 DsMediaControllerViewListener 를 implement 했다면
 * onConnectedMediaPlayer() 를 호출 함
 */
public class DsVideoView extends VideoView {

    private MediaController mMediaController = null;

    public DsVideoView(Context context) {
        super(context);
    }

    public DsVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DsVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MediaController getMediaController() {
        return mMediaController;
    }

    @Override
    public void setMediaController(MediaController controller) {
        mMediaController = controller;

        super.setMediaController(controller);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }
}
