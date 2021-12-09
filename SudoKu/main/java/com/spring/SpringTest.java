package com.spring;

import java.io.*;
import java.lang.reflect.Field;

/**
 * @author 祝永星
 * @version 1.0
 * @date 2021/12/6 17:24
 */
public class SpringTest {

    /**
     * @author 祝永星
     * @version 1.0
     * @date 2021/12/6 17:18
     */
    public static void main(String[] args) {
        System.out.println(File.class);

        // system ClassLoader
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        //Extend ClassLoader
        ClassLoader exClassLoader = systemClassLoader.getParent();
        System.out.println(exClassLoader);

        //rooter classLoader
        ClassLoader rootClassLoader = exClassLoader.getParent();
        System.out.println(rootClassLoader);

    }
}