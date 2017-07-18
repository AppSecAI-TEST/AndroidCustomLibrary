package com.sds.dslibrary.lib.popup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.sds.dslibrary.lib.view.DsRelativeLayout;

/**
 * Created by sds on 2017-07-07.
 */

public abstract class DsPopup extends DsRelativeLayout {

    public DsPopup(Context context) {
        super(context);
        init(context);
    }

    public DsPopup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DsPopup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context c) {
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        FrameLayout background = new FrameLayout(c);
        background.setLayoutParams(lp);
        background.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        addView(background, 0);
    }
}
