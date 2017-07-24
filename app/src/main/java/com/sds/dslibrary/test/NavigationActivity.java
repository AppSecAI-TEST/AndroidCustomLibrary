package com.sds.dslibrary.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;

import com.sds.dslibrary.R;
import com.sds.dslibrary.lib.activity.DsBaseActivity;

/**
 * Created by sds on 2017-07-24.
 */

public class NavigationActivity extends DsBaseActivity {

    private DrawerLayout mLayoutDrawer = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        mLayoutDrawer = (DrawerLayout) findViewById(R.id.layout_drawer);

        init();
    }

    private void init() {
        Button btnOpen = (Button) findViewById(R.id.btn_open);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLayoutDrawer != null) {
                    if (mLayoutDrawer.isDrawerOpen(GravityCompat.END)) {
                        mLayoutDrawer.closeDrawers();
                    } else {
                        mLayoutDrawer.openDrawer(GravityCompat.END);
                    }
                }
            }
        });
    }
}
