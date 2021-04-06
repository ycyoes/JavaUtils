package com.ycyoes.utils.zy;

import com.ycyoes.utils.zy.RestUtil;
import org.springframework.http.HttpMethod;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public class ZhenYouUtil {
	//http://159.75.19.42:8089 172.21.7.15 111.19.144.40
	private static String ip="http://111.19.144.40";
	private static String prot=":8089";
	private static String server = ip+prot;
	// ceshi01 账号 ceshi001 密码 Nucleus!
	private static String zhenYouUserName = "504shq";
	private static String zhenYouPassword = "Nucleus!";
	private static String imei = zhenYouUserName;
	private static String videoConfig = "{\"VideoConferenceID\":\"681fffed-91d4-4917-843a-4b6b872f0b49\",\"VideoConferenceName\":\"3组\",\"IsMuteJoinConference\":false,\"IsAutoReInvite\":false,\"Password\":\"\",\"IsAutoSelectedNumber\":true,\"Number\":null,\"IsAutoScreen\":true,\"VideoConferenceScreenType\":4,\"MainVideoConfigs\":[{\"EVideoFormat\":2,\"EVideoProtocol\":3,\"EFrameRate\":25,\"EBitRate\":1024},{\"EVideoFormat\":1,\"EVideoProtocol\":3,\"EFrameRate\":25,\"EBitRate\":1024},{\"EVideoFormat\":0,\"EVideoProtocol\":1,\"EFrameRate\":30,\"EBitRate\":384},{\"EVideoFormat\":0,\"EVideoProtocol\":1,\"EFrameRate\":30,\"EBitRate\":384}],\"SubVideoConfigs\":[],\"RecallConfig\":null,\"NumberList\":null}";

	// 登录
	public static JSONObject login() {
		JSONObject login = new JSONObject();
		// ceshi01 401000100695 ceshi02 401000100694 Nucleus!
		login.put("userName", zhenYouUserName);
		login.put("password", zhenYouPassword);
		login.put("imei", imei);
//		JSONObject result = RestUtil.postZy(server + "/nuas/api/v1/sessions", login, null);
		JSONObject result = null;
		//System.out.println(result.toString());
		return result;
	}

	// 修改密码
	public static JSONObject editUserPass(JSONObject o, String token) {
//		JSONObject result = RestUtil.putParams(server + "/nuas/api/v1/password", o, token);
		JSONObject result = null;

		return result;
	}

	// 根据用户名获取验证码
	public static JSONObject getYzmByUserName(String token, String userName) {
//		JSONObject result = RestUtil.getUrl(server + "/nuas/api/v1/unauth/captcha/" + userName, token);
		JSONObject result = null;
		return result;
	}

	// 根据用户名重置密码
	public static JSONObject unauthPasswordByUserName(JSONObject o, String token) {
//		JSONObject result = RestUtil.postZy(server + "/nuas/api/v1/unauth/password", o, token);
		JSONObject result = null;
		return result;
	}

	// 联系人模块
	// 添加联系人
	public static JSONObject addUser(JSONObject o, String token) {
//		JSONObject result = RestUtil.postZy(server + "/nuas/api/v1/contacts", o, token);
		JSONObject result = null;
		return result;
	}

	// 修改联系人
	public static JSONObject editUser(JSONObject o, String token, String id) {
//		JSONObject result = RestUtil.putParams(server + "/nuas/api/v1/contacts/" + id, o, token);
		JSONObject result = null;
		return result;
	}

	// 修改联系人
	public static JSONObject deleteUser(JSONObject o, String token, String id) {
//		JSONObject result = RestUtil.putParams(server + "/nuas/api/v1/contacts/", o, token);
		JSONObject result = null;
		return result;
	}

	// 获取联系人集合数据
	public static JSONObject getUserList(String token) {
		// ?page=0&per_page=200"
//		JSONObject result = RestUtil.getUrl(server + "/nuas/api/v1/contacts?page=0&per_page=500", token);
		JSONObject result = null;
		return result;
	}

	// 获取带组织结构的联系人集合数据
	public static JSONObject getUserListOrg(String token) {
		// ?page=0&per_page=200"
//		JSONObject result = RestUtil.getUrl(server + "/nuas/api/v1/contacts_with_organization?page=0&per_page=500",
		JSONObject result = null;
		return result;
	}

	// 获取联系人集合数据
	public static JSONObject getUserByPhonenNumber(String token, String phonenNumber) {
//		JSONObject result = RestUtil.getUrl(server + "/nuas/api/v1/contacts/phone/" + phonenNumber, token);
		JSONObject result = null;
		return result;
		// return result.toJSONString();
	}

	// 获取在线联系人集合数据
	public static JSONObject getOnlineUserList(String token) {
//		JSONObject result = RestUtil.getUrl(server + "/nuas/api/v1/contacts/online", token);
		JSONObject result = null;
		return result;
	}

	// 根据联系电话获取联系人
	public static JSONObject getUserByNumber(String token, String number) {
//		JSONObject result = RestUtil.getUrl(server + "nuas/api/v1/contacts/phone/" + number, token);
		JSONObject result = null;
		return result;
	}

	// 联系人组模块
	// 创建联系人组
	public static JSONObject createUserTeam(JSONObject o, String token) {
//		JSONObject result = RestUtil.postZy(server + "/nuas/api/v1/groups", o, token);
		JSONObject result = null;
		return result;
	}

	// 联系人组添加成员
	public static JSONObject addUserTeam(JSONObject o, String token, String id, String[] ids) {
//		JSONObject result = RestUtil.post2(server + "/nuas/api/v1/groups/" + id + "/contacts", HttpMethod.POST, token,
		JSONObject result = null;
		return result;
	}

	// 删除联系人组
	public static JSONObject deleteGroupById(String token, String groupId) {
//		JSONObject result = RestUtil.deleteUrl(server + "/nuas/api/v1/groups/" + groupId, token);
		JSONObject result = null;
		return result;
	}

	// 获取联系人组
	public static JSONObject getUserTeamByID(String token, String id) {
//		JSONObject result = RestUtil.getUrl(server + "/nuas/api/v1/groups/" + id, token);
		JSONObject result = null;
		return result;
	}

	// 获取联系人组成员
	public static JSONObject getUserTeamMemberByTeamID(String token, String id) {
//		JSONObject result = RestUtil.getUrl(server + "/nuas/api/v1/groups/" + id + "/contacts", token);
		JSONObject result = null;
		return result;
	}

	// 删除联系人组1个成员
	public static JSONObject deleteUserTeamMember(JSONObject o, String token, String id, String userID) {
//		JSONObject result = RestUtil.deleteUrl(server + "/nuas/api/v1/groups/" + id + "/contacts/" + userID, token);
		JSONObject result = null;
		return result;
	}

	// 删除联系人组多个成员
	public static JSONObject deleteUserTeamMembers(JSONObject o, String token, String id, String[] userids) {
//		JSONObject result = RestUtil.post2(server + "/nuas/api/v1/groups/" + id + "/contacts/", HttpMethod.DELETE,
		JSONObject result = null;
		return result;
	}

	// 获取联系人组列表
	public static JSONObject getUserTeamList(String token) {
//		JSONObject result = RestUtil.getUrl(server + "/nuas/api/v1/groups?page=0&per_page=50", token);
		JSONObject result = null;
		return result;
	}

	// 创建电话呼叫
	public static JSONObject createCall(JSONObject o, String token) {
//		JSONObject result = RestUtil.postZy(server + "/nuas/api/v1/calls", o, token);
		JSONObject result = null;
		return result;
	}

	// 挂断电话呼叫
	public static JSONObject guaDuanCall(String token, String userNumber) {
//		JSONObject result = RestUtil.deleteUrl(server + "/nuas/api/v1/conversations/" + userNumber, token);
		JSONObject result = null;
		return result;
	}

     //电话会议模块
	// 创建电话会议
	public static JSONObject createDianHuanHuiyi(String token, String groupId) {
		JSONObject o = new JSONObject();
		o.put("groupId", groupId);
//		JSONObject result = RestUtil.postZy(server + "/nuas/api/v1/conferences", o, token);
		JSONObject result = null;
		return result;
	}

	public static JSONObject shanchuDianhuaHuiyi(String token, String huiyiId) {
//		JSONObject result = RestUtil.deleteUrl(server + "/nuas/api/v1/conferences/" + huiyiId, token);
		JSONObject result = null;
		return result;
	}

	// 添加电话会议成员
	public static JSONObject addCallConferenMember(JSONObject o, String token, int id, String[] ids) {
//		JSONObject result = RestUtil.post2(server + "/nuas/api/v1/conferences/" + id + "/contacts", HttpMethod.POST,
		JSONObject result = null;
		return result;
	}

	// 删除会议成员即踢出已参加会议的联系人
	public static JSONObject deleteCallConferenMember(JSONObject o, String token, int id, String[] ids) {
//		JSONObject result = RestUtil.post2(server + "/nuas/api/v1/conferences/" + id + "/members_kickoff",
		JSONObject result = null;
		return result;
	}

	// 会议人员禁言
	public static JSONObject muteCallConferenMember(JSONObject o, String token, int id, String[] ids) {
//		JSONObject result = RestUtil.post2(server + "/nuas/api/v1/conferences/" + id + "/mute_members", HttpMethod.POST,
		JSONObject result = null;
		return result;
	}

	// 会议人员禁言恢复
	public static JSONObject resumeMuteCallConferenMember(JSONObject o, String token, int id, String[] ids) {
//		JSONObject result = RestUtil.post2(server + "/nuas/api/v1/conferences/" + id + "/mute_members_resume",
		JSONObject result = null;
		return result;
	}

	// 会议人员隔离
	public static JSONObject isolateCallConferenMember(JSONObject o, String token, int id, String[] ids) {
//		JSONObject result = RestUtil.post2(server + "/nuas/api/v1/conferences/" + id + "/isolate_members",
		JSONObject result = null;
		return result;
	}

	// 会议人员隔离恢复
	public static JSONObject resumeIsolateCallConferenMember(JSONObject o, String token, int id, String[] ids) {
//		JSONObject result = RestUtil.post2(server + "/nuas/api/v1/conferences/" + id + "/members_kickoff",
		JSONObject result = null;
		return result;
	}

	// 获取会议人员状态
	public static JSONObject getDianhuahuiyiMemberListStatu(String token, String huiyiNumber) {
//		JSONObject result = RestUtil.getUrl(server + "/nuas/api/v1/conferences/" + huiyiNumber + "/memeber_status",
		JSONObject result = null;
		return result;
	}

	// 视频会议模块
	// 创建视频会议
	public static JSONObject createVideoConferen(String token, String groupId, String[] contact2NumberInfos) {
		JSONObject cvedio = new JSONObject();
		cvedio.put("config", videoConfig);
		cvedio.put("contact2NumberInfos", contact2NumberInfos);
//		JSONObject result = RestUtil.postZy(server + "/nuas/api/v1/video/conference/create/" + groupId, cvedio, token);
		JSONObject result = null;
		return result;
	}

	// 结束视频会议
	public static JSONObject jieshuVideoConferen(String token, String number) {
//		JSONObject result = RestUtil.deleteUrl(server + "/nuas/api/v1/video/conference/stop/" + number, token);
		JSONObject result = null;
		return result;
	}

	// 获取视频会议信息
	public static JSONObject huoquShipinHuiyi(String token, String groupId) {
//		JSONObject result = RestUtil.postZy(server + "/nuas/api/v1/video/conference/group/" + groupId + "/detail", null,
		JSONObject result = null;
		return result;
	}

	public static JSONObject huoquShipinChenyuan(String token, String numbers) {
//		JSONObject result = RestUtil.getUrl(server + "/nuas/api/v1/video/conference/" + numbers + "/members/status",
		JSONObject result = null;
		return result;
	}

	// 视频会议成员邀请
	public static JSONObject yaoQingShipinHuiyi(String token, String huiyiNumber, JSONArray renyuan) {
		renyuan.toJSONString();
//		JSONObject result = RestUtil.post3(server + "/nuas/api/v1/video/conference/invite/" + huiyiNumber + "/contacts",
		JSONObject result = null;
		return result;
	}

	// 视频会议成员剔除
	public static JSONObject tiChuShipinHuiyi(String token, String huiyiNumber, String renyuanId) {
		String[] contact2NumberInfos;
		if (renyuanId != null && !renyuanId.equals("")) {
			contact2NumberInfos = renyuanId.split(",");
		} else {
			contact2NumberInfos = new String[0];
		}

//		JSONObject result = RestUtil.post2(server + "/nuas/api/v1/video/conference/" + huiyiNumber + "/members",
		JSONObject result = null;
		return result;
	}

	// 视频会议禁言
	public static JSONObject jinyanShipinHuiyi(String token, String huiyiNumber, String renyuanId) {
		String[] contact2NumberInfos;
		if (renyuanId != null && !renyuanId.equals("")) {
			contact2NumberInfos = renyuanId.split(",");
		} else {
			contact2NumberInfos = new String[0];
		}

//		JSONObject result = RestUtil.post2(server + "/nuas/api/v1/video/conference/" + huiyiNumber + "/gag_members",
		JSONObject result = null;
		return result;
	}

	// 创建直播
	public static JSONObject createZhiBo(String token, String contactId, String userName) {
		JSONObject o = new JSONObject();
		o.put("appointment", 0);
		o.put("contactDisplayName", userName + "的"+ new Date().getTime()+"直播");
		o.put("contactId", contactId);
		o.put("createTime", new Date().getTime());
		o.put("desc", userName + "的"+ new Date().getTime()+"直播");
		o.put("dvrCount", 0);
		o.put("playCount", 0);
		o.put("status", 0);
		o.put("title", userName + "的"+new Date().getTime()+"直播");
//		JSONObject result = RestUtil.postZy(server + "/nuas/api/v1/gmcc/live/0", o, token);
		JSONObject result = null;
		return result;
	}

	// 结束zhibo
	public static JSONObject jieshuZhibo(String token, String stream) {
//		JSONObject result = RestUtil.deleteUrl(server + "/nuas/api/v1/gmcc/live/" + stream, token);
		JSONObject result = null;
		return result;
	}

	// 创建视频会议直播
	public static JSONObject createVideoConferenLive(String token, String number, String mrl) {
		JSONObject cvedio = new JSONObject();
		cvedio.put("mrl", mrl);
		cvedio.put("title", number+"视频会议");
//		JSONObject result = RestUtil.postZy(server + "/nuas/api/v1/video/conference/" + number + "/live", cvedio,
		JSONObject result = null;
		return result;
	}

	// 结束视频会议直播
	public static JSONObject deleteZhiboLive(String token, String number, String mrl) {
		JSONObject cvedio = new JSONObject();
		cvedio.put("mrl", mrl);
//		JSONObject result = RestUtil.deleteVarPar(server + "/nuas/api/v1/video/conference/" + number + "/live", null,
		JSONObject result = null;
		return result;
	}

	// 直播频道
	public static JSONObject createVideoLive(String token, String groupId) {
//		JSONObject result = RestUtil.postZy(server + "/nuas/api/v1/gmcc/live/" + groupId, null, token);
		JSONObject result = null;
		return result;
	}

	// 添加组织架构
	public static JSONObject addOrg(JSONObject o, String token) {
//		JSONObject result = RestUtil.postZy(server + "/nuas/api/v1/organization", o, token);
		JSONObject result = null;
		return result;
	}

	// 获取组织架构集合数据
	public static JSONObject getOrList(String token) {
		// ?page=0&per_page=1
//		JSONObject result = RestUtil.getUrl(server + "/nuas/api/v1/organization?page=0&per_page=1000", token);
		JSONObject result = null;
		return result;
	}

	// 获取GMCC
	public static JSONObject getGMCC(String token, String x) {
		if(x.equals("")) {
//			JSONObject result = RestUtil.getUrl(server + "/nuas/api/v1/gmcc/media/info?nx=", token);
			JSONObject result = null;
			return result;
		}else {
//			JSONObject result = RestUtil.getUrl(server + "/nuas/api/v1/gmcc/media/info?nx="+x, token);
			JSONObject result = null;
			return result;
		}
	}

	// 获取GMCC
	public static JSONObject getCamera(String token) {
//		JSONObject result = RestUtil.getUrl(server + "/nuas/api/v1/gmcc/camera", token);
		JSONObject result = null;
		return result;
	}
}
