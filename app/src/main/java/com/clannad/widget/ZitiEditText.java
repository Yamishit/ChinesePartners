package com.clannad.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.clannad.utils.Common;

public class ZitiEditText extends EditText {

	public ZitiEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public ZitiEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ZitiEditText(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		if (Common.getInstance().TYPE_FACE != null) {
			setTypeface(Common.getInstance().TYPE_FACE);
		}
	}
}
