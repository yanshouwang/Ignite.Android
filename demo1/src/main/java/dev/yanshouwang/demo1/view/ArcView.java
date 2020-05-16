package dev.yanshouwang.demo1.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import dev.yanshouwang.demo1.R;

public class ArcView extends View {
    private final Paint _paint;
    private float _startAngle;
    private float _sweepAngle;

    public ArcView(Context context) {
        this(context, null);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        _paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        _paint.setStyle(Paint.Style.STROKE);
        final TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ArcView, defStyleAttr, defStyleRes);
        try {
            final int defColor = ContextCompat.getColor(context, R.color.colorPrimary);
            final int color = a.getColor(R.styleable.ArcView_color, defColor);
            final int i = a.getInteger(R.styleable.ArcView_android_strokeLineCap, 0);
            final Paint.Cap cap = Paint.Cap.values()[i];
            final float width = a.getDimension(R.styleable.ArcView_strokeWidth, 0f);
            _paint.setColor(color);
            _paint.setStrokeCap(cap);
            _paint.setStrokeWidth(width);
            _startAngle = a.getFloat(R.styleable.ArcView_startAngle, 0f);
            _sweepAngle = a.getFloat(R.styleable.ArcView_sweepAngle, 0f);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final float width = getWidth();
        final float height = getHeight();
        final float strokeWidth = _paint.getStrokeWidth();
        final float cx = width / 2f;
        final float cy = height / 2f;
        final float r = width < height ? (width - strokeWidth) / 2f : (height - strokeWidth) / 2f;
        final float left = cx - r;
        final float top = cy - r;
        final float right = cx + r;
        final float bottom = cy + r;
        canvas.drawArc(left, top, right, bottom, _startAngle, _sweepAngle, false, _paint);
    }

    public void setColor(@ColorInt int color) {
        _paint.setColor(color);
        invalidate();
    }

    public void setStrokeCap(Paint.Cap cap) {
        _paint.setStrokeCap(cap);
        invalidate();
    }

    public void setStrokeWidth(float width) {
        _paint.setStrokeWidth(width);
        invalidate();
    }

    public void setStartAngle(float startAngle) {
        _startAngle = startAngle;
        invalidate();
    }

    public void setSweepAngle(float sweepAngle) {
        _sweepAngle = sweepAngle;
        invalidate();
    }
}
