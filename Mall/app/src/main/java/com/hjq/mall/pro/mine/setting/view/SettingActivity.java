package com.hjq.mall.pro.mine.setting.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.blankj.utilcode.util.ToastUtils;
import com.hjq.mall.R;
import com.hjq.mall.mvp.presenter.impl.MvpBasePresenter;
import com.hjq.mall.pro.base.BaseActivtiy;
import com.hjq.mall.pro.base.view.navigation.CommonNavigationBuilder;

/**
 * 设置
 */
public class SettingActivity extends BaseActivtiy {

    private Button mLogoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        initToolBar(getWindow().getDecorView().findViewById(R.id.fl_layout));
        mLogoutBtn = findViewById(R.id.btn_logout);
        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutAlert();
            }
        });

    }

    private void showLogoutAlert() {
        new AlertView("提示", "您确定要退出吗？", null, null,
                new String[]{"取消", "确定"},
                this, AlertView.Style.Alert, new OnItemClickListener(){
            public void onItemClick(Object o,int position){
                ToastUtils.showShort("点击了第" + position + "个");
            }
        }).show();
    }

    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected Object getLayout() {
        return R.layout.activity_setting;
    }

    private void initToolBar(View viewContent){
        final CommonNavigationBuilder builder = new CommonNavigationBuilder(this);
        builder.createAndBind((ViewGroup) viewContent);
        builder.setTitleTextView(R.id.iv_title, getString(R.string.navigation_title_setting));
        builder .setImageViewStyle(R.id.iv_left, R.mipmap.ic_launcher, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("点击了");
                finish();
            }
        });
    }
}
