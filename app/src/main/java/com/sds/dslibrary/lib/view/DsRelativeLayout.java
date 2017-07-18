package com.sds.dslibrary.lib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

/**
 * Created by sds on 2017-07-07.
 */

public abstract class DsRelativeLayout extends RelativeLayout {

    public DsRelativeLayout(Context context) {
        super(context);
        init(context);
    }

    public DsRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DsRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context c) {
        LayoutInflater.from(c).inflate(getLayoutResId(), this, true);
    }

    public abstract int getLayoutResId();
}
