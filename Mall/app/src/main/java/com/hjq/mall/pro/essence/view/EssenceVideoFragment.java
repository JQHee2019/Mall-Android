package com.hjq.mall.pro.essence.view;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.hjq.mall.R;
import com.hjq.mall.bean.essence.PostsListBean;
import com.hjq.mall.mvp.presenter.impl.MvpBasePresenter;
import com.hjq.mall.pro.base.BaseFragment;
import com.hjq.mall.pro.base.presenter.BasePresenter;
import com.hjq.mall.pro.essence.presenter.EssenceVideoPresenter;
import com.hjq.mall.pro.essence.view.adapter.EssenceVideoAdapter;
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

    private RefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private EssenceVideoAdapter mVideoAdapter;

    private List<PostsListBean.PostList> mPostList = new ArrayList<>();

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

        mRecyclerView = (RecyclerView) viewContent.findViewById(R.id.recycler_view_test_rv);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mVideoAdapter = new EssenceVideoAdapter(mPostList, getContext());
        mRecyclerView.setAdapter(mVideoAdapter);

        mRefreshLayout = (RefreshLayout)viewContent.findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadData(true);
                // refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
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
            public void onSuccess(List<PostsListBean.PostList> result) {
                if (isDownRefresh){
                    mRefreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                }else{
                    mRefreshLayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                }
                if (result == null){
                    ToastUtils.showShort("加载失败");
                }else {
                    ToastUtils.showShort("加载成功");
                    //刷新UI
                    if (isDownRefresh){
                        // 如果你是下拉刷新,我就情况列表
                        mPostList.clear();
                    }
                    mPostList.addAll(result);
                    mVideoAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String message) {
                ToastUtils.showShort("加载失败");
            }
        });
    }
}
