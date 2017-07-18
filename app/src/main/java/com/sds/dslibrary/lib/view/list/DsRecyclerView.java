package com.sds.dslibrary.lib.view.list;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.sds.dslibrary.lib.view.list.adapter.DsSimpleRecyclerViewAdapter;

/**
 * Created by sds on 2017-07-07.
 */

public class DsRecyclerView extends RecyclerView {

    private OnScrollCallback mScrollCallback = null;

    public DsRecyclerView(Context context) {
        super(context);
    }

    public DsRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DsRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        RecyclerView.Adapter oldAdapter = getAdapter();
        if (oldAdapter instanceof DsSimpleRecyclerViewAdapter) {
            ((DsSimpleRecyclerViewAdapter) oldAdapter).removeRecyclerView();
        }

        super.setAdapter(adapter);

        if (adapter instanceof DsSimpleRecyclerViewAdapter) {
            ((DsSimpleRecyclerViewAdapter) adapter).setRecyclerView(this);
        }
    }

    public void addOnScrollCallback(OnScrollCallback callback) {
        mScrollCallback = callback;

        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy < 0) {
                    // scroll top
                    if (isScrolledToTop()) {
                        if (mScrollCallback != null) {
                            mScrollCallback.onScrolledToTop(recyclerView);
                        }
                    }

                } else if (dy > 0) {
                    // scroll bottom
                    if (isScrolledToBottom()) {
                        if (mScrollCallback != null) {
                            mScrollCallback.onScrolledToBottom(recyclerView);
                        }
                    }
                }

                if (mScrollCallback != null) {
                    mScrollCallback.onScrolled(recyclerView, dx, dy);
                }
            }
        });
    }

    private boolean isScrolledToTop() {
        LayoutManager lm = getLayoutManager();

        if (lm instanceof LinearLayoutManager) {
            int startIdx = 0;
            return ((LinearLayoutManager) lm).findFirstCompletelyVisibleItemPosition() == startIdx;
        }

        return false;
    }

    private boolean isScrolledToBottom() {
        LayoutManager lm = getLayoutManager();

        if (lm instanceof LinearLayoutManager) {
            int lastIdx = lm.getItemCount() - 1;
            return ((LinearLayoutManager) lm).findLastCompletelyVisibleItemPosition() == lastIdx;
        }

        return false;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        setAdapter(null);
    }

    public interface OnScrollCallback {
        void onScrolledToTop(RecyclerView recyclerView);
        void onScrolledToBottom(RecyclerView recyclerView);
        void onScrolled(RecyclerView recyclerView, int dx, int dy);
    }
}
