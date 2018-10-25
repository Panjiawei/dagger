package com.example.andriod_pan.appframe.di.module;


import com.example.andriod_pan.appframe.app.App;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by codeest on 16/8/7.
 */

@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

//    @Provides
//    @Singleton
//    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
//        return retrofitHelper;
//    }




}
