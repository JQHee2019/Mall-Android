package com.hjq.mall.mvp.presenter;


import com.hjq.mall.mvp.view.MvpView;

public interface MvpPresenter<V extends MvpView>  {
    public void attachView(V view);
    public void detachView();
}
