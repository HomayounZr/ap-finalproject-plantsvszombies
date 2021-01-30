package helpers.threads;

import models.LawnMower;

public class LawnMowerGuiThread implements Runnable {

    private LawnMower lawnMower;

    public LawnMowerGuiThread(LawnMower lawnMower){
        this.lawnMower = lawnMower;
    }

    @Override
    public void run() {
        try{

            while(true){
                lawnMower.setLocation(
                        lawnMower.getLocationX() + 5,
                        lawnMower.getLocationY()
                );
                if(lawnMower.getLocationX() > 900){
                    break;
                }
                Thread.sleep(10);
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
