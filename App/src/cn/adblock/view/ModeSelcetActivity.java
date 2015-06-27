package cn.adblock.view;

import cn.adblock.R;
import cn.adblock.R.layout;
import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

public class ModeSelcetActivity extends BaseActivity implements OnClickListener {
	
	private RadioButton btnVpn;
	private RadioButton btnRoot;
	private View viewBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mode_selcet);
		initView();
		setListener();
	}
	
	public void onBack(View v) {
		this.finish();
	}
	
	private void initView(){
		btnRoot = (RadioButton) findViewById(R.id.ams_btn_root);
		btnVpn = (RadioButton) findViewById(R.id.ams_btn_vpn);
		viewBack = (View) findViewById(R.id.about_view_back);
	}
	
	private void setListener(){
		btnRoot.setOnClickListener(this);
		btnVpn.setOnClickListener(this);
		viewBack.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		int vid = v.getId();
		switch (vid) {
		case R.id.ams_btn_root:
			btnRoot.setChecked(true);
			btnVpn.setChecked(false);
			break;
		case R.id.ams_btn_vpn:
			btnVpn.setChecked(true);
			btnRoot.setChecked(false);
			break;
		case R.id.about_view_back:
			finish();
		}
	}
	
}
