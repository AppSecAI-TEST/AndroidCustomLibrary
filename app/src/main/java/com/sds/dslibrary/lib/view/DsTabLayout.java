package com.sds.dslibrary.lib.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.sds.dslibrary.lib.utils.DsResourceUtils;

/**
 * Created by sds on 2017-07-07.
 */

public class DsTabLayout extends TabLayout {

    public DsTabLayout(Context context) {
        super(context);
    }

    public DsTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DsTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setDividerMiddleDrawable(int resId, int padding) {
        setDividerDrawable(padding, resId, LinearLayout.SHOW_DIVIDER_MIDDLE);
    }

    public void setDividerDrawable(int resId, int padding, int showDividers) {
        Drawable d = DsResourceUtils.getDrawable(getContext(), resId);

        if (d != null) {
            LinearLayout linearLayout = (LinearLayout) getChildAt(0);

            if (linearLayout != null) {
                linearLayout.setShowDividers(showDividers);
                linearLayout.setDividerPadding(padding);
                linearLayout.setDividerDrawable(d);
            }
        }
    }
}
