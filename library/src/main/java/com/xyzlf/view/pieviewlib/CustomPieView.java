package com.xyzlf.view.pieviewlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * zhanglifeng on 2016/8/22.
 * 自定义饼状图
 */
public class CustomPieView extends View {

    private List<CustomPieData> datas;

    private Paint paint;
    private RectF rectF;

    private float startAngel;
    private int width;
    private int height;

    public CustomPieView(Context context) {
        this(context, null);
    }

    public CustomPieView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomPieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.circle_color_default));
        paint.setStyle(Paint.Style.FILL);

        rectF = new RectF();
    }

    /**
     * 开始的角度
     * @param startAngel
     */
    public void setStartAngel(float startAngel) {
        this.startAngel = startAngel;
        invalidate();
    }

    /**
     * 数据源
     * @param datas
     */
    public void setDatas(List<CustomPieData> datas) {
        this.datas = datas;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        height = h;

        float r = (float) (Math.min(width, height) / 2 * 0.8);
        rectF.set(-r, -r, r, r);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null != datas && !datas.isEmpty()) {
            float currentStartAngle = startAngel;
            int totalAngle = 0;
            canvas.save();
            canvas.translate(width / 2, height / 2);
            for (CustomPieData data : datas) {
                if (null == data || data.getAngel() <= 0) {
                    continue;
                }
                if (isColorValid(data.getColor())) {
                    paint.setColor(Color.parseColor(data.getColor()));
                } else {
                    paint.setColor(ContextCompat.getColor(getContext(), R.color.circle_color_default));
                }
                canvas.drawArc(rectF, currentStartAngle, data.getAngel(), true, paint);
                currentStartAngle += data.getAngel();
                totalAngle += data.getAngel();
            }
            canvas.restore();

            if (totalAngle <= 0) {
                paint.setColor(ContextCompat.getColor(getContext(), R.color.circle_color_default));
                canvas.drawCircle(width / 2, height / 2, (float) (Math.min(width, height) / 2 * 0.8), paint);
            }
        } else {
            canvas.drawCircle(width / 2, height / 2, (float) (Math.min(width, height) / 2 * 0.8), paint);
        }
    }

    public static final String PATTERN_COLOR = "^#[0-9a-fA-F]{6}$";

    public static final boolean isColorValid(String colorStr) {
        if (TextUtils.isEmpty(colorStr)) {
            return false;
        }
        Pattern pattern = Pattern.compile(PATTERN_COLOR);
        if (pattern != null) {
            Matcher matcher = pattern.matcher(colorStr);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }

}
