package com.sds.dslibrary.lib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.sds.dslibrary.R;
import com.sds.dslibrary.lib.utils.DsDisplayUtils;

/**
 * Created by sds on 2017-07-10.
 */

public class DsResizeImageView extends AppCompatImageView {

    public static final int SCALE_FACTOR_X = 0;
    public static final int SCALE_FACTOR_Y = 1;

    private float mWidth;
    private float mHeight;

    private int mResizeWidth;
    private int mResizeHeight;

    private int mScaleFactorDir;

    public DsResizeImageView(Context context) {
        this(context, null);
    }

    public DsResizeImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DsResizeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DsResizeImageView);

        mWidth = a.getFloat(R.styleable.DsResizeImageView_resize_dp_width, 0);
        mHeight = a.getFloat(R.styleable.DsResizeImageView_resize_dp_height, 0);

        mScaleFactorDir = a.getInt(R.styleable.DsResizeImageView_resize_scale_factor, SCALE_FACTOR_X);

        float scaleFactor = mScaleFactorDir == SCALE_FACTOR_Y ?
                DsDisplayUtils.calcScaleFactorY() :
                DsDisplayUtils.calcScaleFactorX();

        mResizeWidth = DsDisplayUtils.dpToSupportPx(mWidth * scaleFactor);
        mResizeHeight = DsDisplayUtils.dpToSupportPx(mHeight * scaleFactor);

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (!isInEditMode()) {
            onResize();
        }
    }

    private void onResize() {
        ViewGroup.LayoutParams lp = getLayoutParams();

        if (lp != null) {
            if (lp.width != mResizeWidth || lp.height != mResizeHeight) {
                lp.width = mResizeWidth;
                lp.height = mResizeHeight;

                setLayoutParams(lp);
            }
        }
    }
}
