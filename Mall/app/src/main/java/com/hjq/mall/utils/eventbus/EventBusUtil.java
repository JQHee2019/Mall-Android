package com.hjq.mall.utils.eventbus;

import android.app.usage.UsageEvents;

import org.greenrobot.eventbus.EventBus;

public class EventBusUtil {

    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void sendEvent(UsageEvents.Event event) {
        EventBus.getDefault().post(event);
    }

    public static void sendStickyEvent(UsageEvents.Event event) {
        EventBus.getDefault().postSticky(event);
    }

}
