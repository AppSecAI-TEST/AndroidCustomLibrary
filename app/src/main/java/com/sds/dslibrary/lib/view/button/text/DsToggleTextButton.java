package com.sds.dslibrary.lib.view.button.text;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.sds.dslibrary.lib.view.button.DsClickDetector;

/**
 * Created by sds on 2017-07-07.
 */

public class DsToggleTextButton extends DsBaseTextButton {

    private boolean mIsOn;

    private int mBgSavedColor;
    private int mBgOffColor;
    private int mBgOnColor;

    private OnClickCallback mOnClickCallback = null;

    public DsToggleTextButton(Context context) {
        super(context);
        init();
    }

    public DsToggleTextButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DsToggleTextButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mIsOn = false;
    }

    public void initWithColor(int bgOffColor, int bgOnColor) {
        mBgOffColor = bgOffColor;
        mBgOnColor = bgOnColor;

        setOnOff(mIsOn);
    }

    public void setOnOff(boolean isOn) {
        mIsOn = isOn;

        onTouchDetectedWithState(DsClickDetector.TouchState.UP, !isDisabled());
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
        int normal = mIsOn ? mBgOnColor : mBgOffColor;
        int press = mIsOn ? mBgOffColor : mBgOnColor;

        if (isEnable) {
            switch (touchState) {
                case PRESS:
                    setBackgroundColorWithSave(press);
                    break;

                case UP:
                    setBackgroundColorWithSave(normal);
                    break;

                default:
                    break;
            }
        } else {
            setBackgroundColorWithSave(normal);
        }
    }

    @Override
    public void onClickViewWithEnable(View v, boolean isEnable) {
        if (isEnable) {
            setOnOff(!mIsOn);
        }

        if (mOnClickCallback != null) {
            mOnClickCallback.onClickView(v, isEnable, mIsOn);
        }
    }

    public interface OnClickCallback {
        void onClickView(View v, boolean isEnable, boolean isOn);
    }
}
