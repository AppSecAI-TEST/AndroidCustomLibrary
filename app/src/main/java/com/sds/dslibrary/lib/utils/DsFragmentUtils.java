package com.sds.dslibrary.lib.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by sds on 2017-07-07.
 */

public class DsFragmentUtils {

    private static final int INVALID_RES_ID = 0;

    public static Fragment add(FragmentActivity activity, int containerViewId, Fragment fragment) {
        return add(activity, containerViewId, fragment, null);
    }

    public static Fragment add(FragmentActivity activity, int containerViewId, Fragment fragment, String name) {
        return add(activity, containerViewId, fragment, name, INVALID_RES_ID, INVALID_RES_ID);
    }

    public static Fragment add(FragmentActivity activity, int containerViewId, Fragment fragment, String name, int animEnter, int animExit) {
        return add(activity.getSupportFragmentManager(), containerViewId, fragment, name, animEnter, animExit);
    }

    public static Fragment add(Fragment parent, int containerViewId, Fragment child) {
        return add(parent, containerViewId, child, null);
    }

    public static Fragment add(Fragment parent, int containerViewId, Fragment child, String name) {
        return add(parent.getChildFragmentManager(), containerViewId, child, name, INVALID_RES_ID, INVALID_RES_ID);
    }

    public static Fragment add(Fragment parent, int containerViewId, Fragment child, String name, int animEnter, int animExit) {
        return add(parent.getChildFragmentManager(), containerViewId, child, name, animEnter, animExit);
    }

    private static Fragment add(FragmentManager fm, int containerViewId, Fragment fragment, String name, int animEnter, int animExit) {
        if (fragment == null) {
            return null;
        }

        FragmentTransaction ft = fm.beginTransaction();

        if (animEnter != INVALID_RES_ID && animExit != INVALID_RES_ID) {
            ft.setCustomAnimations(animEnter, animExit);
        }

        ft.add(containerViewId, fragment);
        ft.addToBackStack(name);
        ft.commit();

        return fragment;
    }

    public static Fragment replace(FragmentActivity activity, int containerViewId, Fragment fragment) {
        return replace(activity, containerViewId, fragment, INVALID_RES_ID, INVALID_RES_ID);
    }

    public static Fragment replace(FragmentActivity activity, int containerViewId, Fragment fragment, int animEnter, int animExit) {
        return replace(activity.getSupportFragmentManager(), containerViewId, fragment, animEnter, animExit);
    }

    public static Fragment replace(Fragment parent, int containerViewId, Fragment child) {
        return replace(parent, containerViewId, child, INVALID_RES_ID, INVALID_RES_ID);
    }

    public static Fragment replace(Fragment parent, int containerViewId, Fragment child, int animEnter, int animExit) {
        return replace(parent.getChildFragmentManager(), containerViewId, child, animEnter, animExit);
    }

    private static Fragment replace(FragmentManager fm, int containerViewId, Fragment fragment, int animEnter, int animExit ) {
        if (fragment == null) {
            return null;
        }

        FragmentTransaction ft = fm.beginTransaction();

        if (animEnter != INVALID_RES_ID && animExit != INVALID_RES_ID) {
            ft.setCustomAnimations(animEnter, animExit);
        }

        ft.replace(containerViewId, fragment);
        ft.commit();

        return fragment;
    }

    public static void remove(FragmentActivity activity, Fragment fragment) {
        remove(activity.getSupportFragmentManager(), fragment);
    }

    public static void remove(Fragment parent, Fragment child) {
        remove(parent.getChildFragmentManager(), child);
    }

    private static void remove(FragmentManager fm, Fragment fragment) {
        if (fragment == null) {
            return;
        }

        FragmentTransaction ft = fm.beginTransaction();

        ft.remove(fragment);
        ft.commit();

        fm.popBackStack();
    }

    private DsFragmentUtils() {

    }
}
