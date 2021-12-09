package com.ThreadPractice;

/**
 * @author 祝永星
 * @version 1.0
 * @date 2021/12/4 10:59
 */

class ThreadTest extends Thread{
        public void run(){
            for (int i = 0; i < 1000; i++) {
                System.out.println("这是第1个线程");
        }


    }
}

public class Demo01 {


    public static void main(String[] args) {
        int j = 1;
        int m = 2;
        new ThreadTest().start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("这是Main线程");
        }
    }
}
