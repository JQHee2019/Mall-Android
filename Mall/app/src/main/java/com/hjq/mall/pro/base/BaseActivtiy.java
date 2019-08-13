package com.hjq.mall.pro.base;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.hjq.mall.mvp.presenter.impl.MvpBasePresenter;
import com.hjq.mall.mvp.view.impl.MvpActivity;
import com.hjq.mall.utils.eventbus.EventBusUtil;
import com.hjq.mall.utils.permission.PermissionManager;

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
        beforeSetContentView();
        if (getLayout() instanceof Integer) {
            setContentView((Integer) getLayout());
        } else if (getLayout() instanceof  View) {
            setContentView((View) getLayout());
        } else {
            throw new ClassCastException("getLayout() type must be int or View");
        }
        initData();
        // 绑定必须要初始化View
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

    protected abstract Object getLayout();

    public  void beforeSetContentView() {

    }

    public void initData() {

    }

    /**
     * 点击空白区域隐藏键盘.
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {  //把操作放在用户点击的时候
            View v = getCurrentFocus();      //得到当前页面的焦点,ps:有输入框的页面焦点一般会被输入框占据
            if (isShouldHideKeyboard(v, ev)) { //判断用户点击的是否是输入框以外的区域
                hideKeyboard(v.getWindowToken());   //收起键盘
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {  //判断得到的焦点控件是否包含EditText
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],    //得到输入框在屏幕中上下左右的位置
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击位置如果是EditText的区域，忽略它，不收起键盘。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionManager.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
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
