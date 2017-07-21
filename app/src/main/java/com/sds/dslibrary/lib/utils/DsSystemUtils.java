package com.sds.dslibrary.lib.utils;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.UUID;

/**
 * Created by sds on 2017-07-07.
 */

public class DsSystemUtils {

    public static void showSoftInputMode(Context c, View v) {
        InputMethodManager imm = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, 0);
    }

    public static void hideSoftInputMode(Context c, View v) {
        InputMethodManager imm = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    /**
     * require permission
     * <uses-permission android:name="android.permission.READ_PHONE_STATE" />
     * @param c context
     * @return fixed uuid ( because device info )
     */
    public static String getUUID(Context c) {
        TelephonyManager tm = (TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);

        String device = "" + tm.getDeviceId();
        String serial = "" + tm.getSimSerialNumber();
        String androidID = "" + Settings.Secure.getString(c.getContentResolver(), Settings.Secure.ANDROID_ID);
        UUID deviceUUID = new UUID(androidID.hashCode(), ((long)device.hashCode() << 32) | serial.hashCode());

        return deviceUUID.toString();
    }

    /**
     * Random UUID
     * @return random uuid
     */
    public static String getRandomUUID() {
        return UUID.randomUUID().toString();
    }
}
