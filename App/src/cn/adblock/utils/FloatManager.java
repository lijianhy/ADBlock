package cn.adblock.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import cn.adblock.R;
import cn.adblock.view.MainActivity;

public class FloatManager {
	private static View floatWindow;
	private static LayoutParams smallWindowParams;
	private static boolean isEnable;
	/**
	 * 用于控制在屏幕上添加或移除悬浮窗
	 */
	private static WindowManager mWindowManager;

	/**
	 * 创建一个小悬浮窗。初始位置为屏幕的右部中间位置。
	 *
	 * @param context
	 *            必须为应用程序的Context.
	 */
	public static void createSmallWindow(final Context context) {
		final WindowManager windowManager = getWindowManager(context);
		int screenWidth = windowManager.getDefaultDisplay().getWidth();
		int screenHeight = windowManager.getDefaultDisplay().getHeight();
		if (floatWindow == null) {
			floatWindow = new Button(context);
			floatWindow.setBackgroundResource(R.drawable.ic_launcher);
			if (smallWindowParams == null) {
				smallWindowParams = new LayoutParams();
				smallWindowParams.type = LayoutParams.TYPE_PHONE;
				smallWindowParams.format = PixelFormat.RGBA_8888;
				smallWindowParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
						| LayoutParams.FLAG_NOT_FOCUSABLE;
				smallWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
				smallWindowParams.width = 100;
				smallWindowParams.height = 100;
				smallWindowParams.x = screenWidth;
				smallWindowParams.y = screenHeight / 2;
			}
			floatWindow.setOnTouchListener(new OnTouchListener() {
				int lastX, lastY;
				int paramX, paramY;
				boolean isMove = false;

				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						isMove = false;
						lastX = (int) event.getRawX();
						lastY = (int) event.getRawY();
						paramX = smallWindowParams.x;
						paramY = smallWindowParams.y;
						break;
					case MotionEvent.ACTION_MOVE:
						isMove = true;
						int dx = (int) event.getRawX() - lastX;
						int dy = (int) event.getRawY() - lastY;
						smallWindowParams.x = paramX + dx;
						smallWindowParams.y = paramY + dy;
						// 更新悬浮窗位置
						windowManager.updateViewLayout(floatWindow,
								smallWindowParams);
						break;
					case MotionEvent.ACTION_UP:
						if (!isMove) {
							v.performClick();
						}
						break;
					}
					return true;
				}
			});
			floatWindow.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context,
							MainActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
					context.startActivity(intent);
				}
			});
			windowManager.addView(floatWindow, smallWindowParams);
		}
	}

	/**
	 * 将小悬浮窗从屏幕上移除。
	 *
	 * @param context
	 *            必须为应用程序的Context.
	 */
	public static void removeSmallWindow(Context context) {
		if (floatWindow != null) {
			WindowManager windowManager = getWindowManager(context);
			windowManager.removeView(floatWindow);
			floatWindow = null;
		}
	}

	public static void setWindowEnable(boolean flag) {
		isEnable = flag;
	}

	/**
	 * 是否有悬浮窗(包括小悬浮窗和大悬浮窗)显示在屏幕上。
	 *
	 * @return 有悬浮窗显示在桌面上返回true，没有的话返回false。
	 */
	public static boolean isWindowShowing() {
		return floatWindow != null;
	}

	/**
	 * 如果WindowManager还未创建，则创建一个新的WindowManager返回。否则返回当前已创建的WindowManager。
	 *
	 * @param context
	 *            必须为应用程序的Context.
	 * @return WindowManager的实例，用于控制在屏幕上添加或移除悬浮窗。
	 */
	private static WindowManager getWindowManager(Context context) {
		if (mWindowManager == null) {
			mWindowManager = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);
		}
		return mWindowManager;
	}

}
