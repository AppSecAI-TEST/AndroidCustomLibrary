package com.sds.dslibrary.lib.view.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sds.dslibrary.lib.view.list.DsRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sds on 2017-07-07.
 */

/**
 * ***************************
 * ******** Important ********
 * ***************************
 * parent list need to sorted
 * child list need to sorted
 * ***************************
 *
 * @param <VH>  ViewHolder
 * @param <T>   Template
 * @param <P>   Parent
 * @param <C>   Child
 */
public abstract class DsExpandableRecyclerViewAdapter<VH extends DsRecyclerViewHolder, T, P extends T, C extends T> extends DsSimpleRecyclerViewAdapter<VH> {

    public static final int VIEW_TYPE_PARENT = 1;
    public static final int VIEW_TYPE_CHILD = 2;

    protected List<T> mItemList = null;
    protected List<P> mParentList = null;
    protected List<C> mChildList = null;

    public DsExpandableRecyclerViewAdapter(List<P> parentList, List<C> childList) {
        super();

        mItemList = new ArrayList(parentList);

        mParentList = parentList;
        mChildList = childList;
    }

    @Override
    protected void onPreClickItemView(View v, int position) {
        executeExpandOrCollapse(v, position);
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
        int viewType = getItemViewTypeWithPosition(position);

        T item = mItemList.get(position);

        switch (viewType) {
            case VIEW_TYPE_PARENT:
                return mParentList.indexOf(item);

            case VIEW_TYPE_CHILD:
                return mChildList.indexOf(item);

            default:
                break;
        }

        return -1;
    }

    @Override
    public boolean isExpandedPosition(int position) {
        int viewType = getItemViewTypeWithPosition(position);

        if (viewType != VIEW_TYPE_PARENT) {
            return false;
        }

        int checkPosition = position + 1;

        if (checkPosition < getItemCount()) {
            T item = mItemList.get(checkPosition);
            return isChildItem(item);
        }

        return false;
    }

    public void executeExpandOrCollapse(RecyclerView.LayoutManager lm, int position) {
        executeExpandOrCollapse(lm.getChildAt(position), position);
    }

    public void executeExpandOrCollapse(View v, int position) {
        int viewType = getItemViewTypeWithPosition(position);

        switch (viewType) {
            case VIEW_TYPE_PARENT:
                if (isExpandedPosition(position)) {
                    if (removeChildFromItemList(mItemList, mChildList, position) > 0) {
                        onCollapse(v, position);
                    }
                } else {
                    if (addChildToItemList(mItemList, mChildList, position) > 0) {
                        onExpand(v, position);
                    }
                }
                break;

            case VIEW_TYPE_CHILD:
                break;

            default:
                break;
        }
    }

    private int addChildToItemList(List<T> itemList, List<C> childList, int position) {
        P parentItem = (P) itemList.get(position);

        int insertPosition = position + 1;
        int insertCount = 0;

        for (C childItem : childList) {
            if (isChildItemMatchToParent(parentItem, childItem)) {
                itemList.add(insertPosition + insertCount, childItem);
                insertCount++;
            }
        }

        if (insertCount > 0) {
            int changeCount = getItemCount() - insertPosition;
            notifyItemRangeInserted(insertPosition, insertCount);

            if (changeCount > 0) {
                notifyItemRangeChanged(insertPosition, changeCount);
            }
        }

        return insertCount;
    }

    private int removeChildFromItemList(List<T> itemList, List<C> childList, int position) {
        P parentItem = (P) itemList.get(position);

        int removePosition = position + 1;
        int removeCount = 0;

        for (C childItem : childList) {
            if (isChildItemMatchToParent(parentItem, childItem)) {
                itemList.remove(removePosition);
                removeCount++;
            }
        }

        if (removeCount > 0) {
            int changeCount = getItemCount() - removePosition;
            notifyItemRangeRemoved(removePosition, removeCount);

            if (changeCount > 0) {
                notifyItemRangeChanged(removePosition, changeCount);
            }
        }

        return removeCount;
    }

    protected abstract void onExpand(View v, int position);
    protected abstract void onCollapse(View v, int position);
    protected abstract boolean isChildItemMatchToParent(P parentItem, C childItem);
    protected abstract boolean isChildItem(T item);
}
