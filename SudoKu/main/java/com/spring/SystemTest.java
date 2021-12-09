package com.spring;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 祝永星
 * @version 1.0
 * @date 2021/12/6 20:54
 */
public class SystemTest {
    public static void main(String[] args) {
        try {

            new Test1();
            new Test2();
            new Test3();

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}

//普通方法
class Test1 {
    public Test1() throws NoSuchMethodException{
        Person person = new Person("xiaoxing");
        Class c1 = person.getClass();

        Method getName = c1.getDeclaredMethod("getName");


        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            try {
                getName.invoke(person,null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("普通方式执行10亿所需要的时间:"+(endTime-startTime) +"ms");

    }
}


//反射方法
class Test2 {
    public Test2() throws NoSuchMethodException{
        Person person = new Person("xiaoxing");
        Class c1 = person.getClass();

        Method getName = c1.getDeclaredMethod("getName");


        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            try {
                getName.invoke(person,null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("反射执行10亿所需要的时间:"+(endTime-startTime) +"ms");

    }
}


class Test3 {
    public Test3() throws NoSuchMethodException{
        Person person = new Person("xiaoxing");
        Class c1 = person.getClass();


        Method getName = c1.getDeclaredMethod("getName");
        getName.setAccessible(true);//关闭安全检测

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            try {
                getName.invoke(person,null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("关闭检测执行10亿所需要的时间:"+(endTime-startTime) +"ms");

    }
}

