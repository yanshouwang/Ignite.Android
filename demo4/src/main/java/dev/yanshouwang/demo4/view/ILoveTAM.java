package dev.yanshouwang.demo4.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ILoveTAM extends View {
    private final String _str;
    private final Paint _paint;
    private float _x;
    private float _y;

    public ILoveTAM(Context context) {
        this(context, null);
    }

    public ILoveTAM(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ILoveTAM(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ILoveTAM(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        _str = "我爱北京天安门";
        _paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        _x = 0f;
        _y = 0f;

        _paint.setTextSize(80f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

//        final float width = _paint.measureText(_str);
//        _x = (w - width) / 2f;
//        final Paint.FontMetrics fm = _paint.getFontMetrics();
//        final float height = fm.descent - fm.ascent;
//        _y = (h - height) / 2f - fm.ascent;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(_str, _x, _y, _paint);
    }
}
