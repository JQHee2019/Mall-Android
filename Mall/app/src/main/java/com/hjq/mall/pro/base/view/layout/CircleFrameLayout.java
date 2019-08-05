package com.hjq.mall.pro.base.view.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.StringUtils;
import com.hjq.mall.R;

public class CircleFrameLayout extends FrameLayout {
    private int mColor;
    private Paint mPaint;
    private Context mContext;
    private int radius;

    public CircleFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public CircleFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(ContextCompat.getColor(mContext, R.color.app_theme_color));
        setWillNotDraw(false);
    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int childCount = getChildCount();
        radius = w / 2;
    }

    /**
     * @date: 2019/7/24 0024
     * @author: gaoxiaoxiong
     * @description:设置颜色值
     **/
    public void setColor(String color){
        if (StringUtils.isEmpty(color)){
            return;
        }
        mColor = Color.parseColor(color);
        invalidate();
    }

    public void setColor(int color){
        mColor = color;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(radius, radius);
        canvas.drawCircle(0, 0, radius, mPaint);
        canvas.restore();
        super.onDraw(canvas);
    }
}
