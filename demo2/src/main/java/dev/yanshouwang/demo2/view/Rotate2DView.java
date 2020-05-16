package dev.yanshouwang.demo2.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import dev.yanshouwang.demo2.R;

public class Rotate2DView extends View {
    private final Drawable _drawable;

    public Rotate2DView(Context context) {
        this(context, null);
    }

    public Rotate2DView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Rotate2DView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public Rotate2DView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        _drawable = ContextCompat.getDrawable(context, R.drawable.ic_ufo);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        final int length = Math.min(w, h);
        final float v1 = (w - length) / 2f;
        final float v2 = (h - length) / 2f;
        final int left = (int) v1;
        final int top = (int) v2;
        final int right = w - left;
        final int bottom = h - top;

        _drawable.setBounds(left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int left = _drawable.getBounds().left;
        final int top = _drawable.getBounds().top;
        final int right = _drawable.getBounds().right;
        final int bottom = _drawable.getBounds().bottom;

        final float px = (left + right) / 2f;
        final float py = (top + bottom) / 2f;

        canvas.save();
        canvas.rotate(45, px, py);
        _drawable.draw(canvas);
        canvas.restore();
    }
}
