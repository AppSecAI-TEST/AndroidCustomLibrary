package com.sds.dslibrary.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.sds.dslibrary.R;
import com.sds.dslibrary.lib.activity.DsBaseActivity;
import com.sds.dslibrary.lib.view.viewpager.DsInfiniteViewPager;
import com.sds.dslibrary.lib.view.viewpager.indicator.DsViewPagerIndicator;
import com.sds.dslibrary.test.adapter.InfiniteFragmentViewPagerAdapter;

/**
 * Created by sds on 2017-07-10.
 */

public class ViewPagerActivity extends DsBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        initInfiniteViewPager();
    }

    private void initInfiniteViewPager() {
        final InfiniteFragmentViewPagerAdapter adapter = new InfiniteFragmentViewPagerAdapter(getSupportFragmentManager());

        final DsViewPagerIndicator indicator = (DsViewPagerIndicator) findViewById(R.id.view_indicator);
        indicator.init(adapter.getBindCount(), R.drawable.ico_banner_slide_n, R.drawable.ico_banner_slide_p);
        indicator.startWithPosition(0);

        DsInfiniteViewPager viewPager = (DsInfiniteViewPager) findViewById(R.id.view_pager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int pos = adapter.posToBindPosition(position);
                indicator.onSelectPosition(pos);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(adapter);
    }
}
