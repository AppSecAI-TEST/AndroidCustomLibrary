package com.sds.dslibrary.test.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import com.sds.dslibrary.lib.behavior.DsScrollingBehavior;
import com.sds.dslibrary.test.view.ToolbarLayout;

/**
 * Created by sds on 2017-07-10.
 */

public class ToolbarLayoutBehavior extends DsScrollingBehavior<ToolbarLayout> {

    public ToolbarLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public Direction getDirection() {
        return Direction.TOP;
    }

    @Override
    public void doingOnStartNestedScroll(CoordinatorLayout coordinatorLayout, ToolbarLayout child, View directTargetChild, View target, int nestedScrollAxes) {

    }
}
