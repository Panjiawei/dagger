package com.example.andriod_pan.appframe.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.andriod_pan.appframe.utils.TUtil;

public abstract class BaseMVPFragment<P extends BasePresenter, M extends BaseModel> extends SimpleFragment {

    public P mPresenter;
    public M mModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (this instanceof BaseView) mPresenter.onAttach(mModel, this);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }


}
