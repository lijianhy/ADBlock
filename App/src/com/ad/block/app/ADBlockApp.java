package com.ad.block.app;

import com.ad.block.utils.CrashHandler;

import android.app.Application;

/**
 * TODO
 * @author Smile<lijianhy1990@gmail.com>
 * @date 2015年6月20日
 */
public class ADBlockApp extends Application{

	@Override
	public void onCreate() {
		super.onCreate();
//		CrashHandler.getInstance().init(this);
	}
	
}
