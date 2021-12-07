package com.aoeivux.shuDu;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicPlayer {

        void playMusic(File file){
            try
            {
                //创建相当于音乐播放器的对象
                Clip clip = AudioSystem.getClip();
                //将传入的文件转成可播放的文件
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
                //播放器打开这个文件
                clip.open(audioInput);
                //clip.start();//只播放一次
                //循环播放
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch(Exception ex){
                ex.printStackTrace();
            }
    }
}

