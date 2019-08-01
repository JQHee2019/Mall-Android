package com.hjq.mall.pro.attention.view;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.hjq.mall.R;
import com.hjq.mall.mvp.presenter.impl.MvpBasePresenter;
import com.hjq.mall.pro.attention.view.adapter.AttentionAdapter;
import com.hjq.mall.pro.base.BaseFragment;
import com.hjq.mall.pro.base.view.tablayout.TabEntity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttentionFragment extends BaseFragment {

    private CommonTabLayout tablayout;
    private ViewPager viewPager;

    private AttentionAdapter adapter;

    private ArrayList<BaseFragment> mFagments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabs = new ArrayList<>();

    @Override
    public int getContentView() {
        return R.layout.fragment_attention;
    }

    @Override
    public void initContentView(View viewContent) {
        tablayout = viewContent.findViewById(R.id.tablayout);
        viewPager = viewContent.findViewById(R.id.view_pager);

        mFagments.add(new AttentionListFragment());
        mFagments.add(new AttentionSubscribeFragment());
        mTabs.add(new TabEntity("关注", 0, 0));
        mTabs.add(new TabEntity("订阅", 0, 0));

        //设置TabLayout的模式
        /**
        * 注：此条属性必须配合MODE_FIXED使用，不然不起作用
        * GRAVITY_FILL  让每个标签平分TabLayout的全部宽度
        * GRAVITY_CENTER  让每个标签显示自身宽度，然后所有标签居中显示
        * */
        // tablayout.setTabGravity(TabLayout.GRAVITY_CENTER);

        /**
         * MODE_FIXED  表示宽度始终是tablayout控件指定的宽度，如果标签过多，那么就无限挤压控件
         * MODE_SCROLLABLE 表示每个标签都保持自身宽度，一旦标签过多，给标题栏提供支持横向滑动的功能
         * */
       //  tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        adapter = new AttentionAdapter(getChildFragmentManager(), mTabs, mFagments);
        viewPager.setAdapter(adapter);

        //为viewPager的滑动添加监听事件
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tablayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //为tab页的点击添加监听事件
        tablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        // 设置viewpager缓存页面个数，建议：有 n 个tab就缓存 n-1 个页
        viewPager.setOffscreenPageLimit(1);
        viewPager.setCurrentItem(0);
        tablayout.setTabData(mTabs);
        tablayout.setCurrentTab(0);
    }


    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }
}
