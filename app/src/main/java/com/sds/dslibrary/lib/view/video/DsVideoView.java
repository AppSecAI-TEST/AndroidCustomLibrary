package com.sds.dslibrary.lib.view.video;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by sds on 2017-07-07.
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
