package dev.yanshouwang.demo1.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import dev.yanshouwang.demo1.R;

public class CrossView extends View {
    private final Path _path;
    private final Paint _paint;
    private boolean _cocurrent;

    public CrossView(Context context) {
        this(context, null);
    }

    public CrossView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CrossView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CrossView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        _path = new Path();
        _paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        _paint.setStyle(Paint.Style.FILL);
        final TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CrossView, defStyleAttr, defStyleRes);
        try {
            _cocurrent = a.getBoolean(R.styleable.CrossView_cocurrent, true);
            final int i = a.getInteger(R.styleable.CrossView_fillType, 0);
            final Path.FillType ft = Path.FillType.values()[i];
            _path.setFillType(ft);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        updatePath();
    }

    private void updatePath() {
        final float width = getWidth();
        final float height = getHeight();
        final float radius = Math.min(width / 3f, height / 2f);
        final Path.FillType ft = _path.getFillType();
        _path.reset();
        _path.setFillType(ft);
        final float x1 = width / 2f - radius / 2f;
        final float x2 = width / 2f + radius / 2f;
        final float y = height / 2f;
        final Path.Direction dir1 = Path.Direction.CW;
        final Path.Direction dir2 = _cocurrent ? Path.Direction.CW : Path.Direction.CCW;
        _path.addCircle(x1, y, radius, dir1);
        _path.addCircle(x2, y, radius, dir2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(_path, _paint);
    }

    public void setCocurrent(boolean cocurrent) {
        _cocurrent = true;
        updatePath();
        invalidate();
    }

    public void setFillType(Path.FillType ft) {
        _path.setFillType(ft);
        invalidate();
    }
}
