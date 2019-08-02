package com.hjq.mall.pro.essence.model;

import android.content.Context;
import android.text.TextUtils;

import com.hjq.mall.http.HttpConstants;
import com.hjq.mall.http.callback.HttpLoadCallBack;
import com.hjq.mall.http.OkHttpManager;
import com.hjq.mall.pro.base.model.BaseModel;

import java.util.HashMap;
import java.util.Map;

public class EssenceVideoModel extends BaseModel {

    public EssenceVideoModel(Context context) {
        super(context);
    }

    /**
     * 获取精华列表
     * @param type---数据类型(例如:图片  视频  音频  妹子等等)
     * @param page---页码
     * @param maxtime--用户加载更多
     * @param callBack---数据回调监听
     */
    public void getEssenceList(int type, int page, String maxtime, HttpLoadCallBack callBack){
        Map<String, String> requestParam = new HashMap<String, String>();
        requestParam.put("a","list");
        requestParam.put("c","data");
        requestParam.put("type",String.valueOf(type));
        requestParam.put("page",String.valueOf(page));
        if (!TextUtils.isEmpty(maxtime)){
            requestParam.put("maxtime",maxtime);
        }
        OkHttpManager.getInstance().post(HttpConstants.essence, callBack, requestParam);
    }

}
