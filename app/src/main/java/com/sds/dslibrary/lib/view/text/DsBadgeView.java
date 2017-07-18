package com.sds.dslibrary.lib.view.text;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

/**
 * Created by sds on 2017-07-07.
 */

public abstract class DsBadgeView extends AppCompatTextView {

    private int mCount;

    public DsBadgeView(Context context) {
        super(context);
        init();
    }

    public DsBadgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DsBadgeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER);

        mCount = -1;
        updateCount(0);
    }

    public boolean updateCount(int count) {
        if (mCount == count) {
            return false;
        }

        mCount = count;

        setVisibility(count > 0 ? View.VISIBLE : View.GONE);
        setText(String.valueOf(mCount));

        return true;
    }

    public int getCount() {
        return mCount;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int w = widthMeasureSpec;
        int h = heightMeasureSpec;

        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        switch (widthMode) {
            case MeasureSpec.AT_MOST: {
                int padding = getPaddingLeft() + getPaddingRight();
                int minW = getSuggestedMinimumWidth() + padding;
                int measureW = getMeasuredWidth() + padding;

                w = (measureW > minW) ? measureW + getAddPadding() : Math.max(minW, measureW);
            }
            break;

            default: {

            }
            break;
        }

        switch (heightMode) {
            case MeasureSpec.AT_MOST: {
                int padding = getPaddingTop() + getPaddingBottom();
                int minH = getSuggestedMinimumHeight() + padding;
                int measureH = getMeasuredHeight() + padding;

                h = Math.max(minH, measureH);
            }
            break;

            default: {

            }
            break;
        }

        setMeasuredDimension(w, h);
    }

    public abstract int getAddPadding();
}
