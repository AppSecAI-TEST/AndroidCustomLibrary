package com.sds.dslibrary.lib.utils.update;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sds on 2017-07-07.
 */

public class DsUpdateLooper {

    private static final int MSG_UPDATE = 1;
    private static final long INTERVAL = 250;

    private static DsUpdateLooper mInstance = null;

    public static DsUpdateLooper getInstance() {
        if (mInstance == null) {
            synchronized (DsUpdateLooper.class) {
                if (mInstance == null) {
                    mInstance = new DsUpdateLooper();
                }
            }
        }

        return mInstance;
    }

    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            onHandleMessage(msg);
        }
    };

    private final List<LoopUpdater> mList = new ArrayList<>();

    private long mLastUpdateTime;

    private DsUpdateLooper() {

    }

    public void start() {
        removeMessage();

        mLastUpdateTime = SystemClock.elapsedRealtime();

        sendMessage();
    }

    public void add(LoopUpdater l) {
        synchronized (mList) {
            if (!mList.contains(l)) {
                mList.add(l);
            }
        }
    }

    public void remove(LoopUpdater l) {
        synchronized (mList) {
            mList.remove(l);
        }
    }

    private void onHandleMessage(Message msg) {
        if (msg.what == MSG_UPDATE) {
            synchronized (mList) {
                long nowTime = SystemClock.elapsedRealtime();
                long dt = nowTime - mLastUpdateTime;

                mLastUpdateTime = nowTime;

                Iterator<LoopUpdater> it = mList.iterator();

                while (it.hasNext()) {
                    it.next().onUpdate(dt);
                }

                sendMessage();
            }
        }
    }

    private void sendMessage() {
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_UPDATE), INTERVAL);
    }

    private void removeMessage() {
        mHandler.removeMessages(MSG_UPDATE);
    }

    public interface LoopUpdater {
        void onUpdate(long dt);
    }
}
