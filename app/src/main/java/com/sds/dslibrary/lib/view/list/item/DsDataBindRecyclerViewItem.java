package com.sds.dslibrary.lib.view.list.item;

import android.content.Context;
import android.util.AttributeSet;

import com.sds.dslibrary.lib.view.DsRelativeLayout;
import com.sds.dslibrary.lib.view.list.DsRecyclerViewHolder;

/**
 * Created by sds on 2017-07-10.
 */

public abstract class DsDataBindRecyclerViewItem extends DsRelativeLayout {

    private DsRecyclerViewHolder.ItemInfo mItemInfo = null;

    public DsDataBindRecyclerViewItem(Context context) {
        super(context);
    }

    public DsDataBindRecyclerViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DsDataBindRecyclerViewItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void onBindData(DsRecyclerViewHolder.ItemInfo itemInfo, Object obj) {
        mItemInfo = itemInfo;
    }

    public DsRecyclerViewHolder.ItemInfo getItemInfo() {
        return mItemInfo;
    }
}
