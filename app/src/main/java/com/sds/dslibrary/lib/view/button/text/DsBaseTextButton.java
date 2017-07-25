package com.sds.dslibrary.lib.view.button.text;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.sds.dslibrary.lib.view.button.DsClickDetector;

/**
 * Created by sds on 2017-07-07.
 */

public abstract class DsBaseTextButton extends AppCompatTextView implements DsClickDetector.OnClickDetectListener {

    private final DsClickDetector mClickDetector = new DsClickDetector();

    public DsBaseTextButton(Context context) {
        super(context);
        init();
    }

    public DsBaseTextButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DsBaseTextButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mClickDetector.setOnClickDetectListener(this);

        setPadding(0, 0, 0, 0);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchEvent(v, event);
            }
        });
    }

    private boolean onTouchEvent(View v, MotionEvent e) {
        if (!mClickDetector.onTouchEvent(v, e)) {
            return false;
        }

        return true; //super.onTouchEvent(e);
    }

    @Override
    public void onDetect(DsClickDetector.TouchState touchState, boolean isEnable) {
        onTouchDetectedWithState(touchState, isEnable);
    }

    @Override
    public void onClickView(View v, boolean isEnable) {
        onClickViewWithEnable(v, isEnable);
    }

    public void setDisabled(boolean disabled) {
        mClickDetector.setDisable(disabled);
    }

    public boolean isDisabled() {
        return mClickDetector.isDisabled();
    }

    public abstract void onTouchDetectedWithState(DsClickDetector.TouchState touchState, boolean isEnable);
    public abstract void onClickViewWithEnable(View v, boolean isEnable);
}
