package com.ad.block.view;

import android.app.Activity;

import com.umeng.analytics.MobclickAgent;

/**
 * TODO
 * 
 * @author Smile<lijianhy1990@gmail.com>
 * @date 2015年6月20日
 */
public class BaseActivity extends Activity {

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
