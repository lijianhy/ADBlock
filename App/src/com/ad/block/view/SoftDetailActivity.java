package com.ad.block.view;

import com.ad.block.R;
import com.ad.block.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class SoftDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soft_detail);
	}
	
	public void onBack(View v) {
		this.finish();
	}
	
}
