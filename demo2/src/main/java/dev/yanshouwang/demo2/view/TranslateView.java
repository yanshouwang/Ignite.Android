package dev.yanshouwang.demo2.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import dev.yanshouwang.demo2.R;

public class TranslateView extends View {
    private final Drawable _drawable;

    public TranslateView(Context context) {
        this(context, null);
    }

    public TranslateView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TranslateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TranslateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        final int right = _drawable.getBounds().right;
        final int length = right - left;

        final float dx = length / 4f;
        final float dy = length / 4f;

        canvas.save();
        canvas.translate(dx, dy);
        _drawable.draw(canvas);
        canvas.restore();
    }
}
