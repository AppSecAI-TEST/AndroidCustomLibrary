package com.sds.dslibrary.test.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.sds.dslibrary.lib.behavior.DsViewOffsetBehavior;
import com.sds.dslibrary.test.view.ToolbarLayout;

import java.util.List;

/**
 * Created by sds on 2017-07-10.
 */

public class ToolbarContentLayoutBehavior extends DsViewOffsetBehavior<View> {

    public ToolbarContentLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        if (child.getLayoutParams().height == ViewGroup.LayoutParams.MATCH_PARENT) {
            List dependencies = parent.getDependencies(child);

            if (dependencies.isEmpty()) {
                return false;
            }

            ToolbarLayout toolBarLayout = findToolBar(dependencies);

            if (toolBarLayout != null && ViewCompat.isLaidOut(toolBarLayout)) {
                int scrollRange = toolBarLayout.getMeasuredHeight();
                int height = parent.getHeight() - toolBarLayout.getMeasuredHeight() + scrollRange;
                int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.AT_MOST);
                parent.onMeasureChild(child, parentWidthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) dependency.getLayoutParams()).getBehavior();
        if (behavior instanceof ToolbarLayoutBehavior) {
            int headerOffset = ((ToolbarLayoutBehavior) behavior).getOffset();
            int contentOffset = dependency.getHeight() + headerOffset;
            setOffset(contentOffset);
        }

        return false;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof ToolbarLayout;
    }

    private ToolbarLayout findToolBar(List<View> views) {
        for (View v : views) {
            if (v instanceof ToolbarLayout) {
                return (ToolbarLayout) v;
            }
        }

        return null;
    }

    @Override
    public Direction getDirection() {
        return Direction.TOP;
    }
}
