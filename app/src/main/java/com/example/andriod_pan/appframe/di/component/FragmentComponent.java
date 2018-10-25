package com.example.andriod_pan.appframe.di.di.component;

import android.app.Activity;


import com.example.andriod_pan.appframe.di.di.module.FragmentModule;
import com.example.andriod_pan.appframe.di.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
//    /**
//     * 获取注入的Fragment
//     *
//     * @return
//     */
//    Fragment getFragment();

    Activity getActivity();
   // void inject(HomeFragment homeFragment);

}
