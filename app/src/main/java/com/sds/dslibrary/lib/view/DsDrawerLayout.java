package com.sds.dslibrary.lib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;

import com.sds.dslibrary.R;

import java.lang.reflect.Field;

/**
 * Created by sds on 2017-07-24.
 */

public class DsDrawerLayout extends DrawerLayout {

    private static final int DEFAULT_DRAWER_MARGIN = 64; // dp

    private static final String MIN_DRAWER_MARGIN = "mMinDrawerMargin";

    public DsDrawerLayout(Context context) {
        super(context);
        reflection(context, null);
    }

    public DsDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        reflection(context, attrs);
    }

    public DsDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        reflection(context, attrs);
    }

    private void reflection(Context c, AttributeSet attrs) {
        TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.DsDrawerLayout);

        final float density = getResources().getDisplayMetrics().density;
        int defaultDrawerMargin = (int) (DEFAULT_DRAWER_MARGIN * density + 0.5f);
        int minDrawerMargin = a.getInt(R.styleable.DsDrawerLayout_minDrawerMargin, defaultDrawerMargin);

        a.recycle();

        try {
            Class cls = getClass().getSuperclass();
            Field field = cls.getDeclaredField(MIN_DRAWER_MARGIN);
            field.setAccessible(true);
            field.setInt(this, minDrawerMargin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
