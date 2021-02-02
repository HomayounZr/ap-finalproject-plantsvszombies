package helpers.threads;

import appStart.Configurations;
import models.Sun;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * this thread create new suns from sky
 */
public class SkySunGenerator implements Runnable {

    // all suns
    private CopyOnWriteArrayList<Sun> suns;

    /**
     * constructor
     * @param suns CopyOnWriteArrayList Sun
     */
    public SkySunGenerator(CopyOnWriteArrayList<Sun> suns){
        this.suns = suns;
    }

    @Override
    public void run() {
        try{

            while(true){

                // just some location in game frame
                Sun sun = new Sun(523,150);
//                synchronized (suns){
                    suns.add(sun);
//                }

                // if sound was on play the sound
                if(Configurations.hasSound)
                    AudioThreadPool.execute(new AudioPlayer("./sounds/ting.wav",0.5,false));

                Thread.sleep(Configurations.sunLoadSky * 1000);

            }

        } catch (InterruptedException ex){

        }
    }
}
