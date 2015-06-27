package cn.adblock.view;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.adblock.R;
import cn.adblock.widgets.ShareDialog;

public class MainActivity extends BaseActivity implements OnClickListener {

	private View viewTop;
	private View viewMenu;
	private View viewState;
	private View viewAbout;
	private View viewShare;
	private View viewCircleOut;
	private ImageView imgState;
	private TextView textState;
	private TextView textCount;

	// 0开启 1关闭
	private int state;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewTop = findViewById(R.id.amain_view_top);
		viewMenu = findViewById(R.id.amain_img_menu);
		viewState = findViewById(R.id.amain_view_state);
		viewAbout = findViewById(R.id.amain_text_about);
		viewShare = findViewById(R.id.amain_text_share);
		viewCircleOut = findViewById(R.id.amain_view_circleout);
		textState = (TextView) findViewById(R.id.amain_text_state);
		textCount = (TextView) findViewById(R.id.amain_text_count);
		imgState = (ImageView) findViewById(R.id.amain_img_state);
		viewMenu.setOnClickListener(this);
		viewState.setOnClickListener(this);
		viewAbout.setOnClickListener(this);
		viewShare.setOnClickListener(this);
		((AnimationDrawable) imgState.getDrawable()).selectDrawable(0);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.amain_img_menu:
			onMenuClick();
			break;
		case R.id.amain_view_state:
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
		final int dp10 = dip2px(this, 10);
		ValueAnimator va = ObjectAnimator.ofInt(0, 62261);
		va.setDuration(2000);
		va.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				textCount.setText(String.format("%06d",
						animation.getAnimatedValue()));
			}
		});
		ValueAnimator sc = ObjectAnimator
				.ofInt(dp10 * 24, dp10 * 19, dp10 * 24);
		sc.setDuration(2000);
		sc.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				viewCircleOut.setScaleX(((int) animation.getAnimatedValue())
						* 1.0f / (24 * dp10));
				viewCircleOut.setScaleY(((int) animation.getAnimatedValue())
						* 1.0f / (24 * dp10));
			}
		});
		AnimationDrawable animationDrawable = (AnimationDrawable) imgState
				.getDrawable();
		if (state == 0) {
			textState.setText("未开启过滤");
			ValueAnimator colorAnim = ObjectAnimator.ofInt(viewTop,
					"backgroundColor", Color.parseColor("#00cd00"), Color.RED);
			colorAnim.setDuration(2000);
			colorAnim.setEvaluator(new ArgbEvaluator());
			colorAnim.start();
			imgState.setVisibility(View.VISIBLE);
			animationDrawable.stop();
			animationDrawable.selectDrawable(0);
		} else {
			textState.setText("已开启过滤");
			ValueAnimator colorAnim = ObjectAnimator.ofInt(viewTop,
					"backgroundColor", Color.RED, Color.parseColor("#00cd00"));
			colorAnim.setDuration(2000);
			colorAnim.setEvaluator(new ArgbEvaluator());
			colorAnim.start();
			animationDrawable.start();
			imgState.postDelayed(new Runnable() {
				@Override
				public void run() {
					imgState.setVisibility(View.INVISIBLE);
				}
			}, 600);
		}
		state = state ^ 1;
		sc.start();
		va.start();
	}

	private void onAboutClick() {
		startActivity(new Intent(this, SoftDetailActivity.class));
	}

	private void onShareClick() {
		new ShareDialog(this).show();
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

}
