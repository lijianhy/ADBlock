package com.ad.block.view;

import com.ad.block.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * TODO
 * @author Smile<lijianhy1990@gmail.com>
 * @date 2015年6月20日
 */
public class GuiActivity extends BaseActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				startActivity(new Intent(GuiActivity.this, MainActivity.class));
			}
		}, 2000);
	}
	
}
