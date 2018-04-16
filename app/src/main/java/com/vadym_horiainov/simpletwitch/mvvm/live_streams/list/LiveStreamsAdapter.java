package com.vadym_horiainov.simpletwitch.mvvm.live_streams.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vadym_horiainov.simpletwitch.databinding.ItemLiveStreamsBinding;
import com.vadym_horiainov.simpletwitch.mvvm.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class LiveStreamsAdapter extends RecyclerView.Adapter<LiveStreamsAdapter.ViewHolder> {

    private final List<LiveStreamsItemVM> liveStreamsItems = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLiveStreamsBinding itemLiveStreamsBinding = ItemLiveStreamsBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemLiveStreamsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return liveStreamsItems.size();
    }

    public void addItems(List<LiveStreamsItemVM> items) {
        liveStreamsItems.addAll(items);
        notifyDataSetChanged();
    }

    public void clearItems() {
        liveStreamsItems.clear();
    }


    class ViewHolder extends BaseViewHolder {
        private final ItemLiveStreamsBinding binding;

        ViewHolder(ItemLiveStreamsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            final LiveStreamsItemVM liveStreamsItemVM = liveStreamsItems.get(position);
            binding.setViewModel(liveStreamsItemVM);
            binding.executePendingBindings();
        }
    }
}
