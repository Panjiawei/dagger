package com.example.andriod_pan.appframe.utils;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 */
public class MD5 {
    private static String str = System.currentTimeMillis() + "";
    private static String str1 = str.substring(4, 10);

    public static String getTimestamp() {
        String timestamp = str.substring(0, 10);
        return timestamp;
    }

    public static String getNewTimestamp(String time){
        return time.substring(0,10);
    }
    public static String getMd5() {
        String md5 = MD5.getMD5Str("QJJ_APP_200" + str1);
        return md5;
    }

    public static String getPwdMd5(String pwd) {
        String code = MD5.getMd5();
        String pwdmd5 = MD5.getMD5Str(MD5.getMD5Str(pwd)  + code);
        Log.e("code", code);
        return pwdmd5;
    }


    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return md5StrBuff.toString();
    }


}
