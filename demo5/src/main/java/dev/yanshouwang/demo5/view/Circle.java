package dev.yanshouwang.demo5.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import dev.yanshouwang.demo5.R;

public class Circle extends View {
    private float _cx;
    private float _cy;
    private float _r;
    private final Paint _paint;

    public Circle(Context context) {
        this(context, null);
    }

    public Circle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Circle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public Circle(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        _paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Circle, defStyleAttr, defStyleRes);
        try {
            final int defColor = ContextCompat.getColor(context, R.color.accent);
            final int color = a.getColor(R.styleable.Circle_color, defColor);
            _paint.setColor(color);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        _cx = w / 2f;
        _cy = h / 2f;
        _r = Math.min(w, h) / 2f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(_cx, _cy, _r, _paint);
    }
}
