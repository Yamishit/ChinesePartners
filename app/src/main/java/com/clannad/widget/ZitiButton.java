package com.clannad.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.clannad.utils.Common;

public class ZitiButton extends Button {

	public ZitiButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public ZitiButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ZitiButton(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		if (Common.getInstance().TYPE_FACE != null) {
			setTypeface(Common.getInstance().TYPE_FACE);
		}
	}
}
