package com.example.andriod_pan.appframe.activity.model;

import android.util.Log;

import com.example.andriod_pan.appframe.Http.BaseObserver;
import com.example.andriod_pan.appframe.Http.RetrofitFactory;
import com.example.andriod_pan.appframe.activity.contract.LoginContract;
import com.example.andriod_pan.appframe.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by andriod_pan on 2018/7/11.
 */

public class LoginModel implements LoginContract.Model {

    String str;

    @Override
    public void login(String username,OnLoginResultListener onLoginResultListener) {

        HashMap map1 = new HashMap();
        map1.put("mobile", username);
        RetrofitFactory.getInstence().API()
                .checkMobile(RetrofitFactory.getInstence().map2RequestBody(map1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ResponseBody>() {
                    @Override
                    protected void onSuccees(ResponseBody t) throws Exception {
                        str = t.string();
                        Log.e("pan", str);
                          onLoginResultListener.loginSuccess(str);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (!isNetWorkError) {
                            ToastUtil.show("网络异常");
                        }
                         onLoginResultListener.loginFailure();
                    }
                });


    }


    //回调接口
    public interface OnLoginResultListener {

        void loginSuccess(String s);//登录成功后回调的方法，返回User对象

        void loginFailure();//登录失败后回掉的方法

    }

}
