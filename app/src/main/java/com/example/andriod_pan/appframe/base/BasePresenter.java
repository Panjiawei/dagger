package com.example.andriod_pan.appframe.base;


import android.content.Context;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * T-MVP Presenter基类
 * Created by baixiaokang on 16/4/22.
 */

public abstract  class BasePresenter<M, V> {
    public Context context;
    public M mModel;
    //public V mView;
    public Reference<V> mView;
    //public RxManager mRxManager = new RxManager();


    public void onAttach(M model, V view) {
        mModel = model;
        mView = new WeakReference<>(view);
    }

    public V getView() {
        return isViewAttached() ? mView.get() : null;
    }

    public boolean isViewAttached() {
        return null != mView && null != mView.get();
    }

    public void onDetach() {
        if (null != mView) {
            mView.clear();
            mView = null;
        }
    }


//    public void setVM( M m,V v) {
//        this.mView = v;
//        this.mModel = m;
//        this.onStart();
//
//    }

 //   public abstract void onStart();

//    public void onDestroy() {
//      //  mRxManager.clear();
//    }


}
