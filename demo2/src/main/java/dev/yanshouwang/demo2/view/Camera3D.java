package dev.yanshouwang.demo2.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import dev.yanshouwang.demo2.R;

public class Camera3D extends View {
    private final RectF _rect;
    private final Paint _paint;
    private final Camera _camera;
    private final Matrix _matrix;
    private float _tx;
    private float _ty;
    private float _tz;
    private float _rx;
    private float _ry;
    private float _rz;

    public Camera3D(Context context) {
        this(context, null);
    }

    public Camera3D(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Camera3D(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public Camera3D(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        _rect = new RectF();
        _paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        _camera = new Camera();
        _matrix = new Matrix();

        final Resources res = context.getResources();
        final int color = ContextCompat.getColor(context, R.color.colorAccent);
        _paint.setColor(color);
    }

    public void translateX(float x) {
        _tx = x;
        updateMatrix();
    }

    public void translateY(float y) {
        _ty = y;
        updateMatrix();
    }

    private void updateMatrix() {
        _camera.save();
        _camera.translate(_tx, _ty, _tz);
        _camera.rotate(_rx, _ry, _rz);
        _camera.getMatrix(_matrix);
        _camera.restore();

//        final float cx = _rect.centerX();
//        final float cy = _rect.centerY();
//        _matrix.preTranslate(-cx, -cy);
//        _matrix.postTranslate(cx, cy);
//        invalidate();
    }

    public void translateZ(float z) {
        _tz = z;
        updateMatrix();
    }

    public void rotateX(float x) {
        _rx = x;
        updateMatrix();
    }

    public void rotateY(float y) {
        _ry = y;
        updateMatrix();
    }

    public void rotateZ(float z) {
        _rz = z;
        updateMatrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        _rect.left = 0f;
        _rect.top = 0f;
        _rect.right = w;
        _rect.bottom = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.concat(_matrix);
        canvas.drawRect(_rect, _paint);
        canvas.restore();
    }
}
