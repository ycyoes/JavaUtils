package com.ycyoes.test.calculate;

//import com.anysoft.util.BaseException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * HmacSHA256编码
 * 
 */
public class HmacSHA256 {
	protected static final Logger LOG = LoggerFactory.getLogger(HmacSHA256.class);
	
	protected ThreadLocal<Mac> macLocal = new ThreadLocal<Mac>(){
        protected Mac initialValue() {
        	try {
        		return Mac.getInstance(getAlgorithm());
        	}catch (Exception ex){
    			LOG.error(ExceptionUtils.getStackTrace(ex));      
    			return null;
        	}
        };
    };
	
	public String getAlgorithm() {
		return "HmacSHA256";
	}
	
	public String encode(String data, String key) {
		try {
        	byte [] byteKey = Base64.decodeBase64(key);
			for (int i = 0; i < byteKey.length; i++) {
				System.out.print(byteKey[i] + " ");
			}
			System.out.println("byte: " + byteKey);
			String str = new String(byteKey);
			LOG.info("key:" + new String(byteKey));
        	SecretKey secretKey = new SecretKeySpec(byteKey, getAlgorithm());
            Mac mac = macLocal.get();
            mac.init(secretKey);  
            byte[] bytes = mac.doFinal(data.getBytes());
			System.out.println("-------------result1---------");
			for (int i = 0; i < bytes.length; i++) {
				System.out.print(bytes[i] + " ");
			}
			System.out.println("-------------result2---------");
						LOG.info("result:" + new String(bytes));
            return Base64.encodeBase64URLSafeString(bytes);
		}catch (Exception ex){
			LOG.error(ExceptionUtils.getStackTrace(ex));
			return data;
		}
	}

	public String decode(String data, String key) {
		return data;
	}

	public String createKey() {
		try {
			KeyGenerator generator = KeyGenerator.getInstance(getAlgorithm());
	        SecretKey key = generator.generateKey();
	        return Base64.encodeBase64URLSafeString(key.getEncoded());
		} catch (NoSuchAlgorithmException e) {
//			throw new BaseException("core.e1000",ExceptionUtils.getStackTrace(e));
			System.out.println(e);
		}
		return null;
	}
	
	public String createKey(String key){
		return createKey();
	}



	// validate签名
	public static void main(String[] args) {
		String appId = "152999073894506103";
		String appSecret = "IvKWNwM6UE4dqRXeF2GZcPJi5ynr17BbTx3m0ahCAL9";
		String service = "http://consoletest.ctyun.com.cn/login/?next=/console/index/";
//		String service = "https%3A%2F%2Fwww.tcyunsc.cn%3A8888%2Fmanagement%2Fapi%2Fv1%2Fauth%2Fwebsso";
		String ticket = "81f4834f-b2cf-4ceb-ac7e-35d35dd371a2";
		String now = "1629376163656";
//		String now = System.currentTimeMillis()+"";
		HmacSHA256 coder = new HmacSHA256();
		StringBuffer toSign = new StringBuffer();
		toSign.append(appId).append("@");
		toSign.append(service).append("@");
		toSign.append(ticket).append("@");
		toSign.append(now);
		String signed = coder.encode(toSign.toString(),
				appSecret);
		LOG.info("签名文本:\n"
				+ toSign
				+ "\n生成的签名是:\n"
				+ signed);

		LOG.info("地址:\n" + "https://www.ctyun.cn/cas/validate?service="+service+"&ticket="+ticket+"&timestamp="+now+"&appId="+appId+"&signature="+signed);
//		LOG.info("地址:\n" + "https://www.ctyun.cn/cas/validate?service="+service+"&ticket="+ticket+"&timestamp="+"&appId="+appId+"&signature="+signed);
	}

}


