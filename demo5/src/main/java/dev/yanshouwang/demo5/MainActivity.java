package dev.yanshouwang.demo5;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import dev.yanshouwang.demo5.util.ActivityUtils;
import dev.yanshouwang.demo5.view.Token;

public class MainActivity extends AppCompatActivity {
    private Token _token;
    private ObjectAnimator _animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // 沉浸式状态栏
        ActivityUtils.immerse(this);

        _token = findViewById(R.id.token);

        final ImageView code = findViewById(R.id.code);
        final TextView update = findViewById(R.id.update);
        final ImageView state = findViewById(R.id.state);

        _animator = ObjectAnimator.ofFloat(state, "rotation", 0f, 360f).setDuration(500L);

        code.setOnClickListener(v -> onClickCode());
        update.setOnClickListener(v -> onClickUpdate());
    }

    private void onClickUpdate() {
        _token.update();
        _animator.start();
    }

    private void onClickCode() {

    }
}
