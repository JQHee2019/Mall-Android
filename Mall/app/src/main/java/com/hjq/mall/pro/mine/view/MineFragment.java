package com.hjq.mall.pro.mine.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.hjq.mall.R;
import com.hjq.mall.bean.mine.MineBean;
import com.hjq.mall.bean.mine.MineItemType;
import com.hjq.mall.mvp.presenter.impl.MvpBasePresenter;
import com.hjq.mall.pro.base.BaseFragment;
import com.hjq.mall.pro.mine.view.adapter.MineAdapter;
import com.hjq.mall.pro.mine.view.listener.MineClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ImageView avatarImageView;
    private TextView userNameTextView;
    private List<MineBean> datas = new ArrayList<>();

    @Override
    public int getContentView() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initContentView(View viewContent) {
        recyclerView = viewContent.findViewById(R.id.recycler_view);
        avatarImageView = viewContent.findViewById(R.id.iv_avatar);
        userNameTextView = viewContent.findViewById(R.id.tv_user_name);

        avatarImageView.setOnClickListener(this);
        userNameTextView.setOnClickListener(this);

        // 设置RecyclerView
        // 设置显示的布局
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        final MineAdapter adapter = new MineAdapter(datas);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new MineClickListener(getContext()));
    }

    @Override
    public void initData() {
        final MineBean logisticsBean = new MineBean.Builder()
                .setItemType(MineItemType.ITEM_NORMAL)
                .setText("我的物流")
                .build();

        final MineBean lineBean = new MineBean.Builder()
                .setItemType(MineItemType.ITEM_LINE)
                .build();

        final MineBean settingBean = new MineBean.Builder()
                .setId(2)
                .setItemType(MineItemType.ITEM_NORMAL)
                .setText("设置")
                .build();

        datas.add(logisticsBean);
        datas.add(lineBean);
        datas.add(settingBean);
    }

    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        ToastUtils.showShort("头像点击");
    }
}
