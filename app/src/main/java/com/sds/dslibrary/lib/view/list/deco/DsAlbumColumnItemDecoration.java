package com.sds.dslibrary.lib.view.list.deco;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static android.R.attr.padding;

/**
 * Created by sds on 2017-07-07.
 */

public class DsAlbumColumnItemDecoration extends RecyclerView.ItemDecoration {

    private static final int MIN_COLUMN_MULTI_LINE = 2;

    protected int mCol;
    protected int mSpacingX;
    protected int mSpacingY;
    protected int mLeftMargin;
    protected int mRightMargin;

    public DsAlbumColumnItemDecoration(int col, int spacingX, int spacingY) {
        this(col, spacingX, spacingY, 0, 0);
    }

    public DsAlbumColumnItemDecoration(int col, int spacingX, int spacingY, int leftMargin, int rightMargin) {
        mCol = col;
        mSpacingX = spacingX;
        mSpacingY = spacingY;
        mLeftMargin = leftMargin;
        mRightMargin = rightMargin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemPos = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();

        // bottom
        outRect.bottom = 0;

        // top
        outRect.top = isFirstRow(mCol, itemPos) ? 0 : mSpacingY;

        if (mCol >= MIN_COLUMN_MULTI_LINE) {
            int frameWidth = calcFrameWidth(parent);
            int padding = calcPadding(parent, frameWidth);

            if (itemPos % mCol == 0) {
                // left
                outRect.left = mLeftMargin;
                outRect.right = padding;
            } else if ((itemPos + 1) % mCol == 0) {
                // right
                outRect.right = mRightMargin;
                outRect.left = padding;
            } else {
                // other
                outRect.left = padding;
                outRect.right = padding;
            }
        } else {
            outRect.left = mLeftMargin;
            outRect.right = mRightMargin;
        }
    }

    private int calcFrameWidth(View v) {
        int frameWith = (int) ((v.getWidth() - (float) mSpacingX * (mCol - 1)) / mCol);
        return frameWith;
    }

    private int calcPadding(View v, int frameWidth) {
        if (mCol > 0) {
            int padding = v.getWidth() / mCol - frameWidth;
            return padding;
        }

        return 0;
    }

    private boolean isFirstRow(int column, int position) {
        boolean result;

        if (column >= MIN_COLUMN_MULTI_LINE) {
            result = (position < column);
        } else {
            result = (position == 0);
        }

        return result;
    }
}
