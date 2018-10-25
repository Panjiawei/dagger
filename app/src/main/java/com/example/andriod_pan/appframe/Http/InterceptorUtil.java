package com.example.andriod_pan.appframe.Http;

import android.text.TextUtils;
import android.util.Log;

import com.example.andriod_pan.appframe.utils.Constant;
import com.example.andriod_pan.appframe.utils.MD5;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by andriod_pan on 2018/7/10.
 */

public class InterceptorUtil {
    public static String TAG="----";
    //日志拦截器
    public static HttpLoggingInterceptor LogInterceptor(){
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e(TAG, "log: "+message );
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
    }

    public static Interceptor HeaderInterceptor(){
        return   new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request mRequest=chain.request();
                //在这里你可以做一些想做的事,比如token失效时,重新获取token
                //或者添加header等等,PS我会在下一篇文章总写拦截token方法
                mRequest = mRequest.newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("X-QJJ-Sign", MD5.getMD5Str(MD5.getNewTimestamp(System.currentTimeMillis()+"")+"UtOCzqb67d3sN12Kts4URwy8")+","+MD5.getNewTimestamp(System.currentTimeMillis()+""))

                        .build();
                Log.e("qjj",MD5.getMD5Str(MD5.getNewTimestamp(System.currentTimeMillis()+"")+"UtOCzqb67d3sN12Kts4URwy8")+","+MD5.getNewTimestamp(System.currentTimeMillis()+""));
                if (!TextUtils.isEmpty(Constant.token)) {
                    // Log.e("refresh", Constant.token);
                    mRequest = mRequest.newBuilder()
                            .addHeader("Accept", "application/json")
                            .addHeader("X-QJJ-Sign", MD5.getMD5Str(MD5.getNewTimestamp(System.currentTimeMillis()+"")+"UtOCzqb67d3sN12Kts4URwy8")+","+MD5.getNewTimestamp(System.currentTimeMillis()+""))
                            .addHeader("Authorization", Constant.token)
                            .build();
                }
                return chain.proceed(mRequest);
            }
        };

    }


}
