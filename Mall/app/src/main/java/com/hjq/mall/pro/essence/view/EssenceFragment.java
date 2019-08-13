package com.hjq.mall.pro.essence.view;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.hjq.mall.R;
import com.hjq.mall.mvp.presenter.impl.MvpBasePresenter;
import com.hjq.mall.pro.base.BaseFragment;
import com.hjq.mall.pro.base.view.navigation.CommonNavigationBuilder;
import com.hjq.mall.pro.essence.view.adapter.EssenceAdapter;
import com.hjq.mall.pro.essence.view.navigation.EssenceNavigationBuilder;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class EssenceFragment extends BaseFragment {

    private TabLayout mTabEssence;
    private ViewPager mVpEssence;


    @Override
    public int getContentView() {
        return R.layout.fragment_essence;
    }

    @Override
    public void initContentView(View viewContent) {
        initToolBar(viewContent);
        this.mTabEssence = (TabLayout) viewContent.findViewById(R.id.tab_essence);
        this.mVpEssence = (ViewPager) viewContent.findViewById(R.id.vp_essence);
    }

    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void initData() {
        String[] titles = getResources().getStringArray(R.array.essence_video_tab);
        Log.d("debug", String.valueOf(titles));
        EssenceAdapter adapter =
                new EssenceAdapter(getFragmentManager(), Arrays.asList(titles));
        this.mVpEssence.setAdapter(adapter);
        this.mTabEssence.setupWithViewPager(this.mVpEssence);
    }

    private void initToolBar(View viewContent){

        EssenceNavigationBuilder builder = new EssenceNavigationBuilder(getContext());
        builder.createAndBind((ViewGroup) viewContent);
        builder.setImageViewStyle(R.id.iv_title, R.drawable.main_essence_title, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("中间点击了");
            }
        });
        builder.setImageViewStyle(R.id.iv_left, R.drawable.main_essence_btn_selector, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("左边点击了");
            }
        });

        builder .setImageViewStyle(R.id.iv_right, R.drawable.main_essence_btn_more_selector, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("右边点击了");
            }
        });

    }

}
