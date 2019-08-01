package com.hjq.mall.pro.attention.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.hjq.mall.pro.base.BaseFragment;
import com.hjq.mall.pro.base.view.tablayout.TabEntity;

import java.util.ArrayList;

public class AttentionAdapter extends FragmentPagerAdapter {

    private ArrayList<BaseFragment> mFagments;
    private ArrayList<CustomTabEntity> mTabs;

    public AttentionAdapter(FragmentManager fm, ArrayList<CustomTabEntity> mTabs, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.mFagments = fragments;
        this.mTabs = mTabs;
    }

    @Override
    public int getCount() {
        return mFagments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ((TabEntity) mTabs.get(position)).title;
    }

    @Override
    public Fragment getItem(int position) {
        return mFagments.get(position);
    }
}
