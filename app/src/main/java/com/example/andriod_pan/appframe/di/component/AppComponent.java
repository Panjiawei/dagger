package com.example.andriod_pan.appframe.di.di.component;




import com.example.andriod_pan.appframe.app.App;
import com.example.andriod_pan.appframe.di.di.module.AppModule;
import com.example.andriod_pan.appframe.di.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

   // RetrofitHelper retrofitHelper();  //提供http的帮助类

}
