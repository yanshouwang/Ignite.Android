package dev.yanshouwang.demo3.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import dev.yanshouwang.demo3.R;

public class Avatar extends AppCompatImageView {
    private final Drawable _drawable;

    public Avatar(Context context) {
        this(context, null);
    }

    public Avatar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Avatar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        _drawable = ContextCompat.getDrawable(context, R.drawable.ic_mask);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        final float cx = w / 2f;
        final float cy = h / 2f;
        final float length = Math.min(w, h) / 2f;
        final int left = (int) (cx - length);
        final int top = (int) (cy - length);
        final int right = (int) (cx + length);
        final int bottom = (int) (cy + length);

        _drawable.setBounds(left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);

        _drawable.draw(canvas);
    }
}
