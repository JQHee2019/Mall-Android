package com.hjq.mall.utils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

/**
 * 在任何线程下都可以使用toast
 */
public class ToastUtil {
	
	private static Toast toast;
	
	public static void showToast(Context context, String text, int duration) {
		Looper myLooper = Looper.myLooper();
		if (myLooper == null) {
			Looper.prepare();
			myLooper = Looper.myLooper();
		}
		if (toast == null) {
			toast = Toast.makeText(context, text, duration);
		} else {
			toast.setText(text);
			toast.setDuration(duration);
		}
		toast.show();
		if ( myLooper != null) {
			Looper.loop();
			myLooper.quit();
		}
	}
	
	public static void showToast(Context context, String text) {
		Looper myLooper = Looper.myLooper();
		if (myLooper == null) {
			Looper.prepare();
			myLooper = Looper.myLooper();
		}
		if (toast == null) {
			toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		} else {
			toast.setText(text);
			toast.setDuration(Toast.LENGTH_SHORT);
		}
		toast.show();
		if ( myLooper != null) {
			Looper.loop();
			myLooper.quit();
		}
	}
	
	public static void showToast(Context context, int text, int duration) {
		Looper myLooper = Looper.myLooper();
		if (myLooper == null) {
			Looper.prepare();
			myLooper = Looper.myLooper();
		}
		if (toast == null) {
			toast = Toast.makeText(context, text, duration);
		} else {
			toast.setText(text);
			toast.setDuration(duration);
		}
		toast.show();
		if ( myLooper != null) {
			Looper.loop();
			myLooper.quit();
		}
	}
	
	public static void showToast(Context context, int text) {
		Looper myLooper = Looper.myLooper();
		if (myLooper == null) {
			Looper.prepare();
			myLooper = Looper.myLooper();
		}
		if (toast == null) {
			toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		} else {
			toast.setText(text);
			toast.setDuration(Toast.LENGTH_SHORT);
		}
		toast.show();
		if ( myLooper != null) {
			Looper.loop();
			myLooper.quit();
		}
	}
}
