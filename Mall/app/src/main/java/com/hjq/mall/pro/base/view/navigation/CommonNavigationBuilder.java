package com.hjq.mall.pro.base.view.navigation;

import android.content.Context;

import com.hjq.mall.R;


/**
 * 通用导航
 */
public class CommonNavigationBuilder extends NavigationBuilderAdapter {

    public CommonNavigationBuilder(Context context){
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.toolbar_common_layout;
    }
}
