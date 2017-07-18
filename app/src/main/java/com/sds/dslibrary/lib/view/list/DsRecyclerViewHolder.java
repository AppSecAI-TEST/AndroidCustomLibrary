package com.sds.dslibrary.lib.view.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by sds on 2017-07-07.
 */

public class DsRecyclerViewHolder extends RecyclerView.ViewHolder {

    public ItemInfo mItemInfo = null;

    public DsRecyclerViewHolder(View itemView) {
        super(itemView);

        mItemInfo = new ItemInfo();
    }

    public View findViewById(int rid) {
        if (itemView != null) {
            return itemView.findViewById(rid);
        }

        return null;
    }

    public static class ItemInfo {

        public static final int INVALID_POS = -1;

        public int mPosition;
        public int mBindPosition;
        public boolean mIsExpanded;

        public ItemInfo() {
            reset();
        }

        public void reset() {
            mPosition = INVALID_POS;
            mBindPosition = INVALID_POS;
            mIsExpanded = false;
        }
    }

    public interface ItemInfoListener {
        int convertBindPosition(int position);
        boolean isExpandedPosition(int position);
    }
}
