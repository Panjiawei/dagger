package com.example.andriod_pan.appframe.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.andriod_pan.appframe.R;
import com.example.andriod_pan.appframe.activity.contract.LoginContract;
import com.example.andriod_pan.appframe.activity.model.LoginModel;
import com.example.andriod_pan.appframe.activity.presenter.LoginPresenter;
import com.example.andriod_pan.appframe.base.BaseMVPActivity;
import com.example.andriod_pan.appframe.utils.JumpUtil;
import com.example.andriod_pan.appframe.utils.ToastUtil;

public class LoginActivity extends BaseMVPActivity<LoginPresenter, LoginModel> implements LoginContract.View, View.OnClickListener {

    EditText et_mobile;
    Button btn_next;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
        et_mobile = findViewById(R.id.et_mobile);
        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
    }


    @Override
    public void loginSuccess() {
        JumpUtil.GotoActivity(this, MainActivity.class);
        Log.e("tsh", "msg");
    }

    @Override
    public void showMsg(String msg) {
        ToastUtil.show(msg);
        Log.e("tsh", msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_next:
                mPresenter.login(et_mobile.getText().toString().trim());

                break;

        }
    }


}
