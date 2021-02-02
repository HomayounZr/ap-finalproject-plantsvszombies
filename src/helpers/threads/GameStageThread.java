package helpers.threads;

import views.GameState;

/**
 * this class only controls the game stages
 */
public class GameStageThread implements Runnable {

    // GameState instance
    private GameState state;

    /**
     * constructor
     * @param state GameState
     */
    public GameStageThread(GameState state){
        this.state = state;
    }

    @Override
    public void run() {
        try{

            state.startCollectingStage();
            Thread.sleep(50 * 1000);
//            Thread.sleep(15 * 1000);
            state.startStage1();
            Thread.sleep(150 * 1000);
//            Thread.sleep(30 * 1000);
            state.startStage2();
            Thread.sleep(180 * 1000);
//            Thread.sleep(25 * 1000);
            state.startFinalWave();
            Thread.sleep(150 * 1000);
//            Thread.sleep(25 * 1000);
            state.checkIfWon();

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
