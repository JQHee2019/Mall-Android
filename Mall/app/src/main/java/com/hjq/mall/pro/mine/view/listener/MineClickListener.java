package com.hjq.mall.pro.mine.view.listener;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.hjq.mall.bean.mine.MineBean;
import com.hjq.mall.utils.ToastUtil;

public class MineClickListener  extends SimpleClickListener {

    private Context mContext;

    public MineClickListener(final Context context) {
        mContext = context;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MineBean bean= (MineBean) baseQuickAdapter.getData().get(position);
        int id = bean.getId();
        ToastUtil.showToast(mContext, String.valueOf(id));
        switch (id) {
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
