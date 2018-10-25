package com.example.andriod_pan.appframe.Http;

import android.util.Log;

import com.example.andriod_pan.appframe.app.App;
import com.example.andriod_pan.appframe.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andriod_pan on 2018/7/10.
 */

public class RetrofitFactory {

    private static RetrofitFactory mRetrofitFactory;
    private static ApiService mAPIFunction;

    //缓存路径
    File cacheFile = new File(Constants.PATH_CACHE);
    Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);

    private RetrofitFactory() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(40, TimeUnit.SECONDS)//读取超时
                .readTimeout(40, TimeUnit.SECONDS)//连接超时
                .writeTimeout(40, TimeUnit.SECONDS)//写入超时
                .addInterceptor(InterceptorUtil.HeaderInterceptor())
                .addInterceptor(InterceptorUtil.LogInterceptor())//添加日志拦截器
                .addNetworkInterceptor(InterceptorUtil.cacheInterceptor())//设置缓存
                .addInterceptor(InterceptorUtil.cacheInterceptor())
                .cache(cache)
                .build();
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//添加gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava转换器
                .client(mOkHttpClient)
                .build();
        mAPIFunction = mRetrofit.create(ApiService.class);

    }

    public static RetrofitFactory getInstence() {
        if (mRetrofitFactory == null) {
            synchronized (RetrofitFactory.class) {
                if (mRetrofitFactory == null)
                    mRetrofitFactory = new RetrofitFactory();
            }

        }
        return mRetrofitFactory;
    }

    public ApiService API() {
        return mAPIFunction;
    }


    public RequestBody map2RequestBody(HashMap map) {
        Log.e("map2request", "--------------------------------");
//        gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String s_json = gson.toJson(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), s_json);
        return requestBody;
    }

}
