package com.example.andriod_pan.appframe.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.andriod_pan.appframe.Http.BaseObserver;
import com.example.andriod_pan.appframe.Http.RetrofitFactory;
import com.example.andriod_pan.appframe.R;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        RetrofitFactory.getInstence().API()
//                .getHome()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseObserver<Object>() {
//                    @Override
//                    protected void onSuccees(Object t) throws Exception {
//                        Log.e("pan",t.toString());
//                    }
//
//                    @Override
//                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
//
//                    }
//                });

        HashMap map1 = new HashMap();
        map1.put("mobile","17601279633");
        map1.put("type",1);
        map1.put("password",123456);
        RetrofitFactory.getInstence().API()
                .postLoging(RetrofitFactory.getInstence().map2RequestBody(map1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    protected void onSuccees(Object t) throws Exception {
                        Log.e("pan",t.toString());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                    }
                });
    }
}
