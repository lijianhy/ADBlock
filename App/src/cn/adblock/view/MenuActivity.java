package cn.adblock.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import cn.adblock.R;
import cn.adblock.app.ADBlockApp;
import cn.adblock.app.FloatWindowService;
import cn.adblock.utils.ToastUtil;
import cn.adblock.widgets.ConfirmDialog;
import cn.adblock.widgets.ConfirmDialog.ConfirmListener;

public class MenuActivity extends BaseActivity implements OnClickListener,
		ConfirmListener {

	private View viewSetting;
	private View viewMode;
	private View ViewSuggest;
	private View viewAbout;
	private View viewFeedback;
	private View viewCheckupdate;
	private Button btnLogout;

	ConfirmDialog dialog;
	int position = 0;
	View[] views = new View[6];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		dialog = new ConfirmDialog(this);
		initView();
		setListener();
		// startAnims();
	}

	private void initView() {
		viewAbout = (View) findViewById(R.id.amenu_view_about);
		viewSetting = (View) findViewById(R.id.amenu_view_setting);
		viewMode = (View) findViewById(R.id.amenu_view_mode);
		ViewSuggest = (View) findViewById(R.id.amenu_view_suggest);
		viewFeedback = (View) findViewById(R.id.amenu_view_feedback);
		viewCheckupdate = (View) findViewById(R.id.amenu_view_checkupdate);
		btnLogout = (Button) findViewById(R.id.amenu_btn_logout);
		views[0] = viewSetting;
		views[1] = viewMode;
		views[2] = ViewSuggest;
		views[3] = viewAbout;
		views[4] = viewFeedback;
		views[5] = viewCheckupdate;
		// for (View v : views) {
		// v.setVisibility(View.INVISIBLE);
		// }
	}

	private void setListener() {
		viewAbout.setOnClickListener(this);
		viewCheckupdate.setOnClickListener(this);
		viewSetting.setOnClickListener(this);
		viewMode.setOnClickListener(this);
		ViewSuggest.setOnClickListener(this);
		viewFeedback.setOnClickListener(this);
		btnLogout.setOnClickListener(this);
		dialog.setConfirmListener(this);
	}

	private void startAnims() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (position < 6) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					handler.sendEmptyMessage(position);
					position++;
				}
			}
		}).start();
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Animation inFromRight = new TranslateAnimation(
					Animation.RELATIVE_TO_PARENT,
					((msg.what + 1) / 6.0f) * 0.5f,
					Animation.RELATIVE_TO_PARENT, 0.0f,
					Animation.RELATIVE_TO_PARENT, 0.0f,
					Animation.RELATIVE_TO_PARENT, 0.0f);
			inFromRight.setDuration(300);
			inFromRight.setInterpolator(new DecelerateInterpolator());
			inFromRight.setAnimationListener(new SelfAnimationListener(
					views[msg.what]));
			views[msg.what].startAnimation(inFromRight);
		}
	};

	@Override
	public void onClick(View v) {
		int vid = v.getId();
		switch (vid) {
		case R.id.amenu_view_about:
			onAboutClick();
			break;
		case R.id.amenu_view_setting:
			onSettingClick();
			break;
		case R.id.amenu_view_mode:
			onModeClick();
			break;
		case R.id.amenu_view_suggest:
			onSuggestClick();
			break;
		case R.id.amenu_view_feedback:
			onFeedbackClick();
			break;
		case R.id.amenu_view_checkupdate:
			onCheckupdateClick();
			break;
		case R.id.amenu_btn_logout:
			onLogoutClick();
			break;
		}
	}

	private void onLogoutClick() {
		AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
		builder.setTitle("你确定要离开吗？");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				MenuActivity mContext = MenuActivity.this;
				stopService(new Intent(mContext, FloatWindowService.class));
				ADBlockApp.clearNotification(mContext);
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				android.os.Process.killProcess(android.os.Process.myPid());
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	public void onBack(View v) {
		this.finish();
	}

	private void onCheckupdateClick() {
		dialog.show();
	}

	private void onFeedbackClick() {
		startActivity(new Intent(MenuActivity.this, FeedBackActivity.class));
	}

	private void onSuggestClick() {
		startActivity(new Intent(MenuActivity.this, SoftDetailActivity.class));
	}

	private void onModeClick() {
		startActivity(new Intent(MenuActivity.this, ModeSelcetActivity.class));
	}

	private void onSettingClick() {
		startActivity(new Intent(MenuActivity.this, SettingActivity.class));
	}

	private void onAboutClick() {
		startActivity(new Intent(MenuActivity.this, AboutActivity.class));
	}

	@Override
	public void onConfirmClick() {
		ToastUtil.show(this, "已经是最新版本....");
	}

	@Override
	public void onPause() {
		super.onPause();
		ToastUtil.cancel();
	}

	class SelfAnimationListener implements AnimationListener {
		View view;

		public SelfAnimationListener(View view) {
			this.view = view;
		}

		@Override
		public void onAnimationStart(Animation animation) {
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			view.setVisibility(View.VISIBLE);
		}
	}

}
