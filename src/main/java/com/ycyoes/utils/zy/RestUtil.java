package com.ycyoes.utils.zy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * 调用 Restful 接口 Util
 *
 * @author fenghx
 */


public class RestUtil {

    private static String domain = null;

    /**
     * 震友RestAPI 调用器
     */
    private final static RestTemplate RT;

    static {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1000000);
        requestFactory.setReadTimeout(1000000);
        RT = new RestTemplate(requestFactory);
        // 解决乱码问题
        RT.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }

    public static RestTemplate getRestTemplate() {
        return RT;
    }

    /**
     * 发送 get 请求
     */
    public static JSONObject get(String url) {
        return getNative(url, null, null).getBody();
    }

    /**
     * 发送 get 请求
     */
    public static JSONObject get(String url, JSONObject variables) {
        return getNative(url, variables, null).getBody();
    }

    /**
     * 发送 get 请求
     */
    public static JSONObject get(String url, JSONObject variables, JSONObject params) {
        return getNative(url, variables, params).getBody();
    }

    /**
     * 发送 get 请求，返回原生 ResponseEntity 对象
     */
    public static ResponseEntity<JSONObject> getNative(String url, JSONObject variables, JSONObject params) {
        return request(url, HttpMethod.GET, variables, params);
    }

    /**
     * 发送 Post 请求
     */
    public static JSONObject post(String url) {
        return postNative(url, null, null).getBody();
    }

    /**
     * 发送 Post 请求
     */
    public static JSONObject post(String url, JSONObject params) {
        return postNative(url, null, params).getBody();
    }

    /**
     * 发送 Post 请求
     */
    public static JSONObject post(String url, JSONObject variables, JSONObject params) {
        return postNative(url, variables, params).getBody();
    }

    /**
     * 发送 POST 请求，返回原生 ResponseEntity 对象
     */
    public static ResponseEntity<JSONObject> postNative(String url, JSONObject variables, JSONObject params) {
        return request(url, HttpMethod.POST, variables, params);
    }

    /**
     * 发送 put 请求
     */
    public static JSONObject put(String url) {
        return putNative(url, null, null).getBody();
    }

    /**
     * 发送 put 请求
     */
    public static JSONObject put(String url, JSONObject params) {
        return putNative(url, null, params).getBody();
    }

    /**
     * 发送 put 请求
     */
    public static JSONObject put(String url, JSONObject variables, JSONObject params) {
        return putNative(url, variables, params).getBody();
    }

    /**
     * 发送 put 请求，返回原生 ResponseEntity 对象
     */
    public static ResponseEntity<JSONObject> putNative(String url, JSONObject variables, JSONObject params) {
        return request(url, HttpMethod.PUT, variables, params);
    }

    /**
     * 发送 delete 请求
     */
    public static JSONObject delete(String url) {
        return deleteNative(url, null, null).getBody();
    }

    /**
     * 发送 delete 请求
     */
    public static JSONObject delete(String url, JSONObject variables, JSONObject params) {
        return deleteNative(url, variables, params).getBody();
    }

    /**
     * 发送 delete 请求，返回原生 ResponseEntity 对象
     */
    public static ResponseEntity<JSONObject> deleteNative(String url, JSONObject variables, JSONObject params) {
        return request(url, HttpMethod.DELETE, null, variables, params, JSONObject.class);
    }

    /**
     * 发送请求
     */
    public static ResponseEntity<JSONObject> request(String url, HttpMethod method, JSONObject variables,
                                                     JSONObject params) {
        return request(url, method, getHeaderApplicationJson(), variables, params, JSONObject.class);
    }

    /**
     * 发送请求
     *
     * @param url          请求地址
     * @param method       请求方式
     * @param headers      请求头 可空
     * @param variables    请求url参数 可空
     * @param params       请求body参数 可空
     * @param responseType 返回类型
     * @return ResponseEntity<responseType>
     */
    public static <T> ResponseEntity<T> request(String url, HttpMethod method, HttpHeaders headers,
                                                JSONObject variables, Object params, Class<T> responseType) {
        if (StringUtils.isEmpty(url)) {
            throw new RuntimeException("url 不能为空");
        }
        if (method == null) {
            throw new RuntimeException("method 不能为空");
        }
        if (headers == null) {
            headers = new HttpHeaders();
        }
        // 请求体
        String body = "";
        if (params != null) {
            if (params instanceof JSONObject) {
                body = ((JSONObject) params).toJSONString();

            } else {
                body = params.toString();
            }
        }
        // 拼接 url 参数
        if (variables != null) {
            url += ("?" + asUrlVariables(variables));
        }
        // 发送请求
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        return RT.exchange(url, method, request, responseType);
    }
    public static JSONObject post2(String url, HttpMethod method,String token,String[] ids) {
        return request2(url, method,getHeaderApplicationJsonZy(token),JSONObject.class,ids).getBody();
    }
    public static JSONObject post3(String url, HttpMethod method,String token,JSONArray jsonArray) {
        return request3(url, method,getHeaderApplicationJsonZy(token),JSONObject.class,jsonArray).getBody();
    }
    public static <T> ResponseEntity<T> request2(String url, HttpMethod method, HttpHeaders headers,
                                                 Class<T> responseType,String[] ids) {
        if (StringUtils.isEmpty(url)) {
            throw new RuntimeException("url 不能为空");
        }
        if (method == null) {
            throw new RuntimeException("method 不能为空");
        }
        if (headers == null) {
            headers = new HttpHeaders();
        }

        // 发送请求
        HttpEntity<String[]> request = new HttpEntity<>(ids, headers);

        return RT.exchange(url, method, request, responseType);
    }
    public static <T> ResponseEntity<T> request3(String url, HttpMethod method, HttpHeaders headers,
                                                 Class<T> responseType,JSONArray jsonArray) {
        if (StringUtils.isEmpty(url)) {
            throw new RuntimeException("url 不能为空");
        }
        if (method == null) {
            throw new RuntimeException("method 不能为空");
        }
        if (headers == null) {
            headers = new HttpHeaders();
        }

        // 发送请求
        HttpEntity<JSONArray> request = new HttpEntity<>(jsonArray, headers);

        return RT.exchange(url, method, request, responseType);
    }

    /**
     * 获取JSON请求头
     */
    public static HttpHeaders getHeaderApplicationJson() {
        return getHeader(MediaType.APPLICATION_JSON_UTF8_VALUE);
    }

    /**
     * 获取请求头
     */
    public static HttpHeaders getHeader(String mediaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(mediaType));
        headers.add("Accept", mediaType);
        return headers;
    }

    /**
     * 将 JSONObject 转为 a=1&b=2&c=3...&n=n 的形式
     */
    public static String asUrlVariables(JSONObject variables) {
        Map<String, Object> source = variables.getInnerMap();
        Iterator<String> it = source.keySet().iterator();
        StringBuilder urlVariables = new StringBuilder();
        while (it.hasNext()) {
            String key = it.next();
            String value = "";
            Object object = source.get(key);
            if (object != null) {
                if (!StringUtils.isEmpty(object.toString())) {
                    value = object.toString();
                }
            }
            urlVariables.append("&").append(key).append("=").append(value);
        }
        // 去掉第一个&
        return urlVariables.substring(1);
    }
    /**
     * 发送 get 请求
     */
    public static JSONObject getUrl(String url,String token) {
        return getNativeZy(url, null, null,token).getBody();
    }

    /**
     * 发送 get 请求
     */
    public static JSONObject getVariable(String url, JSONObject variables,String token) {
        return getNativeZy(url, variables, null,token).getBody();
    }

    /**
     * 发送 get 请求
     */
    public static JSONObject getParams(String url, JSONObject variables, JSONObject params,String token) {
        return getNativeZy(url, variables, params,token).getBody();
    }
    /**
     * 发送 get 请求，返回原生 ResponseEntity 对象
     */
    public static ResponseEntity<JSONObject> getNativeZy(String url, JSONObject variables, JSONObject params,String token) {
        return requestZy(url, HttpMethod.GET, variables, params,token);
    }

    /**
     * 发送 put 请求
     */
    public static JSONObject putUrl(String url,String token) {
        return putNativeZy(url, null, null,token).getBody();
    }

    /**
     * 发送 put 请求
     */
    public static JSONObject putParams(String url, JSONObject params,String token) {
        return putNativeZy(url, null, params,token).getBody();
    }

    /**
     * 发送 put 请求
     */
    public static JSONObject putVarPar(String url, JSONObject variables, JSONObject params,String token) {
        return putNativeZy(url, variables, params,token).getBody();
    }

    /**
     * 发送 put 请求，返回原生 ResponseEntity 对象
     */
    public static ResponseEntity<JSONObject> putNativeZy(String url, JSONObject variables, JSONObject params,String token) {
        return requestZy(url, HttpMethod.PUT, variables, params,token);
    }

    /**
     * 发送 delete 请求
     */
    public static JSONObject deleteUrl(String url,String token) {
        return deleteNativeZy(url, null, null,token).getBody();
    }

    /**
     * 发送 delete 请求
     */
    public static JSONObject deleteVarPar(String url, JSONObject variables, JSONObject params,String token) {
        return deleteNativeZy(url, variables, params,token).getBody();
    }

    /**
     * 发送 delete 请求，返回原生 ResponseEntity 对象
     */
    public static ResponseEntity<JSONObject> deleteNativeZy(String url, JSONObject variables, JSONObject params,String token) {
        //return request(url, HttpMethod.DELETE, null, variables, params, JSONObject.class);
        return requestZyDel(url, HttpMethod.DELETE,variables, params,token);
    }


    /**
     * 发送 Post 请求
     */
    public static JSONObject postZy(String url, JSONObject params,String token) {
        return postNativeZy(url, null, params,token).getBody();
    }

    /**
     * 发送 POST 请求，返回原生 ResponseEntity 对象
     */
    public static ResponseEntity<JSONObject> postNativeZy(String url, JSONObject variables, JSONObject params,String token) {
        return requestZy(url, HttpMethod.POST, variables, params,token);
    }

    public static ResponseEntity<JSONObject> requestZy(String url, HttpMethod method, JSONObject variables,
                                                       JSONObject params,String token) {
        return request(url, method, getHeaderApplicationJsonZy(token), variables, params, JSONObject.class);
    }
    /**
     * 发送请求
     */
    public static ResponseEntity<JSONObject> requestZyDel(String url, HttpMethod method, JSONObject variables,JSONObject params,String token) {
        return request(url, method, getHeaderApplicationJsonZy(token), variables, params, JSONObject.class);
    }
    /**
     * 获取JSON请求头
     */
    public static HttpHeaders getHeaderApplicationJsonZy(String token) {
        return getHeaderZy(MediaType.APPLICATION_JSON_UTF8_VALUE,token);
    }

    /**
     * 获取请求头
     */
    public static HttpHeaders getHeaderZy(String mediaType,String Token) {
        HttpHeaders headers = new HttpHeaders();
        ArrayList<String> arr = new ArrayList<>();
        headers.setContentType(MediaType.parseMediaType(mediaType));
        // 获取随机数
        String nonce = String.valueOf(new Random().nextInt());
        // 获取时间戳
        String Timestamp = String.valueOf(new Date().getTime());
        String ApiKey = "gns11529c136998cb6";
        arr.add(ApiKey);
        arr.add(Timestamp);
        arr.add(nonce);
        System.out.println("arr before: " + arr);
        // 按值字典排序
        String codeSort = sort(arr);
        System.out.println("arr after: " + codeSort);
        // SHA-1加密生成Signature
        String Signature = sha(codeSort);
        // 加入请求头
        headers.add("Accept", mediaType);
        headers.add("X-Signature", Signature);
        headers.add("X-Timestamp", Timestamp);
        headers.add("X-Nonce", nonce);
		/*System.out.println("-----------------------开始:headers-----------------------");
		System.out.println("X-codeSort:"+codeSort);
		System.out.println("X-Signature:"+Signature);
		System.out.println("X-Timestamp:"+Timestamp);
		System.out.println("X-Nonce:"+nonce);*/
        if(Token !=null && !Token.equals("")) {
            headers.add("X-Token", Token);
        }
		 /*System.out.println("X-Token:"+Token);
		 System.out.println("-----------------------结束:headers-----------------------");*/
        return headers;
    }

    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f' };

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    public static String sha(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String sort(ArrayList<String> arr) {
        String codeSort = "";
        Collections.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                try {
                    String str1 = new String(o1.toString().getBytes("GB2312"), "ISO-8859-1");
                    String str2 = new String(o2.toString().getBytes("GB2312"), "ISO-8859-1");
                    return str1.compareTo(str2);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                return 0;
            }
        });
        for (int i = 0; i < arr.size(); i++) {
            codeSort += arr.get(i);
        }
        return codeSort;
    }
}
