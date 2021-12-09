package com.aoeivux.test.music;

import java.io.File;

/**
 * @author 祝永星
 * @version 1.0
 * @date 2021/12/5 15:47
 */
public class Main {
    public static void main(String[] args) {
        Thread MusicThread = new Thread(new Runnable() {
            @Override
            public void run() {
                new MusicPlayer().playMusic(new File("src/com/aoeivux/test/music/不眠之夜_looperman.wav"));
            }
        });

        MusicThread.start();



    }


}
