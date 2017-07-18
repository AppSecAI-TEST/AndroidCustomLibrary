package com.sds.dslibrary.lib.view.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sds.dslibrary.lib.view.list.DsRecyclerView;
import com.sds.dslibrary.lib.view.list.DsRecyclerViewHolder;

/**
 * Created by sds on 2017-07-07.
 */

public abstract class DsSimpleRecyclerViewAdapter<VH extends DsRecyclerViewHolder> extends RecyclerView.Adapter<VH> implements DsRecyclerViewHolder.ItemInfoListener, View.OnClickListener {

    private DsRecyclerView mRecyclerView = null;
    private OnClickItemListener mOnClickItemListener = null;

    private boolean mRegisterDataObserver = false;

    public DsSimpleRecyclerViewAdapter() {
        super();
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (holder != null) {
            setHolderItemInfo(holder, position);

            final int pos = position;

            holder.itemView.setOnClickListener(this);

            bindViewHolderWithPosition(holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getItemViewTypeWithPosition(position);
    }

    @Override
    public void onClick(View v) {
        RecyclerView recyclerView = getRecyclerView();

        if (recyclerView != null) {
            int pos = recyclerView.getChildAdapterPosition(v);

            if (pos >= 0) {
                onPreClickItemView(v, pos);

                if (mOnClickItemListener != null) {
                    mOnClickItemListener.onClickItem(v, pos);
                }

                onPostClickItemView(v, pos);
            }
        }
    }

    public void setRecyclerView(DsRecyclerView recyclerView) {
        mRecyclerView = recyclerView;

        if (mRecyclerView != null && !mRegisterDataObserver) {
            registerAdapterDataObserver(mDataObserver);
            mRegisterDataObserver = true;
        }
    }

    public void removeRecyclerView() {
        mRecyclerView = null;

        if (mRegisterDataObserver) {
            unregisterAdapterDataObserver(mDataObserver);
            mRegisterDataObserver = false;
        }
    }

    private void onUpdateByNotifyItemRangeChanged(int positionStart, int itemCount) {
        if (mRecyclerView != null) {
            RecyclerView.LayoutManager lm = mRecyclerView.getLayoutManager();

            int start = positionStart;
            int end = positionStart + itemCount;

            for (int i = start; i < end; ++i) {
                View v = lm.findViewByPosition(i);

                if (v == null) {
                    break;
                }

                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);

                if (holder != null) {
                    if (holder instanceof DsRecyclerViewHolder) {
                        setHolderItemInfo((VH) holder, i);
                    }
                }
            }
        }
    }

    private void setHolderItemInfo(VH h, int position) {
        h.mItemInfo.mPosition = position;
        h.mItemInfo.mBindPosition = convertBindPosition(position);
        h.mItemInfo.mIsExpanded = isExpandedPosition(position);
    }

    public void setOnClickItemListener(OnClickItemListener l) {
        mOnClickItemListener = l;
    }

    public DsRecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    private final RecyclerView.AdapterDataObserver mDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            onUpdateByNotifyItemRangeChanged(positionStart, itemCount);
        }
    };

    protected abstract void bindViewHolderWithPosition(VH holder, int position);
    protected abstract int getItemViewTypeWithPosition(int position);
    protected abstract void onPreClickItemView(View v, int position);
    protected abstract void onPostClickItemView(View v, int position);

    public interface OnClickItemListener {
        void onClickItem(View v, int position);
    }
}
