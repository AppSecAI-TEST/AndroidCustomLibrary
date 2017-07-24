package com.sds.dslibrary.lib.view.button.image;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.sds.dslibrary.lib.view.button.DsClickDetector;

/**
 * Created by sds on 2017-07-07.
 */

public class DsToggleImageButton extends DsBaseImageButton {

    private static final int INVALID_RES_ID = 0;

    private boolean mIsOn;

    private int mSavedResId;
    private int mOffResId;
    private int mOnResId;

    private OnClickCallback mOnClickCallback = null;

    public DsToggleImageButton(Context context) {
        super(context);
        init();
    }

    public DsToggleImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DsToggleImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mIsOn = false;
    }

    public void initWithImage(int offResId, int onResId) {
        mOffResId = offResId;
        mOnResId = onResId;

        setOnOff(mIsOn);
    }

    public void setOnOff(boolean isOn) {
        mIsOn = isOn;

        onTouchDetectedWithState(DsClickDetector.TouchState.UP, !isDisabled());
    }

    private void setImageResourceWithSave(int resId) {
        if (resId == INVALID_RES_ID) {
            return;
        }

        if (mSavedResId != resId) {
            mSavedResId = resId;

            setImageResource(resId);
        }
    }

    public void setOnClickCallback(OnClickCallback l) {
        mOnClickCallback = l;
    }

    @Override
    public void onTouchDetectedWithState(DsClickDetector.TouchState touchState, boolean isEnable) {
        int normal = mIsOn ? mOnResId : mOffResId;
        int press = mIsOn ? mOffResId : mOnResId;

        if (isEnable) {
            switch (touchState) {
                case PRESS:
                    setImageResourceWithSave(press);
                    break;

                case UP:
                    setImageResourceWithSave(normal);
                    break;

                default:
                    break;
            }
        } else {
            setImageResourceWithSave(normal);
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
