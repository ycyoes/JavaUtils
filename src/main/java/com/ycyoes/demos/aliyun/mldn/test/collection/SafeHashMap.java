package com.ycyoes.demos.aliyun.mldn.test.collection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SafeHashMap {
    public static void main(String[] args) throws Exception {
        Map<String, Object> map = Collections.synchronizedMap(new HashMap<>());
        map.put("test", "test");
        map.put("1", "1");
        System.out.println(map);

        map = new HashMap<>();
        map.put("1", "2");
        //获取map集合默认容量大小
        Class<?> clz = map.getClass();
        Method capacity = clz.getDeclaredMethod("capacity");
        capacity.setAccessible(true);
        System.out.println("capacity: " + capacity.invoke(map));
        //获取map集合大小
        Field size = clz.getDeclaredField("size");
        size.setAccessible(true);
        System.out.println("size: " + size.get(map));

    }


}

