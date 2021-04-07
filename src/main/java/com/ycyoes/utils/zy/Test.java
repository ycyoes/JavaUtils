package com.ycyoes.utils.zy;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpHeaders;

import java.util.Date;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        String sha = RestUtil.sha("test");
        System.out.println(sha);
//        JSONObject loginResult = ZhenYouUtil.login();
//        System.out.println(loginResult);

        // 获取随机数
        String nonce = String.valueOf(new Random().nextInt());
        // 获取时间戳
        String Timestamp = String.valueOf(new Date().getTime());
        System.out.println("nonce: " + nonce + " timestamp: " + Timestamp);

        HttpHeaders headers  =RestUtil.getHeaderZy("test", "test");
        System.out.println(headers);
    }
}
