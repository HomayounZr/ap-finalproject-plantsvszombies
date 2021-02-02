package helpers.threads;

import models.Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This class is used to play a sound file
 * implementing runnable
 */
public class AudioPlayer implements Runnable{
    //    Player player;
    // file
    private File music;
    // duration of the file
    private double duration;
    // replay the file or not
    private boolean reload;

    /**
     * constructor
     * @param path String path to file
     * @param duration double duration in seconds
     * @param reload boolean reload
     */
    public AudioPlayer(String path,double duration,boolean reload) {
        music = new File(path);
        this.reload = reload;
        this.duration = duration;
    }

    @Override
    public void run() {
        try{

            do{
                // find song and connect to AudioSystem for playing it
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(music);
                clip.open(inputStream);
                clip.start();
                // to replay file, thread will sleep based on
                // file duration and then play it again
                if(reload)
                    Thread.sleep((int)(duration * 1000));
//                System.out.println("play");
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