package com.aoeivux.shuDu;

import java.sql.*;
import java.util.ArrayList;


/**
 * @author 祝永星
 * @version 1.0
 * @date 2021/12/4 13:55
 */


public class JdbcDemo {
    //  MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    //驱动信息
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //用户信息和db_URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/admin?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8";


    private static final String USER = "root";
    private static final String PASS = "123456";


    public ArrayList<String> UserName = new ArrayList<String>();
    public ArrayList<String> PassWord = new ArrayList<String>();


    public static String sqlAdd = null;


    //无参构造，查询类
    public JdbcDemo() {
        Connection conn = null;
        Statement stmt2 = null;
        Statement stmt1 = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");

            //返回一个connection对象
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt1 = conn.createStatement();


            String sqlQuery = "SELECT username,password FROM manage";
            //得到数据集
            ResultSet rs = stmt1.executeQuery(sqlQuery);

            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                String userName = rs.getString("username");
                String passWd = rs.getString("password");

                //添加到集合ArrayList
                UserName.add(userName);
                PassWord.add(passWd);


                System.out.print(userName+"  ");
                System.out.println(passWd);

            }

            rs.close();
            stmt1.close();
            conn.close();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("结束");

    }


    public JdbcDemo(String choice) {
        Connection conn = null;
        Statement stmt2 = null;

        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");

            //返回一个connection对象
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");

            stmt2 = conn.createStatement();

            // 输出数据
//                System.out.print("username: " + userName+"  ");
//                System.out.print("passwd: " + passWd);
//                System.out.print("\n");

            System.out.println("开始");
            //得到数据集
            stmt2.executeUpdate(sqlAdd);
            System.out.println("结束");



            // 完成后关闭

            stmt2.close();
            conn.close();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("结束");
    }

}
