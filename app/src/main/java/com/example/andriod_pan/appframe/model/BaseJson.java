package com.example.andriod_pan.appframe.model;

/**
 * Created by Adrian on 2018/1/2.
 */

public class BaseJson {
    public int code;
    public String message;
    public int count;
    public String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BaseJson{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", count=" + count +
                ", msg='" + msg + '\'' +
                '}';
    }
}
