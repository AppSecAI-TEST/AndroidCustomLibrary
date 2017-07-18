package com.sds.dslibrary.test;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sds.dslibrary.R;
import com.sds.dslibrary.lib.activity.DsBaseActivity;

/**
 * Created by sds on 2017-07-10.
 */

public class CoordinatorActivity extends DsBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
    }
}
