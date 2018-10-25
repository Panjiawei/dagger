package com.example.andriod_pan.appframe.di.module;



import android.text.TextUtils;
import android.util.Log;


import com.example.andriod_pan.appframe.Http.ApiService;
import com.example.andriod_pan.appframe.di.MyUrl;
import com.example.andriod_pan.appframe.utils.Constant;
import com.example.andriod_pan.appframe.utils.Constants;
import com.example.andriod_pan.appframe.utils.MD5;
import com.example.andriod_pan.appframe.utils.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by codeest on 2017/2/26.
 */

@Module
public class HttpModule {

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }


    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }


    @Singleton
    @Provides
    @MyUrl
    Retrofit provideMyRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, ApiService.BASE_URL);
    }

    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        if (true) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e("ok_msg", message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        File cacheFile = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!SystemUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
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
                            .header("X-QJJ-Sign", MD5.getMD5Str(MD5.getNewTimestamp(System.currentTimeMillis()+"")+"UtOCzqb67d3sN12Kts4URwy8")+","+MD5.getNewTimestamp(System.currentTimeMillis()+""))
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        Interceptor apikey = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                request = request.newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("X-QJJ-Sign", MD5.getMD5Str(MD5.getNewTimestamp(System.currentTimeMillis()+"")+"UtOCzqb67d3sN12Kts4URwy8")+","+MD5.getNewTimestamp(System.currentTimeMillis()+""))
                        .build();
                if (!TextUtils.isEmpty(Constant.token)) {
                    // Log.e("refresh", Constant.token);
                    request = request.newBuilder()
                            .addHeader("Accept", "application/json")
                            .addHeader("X-QJJ-Sign", MD5.getMD5Str(MD5.getNewTimestamp(System.currentTimeMillis()+"")+"UtOCzqb67d3sN12Kts4URwy8")+","+MD5.getNewTimestamp(System.currentTimeMillis()+""))
                            .addHeader("Authorization", Constant.token)
                            .build();
                }
                return chain.proceed(request);
            }
        };
        builder.addInterceptor(apikey);
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);

        //设置超时
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }


    @Singleton
    @Provides
    ApiService provideMyService(@MyUrl Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
