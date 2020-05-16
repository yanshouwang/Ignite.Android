package dev.yanshouwang.demo1.model;

import androidx.annotation.LayoutRes;

public class TabModel {
    @LayoutRes
    public final int layoutId;
    public final String title;

    public TabModel(@LayoutRes int layoutId, String title) {
        this.layoutId = layoutId;
        this.title = title;
    }
}
