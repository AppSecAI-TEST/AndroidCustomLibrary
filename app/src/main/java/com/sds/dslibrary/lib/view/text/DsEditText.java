package com.sds.dslibrary.lib.view.text;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * Created by sds on 2017-07-19.
 */

public class DsEditText extends AppCompatEditText {

    private boolean mIsKeyboardOpen;

    public DsEditText(Context context) {
        super(context);

    }

    public DsEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init() {

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();


    }
}
