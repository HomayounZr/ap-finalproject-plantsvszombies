package helpers.threads;

import appStart.Configurations;
import models.Sun;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class SkySunGenerator implements Runnable {

    private CopyOnWriteArrayList<Sun> suns;

    public SkySunGenerator(CopyOnWriteArrayList<Sun> suns){
        this.suns = suns;
    }

    @Override
    public void run() {
        try{

            while(true){

                Sun sun = new Sun(523,150);
//                synchronized (suns){
                    suns.add(sun);
//                }

                Thread.sleep(Configurations.sunLoadSky * 1000);

            }

        } catch (InterruptedException ex){

        }
    }
}
