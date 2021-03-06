package com.example.andriod_pan.appframe.di.component;

import android.app.Activity;


import com.example.andriod_pan.appframe.di.module.ActivityModule;
import com.example.andriod_pan.appframe.di.scope.ActivityScope;

import dagger.Component;

/**
 * Activity注射组件
 *
 * @author wujiajun
 */
@ActivityScope
@Component(dependencies = com.example.andriod_pan.appframe.di.di.component.AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    /**
     * 获取注入的Activity
     *
     * @return Activity
     */
    Activity getActivity();
    //void inject(ProductDetailActivity productDetailActivity);

}
