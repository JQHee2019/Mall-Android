package com.hjq.mall.pro.essence.view;


import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hjq.mall.R;
import com.hjq.mall.bean.PostsListBean;
import com.hjq.mall.mvp.presenter.impl.MvpBasePresenter;
import com.hjq.mall.pro.base.BaseFragment;
import com.hjq.mall.pro.base.presenter.BasePresenter;
import com.hjq.mall.pro.essence.presenter.EssenceVideoPresenter;
import com.hjq.mall.pro.essence.view.adapter.EssenceVideoAdapter;
import com.hjq.mall.utils.ToastUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EssenceVideoFragment extends BaseFragment {

    private int mType = 0;
    private String mTitle;

    private EssenceVideoPresenter presenter;

    private RefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private EssenceVideoAdapter videoAdapter;

    private List<PostsListBean.PostList> postList = new ArrayList<>();

    public void setType(int mType) {
        this.mType = mType;
    }
    public void setTitle(String title){
        this.mTitle = title;
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_essence_video;
    }

    @Override
    public MvpBasePresenter bindPresenter() {
        presenter = new EssenceVideoPresenter(getContext());
        return presenter;
    }

    @Override
    public void initContentView(View viewContent) {

        recyclerView = (RecyclerView) viewContent.findViewById(R.id.recycler_view_test_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        videoAdapter = new EssenceVideoAdapter(postList,getContext());
        recyclerView.setAdapter(videoAdapter);

        refreshLayout = (RefreshLayout)viewContent.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadData(true);
                // refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                loadData(false);
                // refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    @Override
    public void initData() {
        loadData(true);
    }

    private void loadData(final boolean isDownRefresh){
        presenter.getEssenceList(mType, isDownRefresh, new BasePresenter.OnUIThreadListener<List<PostsListBean.PostList>>() {
            @Override
            public void onResult(List<PostsListBean.PostList> result) {
                if (isDownRefresh){
                    refreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                }else{
                    refreshLayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                }
                if (result == null){
                    Looper.prepare();
                    ToastUtil.showToast(getContext(),"加载失败");
                    Looper.loop();
                }else {
                    Looper.prepare();
                    ToastUtil.showToast(getContext(),"加载成功");
                    Looper.loop();
                    //刷新UI
                    if (isDownRefresh){
                        //如果你是下拉刷新,我就情况列表
                        postList.clear();
                    }
                    postList.addAll(result);
                    videoAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
