package com.sds.dslibrary.lib.view.list.adapter;

import android.view.View;

import com.sds.dslibrary.lib.view.list.DsRecyclerViewHolder;

import java.util.List;

/**
 * Created by sds on 2017-07-07.
 */

public abstract class DsOneItemRecyclerViewAdapter<VH extends DsRecyclerViewHolder> extends DsSimpleRecyclerViewAdapter<VH> {

    protected List mItemList = null;

    public DsOneItemRecyclerViewAdapter(List itemList) {
        super();

        mItemList = itemList;
    }

    @Override
    protected int getItemViewTypeWithPosition(int position) {
        return 0;
    }

    @Override
    protected void onPreClickItemView(View v, int position) {

    }

    @Override
    protected void onPostClickItemView(View v, int position) {

    }

    @Override
    public int getItemCount() {
        if (mItemList != null) {
            return mItemList.size();
        }

        return 0;
    }

    @Override
    public int convertBindPosition(int position) {
        return position;
    }

    @Override
    public boolean isExpandedPosition(int position) {
        return false;
    }
}
