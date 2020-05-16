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

public class CircleView extends View {
    private final Paint _paint;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        _paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        final TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleView, defStyleAttr, defStyleRes);
        try {
            final int defColor = ContextCompat.getColor(context, R.color.colorPrimary);
            final int color = a.getColor(R.styleable.CircleView_color, defColor);
            final int i = a.getInteger(R.styleable.CircleView_paintStyle, 0);
            final Paint.Style style = Paint.Style.values()[i];
            final float width = a.getDimension(R.styleable.CircleView_strokeWidth, 0f);
            _paint.setColor(color);
            _paint.setStyle(style);
            _paint.setStrokeWidth(width);
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
        final float radius = width < height ? (width - strokeWidth) / 2f : (height - strokeWidth) / 2f;
        canvas.drawCircle(cx, cy, radius, _paint);
    }

    public void setColor(@ColorInt int color) {
        _paint.setColor(color);
        invalidate();
    }

    public void setPaintStyle(Paint.Style style) {
        _paint.setStyle(style);
        invalidate();
    }

    public void setStrokeWidth(float width) {
        _paint.setStrokeWidth(width);
        invalidate();
    }
}
