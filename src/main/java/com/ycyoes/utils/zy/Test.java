package com.ycyoes.utils.zy;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ycyoes.common.collect.MapUtils;
import com.ycyoes.utils.StringUtils;
import org.springframework.http.HttpHeaders;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        String sha = RestUtil.sha("test");
        System.out.println(sha);

        String token = XiXianUtil.loginUser().getString("token");   //登录获取token
        System.out.println("-----------token: " + token);

        //获取联系人组信息
        JSONObject list = ZhenYouUtil.getUserList(token);
        JSONArray s = list.getJSONArray("data");
//        s.forEach(System.out::println);
        String userId = s.getJSONObject(1).getString("id");
        System.out.println("----------get first id: " + userId);

        //获取
        JSONObject userTeam = new JSONObject();
        String dec="";
        int type = 1;
        if(type==1) {
            dec="视频会议:";
        }
        if(type==2) {
            dec="电话会议:";
        }
        userTeam.put("name", dec+ DateUtil.current());
        userTeam.put("contactIds", new String[]{userId});

        JSONObject group = ZhenYouUtil.createUserTeam(userTeam, token);
        System.out.println("-------------创建联系人组:" + group.toString());

        //获取组id
        String groupId = createGroup(group);
        System.out.println("-------------联系人组id: " + groupId);

        //创建视频会议
        JSONObject shipin = emergencyService.createShiPinHuiYi(groupId, null);

        // 获取随机数
        /*String nonce = String.valueOf(new Random().nextInt());
        // 获取时间戳
        String Timestamp = String.valueOf(new Date().getTime());
        System.out.println("nonce: " + nonce + " timestamp: " + Timestamp);

        HttpHeaders headers  =RestUtil.getHeaderZy("test/test", "test");
        System.out.println(headers);*/
    }

    public static String createGroup(JSONObject createGroup) {
        String groupId = "";
        if (createGroup != null) {
            JSONObject status = createGroup.getJSONObject("status");
            if (status != null) {
                Integer code = status.getInteger("code");
                if (code == 0) {
                    JSONObject data = createGroup.getJSONObject("data");
                    if (data != null) {
                        groupId = data.getString("id");
                    }

                }
            }

        }
        return groupId;
    }
}
