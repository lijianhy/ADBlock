package com.ad.block.view;

import com.ad.block.R;
import com.ad.block.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends Activity implements OnClickListener {
	
	private View viewSetting;
	private View viewMode;
	private View ViewSuggest;
	private View viewAbout;
	private View viewFeedback;
	private View viewCheckupdate;
	private Button btnLogout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		initView();
		setListener();
	}
	
	private void initView(){
		viewAbout = (View) findViewById(R.id.amenu_view_about);
		viewSetting = (View) findViewById(R.id.amenu_view_setting);
		viewMode = (View) findViewById(R.id.amenu_view_mode);
		ViewSuggest = (View) findViewById(R.id.amenu_view_suggest);
		viewFeedback = (View) findViewById(R.id.amenu_view_feedback);
		viewCheckupdate = (View) findViewById(R.id.amenu_view_checkupdate);
		btnLogout = (Button) findViewById(R.id.amenu_btn_logout);
	}
	
	private void setListener(){
		viewAbout.setOnClickListener(this);
		viewCheckupdate.setOnClickListener(this);
		viewSetting.setOnClickListener(this);
		viewMode.setOnClickListener(this);
		ViewSuggest.setOnClickListener(this);
		viewFeedback.setOnClickListener(this);
		btnLogout.setOnClickListener(this);
	}

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
		}
	}
	
	private void onLogoutClick() {
		
	}

	public void onBack(View v) {
		this.finish();
	}

	private void onCheckupdateClick() {
		
	}

	private void onFeedbackClick() {
		startActivity(new Intent(MenuActivity.this,FeedBackActivity.class));
	}

	private void onSuggestClick() {
		startActivity(new Intent(MenuActivity.this,SoftDetailActivity.class));
	}

	private void onModeClick() {
		startActivity(new Intent(MenuActivity.this,ModeSelcetActivity.class));
	}

	private void onSettingClick() {
		startActivity(new Intent(MenuActivity.this,SettingActivity.class));
	}

	private void onAboutClick() {
		startActivity(new Intent(MenuActivity.this,AboutActivity.class));
	}
	
}
