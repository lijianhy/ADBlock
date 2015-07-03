package cn.adblock.widgets;

import cn.adblock.R;
import cn.adblock.view.MainActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

public class FloatView {
	private WindowManager wm;
	private WindowManager.LayoutParams params;
	private Button btn_floatView;
	private boolean isAdded;

	public FloatView(Context context) {
		createFloatView(context);
	}

	public void createFloatView(final Context context) {
		if (btn_floatView == null) {
			btn_floatView = new Button(context);
			btn_floatView.setBackgroundResource(R.drawable.ic_launcher);
		}

		wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		params = new LayoutParams();
		// 设置window type
		params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
		/*
		 * 如果设置为params.type = WindowManager.LayoutParams.TYPE_PHONE; 那么优先级会降低一些,
		 * 即拉下通知栏不可见
		 */
		params.format = PixelFormat.RGBA_8888; // 设置图片格式，效果为背景透明
		// 设置Window flag
		params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
				| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		/*
		 * 下面的flags属性的效果形同“锁定”。 悬浮窗不可触摸，不接受任何事件,同时不影响后面的事件响应。
		 * wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL |
		 * LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_NOT_TOUCHABLE;
		 */
		// 设置悬浮窗的长得宽
		params.width = 100;
		params.height = 100;

		// 设置悬浮窗的Touch监听
		btn_floatView.setOnTouchListener(new OnTouchListener() {
			int lastX, lastY;
			int paramX, paramY;

			public boolean onTouch(View v, MotionEvent event) {
				boolean isMove = false;
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					lastX = (int) event.getRawX();
					lastY = (int) event.getRawY();
					paramX = params.x;
					paramY = params.y;
					break;
				case MotionEvent.ACTION_MOVE:
					isMove = true;
					int dx = (int) event.getRawX() - lastX;
					int dy = (int) event.getRawY() - lastY;
					params.x = paramX + dx;
					params.y = paramY + dy;
					// 更新悬浮窗位置
					wm.updateViewLayout(btn_floatView, params);
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
		btn_floatView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				context.startActivity(new Intent(context, MainActivity.class));
			}
		});
		wm.addView(btn_floatView, params);
		isAdded = true;
	}
}
