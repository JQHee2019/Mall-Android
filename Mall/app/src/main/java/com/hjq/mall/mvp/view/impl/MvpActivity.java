package com.hjq.mall.mvp.view.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hjq.mall.mvp.presenter.impl.MvpBasePresenter;
import com.hjq.mall.mvp.view.MvpView;


/**
 * 将我们的MVP架构集成到我们的Activity
 * MvpActivity---是MVP框架的
 */
public abstract class MvpActivity<P extends MvpBasePresenter> extends AppCompatActivity implements MvpView {

    //MVP架构P是动态的
    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = bindPresenter();
        //我的presenter中介我是不是管理View---关联
        if (presenter != null){
            presenter.attachView(this);
        }
    }

    // 供外部实现
    public abstract P bindPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁的时候---解除绑定
        if (presenter != null){
            presenter.detachView();
        }
    }
}
