package com.sds.dslibrary.lib.view.viewpager.indicator;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by sds on 2017-07-07.
 */

public class DsViewPagerIndicator extends RelativeLayout {

    private static final float DEFAULT_MARGIN_LEFT = 4.0f;   // dp
    private static final float DEFAULT_MARGIN_TOP = 4.0f;    // dp
    private static final float DEFAULT_MARGIN_RIGHT = 4.0f;  // dp
    private static final float DEFAULT_MARGIN_BOTTOM = 4.0f; // dp

    private final ArrayList<View> mIndicatorList = new ArrayList<>();

    private int mResDefault;
    private int mResSelect;

    private int mMarginLeft;
    private int mMarginTop;
    private int mMarginRight;
    private int mMarginBottom;

    private ViewGroup mViewContainer = null;

    public DsViewPagerIndicator(Context context) {
        super(context);
        initViewContainer(context);
    }

    public DsViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewContainer(context);
    }

    public DsViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViewContainer(context);
    }

    private void initViewContainer(Context context) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        mViewContainer = new LinearLayout(context);
        mViewContainer.setLayoutParams(params);
        addView(mViewContainer);
    }

    public DsViewPagerIndicator init(int count, int resDefault, int resSelect) {
        int marginLeft = dpToPx(DEFAULT_MARGIN_LEFT);
        int marginTop = dpToPx(DEFAULT_MARGIN_TOP);
        int marginRight = dpToPx(DEFAULT_MARGIN_RIGHT);
        int marginBottom = dpToPx(DEFAULT_MARGIN_BOTTOM);

        return this.init(count, resDefault, resSelect,
                marginLeft, marginTop, marginRight, marginBottom);
    }

    public DsViewPagerIndicator init(int count, int resDefault, int resSelect,
                                     int marginLeft, int marginTop, int marginRight, int marginBottom) {
        mResDefault = resDefault;
        mResSelect = resSelect;

        mMarginLeft = marginLeft;
        mMarginTop = marginTop;
        mMarginRight = marginRight;
        mMarginBottom = marginBottom;

        addView(count);

        return this;
    }

    public void startWithPosition(int position) {
        onSelectPosition(position);
    }

    public void onSelectPosition(int position) {
        int visibleCount = getVisibleCount();

        for (int i = 0; i < visibleCount; ++i) {
            View v = mIndicatorList.get(i);

            if (v == null)
                continue;

            if (v instanceof ImageView) {
                ((ImageView)v).setImageResource(i == position ? mResSelect : mResDefault);
            }
        }
    }

    private void addView(int count) {
        for (View v : mIndicatorList) {
            v.setVisibility(View.GONE);
        }

        View v;
        ViewGroup.LayoutParams params;

        for (int i = 0; i < count; ++i) {
            if (i < mIndicatorList.size()) {
                v = mIndicatorList.get(i);
                params = v.getLayoutParams();
            } else {
                params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                v = new ImageView(getContext());
                v.setLayoutParams(params);

                mViewContainer.addView(v);
                mIndicatorList.add(v);
            }

            if (params instanceof MarginLayoutParams) {
                ((MarginLayoutParams) params).setMargins(mMarginLeft, mMarginTop, mMarginRight, mMarginBottom);
            }

            if (v instanceof ImageView) {
                ((ImageView)v).setImageResource(mResDefault);
            }

            v.setVisibility(View.VISIBLE);
        }
    }

    private int getVisibleCount() {
        int count = 0;

        for (View v : mIndicatorList) {
            if (v.getVisibility() == View.VISIBLE)
                ++count;
        }

        return count;
    }

    private int dpToPx(float dp) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics()));
    }
}
