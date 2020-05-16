package dev.yanshouwang.demo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import dev.yanshouwang.demo1.adapter.TabViewsAdapter;
import dev.yanshouwang.demo1.model.TabModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final TabLayout tabLayout = findViewById(R.id.tabLayout);
        final ViewPager viewPager = findViewById(R.id.viewPager);

        final FragmentManager fm = getSupportFragmentManager();
        final String title1 = getString(R.string.draw_color);
        final String title2 = getString(R.string.draw_circle);
        final String title3 = getString(R.string.draw_arc);
        final String title4 = getString(R.string.draw_heart);
        final String title5 = getString(R.string.draw_cross);
        final TabModel model1 = new TabModel(R.layout.draw_color, title1);
        final TabModel model2 = new TabModel(R.layout.draw_circle, title2);
        final TabModel model3 = new TabModel(R.layout.draw_arc, title3);
        final TabModel model4 = new TabModel(R.layout.draw_heart, title4);
        final TabModel model5 = new TabModel(R.layout.draw_cross, title5);
        final List<TabModel> models = new ArrayList<>();
        models.add(model1);
        models.add(model2);
        models.add(model3);
        models.add(model4);
        models.add(model5);
        final TabViewsAdapter adapter = new TabViewsAdapter(fm, models);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
