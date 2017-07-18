package com.sds.dslibrary.lib.utils.update;

/**
 * Created by sds on 2017-07-07.
 */

public class DsScheduler implements DsUpdateUtils.OnUpdater {

    private boolean mIsOnce;
    private boolean mIsFinish;

    private long mInterval;
    private long mElapsed;

    private OnScheduleListener mListener = null;

    public DsScheduler(long interval, boolean isOnce, OnScheduleListener l) {
        mIsOnce = isOnce;
        mIsFinish = false;
        mInterval = interval;
        mElapsed = 0;
        mListener = l;
    }

    @Override
    public void onUpdate(long dt, DsUpdateUtils.State state) {
        if (mIsFinish) {
            return;
        }

        if (mListener != null) {
            mElapsed += dt;

            if (mElapsed >= mInterval) {
                if (mIsOnce) {
                    mIsFinish = true;
                }

                mListener.onUpdate(mElapsed);

                mElapsed = 0;
            }
        }
    }

    public interface OnScheduleListener {
        void onUpdate(long dt);
    }
}
