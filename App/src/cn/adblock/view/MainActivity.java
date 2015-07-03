package cn.adblock.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.adblock.R;
import cn.adblock.app.Constans;
import cn.adblock.app.FloatWindowService;
import cn.adblock.utils.SharedPreferencesUtils;
import cn.adblock.widgets.ShareDialog;

import com.umeng.socialize.utils.Log;

public class MainActivity extends BaseActivity implements OnClickListener {

	private View viewTop;
	private View viewMenu;
	private View viewState;
	private View viewAbout;
	private View viewShare;
	private View viewCircleOut;
	private View viewCircle;
	private ImageView imgState;
	private TextView textState;
	private TextView textCount;

	// 0开启 1关闭
	private int state = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("tag", "oncreate================");
		boolean isFloat = (Boolean) SharedPreferencesUtils.getParam(this,
				Constans.KEY_IS_FLOAT, false);
		Intent intent = new Intent(this, FloatWindowService.class);
		intent.putExtra("isEnable", isFloat);
		startService(intent);
		setContentView(R.layout.activity_main);
		viewTop = findViewById(R.id.amain_view_top);
		viewMenu = findViewById(R.id.amain_img_menu);
		viewState = findViewById(R.id.amain_view_state);
		viewAbout = findViewById(R.id.amain_text_about);
		viewShare = findViewById(R.id.amain_text_share);
		viewCircle = findViewById(R.id.amain_view_circle);
		viewCircleOut = findViewById(R.id.amain_view_circleout);
		textState = (TextView) findViewById(R.id.amain_text_state);
		textCount = (TextView) findViewById(R.id.amain_text_count);
		imgState = (ImageView) findViewById(R.id.amain_img_state);
		viewMenu.setOnClickListener(this);
		viewState.setOnClickListener(this);
		viewAbout.setOnClickListener(this);
		viewShare.setOnClickListener(this);
		viewCircle.setOnClickListener(this);
		AnimationDrawable drawable = (AnimationDrawable) imgState.getDrawable();
		drawable.stop();
		drawable.selectDrawable(0);
		setState((Boolean) SharedPreferencesUtils.getParam(this,
				Constans.KEY_IS_OPEN, false));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.amain_img_menu:
			onMenuClick();
			break;
		case R.id.amain_view_state:
		case R.id.amain_view_circle:
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
				.ofInt(dp10 * 26, dp10 * 20, dp10 * 26);
		sc.setDuration(2000);
		sc.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				int animatedValue = (Integer) animation.getAnimatedValue();
				viewCircleOut.setScaleX(animatedValue * 1.0f / (26 * dp10));
				viewCircleOut.setScaleY(animatedValue * 1.0f / (26 * dp10));
			}
		});
		AnimationDrawable animationDrawable = (AnimationDrawable) imgState
				.getDrawable();
		if (state == 0) {
			setState(false);
			animationDrawable.stop();
			animationDrawable.selectDrawable(0);
		} else {
			setState(true);
			animationDrawable.start();
		}
		sc.start();
		va.start();
	}

	private void setState(boolean b) {
		state = b ? 0 : 1;
		SharedPreferencesUtils.setParam(this, Constans.KEY_IS_OPEN, b);
		textState.setText("已开启过滤");
		viewTop.setBackgroundResource(b ? R.drawable.bg_main_green
				: R.drawable.bg_main);
		viewCircle.setBackgroundResource(b ? R.drawable.oval_green
				: R.drawable.oval_red);
		textState.setText(b ? "已开启过滤" : "未开启过滤");
		if (b) {
			imgState.postDelayed(new Runnable() {
				@Override
				public void run() {
					imgState.setVisibility(View.INVISIBLE);
				}
			}, 600);
		} else
			imgState.setVisibility(View.VISIBLE);
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

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			moveTaskToBack(false);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

}
