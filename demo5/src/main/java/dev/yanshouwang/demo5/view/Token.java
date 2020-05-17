package dev.yanshouwang.demo5.view;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import dev.yanshouwang.demo5.R;

public class Token extends View {
    private final RectF[] _cards;
    private float _r;
    private final String[] _numbers;
    private final PointF[] _numberLocations;
    private final PointF[] _lineLocations;
    private final Paint _cardPaint;
    private final Paint _numberPaint;
    private final Paint _linePaint;
    private final int _inactiveColor;
    private final int _activeColor;
    private final Matrix[] _matrices;
    private final Camera _camera;
    private final ValueAnimator _cardAnimator;
    private final ValueAnimator _lineAnimator;
    private float _downX;
    private boolean _consumed;

    public Token(Context context) {
        this(context, null);
    }

    public Token(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Token(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public Token(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        _cards = new RectF[6];
        _numbers = new String[6];
        _numberLocations = new PointF[6];
        _lineLocations = new PointF[3];
        _cardPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        _numberPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        _linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        _matrices = new Matrix[6];
        _camera = new Camera();
        _cardAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(500L);

        final Resources res = context.getResources();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Token, defStyleAttr, defStyleRes);
        try {
            final int defDuration = res.getInteger(R.integer.token_duration);
            final long duration = a.getInteger(R.styleable.Token_android_duration, defDuration);
            _lineAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(duration);
            final int defCardColor = ContextCompat.getColor(context, R.color.colorAccent);
            final int cardColor = a.getColor(R.styleable.Token_cardColor, defCardColor);
            _cardPaint.setColor(cardColor);
            final int defNumberColor = ContextCompat.getColor(context, android.R.color.primary_text_dark);
            final int numberColor = a.getColor(R.styleable.Token_numberColor, defNumberColor);
            _numberPaint.setColor(numberColor);
            final float defSize = res.getDimension(R.dimen.token_text_size);
            final float size = a.getDimension(R.styleable.Token_android_textSize, defSize);
            _numberPaint.setTextSize(size);
            final int inactiveColor = ContextCompat.getColor(context, android.R.color.darker_gray);
            _inactiveColor = a.getColor(R.styleable.Token_inactiveColor, inactiveColor);
            final int activeColor = ContextCompat.getColor(context, R.color.colorPrimary);
            _activeColor = a.getColor(R.styleable.Token_activeColor, activeColor);
        } finally {
            a.recycle();
        }

        _linePaint.setStyle(Paint.Style.STROKE);
        final float strokeWidth = res.getDimension(R.dimen.token_line_height);
        _linePaint.setStrokeWidth(strokeWidth);
        _linePaint.setStrokeCap(Paint.Cap.ROUND);

        _cards[0] = new RectF();
        _cards[1] = new RectF();
        _cards[2] = new RectF();
        _cards[3] = new RectF();
        _cards[4] = new RectF();
        _cards[5] = new RectF();

        _numberLocations[0] = new PointF();
        _numberLocations[1] = new PointF();
        _numberLocations[2] = new PointF();
        _numberLocations[3] = new PointF();
        _numberLocations[4] = new PointF();
        _numberLocations[5] = new PointF();

        _lineLocations[0] = new PointF();
        _lineLocations[1] = new PointF();
        _lineLocations[2] = new PointF();

        _matrices[0] = new Matrix();
        _matrices[1] = new Matrix();
        _matrices[2] = new Matrix();
        _matrices[3] = new Matrix();
        _matrices[4] = new Matrix();
        _matrices[5] = new Matrix();

        final TimeInterpolator i0 = new FastOutSlowInInterpolator();
        _lineAnimator.setInterpolator(i0);
        final TimeInterpolator i1 = new LinearInterpolator();
        _lineAnimator.setInterpolator(i1);

        _lineAnimator.addUpdateListener(animation -> {
            final float value = (float) animation.getAnimatedValue();
            updateProgress(value);
        });
        _lineAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                update();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        _cardAnimator.addUpdateListener(animation -> {
            final float value = (float) animation.getAnimatedValue();
            updateMatrices(value);
        });

        updateNumbers();
    }

    private void updateProgress(float value) {
        final PointF location0 = _lineLocations[0];
        final PointF location1 = _lineLocations[1];
        final PointF location2 = _lineLocations[2];
        location1.x = location0.x + (location2.x - location0.x) * value;
        invalidate();
    }

    private void updateNumbers() {
        for (int i = 0; i < _numbers.length; i++) {
            final double random = Math.random();
            final int value = (int) (random * 9);
            final String number = String.valueOf(value);
            _numbers[i] = number;
        }
    }

    private void updateMatrices(float value) {
        final int a = (int) (value * 6f);
        final int i = Math.min(a, 5);
        for (int j = 0; j < _cards.length; j++) {
            final Matrix matrix = _matrices[j];
            if (j < i) {
                _camera.save();
                _camera.rotateX(0f);
                _camera.getMatrix(matrix);
                _camera.restore();
            } else if (j == i) {
                final float degrees = 90f - (value * 6f - i) * 90f;
                _camera.save();
                _camera.rotateX(degrees);
                _camera.getMatrix(matrix);
                _camera.restore();
            } else {
                _camera.save();
                _camera.rotateX(90f);
                _camera.getMatrix(matrix);
                _camera.restore();
            }
            // 旋转中心
            final RectF rect = _cards[j];
            final float dx = (rect.left + rect.right) / 2f;
            final float dy = (rect.top + rect.bottom) / 2f;
            matrix.preTranslate(-dx, -dy);
            matrix.postTranslate(dx, dy);
        }
        invalidate();
    }

    public void update() {
        updateNumbers();
        _lineAnimator.start();
        _cardAnimator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int width = MeasureSpec.getSize(widthMeasureSpec);
        final float strokeWidth = _linePaint.getStrokeWidth();
        final double desiredHeight = width * (7.0 * 1.8 + 1.0) / 47.0 + strokeWidth / 2.0;
        final int height = (int) Math.ceil(desiredHeight);
        final int measuredWidth = resolveSize(width, widthMeasureSpec);
        final int measuredHeight = resolveSize(height, heightMeasureSpec);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        updateCards(w, h);
        updateNumberLocations();
        updateLineLocations();
    }

    private void updateLineLocations() {
        final RectF card0 = _cards[0];
        final RectF card1 = _cards[1];
        final RectF card5 = _cards[5];
        final float unit = card1.left - card0.right;
        final float strokeWidth = _linePaint.getStrokeWidth();
        final float x0 = card0.left;
        final float x1 = card5.right;
        final float y = card0.bottom + unit + strokeWidth / 2f;
        final PointF location0 = _lineLocations[0];
        final PointF location1 = _lineLocations[1];
        final PointF location2 = _lineLocations[2];
        location0.x = x0;
        location0.y = y;
        location1.x = x0;
        location1.y = y;
        location2.x = x1;
        location2.y = y;
    }

    private void updateNumberLocations() {
        for (int i = 0; i < _numberLocations.length; i++) {
            final PointF location = _numberLocations[i];
            final RectF card = _cards[i];
            final float cardWidth = card.width();
            final float cardHeight = card.height();
            final String number = _numbers[i];
            final float width = _numberPaint.measureText(number);
            final float ascent = _numberPaint.ascent();
            final float descent = _numberPaint.descent();
            final float height = descent - ascent;
            final float dx = (cardWidth - width) / 2f;
            final float dy = (cardHeight - height) / 2f;
            location.x = card.left + dx;
            location.y = card.bottom - dy - descent;
        }
    }

    private void updateCards(int w, int h) {
        final float unit = w / 47f;
        final float width = unit * 7f;
        final float height = width * 1.8f;
        final float strokeWidth = _linePaint.getStrokeWidth();
        final float h1 = height + unit + strokeWidth / 2f;
        final float top = (h - h1) / 2f;
        final float bottom = top + height;

        _r = unit * 0.5f;
        float x = 0f;
        for (RectF rect : _cards) {
            rect.left = x;
            rect.top = top;
            x += width;
            rect.right = x;
            rect.bottom = bottom;
            x += unit;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Cards and Numbers
        for (int i = 0; i < _cards.length; i++) {
            final RectF rect = _cards[i];
            final Matrix matrix = _matrices[i];
            final String number = _numbers[i];
            final PointF location = _numberLocations[i];
            canvas.drawRoundRect(rect, _r, _r, _cardPaint);
            canvas.save();
            canvas.concat(matrix);
            canvas.drawText(number, location.x, location.y, _numberPaint);
            canvas.restore();
        }
        // Line
        final PointF location0 = _lineLocations[0];
        final PointF location1 = _lineLocations[1];
        final PointF location2 = _lineLocations[2];
        _linePaint.setColor(_inactiveColor);
        canvas.drawLine(location0.x, location0.y, location2.x, location2.y, _linePaint);
        if (location0.x != location1.x) {
            _linePaint.setColor(_activeColor);
            canvas.drawLine(location0.x, location0.y, location1.x, location1.y, _linePaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();
        final int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                _downX = x;
                _consumed = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (_consumed) {
                    break;
                }
                final float distance = Math.abs(x - _downX);
                final float threshold = _cards[0].width();
                final float duration = event.getEventTime() - event.getDownTime();
                if (distance > threshold && duration < 1000L) {
                    _cardAnimator.start();
                    _consumed = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                final float width = getWidth();
                final float height = getHeight();
                if (x < 0f || x > width || y < 0 || y > height) {
                    break;
                }
                performClick();
                break;
            case MotionEvent.ACTION_CANCEL:
                _consumed = true;
                break;
        }
        return true;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        _lineAnimator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        _lineAnimator.end();
    }
}
