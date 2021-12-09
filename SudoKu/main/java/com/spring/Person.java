package com.spring;

/**
 * @author 祝永星
 * @version 1.0
 * @date 2021/12/6 19:13
 */
public class Person {

    private String name;
    private int age;
    public String number;

    public void eat(){
        System.out.println("我在吃饭...");
    }

    public String getName() {
        return name;
    }

    public Person(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getNumber() {
        return number;
    }

    public void  sleep(){
        System.out.println("我在睡觉");
    }

    private void happy(){
        System.out.println("SHELL");
    }


}
