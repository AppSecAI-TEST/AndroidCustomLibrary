package com.sds.dslibrary.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.sds.dslibrary.R;
import com.sds.dslibrary.lib.activity.DsBaseActivity;
import com.sds.dslibrary.lib.utils.update.DsScheduler;
import com.sds.dslibrary.lib.utils.update.DsTimer;
import com.sds.dslibrary.lib.utils.update.DsUpdateUtils;

/**
 * Created by sds on 2017-07-10.
 */

public class TimeScheduleActivity extends DsBaseActivity {

    private long mCurrentTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_schedule);

        mCurrentTime = 0;

        initCurrentTime();
        initTimer();
    }

    private void initCurrentTime() {
        final long INTERVAL = 250;

        DsUpdateUtils updateUtils = DsUpdateUtils.getCurrentUtils();
        updateUtils.addScheduler(INTERVAL, false, new DsScheduler.OnScheduleListener() {
            @Override
            public void onUpdate(long dt) {
                long now = System.currentTimeMillis();

                if (mCurrentTime == 0) {
                    mCurrentTime = now;
                } else {
                    mCurrentTime += dt;
                }

                String strCurrent = String.valueOf(mCurrentTime);
                String strNow = String.valueOf(now);

                TextView tv = (TextView) findViewById(R.id.view_text_current_time);
                tv.setText(strCurrent + " / " + strNow + "\n diff : " + (now - mCurrentTime));
            }
        });
    }

    private void initTimer() {
        final long DURATION = 10000;

        long currentTime = System.currentTimeMillis();

        DsUpdateUtils updateUtils = DsUpdateUtils.getCurrentUtils();
        updateUtils.addTimer(currentTime, currentTime + DURATION, new DsTimer.OnTimerListener() {
            @Override
            public void onUpdate(long remainMillis) {
                TextView tv = (TextView) findViewById(R.id.view_text_timer);
                tv.setText(String.valueOf(remainMillis / 1000));
            }

            @Override
            public void onFinish() {
                TextView tv = (TextView) findViewById(R.id.view_text_timer);
                tv.setText("Finish");
            }
        });
    }
}
