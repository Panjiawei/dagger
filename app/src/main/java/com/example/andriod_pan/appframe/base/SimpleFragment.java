package com.example.andriod_pan.appframe.base;

/**
 * Created by ASUS on 2018/1/4.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andriod_pan.appframe.utils.ToastUtil;


/**
 * Created by codeest on 16/8/11.
 * 无MVP的Fragment基类
 */

public abstract class SimpleFragment extends Fragment {

    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
  //  private Unbinder mUnBinder;



    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      //  mUnBinder = ButterKnife.bind(this, view);
        initEventAndData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //mUnBinder.unbind();
    }

    protected abstract int getLayoutId();
    protected abstract void initEventAndData();


    protected void showToast(String content){
        if (!TextUtils.isEmpty(content))
            ToastUtil.show(content);
    }

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
