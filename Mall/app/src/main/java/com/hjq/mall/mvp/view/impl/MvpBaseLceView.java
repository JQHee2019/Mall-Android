package com.hjq.mall.mvp.view.impl;

/**
 *
 */
public abstract class MvpBaseLceView<M> implements MvpLceView<M>{

    @Override
    public void showLoading(boolean pullToRefresh) {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showError(Exception e, boolean pullToRefresh) {

    }

    @Override
    public void showData(M data) {

    }
}
