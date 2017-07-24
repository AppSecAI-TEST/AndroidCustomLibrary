package com.sds.dslibrary.lib.view.video;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;

/**
 * Created by sds on 2017-07-11.
 */

public abstract class DsSimpleMediaController extends MediaController {

    private MediaPlayerControl mPlayer = null;

    public DsSimpleMediaController(Context context) {
        super(context);
    }

    public DsSimpleMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MediaPlayerControl getMediaPlayer() {
        return mPlayer;
    }

    /**
     * VideoView 의 setMediaController 에 의해 호출 됨
     * before called {@link #setMediaPlayer}
     * @param view video view's parent or video view
     */
    @Override
    public void setAnchorView(View view) {
        super.setAnchorView(view);

        View v = makeControllerView(this);
        if (v != null) {
            removeAllViews();

            ViewGroup.LayoutParams lp = makeLayoutParams();
            addView(v, lp != null ? lp : generateDefaultLayoutParams());

            if (v instanceof DsMediaControllerViewListener) {
                ((DsMediaControllerViewListener) v).onConnectedMediaPlayer(mPlayer);
            }
        }
    }

    /**
     * VideoView 의 setMediaController 에 의해 호출 됨
     * after called {@link #setAnchorView}
     * @param player video view
     */
    @Override
    public void setMediaPlayer(MediaPlayerControl player) {
        mPlayer = player;

        super.setMediaPlayer(player);
    }

    public abstract View makeControllerView(ViewGroup parent);
    public abstract ViewGroup.LayoutParams makeLayoutParams();
}
