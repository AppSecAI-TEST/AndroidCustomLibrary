package com.sds.dslibrary.lib.utils.update;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sds on 2017-07-07.
 */

public class DsUpdateUtils implements DsUpdateLooper.LoopUpdater {

    public enum State {
        RESUME,
        PAUSE,
    }

    private static DsUpdateUtils mCurrUtils = null;

    public static DsUpdateUtils getCurrentUtils() {
        return mCurrUtils;
    }

    private final List<OnUpdater> mList = new ArrayList<>();

    private State mState;

    public DsUpdateUtils() {
        onResume();

        DsUpdateLooper.getInstance().add(this);
    }

    public void addTimer(long startMillis, long endMillis, DsTimer.OnTimerListener l) {
        synchronized (mList) {
            DsTimer timer = new DsTimer(startMillis, endMillis, l);
            mList.add(timer);
        }
    }

    public void addScheduler(long interval, boolean isOnce, DsScheduler.OnScheduleListener l) {
        synchronized (mList) {
            DsScheduler scheduler = new DsScheduler(interval, isOnce, l);
            mList.add(scheduler);
        }
    }

    public void remove(OnUpdater updater) {
        synchronized (mList) {
            mList.remove(updater);
        }
    }

    public void onResume() {
        mCurrUtils = this;

        mState = State.RESUME;
    }

    public void onPause() {
        mCurrUtils = null;

        mState = State.PAUSE;
    }

    public void onDestroy() {
        synchronized (mList) {
            mList.clear();
        }

        DsUpdateLooper.getInstance().remove(this);
    }

    @Override
    public void onUpdate(long dt) {
        synchronized (mList) {
            Iterator<OnUpdater> it = mList.iterator();

            while (it.hasNext()) {
                it.next().onUpdate(dt, mState);
            }
        }
    }

    public interface OnUpdater {
        void onUpdate(long dt, State state);
    }
}
