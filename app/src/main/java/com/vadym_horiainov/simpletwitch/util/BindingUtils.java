package com.vadym_horiainov.simpletwitch.util;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vadym_horiainov.simpletwitch.mvvm.live_streams.LiveStreamsAdapter;
import com.vadym_horiainov.simpletwitch.mvvm.live_streams.LiveStreamsItemVM;

import java.util.List;

public final class BindingUtils {

    private BindingUtils() {
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }

    @BindingAdapter({"adapter"})
    public static void addLiveStreamsItems(RecyclerView recyclerView, List<LiveStreamsItemVM> liveStreamsItemViewModels) {
        LiveStreamsAdapter adapter = (LiveStreamsAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.addItems(liveStreamsItemViewModels);
        }
    }
}
