package dev.yanshouwang.demo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import dev.yanshouwang.demo2.adapter.ViewsAdapter;
import dev.yanshouwang.demo2.view.ClipPathView;
import dev.yanshouwang.demo2.view.ClipRectView;
import dev.yanshouwang.demo2.view.MatrixView;
import dev.yanshouwang.demo2.view.Rotate2DView;
import dev.yanshouwang.demo2.view.Rotate3DView;
import dev.yanshouwang.demo2.view.ScaleView;
import dev.yanshouwang.demo2.view.SkewView;
import dev.yanshouwang.demo2.view.TranslateView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final RecyclerView recyclerView = findViewById(R.id.recyclerView);

        final RecyclerView.LayoutManager manager = new GridLayoutManager(this, 2);
        final List<View> views = new ArrayList<>();
        final ImageView view1 = new ImageView(this);
        view1.setImageResource(R.drawable.ic_ufo);
        final View view2 = new ClipRectView(this);
        final View view3 = new ClipPathView(this);
        final View view4 = new TranslateView(this);
        final View view5 = new Rotate2DView(this);
        final View view6 = new ScaleView(this);
        final View view7 = new SkewView(this);
        final View view8 = new MatrixView(this);
        final View view9 = new Rotate3DView(this);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        views.add(view5);
        views.add(view6);
        views.add(view7);
        views.add(view8);
        views.add(view9);
        final RecyclerView.Adapter adapter = new ViewsAdapter(views);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}
