package com.ThreadPractice;

/**
 * @author 祝永星
 * @version 1.0
 * @date 2021/12/4 11:09
 */

class DemoTest02 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("这是第1个线程");
        }
    }

}

public class Demo02 {
    public static void main(String[] args) {
        new Thread(new DemoTest02()).start(); //区别于继承单继承Thread的方法

        for (int i = 0; i < 1000; i++) {
            System.out.println("这是Main线程");
        }
    }
}
