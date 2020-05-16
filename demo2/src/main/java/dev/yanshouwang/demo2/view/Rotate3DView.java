package dev.yanshouwang.demo2.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import dev.yanshouwang.demo2.R;

public class Rotate3DView extends View {
    private final Drawable _drawable;
    private final Matrix _matrix;
    private final Camera _camera;
    private final ObjectAnimator _animator;

    public Rotate3DView(Context context) {
        this(context, null);
    }

    public Rotate3DView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Rotate3DView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public Rotate3DView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        _drawable = ContextCompat.getDrawable(context, R.drawable.ic_ufo);
        _matrix = new Matrix();
        _camera = new Camera();
        _camera.save();
        _camera.dotWithNormal(20f, 200f, 80f);
        _camera.rotateX(45f);
        _camera.getMatrix(_matrix);
        _camera.restore();
        _animator = ObjectAnimator.ofFloat(this, "degree", 0f, 180f).setDuration(1500L);
        final TimeInterpolator interpolator = new AccelerateDecelerateInterpolator();
        _animator.setInterpolator(interpolator);
        _animator.setRepeatCount(ValueAnimator.INFINITE);
        _animator.setRepeatMode(ValueAnimator.REVERSE);
    }

    public void setDegree(float degree) {
        _camera.save();
        _camera.rotateY(45f);
        _camera.rotateZ(45f);
        _camera.rotateX(degree);
        _camera.getMatrix(_matrix);
        _camera.restore();
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        final int length = Math.min(w, h) / 2;
        final float v1 = (w - length) / 2f;
        final int left = (int) v1;
        final int top = h / 2;
        final int right = left + length;
        final int bottom = top + length;

        _drawable.setBounds(left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int w = getWidth();
        final int h = getHeight();
        final float dx = w / 2f;
        final float dy = h / 2f;
        _matrix.preTranslate(-dx, -dy);
        _matrix.postTranslate(dx, dy);

        canvas.save();
        canvas.concat(_matrix);
        _drawable.draw(canvas);
        canvas.restore();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        _animator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        _animator.end();
    }
}
