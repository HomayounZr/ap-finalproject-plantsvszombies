package helpers.threads;

import models.LawnMower;
import views.GameState;

/**
 * Move lawn mowen on GameFrame gui when zombie hits it
 */
public class LawnMowerGuiThread implements Runnable {

    // lawn mower
    private LawnMower lawnMower;
    // game state instance
    private GameState state;

    /**
     * constructor
     * @param lawnMower LawnMower
     * @param state GameState
     */
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
                Thread.sleep(25);
            }

            state.getLawnMowers().remove(lawnMower);

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
