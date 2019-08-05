package com.hjq.mall.pro.meituan.view.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 显示有多少页
 * */
public class MeituanAdapter extends PagerAdapter {

    private List<View> mViewList;

    public MeituanAdapter(List<View> mViewList) {
        this.mViewList = mViewList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return (mViewList.get(position));
    }

    @Override
    public int getCount() {
        if (mViewList == null)
            return 0;
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
