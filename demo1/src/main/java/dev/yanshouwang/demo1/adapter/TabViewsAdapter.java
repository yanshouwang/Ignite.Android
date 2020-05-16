package dev.yanshouwang.demo1.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

import dev.yanshouwang.demo1.model.TabModel;

public class TabViewsAdapter extends FragmentPagerAdapter {
    final List<TabModel> _models;

    public TabViewsAdapter(@NonNull FragmentManager fm, List<TabModel> models) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        _models = models;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        final TabModel model = _models.get(position);
        return new Fragment(model.layoutId);
    }

    @Override
    public int getCount() {
        return _models.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        final TabModel model = _models.get(position);
        return model.title ;
    }
}
