package com.sds.dslibrary.test.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.sds.dslibrary.lib.view.viewpager.adapater.DsInfiniteFragmentPagerAdapter;
import com.sds.dslibrary.test.fragment.InfiniteViewPagerFragment;

/**
 * Created by sds on 2017-07-10.
 */

public class InfiniteFragmentViewPagerAdapter extends DsInfiniteFragmentPagerAdapter {

    public InfiniteFragmentViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItemByPosition(int bindPosition, int pagePosition) {
        return InfiniteViewPagerFragment.newInstance(bindPosition, pagePosition);
    }

    @Override
    public int getBindCount() {
        return 2;
    }
}
