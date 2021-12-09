package com.spring;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author 祝永星
 * @version 1.0
 * @date 2021/12/6 19:17
 */
public class Demo01 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> c1 = Class.forName("com.spring.Person");
        //Fully name of Class
        System.out.println(c1.getName());

        //simplify class name
        System.out.println(c1.getSimpleName());

//        try {
        //找到全部的属性
            Field[] fields = c1.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(field);
//            }
//            System.out.println(c1.getField("number"));
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
        }
        ;



            int i = 0;
            int j = 0;
            Method[] fields1 = c1.getMethods();  //输出加上父类继承的方法
        for (Method s : fields1) {
            System.out.println("方法为："+i+++s);
        }

        Method[] fields2 = c1.getDeclaredMethods(); //输出本类的包括private的方法
        for (Method s : fields2) {
            System.out.println("2方法为："+j+++s);
        }
    }
}
