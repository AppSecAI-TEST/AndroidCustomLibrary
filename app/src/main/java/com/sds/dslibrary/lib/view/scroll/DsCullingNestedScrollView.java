package com.sds.dslibrary.lib.view.scroll;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sds on 2017-07-07.
 */

public abstract class DsCullingNestedScrollView extends NestedScrollView {

    private final Rect mScrollVisibleRect = new Rect();
    private final Rect mChildVisibleRect = new Rect();

    private OnScrollCallback mScrollCallback = null;

    public DsCullingNestedScrollView(Context context) {
        super(context);
    }

    public DsCullingNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DsCullingNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void onCullingChildren() {
        int parentCount = getParentCount();

        for (int pos = 0; pos < parentCount; ++pos) {
            onCulling(getTargetParent(pos));
        }
    }

    private void onCulling(ViewGroup parent) {
        if (parent == null) {
            return;
        }

        int childCount = parent.getChildCount();

        if (childCount > 0) {
            getGlobalVisibleRect(mScrollVisibleRect);

            for (int i = 0; i < childCount; ++i) {
                View child = parent.getChildAt(i);

                if (child instanceof OnCullingListener) {
                    child.getGlobalVisibleRect(mChildVisibleRect);

                    if (mScrollVisibleRect.contains(mChildVisibleRect)) {
                        ((OnCullingListener) child).onVisible();
                    } else {
                        ((OnCullingListener) child).onCulling();
                    }
                }
            }
        }
    }

    private int calculateScrollingBottomOffset() {
        return getChildAt(0).getMeasuredHeight() - getMeasuredHeight();
    }

    public void setScrollCallback(OnScrollCallback l) {
        mScrollCallback = l;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (changed) {

        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        int dx = l - oldl;
        int dy = t - oldt;

        if (dy < 0) {
            // scroll Top
            if (t == 0) {
                if (mScrollCallback != null) {
                    mScrollCallback.onScrolledTop(this);
                }
            }
        } else if (dy > 0) {
            // scroll bottom
            if (t == calculateScrollingBottomOffset()) {
                if (mScrollCallback != null) {
                    mScrollCallback.onScrolledBottom(this);
                }
            }
        }

        if (dy != 0) {
            onCullingChildren();
        }

        if (mScrollCallback != null) {
            mScrollCallback.onScrolled(this, dx, dy);
        }
    }

    public abstract ViewGroup getTargetParent(int position);
    public abstract int getParentCount();

    public interface OnScrollCallback {
        void onScrolledTop(NestedScrollView v);
        void onScrolledBottom(NestedScrollView v);
        void onScrolled(NestedScrollView v, int dx, int dy);
    }

    public interface OnCullingListener {
        void onVisible();
        void onCulling();
    }
}
