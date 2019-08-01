package com.hjq.mall.http.interceptor;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 *
 * 全局状态码拦截
 */
public class TokenInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        // 获取响应实例
        Response response = chain.proceed(originalRequest);
        /*不能直接使用response.body().string()的方式获取请求返回，
          因为response.body().string()之后，response中的流会被关闭，程序会报错
          我们需要创建出一个新的response进行处理
        */
        ResponseBody responseBody = response.peekBody((1024 * 1024));
        String responseStr = responseBody.toString();
        int responseCode = response.code();
        if (responseCode == 401) { // token过期

        }
        return response;
    }
}
