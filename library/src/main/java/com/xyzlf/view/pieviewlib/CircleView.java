package com.xyzlf.view.pieviewlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;

/**
 * zhanglifeng on 2016/8/22.
 */

public class CircleView extends View {

    private static final int DEFAULT_RADIUS = 4;

    private Paint paint;
    private int radius;
    private int color;

    private int width;
    private int height;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.CircleView, 0, 0);
        if (arr != null) {
            radius = (int) arr.getDimension(R.styleable.CircleView_circle_radius, dp2px(getContext(), DEFAULT_RADIUS));
            color = arr.getColor(R.styleable.CircleView_circle_color, Color.WHITE);
            arr.recycle();
        }
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
    }

    /**
     * 单位dp
     * @param radius
     */
    public void setRadius(int radius) {
        this.radius = dp2px(getContext(), radius);
        invalidate();
    }

    /**
     * Color
     * @param color
     */
    public void setColor(@ColorInt int color) {
        if (null != paint) {
            paint.setColor(color);
        }
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        height = h;

        int r = Math.min(width, height) / 2;
        if (radius > r) {
            radius = r;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawCircle(width / 2, height / 2, radius, paint);
    }


    public static int dp2px(Context context, float  dpValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
