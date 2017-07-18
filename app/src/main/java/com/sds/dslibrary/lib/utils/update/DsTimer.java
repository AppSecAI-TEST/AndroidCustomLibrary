package com.sds.dslibrary.lib.utils.update;

/**
 * Created by sds on 2017-07-07.
 */

public class DsTimer implements DsUpdateUtils.OnUpdater {

    private boolean mIsFinish;
    private long mRemainMillis;

    private OnTimerListener mListener = null;

    public DsTimer(long startMillis, long endMillis, OnTimerListener l) {
        mIsFinish = false;
        mRemainMillis = endMillis - startMillis;
        mListener = l;
    }

    @Override
    public void onUpdate(long dt, DsUpdateUtils.State state) {
        if (mIsFinish) {
            return;
        }

        if (mListener != null) {
            mRemainMillis -= dt;

            switch (state) {
                case RESUME:
                    if (mRemainMillis <= 0) {
                        mIsFinish = true;
                        mListener.onFinish();
                    } else {
                        mListener.onUpdate(mRemainMillis);
                    }
                    break;
            }
        }
    }

    public interface OnTimerListener {
        void onUpdate(long remainMillis);
        void onFinish();
    }
}
