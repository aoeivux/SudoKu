package com.aoeivux.test.jdbcProject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

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


    public JdbcDemo() {
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
//            System.out.println("连接数据库...");

            //返回一个connection对象
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
//            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            String sql;
            sql = "SELECT UserName,PassWord FROM manage";
            String sql1 = "INSERT INTO manage(username,password) values (1, 1)";
            stmt.executeUpdate(sql1);
            //得到数据集
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                String userName  = rs.getString("UserName");
                String passWd = rs.getString("PassWord");

                UserName.add(userName);
                PassWord.add(passWd);

//                // 输出数据
                System.out.print("username: " + userName+"  ");
                System.out.print("passwd: " + passWd);
                System.out.print("\n");
//

            }

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

//        System.out.println("Goodbye!");



    }
}
