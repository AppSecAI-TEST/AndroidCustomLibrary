package com.sds.dslibrary.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.sds.dslibrary.R;
import com.sds.dslibrary.lib.activity.DsBaseActivity;
import com.sds.dslibrary.lib.utils.DsSystemUtils;

/**
 * Created by sds on 2017-07-20.
 */

public class DeviceInfoActivity extends DsBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);

        initUUID();
    }

    private void initUUID() {
        String uuid = DsSystemUtils.getUUID(this);

        TextView tvFixedUUID = (TextView) findViewById(R.id.view_text_fixed_uuid);
        tvFixedUUID.setText("fix : " + uuid);

        TextView tvRandomUUID = (TextView) findViewById(R.id.view_text_random_uuid);
        tvRandomUUID.setText("ran : " + DsSystemUtils.getRandomUUID());
    }
}
