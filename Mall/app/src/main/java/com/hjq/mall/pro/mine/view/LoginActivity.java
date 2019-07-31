package com.hjq.mall.pro.mine.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hjq.mall.R;
import com.hjq.mall.mvp.presenter.impl.MvpBasePresenter;
import com.hjq.mall.pro.base.BaseActivtiy;

public class LoginActivity extends BaseActivtiy {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }
}
