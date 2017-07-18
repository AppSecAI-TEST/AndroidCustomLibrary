package com.sds.dslibrary.lib.view.viewpager.adapater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

/**
 * Created by sds on 2017-07-07.
 */

public abstract class DsInfiniteFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private static final int LOOP_EXCEPTION_COUNT = 1;
    private static final int LOOP_MIN_ITEM_COUNT = 4;
    private static final int MULTI_VALUE = 2;

    private static final int MAX_ITEM_COUNT = 1000;

    public DsInfiniteFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public int getPageCount() {
        int bindCount = getBindCount();

        if (bindCount > LOOP_EXCEPTION_COUNT &&
                bindCount < LOOP_MIN_ITEM_COUNT) {
            return  bindCount * MULTI_VALUE;
        }

        return bindCount;
    }

    public int posToBindPosition(int position) {
        return position % getBindCount();
    }

    public int posToPagePos(int position) {
        return position % getPageCount();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int pagePosition = posToPagePos(position);
        return super.instantiateItem(container, pagePosition);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        int pagePosition = posToPagePos(position);
        super.destroyItem(container, pagePosition, object);
    }

    @Override
    public Fragment getItem(int position) {
        int bindPosition = posToBindPosition(position);
        int pagePosition = posToPagePos(position);
        return getItemByPosition(bindPosition, pagePosition);
    }

    @Override
    public int getCount() {
        int bindCount = getBindCount();

        if (bindCount <= LOOP_EXCEPTION_COUNT) {
            return bindCount;
        }

        return MAX_ITEM_COUNT;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public abstract Fragment getItemByPosition(int bindPosition, int pagePosition);
    public abstract int getBindCount();
}
