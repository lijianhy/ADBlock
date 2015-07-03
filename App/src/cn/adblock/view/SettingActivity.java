package cn.adblock.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import cn.adblock.R;
import cn.adblock.app.Constans;
import cn.adblock.app.FloatWindowService;
import cn.adblock.utils.SharedPreferencesUtils;

import com.zcw.togglebutton.ToggleButton;
import com.zcw.togglebutton.ToggleButton.OnToggleChanged;

public class SettingActivity extends BaseActivity implements OnClickListener {
	
	private View viewBack;
	private ToggleButton csbNotice;
	private ToggleButton csbDesktop;
	private ToggleButton csbStart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initView();
		setListener();
		csbNotice.toggle();
		csbDesktop.toggle();
		csbStart.toggle();
	}
	
	private void initView(){
		viewBack = (View) findViewById(R.id.about_view_back);
		csbNotice = (ToggleButton) findViewById(R.id.as_switchbutton_notice);
		csbDesktop = (ToggleButton) findViewById(R.id.as_switchbutton_desktop);
		csbStart = (ToggleButton) findViewById(R.id.as_switchbutton_start);
	}
	
	private void setListener(){
		viewBack.setOnClickListener(this);
		csbDesktop.setOnToggleChanged(new OnToggleChanged() {
			
			@Override
			public void onToggle(boolean on) {
				SharedPreferencesUtils.setParam(SettingActivity.this, Constans.KEY_IS_FLOAT, on);
				Intent intent = new Intent(SettingActivity.this, FloatWindowService.class);
				intent.putExtra("isEnable", on);
				startService(intent);
			}
		});
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

}
