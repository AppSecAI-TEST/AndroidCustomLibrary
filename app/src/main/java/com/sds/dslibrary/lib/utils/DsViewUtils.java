package com.sds.dslibrary.lib.utils;

import android.view.View;

/**
 * Created by sds on 2017-07-20.
 */

public class DsViewUtils {

    public static void setVisibility(View v, int visibility) {
        if (v == null) {
            return;
        }

        if (v.getVisibility() == visibility) {
            return;
        }

        v.setVisibility(visibility);
    }

    private DsViewUtils() {

    }
}
