package com.opensource.mybatis;

import java.sql.*;

public class MySQLTest {

    public static String URL = "jdbc:mysql://127.0.0.1:3306/mybatis?characterEcoding=utf-8";
    public static String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String USERNAME = "root";
    public static String PASSWORD = "root";

    public static void main(String[] args) {
        try {
            Class.forName(DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from user";

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String age = rs.getString("age");
                System.out.println("name: " + name + " email: " + email + " age: " + age);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                conn.close();
                pst.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
