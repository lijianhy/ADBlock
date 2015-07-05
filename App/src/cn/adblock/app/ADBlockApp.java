package cn.adblock.app;

import android.app.Application;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import cn.adblock.R;
import cn.adblock.utils.SharedPreferencesUtils;
import cn.adblock.view.MainActivity;

/**
 * TODO
 * 
 * @author Smile<lijianhy1990@gmail.com>
 * @date 2015年6月20日
 */
public class ADBlockApp extends Application {

	public static final int NOTIFY_ID = 6666;

	@Override
	public void onCreate() {
		super.onCreate();
		boolean isFloat = (Boolean) SharedPreferencesUtils.getParam(this,
				Constans.KEY_IS_FLOAT, false);
		boolean isNotice = (Boolean) SharedPreferencesUtils.getParam(this,
				Constans.KEY_IS_NOTICE, false);
		ADBlockApp.onNoticeSetting(getApplicationContext(), isNotice);
		Intent intent = new Intent(this, FloatWindowService.class);
		intent.putExtra("isEnable", isFloat);
		startService(intent);
		// CrashHandler.getInstance().init(this);
	}

	public static void createNotifiy(Context context) {
		NotificationManager nm = (NotificationManager) context
				.getSystemService(NOTIFICATION_SERVICE);
		Intent notificationIntent = new Intent(context, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				notificationIntent, 0);

		Builder builder = new Notification.Builder(context);
		builder.setContentIntent(contentIntent)
				.setSmallIcon(R.drawable.logo_float)
				// 设置状态栏里面的图标（小图标）
				.setLargeIcon(
						BitmapFactory.decodeResource(context.getResources(),
								R.drawable.ic_launcher_h))
				// //设置状态栏的显示的信息
				.setWhen(System.currentTimeMillis())// 设置时间发生时间
				.setAutoCancel(false)// 设置不可以清除
				.setOngoing(true).setSound(null).setContentTitle("ADBlock正在运行")// 设置下拉列表里的标题
				.setContentText("运行中");// 设置上下文内容
		Notification n = builder.getNotification();// 获取一个Notification
		nm.notify(NOTIFY_ID, n);// 显示通知
	}

	public static void clearNotification(Context context) {
		// 启动后删除之前我们定义的通知
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(NOTIFICATION_SERVICE);
		notificationManager.cancel(NOTIFY_ID);

	}

	public static void onNoticeSetting(Context context, boolean on) {
		if (on) {
			createNotifiy(context);
		} else {
			clearNotification(context);
		}
	}
}
