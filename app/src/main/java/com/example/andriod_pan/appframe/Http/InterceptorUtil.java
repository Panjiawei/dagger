package com.example.andriod_pan.appframe.Http;

import android.text.TextUtils;
import android.util.Log;

import com.example.andriod_pan.appframe.app.App;
import com.example.andriod_pan.appframe.utils.Constant;
import com.example.andriod_pan.appframe.utils.Constants;
import com.example.andriod_pan.appframe.utils.MD5;
import com.example.andriod_pan.appframe.utils.SystemUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by andriod_pan on 2018/7/10.
 */

public class InterceptorUtil {
    public static String TAG = "----";

    //日志拦截器
    public static HttpLoggingInterceptor LogInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e(TAG, "log: " + message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
    }

    public static Interceptor HeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request mRequest = chain.request();
                //在这里你可以做一些想做的事,比如token失效时,重新获取token
                //或者添加header等等,PS我会在下一篇文章总写拦截token方法
                mRequest = mRequest.newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("X-QJJ-Sign", MD5.getMD5Str(MD5.getNewTimestamp(System.currentTimeMillis() + "") + "UtOCzqb67d3sN12Kts4URwy8") + "," + MD5.getNewTimestamp(System.currentTimeMillis() + ""))

                        .build();
                Log.e("qjj", MD5.getMD5Str(MD5.getNewTimestamp(System.currentTimeMillis() + "") + "UtOCzqb67d3sN12Kts4URwy8") + "," + MD5.getNewTimestamp(System.currentTimeMillis() + ""));
                if (!TextUtils.isEmpty(Constant.token)) {
                    // Log.e("refresh", Constant.token);
                    mRequest = mRequest.newBuilder()
                            .addHeader("Accept", "application/json")
                            .addHeader("X-QJJ-Sign", MD5.getMD5Str(MD5.getNewTimestamp(System.currentTimeMillis() + "") + "UtOCzqb67d3sN12Kts4URwy8") + "," + MD5.getNewTimestamp(System.currentTimeMillis() + ""))
                            .addHeader("Authorization", Constant.token)
                            .build();
                }
                return chain.proceed(mRequest);
            }
        };

    }



    public static Interceptor cacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!SystemUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)//此处不设置过期时间
                            .build();
                }
                Response response = chain.proceed(request);
                if (SystemUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
//                    response.newBuilder()
//                            .header("Cache-Control", "public, max-age=" + maxAge)
//                            .removeHeader("Pragma")
//                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .header("X-QJJ-Sign", MD5.getMD5Str(MD5.getNewTimestamp(System.currentTimeMillis() + "") + "UtOCzqb67d3sN12Kts4URwy8") + "," + MD5.getNewTimestamp(System.currentTimeMillis() + ""))
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }

        };
    }




}