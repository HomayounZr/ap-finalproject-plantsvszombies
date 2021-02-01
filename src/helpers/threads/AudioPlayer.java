package helpers.threads;

import models.Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AudioPlayer implements Runnable{
    //    Player player;
    private File music;
    private double duration;
    private boolean reload;

    public AudioPlayer(String path,double duration,boolean reload) {
        music = new File(path);
        this.reload = reload;
        this.duration = duration;
    }

    @Override
    public void run() {
        try{

            do{
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(music);
                clip.open(inputStream);
                clip.start();
                if(reload)
                    Thread.sleep((int)(duration * 1000));
                System.out.println("play");
            } while(reload);

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void stop(){
        this.reload = false;
    }
//
//    public void play() throws FileNotFoundException {
//
//        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
//        //player = new Player(buffer);
//       // player.play();
//    }
}