package com.sds.dslibrary.lib.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sds on 2017-07-10.
 */

public abstract class DsViewOffsetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {

    public enum Direction {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
    };

    protected Helper mHelper = null;

    public DsViewOffsetBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, V child, int layoutDirection) {
        parent.onLayoutChild(child, layoutDirection);

        if (mHelper == null) {
            mHelper = new Helper(child);
        }

        updateOffset();

        return true;
    }

    public boolean setOffset(int offset) {
        switch (getDirection()) {
            case TOP:
            case BOTTOM:
                return setOffsetY(offset);

            case LEFT:
            case RIGHT:
                return setOffsetX(offset);

            default:
                break;
        }

        return false;
    }

    public void updateOffset() {
        switch (getDirection()) {
            case TOP:
            case BOTTOM:
                updateOffsetY();
                break;

            case LEFT:
            case RIGHT:
                updateOffsetX();
                break;

            default:
                break;
        }
    }

    protected int computeScrollOffset(int oldOffset, int range, int delta) {
        range = Math.abs(range);

        int newOffset = 0;

        switch (getDirection()) {
            case TOP:
            case LEFT:
                newOffset = Math.min(Math.max(-range, oldOffset - delta), 0);
                break;

            case BOTTOM:
            case RIGHT:
                newOffset = Math.min(Math.max(0, oldOffset + delta), range);
                break;

            default:
                break;
        }

        return newOffset;
    }

    private boolean setOffsetX(int offset) {
        return mHelper.setOffsetX(offset);
    }

    private boolean setOffsetY(int offset) {
        return mHelper.setOffsetY(offset);
    }

    public int getOffset() {
        return mHelper.mOffset;
    }

    private void updateOffsetX() {
        mHelper.updateOffsetX();
    }

    private void updateOffsetY() {
        mHelper.updateOffsetY();
    }

    public abstract Direction getDirection();

    public static class Helper {
        private View mView = null;

        private int mOffset;

        public Helper(View v) {
            mView = v;
        }

        private void updateOffsetX() {
            ViewCompat.setTranslationX(mView, (float) mOffset);
        }

        private void updateOffsetY() {
            ViewCompat.setTranslationY(mView, (float) mOffset);
        }

        public boolean setOffsetX(int offset) {
            if (mOffset == offset) {
                return false;
            }

            mOffset = offset;
            updateOffsetX();

            return true;
        }

        public boolean setOffsetY(int offset) {
            if (mOffset == offset) {
                return false;
            }

            mOffset = offset;
            updateOffsetY();

            return true;
        }
    }
}
