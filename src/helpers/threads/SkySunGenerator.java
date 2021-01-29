package helpers.threads;

import appStart.Configurations;
import models.Sun;

import java.util.ArrayList;

public class SkySunGenerator implements Runnable {

    private ArrayList<Sun> suns;

    public SkySunGenerator(ArrayList<Sun> suns){
        this.suns = suns;
    }

    @Override
    public void run() {
        try{

            while(true){

                Sun sun = new Sun(523,150);
                suns.add(sun);

                Thread.sleep(Configurations.sunLoadSky * 1000);

            }

        } catch (InterruptedException ex){

        }
    }
}
