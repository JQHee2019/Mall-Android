package com.hjq.mall.http.callback;

import android.content.Context;

import com.hjq.mall.pro.base.view.dialogs.LoadingProgressDialog;

import java.io.IOException;

// import dmax.dialog.SpotsDialog;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

//添加对请求时对话框的处理
public abstract class HttpLoadCallBack<T> extends HttpBaseCallBack<T> {

    private Context context;
    private String message;
    private boolean isCancelable;

    public HttpLoadCallBack(Context context) {
        this.context = context;
    }

    private void showDialog() {
        LoadingProgressDialog.stopLoading();
        LoadingProgressDialog.showLoading(context, message, isCancelable);
    }

    private void hideDialog() {
        LoadingProgressDialog.stopLoading();
    }

    public void setMsg(String str, boolean isCancelable) {
        this.isCancelable = isCancelable;
        this.message = str;

    }

    public void setMsg(int resId, boolean isCancelable) {
        this.isCancelable = isCancelable;
        this.message = context.getString(resId);
    }


    @Override
    public void OnRequestBefore(Request request) {
        showDialog();

    }

    @Override
    public void onFailure(Call call, IOException e) {
        hideDialog();
    }

    @Override
    public void onResponse(Response response) {
        hideDialog();
    }

    @Override
    public void inProgress(int progress, long total, int id) {

    }
}