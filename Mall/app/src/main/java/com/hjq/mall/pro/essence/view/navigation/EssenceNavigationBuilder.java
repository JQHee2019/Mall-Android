package com.hjq.mall.pro.essence.view.navigation;

import android.content.Context;

import com.hjq.mall.R;
import com.hjq.mall.pro.base.view.navigation.NavigationBuilderAdapter;

public class EssenceNavigationBuilder extends NavigationBuilderAdapter {

    public EssenceNavigationBuilder(Context context){
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.toolbar_essence_layout;
    }
}
