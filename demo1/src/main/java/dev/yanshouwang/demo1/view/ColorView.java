package dev.yanshouwang.demo1.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import dev.yanshouwang.demo1.R;

public class ColorView extends View {
    @ColorInt
    private int _color;

    public ColorView(Context context) {
        this(context, null);
    }

    public ColorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        final TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ColorView, defStyleAttr, defStyleRes);
        try {
            final int color = ContextCompat.getColor(context, R.color.colorPrimary);
            _color = a.getColor(R.styleable.ColorView_color, color);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(_color);
    }

    public int getColor() {
        return _color;
    }

    public void setColor(@ColorInt int color) {
        _color = color;
        invalidate();
    }
}
