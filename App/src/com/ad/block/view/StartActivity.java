package com.ad.block.view;

import com.ad.block.R;
import com.ad.block.app.Constans;
import com.ad.block.utils.SharedPreferencesUtils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * TODO
 * 
 * @author Smile<lijianhy1990@gmail.com>
 * @date 2015年6月20日
 */
public class StartActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				boolean isFirst = (Boolean) SharedPreferencesUtils.getParam(
						StartActivity.this, Constans.KEY_IS_FIRST, false);
				if (isFirst) {
					startActivity(new Intent(StartActivity.this,
							GuiActivity.class));
				} else
					startActivity(new Intent(StartActivity.this,
							MainActivity.class));
			}
		}, 2000);
	}

}
