package com.sds.dslibrary.lib.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by sds on 2017-07-10.
 */

public abstract class DsScrollingBehavior<V extends View> extends DsViewOffsetBehavior<V> {

    private int mTouchSlop;
    private int mSkippedOffset;

    private boolean mIsScrolling;

    public DsScrollingBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        mTouchSlop = viewConfiguration.getScaledTouchSlop();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V child, View directTargetChild, View target, int nestedScrollAxes) {
        if (checkScrollDirection(nestedScrollAxes)) {
            mIsScrolling = false;
            mSkippedOffset = 0;

            doingOnStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);

            return true;
        }

        return false;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V child, View target, int dx, int dy, int[] consumed) {
        int delta = getDeltaByDirection(dx, dy);

        if (!mIsScrolling) {
            mSkippedOffset += delta;

            if (Math.abs(mSkippedOffset) >= mTouchSlop) {
                mIsScrolling = true;
                target.getParent().requestDisallowInterceptTouchEvent(true);
            }
        }

        if (mIsScrolling && delta != 0) {
            int scrollRange = getMeasuredOffset(child);
            int oldOffset = getOffset();
            int newOffset = computeScrollOffset(oldOffset, scrollRange, delta);

            consumed[1] = newOffset - oldOffset;

            setOffset(newOffset);
        }
    }

    private boolean checkScrollDirection(int nestedScrollAxes) {
        switch (getDirection()) {
            case TOP:
            case BOTTOM:
                return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;

            case LEFT:
            case RIGHT:
                return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_HORIZONTAL) != 0;

            default:
                break;
        }

        return false;
    }

    private int getDeltaByDirection(int dx, int dy) {
        switch (getDirection()) {
            case TOP:
            case BOTTOM:
                return dy;

            case LEFT:
            case RIGHT:
                return dx;

            default:
                break;
        }

        return 0;
    }

    private int getMeasuredOffset(View v) {
        switch (getDirection()) {
            case TOP:
            case BOTTOM:
                return v.getMeasuredHeight();

            case LEFT:
            case RIGHT:
                return v.getMeasuredWidth();
        }

        return 0;
    }

    public abstract void doingOnStartNestedScroll(CoordinatorLayout coordinatorLayout, V child, View directTargetChild, View target, int nestedScrollAxes);
}
