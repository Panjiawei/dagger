package com.example.andriod_pan.appframe.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.andriod_pan.appframe.utils.JumpUtil;
import com.example.andriod_pan.appframe.utils.TUtil;

/**
 * Created by andriod_pan on 2018/7/11.
 */

public abstract class BaseMVPActivity<P extends BasePresenter, M extends BaseModel> extends SimpleActivity implements  JumpUtil.JumpInterface{

    public P mPresenter;
    public M mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void init() {
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (this instanceof BaseView) mPresenter.onAttach( mModel,this);
        setMap();
    }


  
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }



}
