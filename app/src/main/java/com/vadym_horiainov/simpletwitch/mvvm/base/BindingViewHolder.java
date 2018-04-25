package com.vadym_horiainov.simpletwitch.mvvm.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BindingViewHolder extends RecyclerView.ViewHolder {

    public BindingViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBind(int position);
}
