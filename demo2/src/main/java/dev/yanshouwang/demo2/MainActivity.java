package dev.yanshouwang.demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;

import dev.yanshouwang.demo2.view.Camera3D;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//
//        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
//
//        final RecyclerView.LayoutManager manager = new GridLayoutManager(this, 2);
//        final List<View> views = new ArrayList<>();
//        final ImageView view1 = new ImageView(this);
//        view1.setImageResource(R.drawable.ic_ufo);
//        final View view2 = new ClipRectView(this);
//        final View view3 = new ClipPathView(this);
//        final View view4 = new TranslateView(this);
//        final View view5 = new Rotate2DView(this);
//        final View view6 = new ScaleView(this);
//        final View view7 = new SkewView(this);
//        final View view8 = new MatrixView(this);
//        final View view9 = new Rotate3DView(this);
//        views.add(view1);
//        views.add(view2);
//        views.add(view3);
//        views.add(view4);
//        views.add(view5);
//        views.add(view6);
//        views.add(view7);
//        views.add(view8);
//        views.add(view9);
//        final RecyclerView.Adapter adapter = new ViewsAdapter(views);
//
//        recyclerView.setLayoutManager(manager);
//        recyclerView.setAdapter(adapter);

        setContentView(R.layout.xyz);
        final Camera3D c3d = findViewById(R.id.c3d);
        final SeekBar tx = findViewById(R.id.tx);
        final SeekBar ty = findViewById(R.id.ty);
        final SeekBar tz = findViewById(R.id.tz);
        final SeekBar rx = findViewById(R.id.rx);
        final SeekBar ry = findViewById(R.id.ry);
        final SeekBar rz = findViewById(R.id.rz);
        tx.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                final float x = progress - 50;
                c3d.translateX(x);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ty.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                final float y = progress - 50;
                c3d.translateY(y);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        tz.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                final float z = progress - 50;
                c3d.translateZ(z);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        rx.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                final float x = progress - 50;
                c3d.rotateX(x);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ry.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                final float y = progress - 50;
                c3d.rotateY(y);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        rz.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                final float z = progress - 50;
                c3d.rotateZ(z);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
