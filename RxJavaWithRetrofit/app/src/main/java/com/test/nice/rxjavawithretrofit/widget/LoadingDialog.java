package com.test.nice.rxjavawithretrofit.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.test.nice.rxjavawithretrofit.R;


/**
 * Created by xingge on 2017/5/17.
 */

public class LoadingDialog extends Dialog{
	private Context context;
	private ImageView imagLoading;
	private Animation operatingAnim;

	public LoadingDialog(Context context) {
		super(context, R.style.loading_dialog);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		imagLoading = (ImageView)findViewById(R.id.loading);
		operatingAnim = AnimationUtils.loadAnimation(context, R.anim.circle);
		LinearInterpolator lin = new LinearInterpolator();
		operatingAnim.setInterpolator(lin);
		operatingAnim.setDuration(2000);

		imagLoading.startAnimation(operatingAnim);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		if (imagLoading != null) {
			imagLoading.startAnimation(operatingAnim);
		}
		super.show();
	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		if (imagLoading != null) {
			imagLoading.clearAnimation();
		}
		super.dismiss();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction()==MotionEvent.ACTION_MOVE) {
			dismiss();
			return true;
		}
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			dismiss();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
