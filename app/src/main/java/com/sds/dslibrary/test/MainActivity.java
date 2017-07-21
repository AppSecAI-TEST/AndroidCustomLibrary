package com.sds.dslibrary.test;

import android.os.Bundle;
import android.os.Process;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.View;

import com.sds.dslibrary.R;
import com.sds.dslibrary.lib.activity.DsBaseActivity;
import com.sds.dslibrary.lib.utils.DsDisplayUtils;
import com.sds.dslibrary.lib.utils.update.DsUpdateLooper;
import com.sds.dslibrary.lib.view.list.DsRecyclerView;
import com.sds.dslibrary.lib.view.list.adapter.DsSimpleRecyclerViewAdapter;
import com.sds.dslibrary.lib.view.list.deco.DsAlbumColumnItemDecoration;
import com.sds.dslibrary.test.adapter.MainMenuRecyclerViewAdapter;
import com.sds.dslibrary.test.data.DataMainMenu;
import com.sds.dslibrary.test.view.item.MainMenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends DsBaseActivity {

    private final List<DataMainMenu.Item> mMenuList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        initMenuList();
        initRecyclerView();
    }

    private void init() {
        DsDisplayUtils.init(this, DisplayMetrics.DENSITY_XXHIGH, 1080, 1920);
        DsUpdateLooper.getInstance().start();
    }

    private void initMenuList() {
        mMenuList.clear();

        for (DataMainMenu.Item item : DataMainMenu.Item.values()) {
            mMenuList.add(item);
        }
    }

    private void initRecyclerView() {
        final int COLUMN = 1;
        final int SPACING_X = 0;
        final int SPACING_Y = DsDisplayUtils.dpToSupportPx(7.0f);

        MainMenuRecyclerViewAdapter adapter = new MainMenuRecyclerViewAdapter(mMenuList);
        adapter.setOnClickItemListener(new DsSimpleRecyclerViewAdapter.OnClickItemListener() {
            @Override
            public void onClickItem(View v, int position) {
                onClickMenuItem(v, position);
            }
        });

        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        lm.setItemPrefetchEnabled(true);

        DsRecyclerView recyclerView = (DsRecyclerView) findViewById(R.id.view_recycler);
        recyclerView.setLayoutManager(lm);
        recyclerView.addItemDecoration(new DsAlbumColumnItemDecoration(COLUMN, SPACING_X, SPACING_Y));
        recyclerView.setAdapter(adapter);
    }

    private void onClickMenuItem(View v, int position) {
        if (v instanceof MainMenuItem) {
            DataMainMenu.Item item = ((MainMenuItem) v).getData();

            switch (item) {
                case DEVICE_INFO:
                    startActivity(DeviceInfoActivity.class, true);
                    break;

                case INFINITE_VIEWPAGER:
                    startActivity(ViewPagerActivity.class, true);
                    break;

                case COORDINATOR:
                    startActivity(CoordinatorActivity.class, true);
                    break;

                case TIME_SCHEDULE:
                    startActivity(TimeScheduleActivity.class, true);
                    break;

                case VIDEO:
                    startActivity(VideoActivity.class, true);
                    break;

                case DATABASE:
                    startActivity(DatabaseActivity.class, true);
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (mLayoutPopupContainer != null) {
            int childCount = mLayoutPopupContainer.getChildCount();

            if (childCount > 0) {
                mLayoutPopupContainer.removeViewAt(childCount - 1);
            } else {
                onBackPressedKillProcess();
            }
        } else {
            onBackPressedKillProcess();
        }
    }

    private void onBackPressedKillProcess() {
        finish();
        Process.killProcess(Process.myPid());
    }
}
