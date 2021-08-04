package com.tt;

import com.bytedance.caijing.tt_pay.TTPayLog;
import com.bytedance.caijing.tt_pay.TTPayService;
import com.bytedance.caijing.tt_pay.model.TradeCreateRequest;
import com.bytedance.caijing.tt_pay.model.TradeCreateResponse;

public class MicroAppDemo {
    public static void main(String[] args) {
        // log级别可配置为 debug，info，warn，error
        TTPayLog.logLevel = TTPayLog.LogLevel.debug;
        // 小程序平台开通支付后，分配给开发者的app_id（非小程序的appid），用于获取 签名/验签 的密钥信息
        TTPayService.appId = "800000057774";
        // 小程序平台开通支付后，分配给开发者的支付密钥(非小程序的app_secret)
        TTPayService.appSecret = "CaiJingQianBaoTongYiCeShi";
        // 小程序平台开通支付后，分配给开发者的merchant_id
        TTPayService.merchantId = "1300000005";
        
        TradeCreateRequest request = TradeCreateRequest.builder()
                // 此处是随机生成的，使用时请填写您的业务订单号
        		.outOrderNo("" + System.currentTimeMillis())
                // 填写用户的唯一标识
                .uid("11131318505533333333333333333333333")
                // 填写订单金额，分为单位，不能有小数点
                .totalAmount(1L)
                // 填写您的订单名称
                .subject("gogokid精品课")
                // 填写您的订单内容
                .body("gogokid精品课，56节")
                // 交易时间，此处自动生成，您也可以根据需求赋值，但必须为Unix时间戳
                // 同一笔订单两次发起支付时，tradeTime要传相同的值，否则拉起收银台会失败
                .tradeTime("" + System.currentTimeMillis() / 1000)
                // 填写您的订单有效时间（单位：秒）
                .validTime("36000000")
                // 严格json字符串格式。ip需要为用户的真实ip地址
                .riskInfo("{\"ip\":\"100.120.154.110\"}")             
                // 调用微信H5支付统一下单接口返回的mweb_url字段值。如果值为空，则收银台不会展示微信支付;
                .wxUrl("https://wx.tenpay.com/cgi-bin/mmpayweb-bin/checkmweb?prepay_id=wx12203020777710874a3578888880278600&package=2202098580")
                // wx_url有值时，传固定值：MWEB; wx_url无值时，传空
                .wxType("MWEB")                                 
                // 调用支付宝App支付的签名订单信息，详见支付宝App支付请求参数说明。如果值为空，则收银台不会展示支付宝支付;
                .alipayUrl("alipay_sdk=alipay-sdk-java-4.8.73.ALL&app_id=2019022763436277&biz_content=%7B%22body%22%3A%22%E5%A7%93%E5%90%8D%E5%A5%91%E5%90%88%E5%BA%A6%E9%85%8D%E5%AF%B9%22%2C%22out_trade_no%22%3A%225eba96cfb3b7c63c3beaac11%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%A7%93%E5%90%8D%E5%A5%91%E5%90%88%E5%BA%A6%E9%85%8D%E5%AF%B9%22%2C%22timeout_express%22%3A%2260m%22%2C%22total_amount%22%3A%221%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=https%3A%2F%2Fwww.adota.cn%2Fwxpro%2Fpay%2Fali%2Fcallback&sign=LWCZVmaUQIjAqlkY0yHzgHIn6H8O3G3l%2FrJKW4s3aEpeneRcR8P8Ek2DbnSXBaegD7ifbkRmm1%2FE%2BYPccjR2PKSp5EIe2zbQFYt83QguPzO4ZAhwaOFCwpuO%2BHy2mt1uqtZ8iszzL8BTwBKjA76p3D53NWdBaIETl9sNnJuwU0u%2FNREcWVVDHR0Mr7tJWgo9MwpYEv%2BEBwxEGuKvFkwQ6IWQT3bBP58V8TTzrMsqPDerQaXEzXslJWeeYKqcnPdsu%2FjUNETz5LWjKB5rxjLuldUXt0UWxWuAXTACe3WOeDI0M7Qg1o7rY3eEBpRKxgaAHf1uHUZVUo4ayndDEBtVsw%3D%3D&sign_type=RSA2&timestamp=2020-05-12+20%3A30%3A07&version=1.0") 
                .build();
        try {
            TradeCreateResponse response = TTPayService.TradeCreate(request);
            String orderInfo = response.getAppletParams();
            System.out.println("tt.pay's orderInfo params: " + orderInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
