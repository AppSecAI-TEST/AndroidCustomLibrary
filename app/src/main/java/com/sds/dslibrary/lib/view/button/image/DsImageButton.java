package com.sds.dslibrary.lib.view.button.image;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.sds.dslibrary.lib.view.button.DsClickDetector;

/**
 * Created by sds on 2017-07-07.
 */

public class DsImageButton extends  DsBaseImageButton {

    private static final int INVALID_RES_ID = 0;

    private int mSavedResId;
    private int mNormalResId;
    private int mPressResId;
    private int mDisableResId;

    private OnClickCallback mOnClickCallback = null;

    public DsImageButton(Context context) {
        super(context);
    }

    public DsImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DsImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initWithImage(int normalResId, int pressResId) {
        this.initWithImage(normalResId, pressResId, normalResId);
    }

    public void initWithImage(int normalResId, int pressResId, int disableResId) {
        mNormalResId = normalResId;
        mPressResId = pressResId;
        mDisableResId = disableResId;

        setImageResourceWithSave(mNormalResId);
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
    public void updateImageWithState(DsClickDetector.TouchState touchState, boolean isEnable) {
        if (isEnable) {
            switch (touchState) {
                case PRESS:
                    setImageResourceWithSave(mPressResId);
                    break;

                case UP:
                    setImageResourceWithSave(mNormalResId);
                    break;

                default:
                    break;
            }
        } else {
            setImageResourceWithSave(mDisableResId);
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
