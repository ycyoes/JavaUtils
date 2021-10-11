package com.ycyoes.test.huawei;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

public class SendDiffSms {

    //无需修改,用于格式化鉴权头域,给"X-WSSE"参数赋值
    private static final String WSSE_HEADER_FORMAT = "UsernameToken Username=\"%s\",PasswordDigest=\"%s\",Nonce=\"%s\",Created=\"%s\"";
    //无需修改,用于格式化鉴权头域,给"Authorization"参数赋值
    private static final String AUTH_HEADER_VALUE = "WSSE realm=\"SDP\",profile=\"UsernameToken\",type=\"Appkey\"";


    public static void main(String[] args) {

        //必填,请参考"开发准备"获取如下数据,替换为实际值
        String url = "https://rtcsms.ap-southeast-1.myhuaweicloud.com:443/sms/batchSendDiffSms/v1"; //APP接入地址+接口访问URI
        String appKey = "c8RWg3ggEcyd4D3p94bf3Y7x1Ile"; //Application Key
        String appSecret = "q4Ii87BhST9vcs8wvrzN80SfD7Al"; //Application Secret
        String sender = "csms12345678"; //中国大陆短信签名通道号或全球短信通道号
        String templateId1 = "8ff55eac1d0b478ab3c06c3c6a492300"; //模板ID1
        String templateId2 = "8ff55eac1d0b478ab3c06c3c6a492300"; //模板ID2

        //条件必填,中国大陆短信关注,当templateId指定的模板类型为通用模板时生效且必填,必须是已审核通过的,与模板类型一致的签名名称
        //全球短信不用关注该参数
        String signature1 = "华为云短信测试"; //签名名称1
        String signature2 = "华为云短信测试"; //签名名称2

        //必填,全局号码格式(包含国家码),示例:+8615123456789,多个号码之间用英文逗号分隔
        String[] receiver1 = {"+8615123456789", "+8615234567890"}; //模板1的接收号码
        String[] receiver2 = {"+8615123456789", "+8615234567890"}; //模板2的接收号码

        //选填,短信状态报告接收地址,推荐使用域名,为空或者不填表示不接收状态报告
        String statusCallBack = "";

        /**
         * 选填,使用无变量模板时请赋空值 String[] templateParas = {};
         * 单变量模板示例:模板内容为"您的验证码是${NUM_6}"时,templateParas可填写为{"369751"}
         * 双变量模板示例:模板内容为"您有${NUM_2}件快递请到${TXT_20}领取"时,templateParas可填写为{"3","人民公园正门"}
         * 查看更多模板和变量规范:产品介绍>模板和变量规范
         */
        String[] templateParas1 = {"123456"}; //模板1变量，此处以单变量验证码短信为例，请客户自行生成6位验证码，并定义为字符串类型，以杜绝首位0丢失的问题（例如：002569变成了2569）。
        String[] templateParas2 = {"234567"}; //模板2变量，此处以单变量验证码短信为例，请客户自行生成6位验证码，并定义为字符串类型，以杜绝首位0丢失的问题（例如：002569变成了2569）。

        //smsContent,不携带签名名称时,signature请填null
        List<Map<String, Object>> smsContent = new ArrayList<Map<String, Object>>();
        Map<String, Object> item1 = initDiffSms(receiver1, templateId1, templateParas1, signature1);
        Map<String, Object> item2 = initDiffSms(receiver2, templateId2, templateParas2, signature2);
        if (null != item1 && !item1.isEmpty()) {
            smsContent.add(item1);
        }
        if (null != item2 && !item2.isEmpty()) {
            smsContent.add(item2);
        }
    }

    /**
     * 构造smsContent参数值
     * @param receiver
     * @param templateId
     * @param templateParas
     * @param signature | 签名名称,使用中国大陆短信通用模板时填写
     * @return
     */
    static Map<String, Object> initDiffSms(String[] receiver, String templateId, String[] templateParas,
                                           String signature) {
        if (null == receiver || null == templateId || receiver.length == 0 || templateId.isEmpty()) {
            System.out.println("initDiffSms(): receiver or templateId is null.");
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("to", receiver);
        map.put("templateId", templateId);
        if (null != templateParas && templateParas.length > 0) {
            map.put("templateParas", templateParas);
        }
        if (null != signature && !signature.isEmpty()) {
            map.put("signature", signature);
        }

        return map;
    }

    /**
     * 构造请求Body体
     * @param sender
     * @param smsContent
     * @param statusCallbackUrl
     * @return
     */
    static String buildRequestBody(String sender, List<Map<String, Object>> smsContent,
                                   String statusCallbackUrl) {
        if (null == sender || null == smsContent || sender.isEmpty() || smsContent.isEmpty()) {
            System.out.println("buildRequestBody(): sender or smsContent is null.");
            return null;
        }
        JSONArray jsonArr = new JSONArray();

        for(Map<String, Object> it: smsContent){
            jsonArr.put(it);
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("from", sender);
        data.put("smsContent", jsonArr);
        if (null != statusCallbackUrl && !statusCallbackUrl.isEmpty()) {
            data.put("statusCallback", statusCallbackUrl);
        }

        return new JSONObject(data).toString();
    }

    /**
     * 构造X-WSSE参数值
     * @param appKey
     * @param appSecret
     * @return
     */
    static String buildWsseHeader(String appKey, String appSecret) {
        if (null == appKey || null == appSecret || appKey.isEmpty() || appSecret.isEmpty()) {
            System.out.println("buildWsseHeader(): appKey or appSecret is null.");
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String time = sdf.format(new Date()); //Created
        String nonce = UUID.randomUUID().toString().replace("-", ""); //Nonce

        byte[] passwordDigest = DigestUtils.sha256(nonce + time + appSecret);
        String hexDigest = Hex.encodeHexString(passwordDigest);

        //如果JDK版本是1.8,请加载原生Base64类,并使用如下代码
        String passwordDigestBase64Str = Base64.getEncoder().encodeToString(hexDigest.getBytes()); //PasswordDigest
        //如果JDK版本低于1.8,请加载三方库提供Base64类,并使用如下代码
        //String passwordDigestBase64Str = Base64.encodeBase64String(hexDigest.getBytes(Charset.forName("utf-8"))); //PasswordDigest
        //若passwordDigestBase64Str中包含换行符,请执行如下代码进行修正
        //passwordDigestBase64Str = passwordDigestBase64Str.replaceAll("[\\s*\t\n\r]", "");

        return String.format(WSSE_HEADER_FORMAT, appKey, passwordDigestBase64Str, nonce, time);
    }

}
