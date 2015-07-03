package cn.adblock.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import cn.adblock.utils.FloatManager;

public class FloatWindowService extends Service {

	private Handler handler = new Handler();
	private Timer timer;
	private static boolean enable;

	@Override
	public IBinder onBind(Intent intent) {
		return new FloatWindowBinder();
	}
	
	class FloatWindowBinder extends Binder{
		public FloatWindowService getService(){
			return FloatWindowService.this;
		}
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		enable = intent.getBooleanExtra("isEnable", false);
		// 开启定时器，每隔0.5秒刷新一次
		if (timer == null) {
			timer = new Timer();
			timer.schedule(new RefreshTask(), 0, 500);
		}
		return 0;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// Service被终止的同时也停止定时器继续运行
		timer.cancel();
		timer = null;
	}

	class RefreshTask extends TimerTask {

		@Override
		public void run() {
			// 当前界面不是其他应用，且没有悬浮窗显示，则创建悬浮窗。
			boolean isOther = isOther();
			if (!isOther && !FloatManager.isWindowShowing()
					&& isEnable()) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						FloatManager.createSmallWindow(getApplicationContext());
					}
				});
			}
			// 当前界面是其他应用，且有悬浮窗显示，则移除悬浮窗。
			else if (isOther && FloatManager.isWindowShowing()
					|| !isEnable()) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						FloatManager.removeSmallWindow(getApplicationContext());
					}
				});
			}
			// else if (isHome() && FloatManager.isWindowShowing()) {
			// // 当前界面是桌面，且有悬浮窗显示，则更新内存数据。
			// handler.post(new Runnable() {
			// @Override
			// public void run() {
			// FloatManager.updateUsedPercent(getApplicationContext());
			// }
			// });
			// }
		}
	}

	/**
	 * 判断当前界面是否是其他应用
	 */
	private boolean isOther() {
		ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> rti = mActivityManager.getRunningTasks(1);
		boolean isHome = getHomes().contains(
				rti.get(0).topActivity.getPackageName());
		boolean isSelf = getPackageName().contains(
				rti.get(0).topActivity.getPackageName());
		return !(isHome || isSelf);
	}

	public boolean isEnable() {
		return enable;
	}

	/**
	 * 获得属于桌面的应用的应用包名称
	 * 
	 * @return 返回包含所有包名的字符串列表
	 */
	private List<String> getHomes() {
		List<String> names = new ArrayList<String>();
		PackageManager packageManager = this.getPackageManager();
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(
				intent, PackageManager.MATCH_DEFAULT_ONLY);
		for (ResolveInfo ri : resolveInfo) {
			names.add(ri.activityInfo.packageName);
		}
		return names;
	}

}