package com.sds.dslibrary.lib.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.sds.dslibrary.lib.popup.DsPopup;
import com.sds.dslibrary.lib.utils.DsResourceUtils;
import com.sds.dslibrary.lib.utils.update.DsUpdateLooper;
import com.sds.dslibrary.lib.utils.update.DsUpdateUtils;

/**
 * Created by sds on 2017-07-07.
 */

public class DsBaseActivity extends AppCompatActivity {

    private final DsUpdateUtils mUpdateUtils = new DsUpdateUtils();

    protected ViewGroup mLayoutPopupContainer = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DsUpdateLooper.getInstance().add(mUpdateUtils);
    }

    public void addPopup(DsPopup popup) {
        if (mLayoutPopupContainer != null) {
            mLayoutPopupContainer.bringToFront();
            mLayoutPopupContainer.addView(popup);
        }
    }

    public void removePopup(DsPopup popup) {
        if (mLayoutPopupContainer != null) {
            mLayoutPopupContainer.removeView(popup);
        }
    }

    private void addPopupContainer() {
        if (mLayoutPopupContainer == null) {
            mLayoutPopupContainer = new FrameLayout(this);
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            addContentView(mLayoutPopupContainer, lp);
        }
    }

    public void startActivity(Class<?> cls, boolean isAddToBackStack) {
        startActivity(new Intent(this, cls));

        if (!isAddToBackStack) {
            finish();
        }
    }

    public void showToast(int resId) {
        showToast(DsResourceUtils.getText(this, resId));
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        addPopupContainer();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);

        addPopupContainer();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);

        addPopupContainer();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mUpdateUtils.onResume();
    }

    @Override
    protected void onPause() {
        mUpdateUtils.onPause();

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mUpdateUtils.onDestroy();

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (mLayoutPopupContainer != null) {
            int childCount = mLayoutPopupContainer.getChildCount();

            if (childCount > 0) {
                mLayoutPopupContainer.removeViewAt(childCount - 1);
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }
}
