package helpers.threads;

import models.LawnMower;
import views.GameState;

public class LawnMowerGuiThread implements Runnable {

    private LawnMower lawnMower;
    private GameState state;

    public LawnMowerGuiThread(LawnMower lawnMower,GameState state){
        this.lawnMower = lawnMower;
        this.state = state;
    }

    @Override
    public void run() {
        try{

            while(true){
                lawnMower.setLocation(
                        lawnMower.getLocationX() + 10,
                        lawnMower.getLocationY()
                );
                if(lawnMower.getLocationX() > 900){
                    break;
                }
                Thread.sleep(50);
            }

            state.getLawnMowers().remove(lawnMower);

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
