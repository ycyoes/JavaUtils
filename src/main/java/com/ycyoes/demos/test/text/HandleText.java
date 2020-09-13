package com.ycyoes.demos.test.text;

import java.io.*;
import java.util.*;

public class HandleText {
    public static void main(String[] args) {
        String name = System.getProperty("user.dir") + "\\src\\main\\java\\com\\ycyoes\\demos\\test\\text\\";
        System.out.println(name);
        List<String> list = readFile(name + "file.txt");
        list.stream().forEach(System.out::println);
        List<String> keyList = readFile(name + "key.txt");
        System.out.println("key----------------");
        keyList.stream().forEach(System.out::println);
        Map<String, Object> map = new HashMap<>();
        System.out.println("----------map------------");
        for (int i = 0; i < list.size(); i++) {
            String[] des = list.get(i).split(",");
            System.out.println(des[0].trim() + des[1] + " " + des[2] + Objects.equals("0", des[2].trim()));
            map.put(des[0].trim(), (Objects.equals(des[1].trim(), "0") ? "" : des[1].trim()) + "\t" + (Objects.equals(des[2].trim(), "0") ? "" : des[2].trim()));
        }
//        System.out.println("map: " + map);
        System.out.println("-----------out------------");
        for (int i = 0; i < keyList.size(); i++) {
            String key = keyList.get(i).trim();
            String mapKey = key.substring(0, key.indexOf("("));
            System.out.println(mapKey + "\t" + map.get(mapKey));
        }

    }

    public String getPath() {
        return this.getClass().getClassLoader().getResource("/").getPath();
    }

    /**
     * 逐行读取文件
     *
     * @param name  文件全路径
     * @return
     */
    public static List<String> readFile(String name) {
        // 使用ArrayList来存储每行读取到的字符串
        List<String> arrayList = new ArrayList<>();
        try {
            File file = new File(name);
            InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bf = new BufferedReader(inputReader);
            // 按行读取字符串
            String str;
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
            }
            bf.close();
            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

}
