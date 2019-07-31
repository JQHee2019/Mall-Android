package com.hjq.mall.pro.base;

import android.app.usage.UsageEvents;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hjq.mall.mvp.presenter.impl.MvpBasePresenter;
import com.hjq.mall.mvp.view.impl.MvpActivity;
import com.hjq.mall.utils.eventbus.EventBusUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * BaseActivtiy---是我们项目的activity
 */
public abstract class BaseActivtiy<P extends MvpBasePresenter> extends MvpActivity<P> {

    private Unbinder mUnBinder = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUnBinder = ButterKnife.bind(this);
        if (isRegisterEventBus()) {//注册EventBus
            EventBusUtil.register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        if (isRegisterEventBus()) {//注销EventBus
            EventBusUtil.unregister(this);
        }
    }

    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    //当然接收后执行模式有好几种，详细请查看源码，这个主要使用主线程的
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(UsageEvents.Event event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusCome(UsageEvents.Event event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
    }

    /** 一般情况下，发布者将事件发出，如果没有对该事件感兴趣的订阅者，那么这条消息就消失了；
     * 而粘性事件则允许，在订阅者后来注册到事件中心，还能收到该事件
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected void receiveEvent(UsageEvents.Event event) {

    }

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     */
    protected void receiveStickyEvent(UsageEvents.Event event) {

    }

}
