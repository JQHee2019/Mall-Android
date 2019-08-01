package com.hjq.mall.pro.newpost.view;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.hjq.mall.R;
import com.hjq.mall.mvp.presenter.impl.MvpBasePresenter;
import com.hjq.mall.pro.base.BaseFragment;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewpostFragment extends BaseFragment {

    private WebView mWebView;
    private ProgressBar progressBar;

    private Handler mHandler = new Handler();

    private  Handler gobackHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mWebView.goBack();
                    break;
            }
        }
    };

    @Override
    public int getContentView() {
        return R.layout.fragment_newpost;
    }

    @Override
    public void initContentView(View viewContent) {
        mWebView = (WebView) viewContent.findViewById(R.id.web_view);
        progressBar = (ProgressBar) viewContent.findViewById(R.id.progress_bar);
        mWebView.loadUrl("https://www.baidu.com");

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new JSObject(),"Native");
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
                    if(event.getAction()==KeyEvent.ACTION_DOWN){ //只处理一次
                        gobackHandler.sendEmptyMessage(1);
                    }
                    return  true;
                }
                return false;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress == 100){
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }

            }
        });

    }

    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }

    public class JSObject{
        @JavascriptInterface
        public void call(String phone){
            Log.e("phone", "phone---->" + phone);
        }

        @JavascriptInterface
        public void showContacts(){
            try {
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", "zhangsan");
                jsonObject.put("amount", "50");
                jsonObject.put("phone", "123465798");
                jsonArray.put(jsonObject);

                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", "lisi");
                jsonObject1.put("amount", "48");
                jsonObject1.put("phone", "987456123");
                jsonArray.put(jsonObject1);

                final String json = jsonArray.toString();
                // 调用javascript中的show（）方法
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mWebView.loadUrl("javascript:show('" + json + "')");
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
