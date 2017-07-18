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

    @Override
    public void setAnchorView(View view) {
        View v = makeControllerView(this);

        super.setAnchorView(view);

        if (v != null) {
            if (v instanceof DsMediaControllerViewListener) {
                ((DsMediaControllerViewListener) v).setMediaPlayer(mPlayer);
            }

            ViewGroup.LayoutParams lp = makeLayoutParams();

            removeAllViews();
            addView(v, lp != null ? lp : generateDefaultLayoutParams());
        }
    }

    @Override
    public void setMediaPlayer(MediaPlayerControl player) {
        mPlayer = player;

        super.setMediaPlayer(player);
    }

    public MediaPlayerControl getMediaPlayer() {
        return mPlayer;
    }

    public abstract View makeControllerView(ViewGroup parent);

    public abstract ViewGroup.LayoutParams makeLayoutParams();
}
