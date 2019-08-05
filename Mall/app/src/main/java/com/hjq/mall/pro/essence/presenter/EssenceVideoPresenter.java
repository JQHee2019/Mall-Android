package com.hjq.mall.pro.essence.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.hjq.mall.bean.essence.PostsListBean;
import com.hjq.mall.http.callback.HttpLoadCallBack;
import com.hjq.mall.pro.base.presenter.BasePresenter;
import com.hjq.mall.pro.essence.model.EssenceVideoModel;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class EssenceVideoPresenter extends BasePresenter<EssenceVideoModel> {

    private int page = 0;
    private String maxtime = null;

    public EssenceVideoPresenter(Context context) {
        super(context);
    }

    @Override
    public EssenceVideoModel bindModel() {
        return new EssenceVideoModel(getContext());
    }

    //定义解析方法
    public void getEssenceList(int type,
                               final boolean isDownRefresh,
                               final OnUIThreadListener<List<PostsListBean.PostList>> onUIThreadListener){
        if (isDownRefresh){
            maxtime = null;
        }

        HttpLoadCallBack callBack = new HttpLoadCallBack<String>(getContext()) {
            @Override
            public void onSuccess(Call call, Response response, String result) {
                if (TextUtils.isEmpty(result)){
                    //等于空---通知UI线程---刷新UI界面
                    onUIThreadListener.onSuccess(null);
                } else {
                    //不等于null
                    //解析数据
                    PostsListBean postsListBean = getGson().fromJson(result,PostsListBean.class);
                    //处理分页逻辑---UI层只负责现实数据,不要做任何与网络相关的逻辑处理
                    if (postsListBean != null && postsListBean.getInfo() != null){
                        maxtime = postsListBean.getInfo().getMaxtime();
                    }
                    if (isDownRefresh){
                        page = 0;
                    }else {
                        page++;
                    }
                    //MVP架构---编程思想---属于移动架构师
                    //MVP架构适合面向对象的语言设计
                    //编程思想运用在任何的语言
                    onUIThreadListener.onSuccess(postsListBean.getList());
                }
            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                onUIThreadListener.onError(null);
            }
        };
        callBack.setMsg("加载中...", true);

        //执行网络请求
        getModel().getEssenceList(type, page, maxtime, callBack);
    }
}
