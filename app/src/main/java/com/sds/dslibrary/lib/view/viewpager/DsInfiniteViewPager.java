package com.sds.dslibrary.lib.view.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.sds.dslibrary.lib.view.viewpager.adapater.DsInfiniteFragmentPagerAdapter;

/**
 * Created by sds on 2017-07-07.
 */

public class DsInfiniteViewPager extends ViewPager {

    private int mOffsetAmount;
    private int mMaxItemCount;

    public DsInfiniteViewPager(Context context) {
        super(context);
        init();
    }

    public DsInfiniteViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mOffsetAmount = 0;
        mMaxItemCount = 0;
    }

    public void moveToRightItem(boolean smoothScroll) {
        int position = super.getCurrentItem();
        position = Math.min(position + 1, mMaxItemCount);

        moveToItemByPosition(position, smoothScroll);
    }

    public void moveToLeftItem(boolean smoothScroll) {
        int position = super.getCurrentItem();
        position = Math.max(position - 1, 0);

        moveToItemByPosition(position, smoothScroll);
    }

    private void moveToItemByPosition(int position, boolean smoothScroll) {
        PagerAdapter adapter = getAdapter();

        if (adapter instanceof DsInfiniteFragmentPagerAdapter) {
            position -= mOffsetAmount;
        }

        setCurrentItem(position, smoothScroll);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);

        if (adapter instanceof DsInfiniteFragmentPagerAdapter) {
            DsInfiniteFragmentPagerAdapter infiniteAdapter = (DsInfiniteFragmentPagerAdapter) adapter;

            mMaxItemCount = infiniteAdapter.getCount();

            int pageCount = infiniteAdapter.getPageCount();
            if (pageCount != 0) {
                int offset = (int) (mMaxItemCount / 2.0f);
                int diff = (offset % pageCount);

                mOffsetAmount = Math.max(offset - diff, 0);
            }
        }

        setCurrentItem(0);
    }

    @Override
    public void setCurrentItem(int item) {
        setCurrentItem(item, false);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        PagerAdapter adapter = getAdapter();

        if (adapter instanceof DsInfiniteFragmentPagerAdapter) {
            DsInfiniteFragmentPagerAdapter infiniteAdapter = (DsInfiniteFragmentPagerAdapter) adapter;

            int count = infiniteAdapter.getCount();

            if (count != 0) {
                item = mOffsetAmount + (item % count);
            }
        }

        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public int getCurrentItem() {
        PagerAdapter adapter = getAdapter();

        if (adapter instanceof DsInfiniteFragmentPagerAdapter) {
            DsInfiniteFragmentPagerAdapter infiniteAdapter = (DsInfiniteFragmentPagerAdapter) adapter;

            int position = super.getCurrentItem();
            int pageCount = infiniteAdapter.getPageCount();

            if (pageCount != 0) {
                return infiniteAdapter.posToPagePos(position);
            }
        }

        return super.getCurrentItem();
    }
}
