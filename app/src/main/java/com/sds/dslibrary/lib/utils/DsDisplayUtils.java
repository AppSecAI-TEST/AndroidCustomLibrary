package com.sds.dslibrary.lib.utils;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by sds on 2017-07-07.
 */

public class DsDisplayUtils {

    private static final int DEFAULT_DPI = DisplayMetrics.DENSITY_DEFAULT;
    private static final Point mWinSize = new Point();

    private static int mSupportDPI = DisplayMetrics.DENSITY_DEFAULT;
    private static int mDesignDPI = DisplayMetrics.DENSITY_DEFAULT;

    private static int mDesignWidth;
    private static int mDesignHeight;

    private static DisplayMetrics mSupportMetrics = null;

    public static void init(Context c, int designDPI, int designWidthPx, int designHeightPx) {
        mSupportMetrics = getSupportMetrics(c);

        mSupportDPI = mSupportMetrics.densityDpi;
        mDesignDPI = designDPI;

        mDesignWidth = designWidthPx;
        mDesignHeight = designHeightPx;

        getWindowSize(c, mWinSize);
    }

    // dp or px
    public static int dpToSupportPx(float dp) {
        // return (int)(dp * getSupportDensity + 0.5f);
        return Math.round(dp * getSupportDensity());
    }

    public static int pxToSupportDp(float px) {
        return Math.round(px / getSupportDensity());
    }

    public static int designPxToSupportPx(float designPx) {
        designPx /= getDesignDensity();
        designPx *= getSupportDensity();
        return Math.round(designPx);
    }

    public static int supportPxToDesignPx(float supportPx) {
        supportPx /= getSupportDensity();
        supportPx *= getDesignDensity();
        return Math.round(supportPx);
    }

    public static int designPxToDesignDp(float designPx) {
        return Math.round(designPx / getDesignDensity());
    }

    // scaleFactor
    public static void applyScaleFactorX(View v) {
        applyScaleFactor(v, calcScaleFactorX());
    }

    public static void applyScaleFactorY(View v) {
        applyScaleFactor(v, calcScaleFactorY());
    }

    private static void applyScaleFactor(View v, float scaleFactor) {
        if (v != null) {
            ViewGroup.LayoutParams lp = v.getLayoutParams();
            if (lp != null) {
                lp.width *= scaleFactor;
                lp.height *= scaleFactor;

                v.setLayoutParams(lp);
            }
        }
    }

    public static float calcScaleFactorX() {
        if (mWinSize == null) {
            return 1.0f;
        }

        return calcScaleFactor(pxToSupportDp(mWinSize.x), designPxToDesignDp(mDesignWidth));
    }

    public static float calcScaleFactorY() {
        if (mWinSize == null) {
            return 1.0f;
        }

        return calcScaleFactor(pxToSupportDp(mWinSize.y), designPxToDesignDp(mDesignHeight));
    }

    private static float calcScaleFactor(int supportDp, int designDp) {
        return (float) supportDp / (float) designDp;
    }

    // window size
    public static final Point getWindowSize() {
        return mWinSize;
    }

    public static void getWindowSize(Context c, Point outPoint) {
        WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getSize(outPoint);
    }

    // metrics
    public static final DisplayMetrics getSupportMetrics() {
        return mSupportMetrics;
    }

    public static DisplayMetrics getSupportMetrics(Context c) {
        return getSupportMetrics(c, new DisplayMetrics());
    }

    private static DisplayMetrics getSupportMetrics(Context c, DisplayMetrics outMetrics) {
        WindowManager windowManager = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics;
    }

    // density
    private static float getSupportDensity() {
        return (float) mSupportDPI / (float) DEFAULT_DPI;
    }

    private static float getDesignDensity() {
        return (float) mDesignDPI / (float) DEFAULT_DPI;
    }

    private DsDisplayUtils() {

    }
}
