package helpers.threads;

import models.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;



public class AudioPlayer extends Thread{
    Player player;
    File music;

    public AudioPlayer(String a) {
        File file = new File(a);
        this.music = file;
    }

    @Override
    public void run() {
        super.run();
        try {
            play();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void play() throws FileNotFoundException {

        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
        //player = new Player(buffer);
       // player.play();
    }
}