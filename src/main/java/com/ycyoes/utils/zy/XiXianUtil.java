package com.ycyoes.utils.zy;

import java.util.ArrayList;
import java.util.Date;

import org.jeecg.common.util.RestUtil;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Administrator
 *
 */
public class XiXianUtil {
	public static  final String fd="61980100000000";
	public static  final String kg="61980200000000";
	public static  final String qh="61980300000000";
	public static  final String fx="61980400000000";
	public static  final String jh="61980500000000";
	//网格 消防，医疗，治安，民政局，公安局，应急管理局对应1234567
	public static  final Integer depSignWG=1;
	public static  final Integer depSignXF=2;
	public static  final Integer depSignYL=3;
	public static  final Integer depSignZA=4;
	public static  final Integer depSignMZJ=5;
	public static  final Integer depSignGAJ=6;
	public static  final Integer depSignYJGLJ=7;
	//18710477736
	public static final String telephone="7000";
	//设置全局网格级别2、3、4、5、6
	public static  final Integer level=2;
	//设置全局数据1真0假
	public static  boolean  datas=false;
	public static String ApiKey = "gns11529c136998cb6";
	
	private static String server = "https://xixian-test.spacecig.com:18443/zhzlApi/";
	// 网格下的网格员列表
	private static String memberListByGird = "gridMemberQuery/gridMemberList";
	// 网格人员详情
	private static String girdMemberInfoByUserId = "gridMemberQuery/gridMemberDetails";
	// 根据网格人员查询网格详情
	private static String gridInfoByUserId = "gridMemberQuery/gridInfo";
	// 根据部门id查询部门人员
	private static String deptUserListByDepId = "gridMemberQuery/departmentUserList";
	private static String cityComponentsDetailsById = "wdglApi/cityComponentsApi/cityComponentsDetailsById";
	// 部件相关
	private static String cityComponentsQuery = "wdglApi/cityComponentsApi/cityComponentsQuery";
	// 场所列表
	private static String cityPlaceQuery = "wdglApi/cityComponentsApi/cityPlaceQuery";
	// 场所详情
	private static String cityPlaceDetails = "wdglApi/cityComponentsApi/cityPlaceDetails";
	// 学校列表查询
	private static String citySchoolQuery = "wdglApi/cityComponentsApi/citySchoolQuery";
	// 学校详情查询
	private static String schoolDetails = "wdglApi/cityComponentsApi/schoolDetails";
	// 应急事件查询
	private static String eventQuery = "yingJi/event/eventQuery";
	// 事件来源统计
	private static String eventCountBySource = "yingJi/event/eventCountBySource";
	// 事件根据新城统计
	private static String eventCountByXinCheng = "yingJi/event/eventCountByXinCheng";
	private static String getGridHierarchy ="gridMemberQuery/getGridHierarchy";
	private static String getGridListByZuoBiao ="yingJi/areaCount/getDepartInfo";
	private static String getPlaceInfoListByZuoBiao ="yingJi/areaCount/getPlaceInfo";


	
	// 根据 departmentId,placeTypeMax,placeType 查询场所列表
		public static JSONObject cityPlaceQuery(String departmentId, String placeTypeMax, String placeType) {
			JSONObject result = new JSONObject();
			JSONObject cityPlace = new JSONObject();
			if (departmentId != null && !departmentId.equals("")) {
				cityPlace.put("departmentId", departmentId);
			} else {
				result.put("departmentId", "departmentId不能为空!");
				return result;
			}
			if (placeTypeMax != null && !placeTypeMax.equals("")) {
				cityPlace.put("placeTypeMax", placeTypeMax);
			}
			if (placeType != null && !placeType.equals("")) {
				cityPlace.put("placeType", placeType);
			}

			String jsonS = XixianRestUtil.get(server + cityPlaceQuery, cityPlace);
			result = JSONObject.parseObject(jsonS);
			return result;
		}

		// 根据 qucode,id查询场所详情
		public static JSONObject cityPlaceDetails(String qucode, String id) {
			JSONObject result = new JSONObject();
			JSONObject Details = new JSONObject();
			if (qucode != null && !qucode.equals("")) {
				Details.put("qucode", qucode);
			} else {
				result.put("departmentId", "departmentId不能为空!");
				return result;
			}
			if (id != null && !id.equals("")) {
				Details.put("id", qucode);
			} else {
				result.put("id", "id不能为空!");
				return result;
			}
			String jsonS = XixianRestUtil.get(server + cityPlaceDetails, Details);
			result = JSONObject.parseObject(jsonS);
			return result;
		}
		// 根据 deptId,查询网格上下级
				public static JSONObject gridCengJiByDeptId(String deptId) {
					JSONObject result = new JSONObject();
					JSONObject Details = new JSONObject();
					if (deptId != null && !deptId.equals("")) {
						Details.put("deptId", deptId);
					} else {
						result.put("deptId", "deptId不能为空!");
						return result;
					}
					String jsonS = XixianRestUtil.get(server + getGridHierarchy, Details);
					result = JSONObject.parseObject(jsonS);
					return result;
				}
		
	
	// 根据网格id获取网格员列表
	public static JSONObject memberListByGird(String gird) {
		JSONObject result = new JSONObject();
		if (gird != null && !gird.equals("")) {
			JSONObject createCall = new JSONObject();
			createCall.put("deptId", gird);
			createCall.put("total", 1);
			String jsonS = XixianRestUtil.get(server + memberListByGird, createCall);
			result = JSONObject.parseObject(jsonS);
		} else {
			result.put("data", "gird不能为空!");
		}

		return result;
	}

