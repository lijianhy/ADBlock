package com.ad.block.view;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.ad.block.R;
import com.ad.block.widgets.ShareDialog;

public class MainActivity extends Activity implements OnClickListener {

	private View viewTop;
	private View viewMenu;
	private View viewState;
	private View viewAbout;
	private View viewShare;
	private TextView textCount;

	// 0开启 1关闭
	private int state;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewTop = findViewById(R.id.amain_view_top);
		viewMenu = findViewById(R.id.amain_img_menu);
		viewState = findViewById(R.id.amain_text_state);
		viewAbout = findViewById(R.id.amain_text_about);
		viewShare = findViewById(R.id.amain_text_share);
		textCount = (TextView) findViewById(R.id.amain_text_count);
		viewMenu.setOnClickListener(this);
		viewState.setOnClickListener(this);
		viewAbout.setOnClickListener(this);
		viewShare.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.amain_img_menu:
			onMenuClick();
			break;
		case R.id.amain_text_state:
			onStateClick();
			break;
		case R.id.amain_text_about:
			onAboutClick();
			break;
		case R.id.amain_text_share:
			onShareClick();
			break;
		}
	}

	private void onMenuClick() {
		startActivity(new Intent(this, MenuActivity.class));
	}

	private void onStateClick() {
		ValueAnimator va = ObjectAnimator.ofInt(0, 62261);
		va.setDuration(2000);
		va.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				textCount.setText(String.format("%06d",
						animation.getAnimatedValue()));
			}
		});
		if (state == 0) {
			((TextView) viewState).setText("未开启过滤");
			ValueAnimator colorAnim = ObjectAnimator.ofInt(viewTop,
					"backgroundColor", Color.parseColor("#00cd00"), Color.RED);
			colorAnim.setDuration(2000);
			colorAnim.setEvaluator(new ArgbEvaluator());
			colorAnim.start();
		} else {
			((TextView) viewState).setText("已开启过滤");
			ValueAnimator colorAnim = ObjectAnimator.ofInt(viewTop,
					"backgroundColor", Color.RED, Color.parseColor("#00cd00"));
			colorAnim.setDuration(2000);
			colorAnim.setEvaluator(new ArgbEvaluator());
			colorAnim.start();
		}
		state = state ^ 1;
		va.start();
	}

	private void onAboutClick() {
		startActivity(new Intent(this, AboutActivity.class));
	}

	private void onShareClick() {
		new ShareDialog(this).show();
	}

}
