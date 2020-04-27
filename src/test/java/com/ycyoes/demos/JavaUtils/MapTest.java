package com.ycyoes.demos.JavaUtils;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTest {

    @Test
    public void testMap() {
        Map<String, Object> map = new HashMap();
        map.put("a", 1);
        map.put("b", 2);
        String map1Str = JSON.toJSONString(map);
        System.out.println(map1Str);
        Map<String, Object> map2 = new HashMap();
        map2.put("b", 2);
        map2.put("a", 1);
        String map2Str = JSON.toJSONString(map2);
        System.out.println(map2Str);
        Assert.assertEquals(map1Str, map2Str);

        List<Object> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        List<Object> list2 = new ArrayList<>();
        list2.add("2");
        list2.add("1");
        System.out.println(JSON.toJSONString(list) + " " + JSON.toJSONString(list2));
        Assert.assertEquals(JSON.toJSONString(list), JSON.toJSONString(list2));
    }


}
