package com.ycyoes.utils.zy;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {
    public static void main(String[] args) {
        String sha = RestUtil.sha("test");
        System.out.println(sha);

        JSONObject loginInfo = XiXianUtil.loginUser();   //登录获取token
        String token = loginInfo.getString("token");
        String id = loginInfo.getString("id");
        String name = loginInfo.getString("name");
        System.out.println("-----------token: " + token + " \r\n id: " + id + " name: " + name);

        JSONObject orgList = ZhenYouUtil.getOrList(token);
        System.out.println("组织架构信息： " + orgList.toString());

        //获取联系人组信息
        JSONObject list = ZhenYouUtil.getUserList(token);
        JSONArray s = list.getJSONArray("data");
        s.forEach(System.out::println);
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
        System.out.println("-------------userTeam: " + userTeam);
        JSONObject group = ZhenYouUtil.createUserTeam(userTeam, token);
        System.out.println("-------------创建联系人组:" + group.toString());

        //获取组id
        String groupId = createGroup(group);
        System.out.println("-------------联系人组id: " + groupId);

        //联系人组添加联系人
        JSONObject addUserInfo = ZhenYouUtil.addUserTeam(token, groupId, new String[]{"401000100029", "401000100030"});
        System.out.println("-------------添加联系人组信息： " + addUserInfo);

        JSONObject groupMemberInfos = ZhenYouUtil.getUserTeamMemberByTeamID(token, groupId);
        System.out.println("-------------获取添加后的联系人组信息: " + groupMemberInfos);




        //创建视频会议
        String[] contact2NumberInfos = new String[0];
        JSONObject shipinhuiyi = ZhenYouUtil.createVideoConferen(token, groupId, contact2NumberInfos);
        System.out.println("-------------创建视频会议: " + shipinhuiyi);

        //获取视频会议号
        String conferenceNum = getConferenceNumber(shipinhuiyi);
        System.out.println("-------------获取视频会议号: " + conferenceNum);

        //创建直播
        JSONObject o = ZhenYouUtil.createZhiBo(token, id, name);
//        JSONObject o = ZhenYouUtil.createZhiBo(XiXianUtil.loginUser().getString("token"),XiXianUtil.loginUser().getString("id"),XiXianUtil.loginUser().getString("name"));
        System.out.println("-------------创建直播： " + o);

        //获取mrl
        JSONObject data = o.getJSONObject("data");
        String mrl = data.getString("baseMrl") + data.getString("stream") + "?token=" + data.getString("token");
        System.out.println("-------------mrl: " + mrl);

        JSONObject video = ZhenYouUtil.createVideoConferenLive(XiXianUtil.loginUser().getString("token"), conferenceNum, mrl);
        System.out.println("-------------video: " + video);


        // 获取随机数
        /*String nonce = String.valueOf(new Random().nextInt());
        // 获取时间戳
        String Timestamp = String.valueOf(new Date().getTime());
        System.out.println("nonce: " + nonce + " timestamp: " + Timestamp);

        HttpHeaders headers  =RestUtil.getHeaderZy("test/test", "test");
        System.out.println(headers);*/
    }

    public static String getConferenceNumber(JSONObject shipin) {
        String huiyinumber = "";
        if (shipin != null) {
            JSONObject status = shipin.getJSONObject("status");
            if (status != null) {
                Integer code = status.getInteger("code");
                if (code == 0) {
                    JSONObject data = shipin.getJSONObject("data");
                    if (data != null) {
                        huiyinumber = data.getString("conferenceNumber");
                    }

                }
            }

        }
        return huiyinumber;
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
