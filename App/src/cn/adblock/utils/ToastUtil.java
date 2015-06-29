package cn.adblock.utils;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import cn.adblock.R;

public class ToastUtil {
	public static final int LENGTH_SHORT = android.widget.Toast.LENGTH_SHORT;
	public static final int LENGTH_LONG = android.widget.Toast.LENGTH_LONG;

	private static android.widget.Toast toast;
	private static Handler handler = new Handler();

	private static Runnable run = new Runnable() {
		public void run() {
			toast.cancel();
		}
	};
	
	public static void cancel(){
		if (null != toast) {
			toast.cancel();
		}
	}

	private static void toast(Context ctx, CharSequence msg, int duration) {
		handler.removeCallbacks(run);
		// handler的duration不能直接对应Toast的常量时长，在此针对Toast的常量相应定义时长
		switch (duration) {
		case LENGTH_SHORT:// Toast.LENGTH_SHORT值为0，对应的持续时间大概为1s
			duration = 1000;
			break;
		case LENGTH_LONG:// Toast.LENGTH_LONG值为1，对应的持续时间大概为3s
			duration = 3000;
			break;
		default:
			break;
		}
		cancel();
		toast = getToast(ctx, msg.toString());
		handler.postDelayed(run, duration);
		toast.show();
	}

	/**
	 * 弹出Toast
	 * 
	 * @param ctx
	 *            弹出Toast的上下文
	 * @param msg
	 *            弹出Toast的内容
	 */
	public static void show(Context ctx, CharSequence msg)
			throws NullPointerException {
		if (null == ctx) {
			throw new NullPointerException("The ctx is null!");
		}
		toast(ctx, msg, LENGTH_SHORT);
	}

	/**
	 * 弹出Toast
	 * 
	 * @param ctx
	 *            弹出Toast的上下文
	 * @param msg
	 *            弹出Toast的内容
	 * @param duration
	 *            弹出Toast的持续时间
	 */
	public static void show(Context ctx, CharSequence msg, int duration)
			throws NullPointerException {
		if (null == ctx) {
			throw new NullPointerException("The ctx is null!");
		}
		if (0 > duration) {
			duration = LENGTH_SHORT;
		}
		toast(ctx, msg, duration);
	}

	/**
	 * 弹出Toast
	 * 
	 * @param ctx
	 *            弹出Toast的上下文
	 * @param msg
	 *            弹出Toast的内容的资源ID
	 * @param duration
	 *            弹出Toast的持续时间
	 */
	public static void show(Context ctx, int resId, int duration)
			throws NullPointerException {
		if (null == ctx) {
			throw new NullPointerException("The ctx is null!");
		}
		if (0 > duration) {
			duration = LENGTH_SHORT;
		}
		toast(ctx, ctx.getResources().getString(resId), duration);
	}

	public static Toast getToast(Context context, String string) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View layout = inflater.inflate(R.layout.view_toast, null);
		TextView text = (TextView) layout.findViewById(R.id.text);
		text.setText(string);
		Toast toast = new Toast(context);
		// 设置Toast显示位置(起点位置,水平向右位移,垂直向下位移)
		// toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 1150);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(layout);
		return toast;
	}
}
