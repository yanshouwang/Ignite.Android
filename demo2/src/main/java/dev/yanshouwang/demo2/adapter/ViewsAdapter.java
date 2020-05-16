package dev.yanshouwang.demo2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.yanshouwang.demo2.R;

public class ViewsAdapter extends RecyclerView.Adapter<ViewsAdapter.ViewHolder> {
    private final List<View> _views;

    public ViewsAdapter(List<View> views) {
        _views = views;
    }

    @NonNull
    @Override
    public ViewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final View itemView = LayoutInflater.from(context).inflate(R.layout.container, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewsAdapter.ViewHolder holder, int position) {
        final View view = _views.get(position);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
        layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        holder.constraintLayout.addView(view, layoutParams);
    }

    @Override
    public void onViewRecycled(@NonNull ViewsAdapter.ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.constraintLayout.removeAllViews();
    }

    @Override
    public int getItemCount() {
        return _views.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final ConstraintLayout constraintLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }
}
