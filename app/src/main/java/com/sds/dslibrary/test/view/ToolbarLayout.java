package com.sds.dslibrary.test.view;

import android.content.Context;
import android.util.AttributeSet;

import com.sds.dslibrary.R;
import com.sds.dslibrary.lib.view.DsRelativeLayout;

/**
 * Created by sds on 2017-07-10.
 */

public class ToolbarLayout extends DsRelativeLayout {

    public ToolbarLayout(Context context) {
        super(context);
    }

    public ToolbarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.view_tool_bar;
    }
}
