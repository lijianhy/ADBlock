package com.ad.block.view;

import com.ad.block.R;
import com.ad.block.R.layout;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class AboutActivity extends Activity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
	}
	
	private void initView(){
		
	}
	
	private void setListener(){
		
	}

	@Override
	public void onClick(View v) {
		
	}

	public void onBack(View v) {
		this.finish();
	}
}
