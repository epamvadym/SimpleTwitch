package com.vadym_horiainov.simpletwitch.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vadym_horiainov.simpletwitch.mvvm.live_streams.list.LiveStreamsAdapter;
import com.vadym_horiainov.simpletwitch.mvvm.live_streams.list.LiveStreamsItemVM;

import java.util.List;

public final class BindingUtils {

    private BindingUtils() {
    }

    @BindingAdapter("android:src")
    public static void setImageUri(ImageView imageView, Object image) {
        if (image instanceof String) {
            setImageUrl(imageView, (String) image);
        } else if (image instanceof Drawable) {
            imageView.setImageDrawable((Drawable) image);
        } else if (image instanceof Integer) {
            imageView.setImageResource((Integer) image);
        }
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
            adapter.updateItems(liveStreamsItemViewModels);
        }
    }
}