	// 根据网格员id获取网格员信息
	public static JSONObject girdMemberInfoByUserId(String xinquCode, String userId) {
		JSONObject result = new JSONObject();
		if (xinquCode == null || xinquCode.equals("")) {
			result.put("data", "xinquCode不能为空!");
			return result;
		} else if (userId == null || userId.equals("")) {
			result.put("data", "userId不能为空!");
			return result;
		} else {
			JSONObject createCall = new JSONObject();
			createCall.put("userId", userId);
			createCall.put("code", xinquCode);
			String jsonS = XixianRestUtil.get(server + girdMemberInfoByUserId, createCall);
			result = JSONObject.parseObject(jsonS);
		}
		return result;

	}
	
	// 根据坐标获取场所员信息
		public static JSONObject getPlaceInfoListByZuoBiao(String lng, String lat,String fanwei) {
			JSONObject result = new JSONObject();
			if (lng == null || lng.equals("")) {
				result.put("lng", "经度不能为空!");
				return result;
			} else if (lat == null || lat.equals("")) {
				result.put("lat", "纬度不能为空!");
				return result;
			} else {
				JSONObject createCall = new JSONObject();
				createCall.put("x", lng);
				createCall.put("y", lat);
				String jsonS = XixianRestUtil.get(server + getPlaceInfoListByZuoBiao, createCall);
				result = JSONObject.parseObject(jsonS);
			}
			return result;

		}
		// 根据坐标获网格员信息
				public static JSONObject getGridListByZuoBiao(String lng, String lat,String fanwei) {
					JSONObject result = new JSONObject();
					if (lng == null || lng.equals("")) {
						result.put("lng", "经度不能为空!");
						return result;
					} else if (lat == null || lat.equals("")) {
						result.put("lat", "纬度不能为空!");
						return result;
					} else {
						JSONObject createCall = new JSONObject();
						createCall.put("x", lng);
						createCall.put("y", lat);
						String jsonS = XixianRestUtil.get(server + getGridListByZuoBiao, createCall);
						result = JSONObject.parseObject(jsonS);
					}
					return result;

				}

	// 根据网格员id获取网格信息
	public static JSONObject gridInfoByUserId(String xinquCode, String userId) {
		JSONObject result = new JSONObject();
		if (xinquCode == null || xinquCode.equals("")) {
			result.put("data", "xinquCode不能为空!");
			return result;
		} else if (userId == null || userId.equals("")) {
			result.put("data", "userId不能为空!");
			return result;
		} else {
			JSONObject createCall = new JSONObject();
			createCall.put("userId", userId);
			createCall.put("code", xinquCode);
			String jsonS = XixianRestUtil.get(server + gridInfoByUserId, createCall);
			result = JSONObject.parseObject(jsonS);
		}
		return result;

	}

	/**
	 * @param depId
	 * @return
	 * @dec根据部门id查询部门人员列表
	 */

	public static JSONObject deptUserListByDepId(String depId) {
		JSONObject result = new JSONObject();
		if (depId != null && !depId.equals("")) {
			JSONObject createCall = new JSONObject();
			createCall.put("depId", depId);
			createCall.put("total", 1);
			String jsonS = XixianRestUtil.get(server + deptUserListByDepId, createCall);
			result = JSONObject.parseObject(jsonS);
		} else {
			result.put("data", "depId不能为空!");
		}

		return result;
	}

	// 根据组件id查询组件详情
	public static JSONObject cityComponentsDetailsById(String objCode) {
		JSONObject result = new JSONObject();
		if (objCode != null && !objCode.equals("")) {
			JSONObject createCall = new JSONObject();
			createCall.put("objCode", objCode);
			String jsonS = XixianRestUtil.get(server + cityComponentsDetailsById, createCall);
			result = JSONObject.parseObject(jsonS);
		} else {
			result.put("data", "depId不能为空!");
		}

		return result;
	}
	
	// 获取Token
		public static JSONObject loginUser() {
			String token="";
			String id="";
			String displayName="";
			JSONObject login = ZhenYouUtil.login();
			JSONObject login1 = new JSONObject();
			if(login!=null) {
				JSONObject status = login.getJSONObject("status");
				if(status!=null) {
					Integer code=status.getInteger("code");
					if(code==0) {
						JSONObject data = login.getJSONObject("data");
						if(data!=null) {
							token=data.getString("token");
							JSONObject userInfo=data.getJSONObject("userInfo");
							displayName=userInfo.getString("displayName");
							id=userInfo.getString("id");
							
						}
						
					}
				}
				
			}
			login1.put("token", token);
			login1.put("id", id);
			login1.put("name", displayName);
			return login1;
		}
		// 根据组件id查询组件详情
		public static String zhoboToken(String UserId) {
			String token="";
			ArrayList<String> arr = new ArrayList<>();
			String Timestamp = String.valueOf(new Date().getTime());
			arr.add(ApiKey);
			arr.add(Timestamp);
			arr.add(UserId);
			// 按值字典排序
			String codeSort = RestUtil.sort(arr);
			// SHA-1加密生成Signature
			String Signature = RestUtil.sha(codeSort);
			token=UserId+"_"+Timestamp+"_"+Signature;
			return token;
		}
	// 根据组件id查询组件详情
	public static JSONObject eventCountByXinCheng() {
		JSONObject result = new JSONObject();
		String jsonS = XixianRestUtil.get(server + eventCountByXinCheng);
		result = JSONObject.parseObject(jsonS);
		return result;
	}

	
}
