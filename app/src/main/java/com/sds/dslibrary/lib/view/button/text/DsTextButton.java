package com.sds.dslibrary.lib.view.button.text;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.sds.dslibrary.lib.view.button.DsClickDetector;

/**
 * Created by sds on 2017-07-07.
 */

public class DsTextButton extends DsBaseTextButton {

    private int mBgSavedColor;
    private int mBgNormalColor;
    private int mBgPressColor;
    private int mBgDisableColor;

    private OnClickCallback mOnClickCallback = null;

    public DsTextButton(Context context) {
        super(context);
    }

    public DsTextButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DsTextButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initWithColor(int bgNormalColor, int bgPressColor) {
        this.initWithColor(bgNormalColor, bgPressColor, bgNormalColor);
    }

    public void initWithColor(int bgNormalColor, int bgPressColor, int bgDisableColor) {
        mBgNormalColor = bgNormalColor;
        mBgPressColor = bgPressColor;
        mBgDisableColor = bgDisableColor;

        setBackgroundColorWithSave(mBgNormalColor);
    }

    private void setBackgroundColorWithSave(int color) {
        if (mBgSavedColor != color) {
            mBgSavedColor = color;

            setBackgroundColor(color);
        }
    }

    public void setOnClickCallback(OnClickCallback l) {
        mOnClickCallback = l;
    }

    @Override
    public void onTouchDetectedWithState(DsClickDetector.TouchState touchState, boolean isEnable) {
        if (isEnable) {
            switch (touchState) {
                case PRESS:
                    setBackgroundColorWithSave(mBgPressColor);
                    break;

                case UP:
                    setBackgroundColorWithSave(mBgNormalColor);
                    break;

                default:
                    break;
            }
        } else {
            setBackgroundColorWithSave(mBgDisableColor);
        }
    }

    @Override
    public void onClickViewWithEnable(View v, boolean isEnable) {
        if (mOnClickCallback != null) {
            mOnClickCallback.onClickView(v, isEnable);
        }
    }

    public interface OnClickCallback {
        void onClickView(View v, boolean isEnable);
    }
}
