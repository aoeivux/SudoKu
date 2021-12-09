package com.IOpractice;

import java.io.*;

/**
 * @author zyx
 * @version 1.0
 * @date 2021/12/3 19:40
 */
public class Main {
    Main(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        PrintStream printStream = new PrintStream(System.out);
        PrintStream printErr = new PrintStream(System.err);
        printErr.println("Welcome,world!");
        

    }

    public static void main(String[] args) {
        new Main();
    }
}

