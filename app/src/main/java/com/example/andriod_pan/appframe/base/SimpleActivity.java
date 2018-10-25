package com.example.andriod_pan.appframe.base;

import android.app.Activity;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.text.TextUtils;


import com.example.andriod_pan.appframe.utils.ToastUtil;

import java.util.HashMap;


/**
 * Created by codeest on 16/8/11.
 * 无MVP的activity基类
 */

public abstract class SimpleActivity extends AppCompatActivity {


    protected Activity mContext;
  //  private Unbinder mUnBinder;


    protected HashMap map = new HashMap();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
       // mUnBinder = ButterKnife.bind(this);
        mContext = this;
        onViewCreated();
        initEventAndData();
        setMap();

    }



    protected void showToast(String content) {
        if (!TextUtils.isEmpty(content))
            ToastUtil.show(content);
    }



    protected void onViewCreated() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mUnBinder.unbind();
    }

    protected abstract int getLayout();

    protected abstract void initEventAndData();


    protected void setMap() {
//        String token = (String) SpUtils.getParam(mContext, "token", "");
//        String device_token = (String) SpUtils.getParam(mContext, "device_token", "");
////        RequestParams params = new RequestParams();
//        map.put("code", MD5.getMd5());
//        map.put("tp", MD5.getTimestamp());
//        map.put("platform", "Android");
//        map.put("version",
//                SystemTool.getAppVersionName(MyApplication._context) + "");
//        map.put("register_id", device_token);
//        map.put("token", token);
//        map.put("deviceid", MyApplication.getUDID());
//        map.put("app_data",
//                MyApplication.getUDID());
    }
}
