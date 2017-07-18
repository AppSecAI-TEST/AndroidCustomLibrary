package com.sds.dslibrary.test;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;

import com.sds.dslibrary.R;
import com.sds.dslibrary.lib.activity.DsBaseActivity;
import com.sds.dslibrary.lib.utils.DsDisplayUtils;
import com.sds.dslibrary.lib.utils.update.DsScheduler;
import com.sds.dslibrary.lib.utils.update.DsUpdateUtils;
import com.sds.dslibrary.lib.view.DsRelativeLayout;
import com.sds.dslibrary.lib.view.button.image.DsToggleImageButton;
import com.sds.dslibrary.lib.view.video.DsSimpleMediaController;
import com.sds.dslibrary.lib.view.video.DsVideoView;

/**
 * Created by sds on 2017-07-10.
 */

public class VideoActivity extends DsBaseActivity {

    private static final float DP_VIDEO_VIEW_HEIGHT_IN_PORTRAIT = 220.33f;

    private static final String URL_VIDEO = "http://livestream.nsmall.com/IPHONE/nsmallMobile.m3u8";

    private final Point mWinSize = new Point();

    private DsVideoView mVideoView = null;
    private CustomMediaController mMediaController = null;

    private OrientationEventListener mOrientationListener = null;

    private int mSavedOrientation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        initVideoView();
        initOrientationListener();

        updateWithOrientation(getResources().getConfiguration().orientation);

        addVideoChecker();
    }

    private void initVideoView() {
        mMediaController = new CustomMediaController(this);

        mVideoView = (DsVideoView) findViewById(R.id.view_video);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        mVideoView.setMediaController(mMediaController);
    }

    private void initOrientationListener() {
        mOrientationListener = new OrientationEventListener(this) {
            @Override
            public void onOrientationChanged(int orientation) {
                final int ORIENTATION_PORTRAIT_FROM = 0;
                final int ORIENTATION_PORTRAIT_TO = 7;

                final int ORIENTATION_LAND_SCAPE_LEFT_TOP_FROM = 80;
                final int ORIENTATION_LAND_SCAPE_LEFT_TOP_TO = 90;

                final int ORIENTATION_LAND_SCAPE_RIGHT_TOP_FROM = 270;
                final int ORIENTATION_LAND_SCAPE_RIGHT_TOP_TO = 280;

                if (orientation >= ORIENTATION_PORTRAIT_FROM && orientation <= ORIENTATION_PORTRAIT_TO) {
                    // portrait
                    setForceRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
                } else if (orientation >= ORIENTATION_LAND_SCAPE_LEFT_TOP_FROM && orientation <= ORIENTATION_LAND_SCAPE_LEFT_TOP_TO) {
                    // landscape
                    setForceRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
                } else if (orientation >= ORIENTATION_LAND_SCAPE_RIGHT_TOP_FROM && orientation <= ORIENTATION_LAND_SCAPE_RIGHT_TOP_TO) {
                    // landscape
                    setForceRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
                }
            }
        };

        if (mOrientationListener.canDetectOrientation()) {
            mOrientationListener.enable();
        } else {
            mOrientationListener.disable();
        }
    }

    private void addVideoChecker() {
        DsUpdateUtils updateUtils = DsUpdateUtils.getCurrentUtils();

        if (updateUtils != null) {
            updateUtils.addScheduler(1000, false, new DsScheduler.OnScheduleListener() {
                @Override
                public void onUpdate(long dt) {
                    if (mVideoView != null) {
                        if (!mVideoView.isPlaying()) {
                            playVideo(URL_VIDEO);
                        }
                    }
                }
            });
        }
    }

    private void playVideo(String url) {
        mVideoView.setVideoURI(Uri.parse(url));
    }

    private void updateWithOrientation(int orientation) {
        updateVideoViewWithOrientation(orientation);
    }

    private void updateVideoViewWithOrientation(int orientation) {
        if (mVideoView != null && mMediaController.getCustomMediaControllerView() != null) {
            View customControllerView = mMediaController.getCustomMediaControllerView();

            ViewGroup.LayoutParams lpVideo = mVideoView.getLayoutParams();
            ViewGroup.LayoutParams lpController = customControllerView.getLayoutParams();

            if (lpVideo != null && lpController != null) {
                DsDisplayUtils.getWindowSize(this, mWinSize);

                if (orientation == ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT) {
                    lpVideo.height = DsDisplayUtils.dpToSupportPx(DP_VIDEO_VIEW_HEIGHT_IN_PORTRAIT);
                    lpController.height = lpVideo.height;
                } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE) {
                    lpVideo.height = mWinSize.y;
                    lpController.height = lpVideo.height;
                }
            }

            if (customControllerView instanceof CustomMediaControllerView) {
                ((CustomMediaControllerView) customControllerView).updateButtonWithOrientation(orientation);
            }
        }
    }

    private void setForceRequestedOrientation(int requestedOrientation) {
        // TODO sds : add login for event that from button or sensor
        if (mSavedOrientation == requestedOrientation) {
            return;
        }

        mSavedOrientation = requestedOrientation;

        setRequestedOrientation(requestedOrientation);
        updateVideoViewWithOrientation(requestedOrientation);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        //updateWithOrientation(newConfig.orientation);
    }

    class CustomMediaController extends DsSimpleMediaController {

        private View mCustomMediaControllerView = null;

        public CustomMediaController(Context context) {
            super(context);
        }

        public CustomMediaController(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public View getCustomMediaControllerView() {
            return mCustomMediaControllerView;
        }

        @Override
        public View makeControllerView(ViewGroup parent) {
            mCustomMediaControllerView = new CustomMediaControllerView(parent.getContext());
            return mCustomMediaControllerView;
        }

        @Override
        public ViewGroup.LayoutParams makeLayoutParams() {
            ViewGroup.LayoutParams lpVideo = mVideoView.getLayoutParams();

            if (lpVideo != null) {
                return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        lpVideo.height);
            }

            return null;
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (isShowing()) {
                        hide();
                    } else {
                        show(0);
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                    hide();
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    class CustomMediaControllerView extends DsRelativeLayout implements DsToggleImageButton.OnClickCallback {

        private final int BUTTON_RES_ID[] = {
                R.id.btn_full_screen,
        };

        public CustomMediaControllerView(Context context) {
            super(context);
            init();
        }

        public CustomMediaControllerView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        private void init() {
            final int btnOff[] = {
                    R.drawable.btn_full_screen,
            };

            final int btnOn[] = {
                    R.drawable.btn_origin_screen,
            };

            for (int i = 0; i < BUTTON_RES_ID.length; ++i) {
                DsToggleImageButton btn = (DsToggleImageButton) findViewById(BUTTON_RES_ID[i]);
                btn.initWithImage(btnOff[i], btnOn[i]);
                btn.setOnClickCallback(this);
            }
        }

        public void updateButtonWithOrientation(int orientation) {
            boolean isOn = orientation == Configuration.ORIENTATION_LANDSCAPE;

            DsToggleImageButton btn = (DsToggleImageButton) findViewById(R.id.btn_full_screen);
            btn.setOnOff(isOn);
        }

        @Override
        public void onClickView(View v, boolean isEnable, boolean isOn) {
            switch (v.getId()) {
                case R.id.btn_full_screen:
                    if (isEnable) {
                        if (isOn) {
                            setForceRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
                        } else {
                            setForceRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
                        }
                    }
                    break;

                default:
                    break;
            }
        }

        @Override
        public int getLayoutResId() {
            return R.layout.view_custom_media_controller;
        }
    }
}
