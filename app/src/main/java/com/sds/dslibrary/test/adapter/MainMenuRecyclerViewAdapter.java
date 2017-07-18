package com.sds.dslibrary.test.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.sds.dslibrary.lib.view.list.DsRecyclerViewHolder;
import com.sds.dslibrary.lib.view.list.adapter.DsOneItemRecyclerViewAdapter;
import com.sds.dslibrary.lib.view.list.item.DsDataBindRecyclerViewItem;
import com.sds.dslibrary.test.view.item.MainMenuItem;

import java.util.List;

/**
 * Created by sds on 2017-07-10.
 */

public class MainMenuRecyclerViewAdapter extends DsOneItemRecyclerViewAdapter<DsRecyclerViewHolder> {

    public MainMenuRecyclerViewAdapter(List itemList) {
        super(itemList);
    }

    @Override
    protected void bindViewHolderWithPosition(DsRecyclerViewHolder holder, int position) {
        if (holder.itemView instanceof DsDataBindRecyclerViewItem) {
            ((DsDataBindRecyclerViewItem) holder.itemView).onBindData(holder.mItemInfo, null);
        }
    }

    @Override
    public DsRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = new MainMenuItem(parent.getContext());
        return new DsRecyclerViewHolder(v);
    }
}
