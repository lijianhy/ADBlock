package cn.adblock.view;

import cn.adblock.R;
import cn.adblock.R.layout;
import cn.adblock.widgets.CheckSwitchButton;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

public class SettingActivity extends BaseActivity implements OnClickListener {
	
	private View viewBack;
	private CheckSwitchButton csbNotice;
	private CheckSwitchButton csbDesktop;
	private CheckSwitchButton csbStart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initView();
		setListener();
	}
	
	private void initView(){
		viewBack = (View) findViewById(R.id.about_view_back);
		csbNotice = (CheckSwitchButton) findViewById(R.id.as_switchbutton_notice);
		csbDesktop = (CheckSwitchButton) findViewById(R.id.as_switchbutton_desktop);
		csbStart = (CheckSwitchButton) findViewById(R.id.as_switchbutton_start);
	}
	
	private void setListener(){
		viewBack.setOnClickListener(this);
	}
	
	public void onBack(View v) {
		this.finish();
	}

	@Override
	public void onClick(View v) {
		int vid = v.getId();
		switch (vid) {
		case R.id.about_view_back:
			finish();
			break;
		}
	}
	
	public void checkStatus(){
		csbDesktop.setChecked(csbDesktop.isChecked());
	}
	
}
