package com.ycyoes.biz.dataprocess;

import com.ycyoes.common.io.FileUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class PersonData {
    public static final String DRIVER = "oracle.jdbc.OracleDriver";
    public static final String URL = "jdbc:oracle:thin:@136.2.34.65:15233:xe";
    public static final String USERNAME = "cigproxy";
    public static final String PASSWORD = "cigproxy";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        long startTime = System.currentTimeMillis();
        String fileOutPut = "E:/test.txt";
        Map<String, String> map = getDomainByName("'realPersonType', 'gender', 'nation'");
        map.forEach((key, value) -> {
            System.out.println("key: " + key + " value: " + value);
        });
        Connection conn = getConnection();
        Statement sta = null;
        ResultSet rs = null;

        StringBuffer sb = new StringBuffer();
        try {
            sta = conn.createStatement();
            String sql = "select card_num, name, gender, birth_date, nation, d_addr, person_type, r_addr from zz_person";
            rs = sta.executeQuery(sql);
            int count = 0;
            while (rs.next()) {
                String cardNum = rs.getString("card_num");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String birthDate = rs.getString("birth_date");
                String nation = rs.getString("nation");
                String dAddr = rs.getString("d_addr");
                String personType = rs.getString("person_type");
                String rAddr = rs.getString("r_addr");
                sb.append(cardNum + ",");
                sb.append(name + ",");
                sb.append(map.get("gender_" + gender) + ",");
                sb.append(birthDate + ",");
                sb.append(map.get("nation_" + nation) + ",");
                sb.append(dAddr + ",");
                sb.append(map.get("realPersonType_" + personType) + ",");
                sb.append(rAddr + ",");
                sb.append("\r\n");
                count++;
                if (count == 1000) {
                    FileUtils.writeToFile(fileOutPut, sb.toString(), true);
                    System.out.println("写入文件");
                    sb = new StringBuffer();
                    count = 0;
                }
            }
            //非整千条数据时，导出剩余的数据
            FileUtils.writeToFile(fileOutPut, sb.toString(), true);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                sta.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("程序执行时间: " + (endTime - startTime) / 1000 + "秒");
    }


    /**
     * 通过domainname获取字典项的key,value值
     * @param domainNames   域名，多个可用,分隔
     * @return  map集合，key - domainname+key, value - value
     */
    public static Map<String, String> getDomainByName(String domainNames) {
        Map<String, String> map = new HashMap<>();
        Connection conn = getConnection();
        Statement sta = null;
        ResultSet rs = null;
        try {
            sta = conn.createStatement();
            String sql = "select domainname, key, value from DOMAINS where DOMAINNAME in (" + domainNames + ")";
            rs = sta.executeQuery(sql);
            while (rs.next()) {
                String domainname = rs.getString("domainname");
                String key = rs.getString("key");
                String value = rs.getString("value");
                map.put((domainname + "_" + key), value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                sta.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return map;
    }



    /**
     * 获得Connection
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


}
