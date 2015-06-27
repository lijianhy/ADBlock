package cn.adblock.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Smile<lijian@adeaz.com>
 * @date 2015年4月10日
 */
public class SharedPreferencesUtils {
	/**
	 * 保存在手机里面的文件名
	 */
	private static final String FILE_NAME = "ipk_app";

	/**
	 * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
	 * 
	 * @param context
	 * @param key
	 * @param object
	 */
	public static void setParam(Context context, String key, Object object) {

		if (object == null || key == null) {
			return;
		}
		String type = object.getClass().getSimpleName();
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();

		if (String.class.getSimpleName().equals(type)) {
			editor.putString(key, (String) object);
		} else if (Integer.class.getSimpleName().equals(type)) {
			editor.putInt(key, (Integer) object);
		} else if (Boolean.class.getSimpleName().equals(type)) {
			editor.putBoolean(key, (Boolean) object);
		} else if (Float.class.getSimpleName().equals(type)) {
			editor.putFloat(key, (Float) object);
		} else if (Double.class.getSimpleName().equals(type)) {
			editor.putFloat(key, Float.parseFloat(String.valueOf(object)));
		} else if (Long.class.getSimpleName().equals(type)) {
			editor.putLong(key, (Long) object);
		}
		editor.commit();
	}

	/**
	 * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
	 * 
	 * @param context
	 * @param key
	 * @param defaultObject
	 * @return
	 */
	public static Object getParam(Context context, String key,
			Object defaultObject) {
		String type = defaultObject.getClass().getSimpleName();
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);

		if (String.class.getSimpleName().equals(type)) {
			return sp.getString(key, (String) defaultObject);
		} else if (Integer.class.getSimpleName().equals(type)) {
			return sp.getInt(key, (Integer) defaultObject);
		} else if (Boolean.class.getSimpleName().equals(type)) {
			return sp.getBoolean(key, (Boolean) defaultObject);
		} else if (Float.class.getSimpleName().equals(type)) {
			return sp.getFloat(key, (Float) defaultObject);
		} else if (Double.class.getSimpleName().equals(type)) {
			float result = sp.getFloat(key,
					Float.parseFloat(String.valueOf(defaultObject)));
			return Double.parseDouble(String.valueOf(result));
		} else if (Long.class.getSimpleName().equals(type)) {
			return sp.getLong(key, (Long) defaultObject);
		}
		return null;
	}
}
