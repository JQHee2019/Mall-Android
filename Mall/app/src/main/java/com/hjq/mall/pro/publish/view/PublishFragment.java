package com.hjq.mall.pro.publish.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hjq.mall.R;
import com.hjq.mall.mvp.presenter.impl.MvpBasePresenter;
import com.hjq.mall.pro.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublishFragment extends BaseFragment {


    public PublishFragment() {
        // Required empty public constructor
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_publish;
    }

    @Override
    public void initContentView(View viewContent) {

    }

    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }
}
