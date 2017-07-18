package com.sds.dslibrary.test.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.sds.dslibrary.R;
import com.sds.dslibrary.lib.fragment.DsFragment;

/**
 * Created by sds on 2017-07-10.
 */

public class InfiniteViewPagerFragment extends DsFragment {

    public static InfiniteViewPagerFragment newInstance(int bindPos, int pagePos) {
        Bundle bundle = new Bundle();
        bundle.putInt("bindPos", bindPos);
        bundle.putInt("pagePos", pagePos);

        InfiniteViewPagerFragment fragment = new InfiniteViewPagerFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    public InfiniteViewPagerFragment() {
        super();
    }

    @Override
    public void onActivityCreated() {
        Bundle bundle = getArguments();

        int bindPos = bundle.getInt("bindPos");
        int pagePos = bundle.getInt("pagePos");

        TextView tvBind = (TextView) findViewById(R.id.view_text_bind);
        tvBind.setText("BindPos : " + String.valueOf(bindPos));

        TextView tvPage = (TextView) findViewById(R.id.view_text_page);
        tvPage.setText("PagePos : " + String.valueOf(pagePos));
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_infinite_view_pager;
    }
}
