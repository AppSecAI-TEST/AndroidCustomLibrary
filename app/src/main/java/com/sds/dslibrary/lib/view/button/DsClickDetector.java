package com.sds.dslibrary.lib.view.button;

import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by sds on 2017-07-07.
 */

public class DsClickDetector {

    public enum TouchState {
        PRESS,
        UP,
    }

    private final Rect mTouchRect = new Rect();

    private boolean mKeepInsideTouch;
    private boolean mIsEnable;

    private OnClickDetectListener mOnClickDetectListener = null;

    public DsClickDetector() {
        super();

        mKeepInsideTouch = false;
        mIsEnable = true;
    }

    public boolean onTouchEvent(View v, MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                boolean isValidTouch = isInSideTouch(v, (int) e.getX(), (int) e.getY());

                if (!isValidTouch) {
                    return false;
                }

                mKeepInsideTouch = true;

                if (mOnClickDetectListener != null) {
                    mOnClickDetectListener.onDetect(TouchState.PRESS, mIsEnable);
                }
            }
            break;

            case MotionEvent.ACTION_MOVE: {
                boolean isValidTouch = isInSideTouch(v, (int)e.getX(), (int)e.getY());

                if (!isValidTouch) {
                    if (mKeepInsideTouch) {
                        mKeepInsideTouch = false;
                    }

                    if (mOnClickDetectListener != null) {
                        mOnClickDetectListener.onDetect(TouchState.UP, mIsEnable);
                    }

                    return false;
                }
            }
            break;

            case MotionEvent.ACTION_UP: {
                if (mOnClickDetectListener != null) {
                    mOnClickDetectListener.onDetect(TouchState.UP, mIsEnable);
                }

                if (mKeepInsideTouch) {
                    if (mOnClickDetectListener != null) {
                        mOnClickDetectListener.onClickView(v, mIsEnable);
                    }
                }
            }
            break;

            case MotionEvent.ACTION_CANCEL: {
                mKeepInsideTouch = false;

                if (mOnClickDetectListener != null) {
                    mOnClickDetectListener.onDetect(TouchState.UP, mIsEnable);
                }
            }

            default:
                break;
        }

        return true;
    }

    private boolean isInSideTouch(View v, int posX, int posY) {
        return (getContentRect(v, mTouchRect) && mTouchRect.contains(posX, posY));
    }

    private boolean getContentRect(View v, Rect outRect) {
        if (ViewCompat.isLaidOut(v)) {
            outRect.set(0, 0, v.getWidth(), v.getHeight());
            return true;
        }

        return false;
    }

    public void setOnClickDetectListener(OnClickDetectListener l) {
        mOnClickDetectListener = l;
    }

    public void setDisable(boolean disable) {
        mIsEnable = !disable;

        if (mOnClickDetectListener != null) {
            mOnClickDetectListener.onDetect(TouchState.UP, mIsEnable);
        }
    }

    public boolean isDisabled() {
        return !mIsEnable;
    }

    public interface OnClickDetectListener {
        void onDetect(TouchState touchState, boolean isEnable);
        void onClickView(View v, boolean isEnable);
    }
}
