package dev.yanshouwang.demo1.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import dev.yanshouwang.demo1.R;

public class HeartView extends View {
    private final Path _path;
    private final Paint _paint;

    public HeartView(Context context) {
        this(context, null);
    }

    public HeartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public HeartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        _path = new Path();
        _paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        final TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HeartView, defStyleAttr, defStyleRes);
        try {
            final int defColor = ContextCompat.getColor(context, R.color.colorPrimary);
            final int color = a.getColor(R.styleable.HeartView_color, defColor);
            _paint.setColor(color);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        final double a = Math.toRadians(67.5);
        final double ratio = 4 / (Math.tan(a) + 1);
        final float width = (float) w / h < ratio ? w : (float) (h * ratio);
        final float height = (float) (w / ratio);
        final float diameter = width / 2f;
        final float left = (w - width) / 2f;
        final float top = (h - height) / 2f;

        _path.reset();
        _path.addArc(left, top, left + diameter, top + diameter, -225f, 225f);
        _path.arcTo(left + diameter, top, left + width, top + diameter, -180f, 225f, false);
        _path.lineTo(left + diameter, top + height);
        _path.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(_path, _paint);
    }

    public void setColor(@ColorInt int color) {
        _paint.setColor(color);
        invalidate();
    }
}
