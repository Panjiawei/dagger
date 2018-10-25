package com.example.andriod_pan.appframe.Http;


import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


/**
 * @author helin
 * create by 2018-05-17
 */
public interface ApiService {

    // public static final String BASE_URL = "http://grayconf.i2863.com";
    // public static final String BASE_URL = "http://192.168.3.33:800/";
    public static final String BASE_URL = "https://preapi.mymoneygohome.com/";
    //public static final String BASE_URL = "http://api.mymoneygohome.com/";

    //获取首页信息
    @GET("invest/v410/home")
    Observable<Object> getHome();

    // 用户登录
    @POST("api/auth/login")
    Observable<ResponseBody> postLoging(@Body RequestBody requestBody);


    //校验手机号
//    @FormUrlEncoded
    @POST("api/auth/validate_login")
    Observable<ResponseBody> checkMobile(@Body RequestBody requestBody);


    /**
     * 获取配置信息
     *
     * @return
     */
    @GET("/NewsControl/recommendNews/randomBySection")
    Observable<String> getVideo(@Query("secId") String secId, @Query("newsId") String newsId);

    /**
     * 获取配置信息
     *
     * @return
     */
    @GET("/App_Config/appConfig/getAppCinfig")
    Observable<String> getAppConfig(@Query("siteId") String siteId, @Query("tagName") String tagName);


    /**
     * Map的key作为表单的键
     * Get方式@QueryMap和@Query
     */
    @GET("invest/v1/home")
    Observable<ResponseBody> login(@Query("username") String username, @Query("password") String password);

    @GET("invest/v1/home")
    Observable<ResponseBody> home(@QueryMap Map<String, Object> maps);

    /**
     * Map的key作为表单的键
     * Post方式@FieldMap和@Field
     */
    @POST()
    @FormUrlEncoded
//表单上传关键字
    Observable<ResponseBody> executePost(@FieldMap Map<String, Object> maps);


}
