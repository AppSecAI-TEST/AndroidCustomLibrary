package com.sds.dslibrary.lib.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sds.dslibrary.lib.activity.DsBaseActivity;

/**
 * Created by sds on 2017-07-07.
 */

public abstract class DsFragment extends Fragment {

    private View mRootView = null;

    public DsFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutResId(), container, false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        onActivityCreated();
    }

    public View findViewById(int id) {
        if (mRootView != null) {
            return mRootView.findViewById(id);
        }

        return null;
    }

    public void addPopup(View v) {
        FragmentActivity activity = getActivity();
        if (activity instanceof DsBaseActivity) {
            ((DsBaseActivity) activity).addPopup(v);
        }
    }

    public void removePopup(View v) {
        FragmentActivity activity = getActivity();
        if (activity instanceof DsBaseActivity) {
            ((DsBaseActivity) activity).removePopup(v);
        }
    }

    public void startActivity(Class<?> cls, boolean isAddToBackStack) {
        FragmentActivity activity = getActivity();
        if (activity instanceof DsBaseActivity) {
            ((DsBaseActivity) activity).startActivity(cls, isAddToBackStack);
        }
    }

    public void showToast(int resId) {
        FragmentActivity activity = getActivity();
        if (activity instanceof DsBaseActivity) {
            ((DsBaseActivity) activity).showToast(resId);
        }
    }

    public void showToast(String msg) {
        FragmentActivity activity = getActivity();
        if (activity instanceof DsBaseActivity) {
            ((DsBaseActivity) activity).showToast(msg);
        }
    }

    public View getRootView() {
        return mRootView;
    }

    public abstract void onActivityCreated();
    public abstract int getLayoutResId();
}
