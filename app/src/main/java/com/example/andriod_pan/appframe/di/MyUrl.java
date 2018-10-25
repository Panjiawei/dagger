package com.example.andriod_pan.appframe.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Adrian on 2018/1/2.
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface MyUrl {

}
