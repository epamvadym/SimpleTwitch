package com.vadym_horiainov.simpletwitch.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.VideoView;

public class SimpleVideoView extends VideoView {

	public SimpleVideoView(Context context) {
		super(context);
	}

	public SimpleVideoView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SimpleVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public SimpleVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		getHolder().setSizeFromLayout();
	}
}
