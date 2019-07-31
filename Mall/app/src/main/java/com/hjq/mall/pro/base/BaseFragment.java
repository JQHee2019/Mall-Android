package com.hjq.mall.pro.base;

import android.app.usage.UsageEvents;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hjq.mall.mvp.presenter.impl.MvpBasePresenter;
import com.hjq.mall.mvp.view.impl.MvpFragment;
import com.hjq.mall.utils.eventbus.EventBusUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 *
 */
public abstract class BaseFragment<P extends MvpBasePresenter> extends MvpFragment<P>{
    //我们自己的Fragment需要缓存视图
    private View viewContent;//缓存视图
    private boolean isInit;
    private Unbinder mUnBinder = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isRegisterEventBus()) {//注册EventBus
            EventBusUtil.register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        if (isRegisterEventBus()) {//注销EventBus
            EventBusUtil.unregister(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (viewContent == null){
            viewContent = inflater.inflate(getContentView(),container,false);
            initContentView(viewContent);
        }

        //判断Fragment对应的Activity是否存在这个视图
        ViewGroup parent = (ViewGroup) viewContent.getParent();
        if (parent != null){
            //如果存在,那么我就干掉,重写添加,这样的方式我们就可以缓存视图
            parent.removeView(viewContent);
        }
        mUnBinder = ButterKnife.bind(this,viewContent);
        return viewContent;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isInit){
            this.isInit = true;
            initData();
        }
    }

    @Override
    public P bindPresenter() {
        return null;
    }

    public void initData(){

    }


    public abstract int getContentView();


    public abstract void initContentView(View viewContent);


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