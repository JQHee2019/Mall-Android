package com.hjq.mall.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

/**
 * 在任何线程下都可以使用toast
 */
public class ToastUtil {
	
	private static Toast toast;
	
	public static void showToast(final Context context, String text, int duration) {

		if ("main".equals(Thread.currentThread().getName())) {
            if (toast == null) {
                toast = Toast.makeText(context, text, duration);
            } else {
                toast.setText(text);
                toast.setDuration(duration);
            }
            toast.show();
		} else {
			((Activity) context).runOnUiThread(new Runnable() {
				@Override
				public void run() {
                    if (toast == null) {
                        toast = Toast.makeText(context, text, duration);
                    } else {
                        toast.setText(text);
                        toast.setDuration(duration);
                    }
                    toast.show();
				}
			});
		}

	}
	
	public static void showToast(final Context context, String text) {

		if ("main".equals(Thread.currentThread().getName())) {
            if (toast == null) {
                toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            } else {
                toast.setText(text);
                toast.setDuration(Toast.LENGTH_SHORT);
            }
            toast.show();
		} else {
			((Activity) context).runOnUiThread(new Runnable() {
				@Override
				public void run() {
                    if (toast == null) {
                        toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                    } else {
                        toast.setText(text);
                        toast.setDuration(Toast.LENGTH_SHORT);
                    }
                    toast.show();
				}
			});
		}
	}
	
	public static void showToast(final Context context, int text, int duration) {

		if ("main".equals(Thread.currentThread().getName())) {
            if (toast == null) {
                toast = Toast.makeText(context, text, duration);
            } else {
                toast.setText(text);
                toast.setDuration(duration);
            }
			toast.show();
		} else {
			((Activity) context).runOnUiThread(new Runnable() {
				@Override
				public void run() {
                    if (toast == null) {
                        toast = Toast.makeText(context, text, duration);
                    } else {
                        toast.setText(text);
                        toast.setDuration(duration);
                    }
                    toast.show();
				}
			});
		}
	}
	
	public static void showToast(final Context context, int text) {

		if ("main".equals(Thread.currentThread().getName())) {
            if (toast == null) {
                toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            } else {
                toast.setText(text);
                toast.setDuration(Toast.LENGTH_SHORT);
            }
			toast.show();
		} else {
			((Activity) context).runOnUiThread(new Runnable() {
				@Override
				public void run() {
                    if (toast == null) {
                        toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                    } else {
                        toast.setText(text);
                        toast.setDuration(Toast.LENGTH_SHORT);
                    }
					toast.show();
				}
			});
		}
	}
}
