package com.sds.dslibrary.test.view.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.sds.dslibrary.R;
import com.sds.dslibrary.lib.view.list.DsRecyclerViewHolder;
import com.sds.dslibrary.lib.view.list.item.DsDataBindRecyclerViewItem;
import com.sds.dslibrary.test.data.DataMainMenu;

/**
 * Created by sds on 2017-07-10.
 */

public class MainMenuItem extends DsDataBindRecyclerViewItem {

    private DataMainMenu.Item mData = null;

    public MainMenuItem(Context context) {
        super(context);
    }

    public MainMenuItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MainMenuItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onBindData(DsRecyclerViewHolder.ItemInfo itemInfo, Object obj) {
        super.onBindData(itemInfo, obj);

        int bindPos = itemInfo.mBindPosition;

        if (DataMainMenu.Item.values().length > bindPos) {
            DataMainMenu.Item menuItem = DataMainMenu.Item.values()[bindPos];
            onSetData(menuItem);
        }
    }

    public void onSetData(DataMainMenu.Item menuItem) {
        mData = menuItem;

        if (menuItem == null) {
            return;
        }

        TextView tvTitle = (TextView) findViewById(R.id.view_text_title);
        tvTitle.setText(menuItem.mTitle);
    }

    public DataMainMenu.Item getData() {
        return mData;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.view_main_menu_item;
    }
}
