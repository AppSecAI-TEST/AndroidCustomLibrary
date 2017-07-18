package com.sds.dslibrary.lib.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * Created by sds on 2017-07-07.
 */

public class DsResourceUtils {

    private static final String DEF_TYPE_DRAWABLE = "drawable";
    private static final String DEF_TYPE_STRING = "string";

    // color
    public static int getColor(Context c,int resId) {
        return ContextCompat.getColor(c, resId);
    }

    // drawable
    public static Drawable getDrawable(Context c, int resId) {
        return ContextCompat.getDrawable(c, resId);
    }

    public static int getDrawableId(Context c, String name) {
        return getResId(c, DEF_TYPE_DRAWABLE, name);
    }

    // text
    public static String getText(Context c, int resId) {
        return (String) c.getResources().getText(resId);
    }

    public static int getTextId(Context c, String name) {
        return getResId(c, DEF_TYPE_STRING, name);
    }

    // dimension
    public static float getDimension(Context c, int resId) {
        return c.getResources().getDimension(resId);
    }

    // common
    public static int getResId(Context c, String defType, String resName) {
        return c.getResources().getIdentifier(resName, defType, c.getPackageName());
    }

    private DsResourceUtils() {

    }
}
