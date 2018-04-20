package com.vadym_horiainov.simpletwitch.mvvm.live_streams.list;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vadym_horiainov.simpletwitch.databinding.ItemLiveStreamsBinding;
import com.vadym_horiainov.simpletwitch.mvvm.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class LiveStreamsAdapter extends RecyclerView.Adapter<LiveStreamsAdapter.ViewHolder> {

    private final LiveStreamsDiffUtil diffUtil = new LiveStreamsDiffUtil();
    private List<LiveStreamsItemVM> liveStreamsItems = new ArrayList<>();

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

    public void updateItems(List<LiveStreamsItemVM> items) {
        diffUtil.setOldList(new ArrayList<>(liveStreamsItems));
        Log.d("diff", "addItems: old:" + liveStreamsItems.size());
//        liveStreamsItems.addAll(items);
        diffUtil.setNewList(items);
        Log.d("diff", "addItems: new:" + items.size());
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtil);
        liveStreamsItems = new ArrayList<>(items);
        diffResult.dispatchUpdatesTo(this);
    }

    public void clearItems() {
        liveStreamsItems.clear();
        notifyDataSetChanged();
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

    private static class LiveStreamsDiffUtil extends DiffUtil.Callback {
        private List<LiveStreamsItemVM> oldList;
        private List<LiveStreamsItemVM> newList;

        void setOldList(List<LiveStreamsItemVM> oldList) {
            this.oldList = new ArrayList<>(oldList);
        }

        void setNewList(List<LiveStreamsItemVM> newList) {
            this.newList = new ArrayList<>(newList);
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).getName().equals(newList.get(newItemPosition).getName());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return areItemsTheSame(oldItemPosition, newItemPosition);
        }
    }
}
