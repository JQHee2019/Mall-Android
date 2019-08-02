package com.hjq.mall.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 在任何线程下都可以使用toast
 */
public class ToastUtil {
	
	private static Toast toast;
	
	public static void showToast(final Context context, String text, int duration) {

        Activity activity = ((Activity) context);
        if (activity == null) {
            return;
        }
		if ("main".equals(Thread.currentThread().getName())) {
            getToast(activity, text, duration).show();
		} else {
            activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
                    getToast(activity, text, duration).show();
				}
			});
		}

	}

	public static void showToast(final Context context, int resId, int duration) {

		Activity activity = ((Activity) context);
		if (activity == null) {
			return;
		}
		if ("main".equals(Thread.currentThread().getName())) {
			getToast(activity, context.getString(resId), duration).show();
		} else {
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					getToast(activity, context.getString(resId), duration).show();
				}
			});
		}
	}
	
	public static void showToast(final Context context, String text) {

	    Activity activity = ((Activity) context);
	    if (activity == null) {
	        return;
        }
		if ("main".equals(Thread.currentThread().getName())) {
            getToast(activity, text, Toast.LENGTH_SHORT).show();
		} else {
            activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
                    getToast(activity, text, Toast.LENGTH_SHORT).show();
				}
			});
		}
	}

	public static void showToast(final Context context, int resId) {

        Activity activity = ((Activity) context);
        if (activity == null) {
            return;
        }
		if ("main".equals(Thread.currentThread().getName())) {
            getToast(activity, context.getString(resId), Toast.LENGTH_SHORT).show();
		} else {
            activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
                    getToast(activity, context.getString(resId), Toast.LENGTH_SHORT).show();
				}
			});
		}
	}

	private static Toast getToast(final Context context, String text, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, text, duration);
        } else {
            toast.setText(text);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        return toast;
    }
}
