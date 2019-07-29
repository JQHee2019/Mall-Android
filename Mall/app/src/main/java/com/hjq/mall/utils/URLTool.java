package com.hjq.mall.utils;

import java.util.Map;
import java.util.Set;

public class URLTool {

    /**
     * 根据map集合拼接成URL
     */
    public static String paramsConvertUrl(Map<String, Object> params) {
        StringBuilder urlParams = new StringBuilder("?");
        Set<Map.Entry<String, Object>> entries = params.entrySet();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            urlParams.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        String urlParamsStr = urlParams.toString();
        return urlParamsStr.substring(0, urlParamsStr.length()-1);
    }

}
