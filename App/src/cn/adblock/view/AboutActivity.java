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

public class AboutActivity extends BaseActivity implements OnClickListener {
	
	private View viewBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		initView();
		setListener();
	}
	
	private void initView(){
		viewBack = (View) findViewById(R.id.about_view_back);
	}
	
	private void setListener(){
		viewBack.setOnClickListener(this);
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

	public void onBack(View v) {
		this.finish();
	}
}
