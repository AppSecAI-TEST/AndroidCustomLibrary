package com.sds.dslibrary.lib.view.text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by sds on 2017-07-07.
 */

public abstract class DsDrawLineTextView extends AppCompatTextView {

    public DsDrawLineTextView(Context context) {
        super(context);
        init();
    }

    public DsDrawLineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DsDrawLineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        onSetPaint(getPaint());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        onDrawCustom(canvas);
    }

    protected void onDrawCustom(Canvas canvas) {
        if (canvas != null && getPaint() != null) {
            int w = getMeasuredWidth();
            int h = getMeasuredHeight();

            int drawStartX = Math.max(0, Math.min(drawStarX(), w));
            int drawStartY = Math.max(0, Math.min(drawStarY(), h));
            int drawStopX = Math.max(0, Math.min(drawStopX(), w));
            int drawStopY = Math.max(0, Math.min(drawStopY(), h));

            canvas.drawLine(drawStartX, drawStartY, drawStopX, drawStopY, getPaint());
        }
    }

    public abstract void onSetPaint(Paint paint);
    public abstract int drawStarX();
    public abstract int drawStarY();
    public abstract int drawStopX();
    public abstract int drawStopY();
}
