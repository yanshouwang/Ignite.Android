package dev.yanshouwang.demo5.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import dev.yanshouwang.demo5.R;

public class Concave extends View {
    private final Path _path;
    private final Paint _paint;

    public Concave(Context context) {
        this(context, null);
    }

    public Concave(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Concave(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public Concave(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        _path = new Path();
        _paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Concave, defStyleAttr, defStyleRes);
        try {
            final int defColor = ContextCompat.getColor(context, R.color.colorPrimary);
            final int color = a.getColor(R.styleable.Concave_color, defColor);
            _paint.setColor(color);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        updatePath(w, h);
    }

    private void updatePath(int w, int h) {
        final float cx = w / 2f;
        final float cy = h / 1f;
        final float r = w / 10f;
        final float r1 = r * 1.1f;
        final float r02 = r1 * 0.5f;
        final float a = r02 / (r02 + r1);
        final double rads = Math.acos(a);
        final float degrees = (float) Math.toDegrees(rads);
        final float dx = (float) Math.tan(rads) * r02;
        final float cx0 = cx - dx;
        final float cx2 = cx + dx;
        final float cy02 = cy - r02;
        final float left0 = cx0 - r02;
        final float left1 = cx - r1;
        final float left2 = cx2 - r02;
        final float top02 = cy02 - r02;
        final float top1 = cy - r1;
        final float right0 = cx0 + r02;
        final float right1 = cx + r1;
        final float right2 = cx2 + r02;
        final float bottom02 = cy02 + r02;
        final float bottom1 = cy + r1;

        _path.lineTo(0, cy);
        _path.lineTo(cx0, cy);
        _path.arcTo(left0, top02, right0, bottom02, 90, -degrees, false);
        _path.arcTo(left1, top1, right1, bottom1, 270 - degrees, degrees * 2, false);
        _path.arcTo(left2, top02, right2, bottom02, 90 + degrees, -degrees, false);
        _path.lineTo(w, cy);
        _path.lineTo(w, 0);
        _path.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(_path, _paint);
    }
}
