package helpers.threads;

import views.GameState;

public class GameStageThread implements Runnable {

    private GameState state;

    public GameStageThread(GameState state){
        this.state = state;
    }

    @Override
    public void run() {
        try{

            state.startCollectingStage();
            Thread.sleep(50 * 1000);
            state.startStage1();
            Thread.sleep(150 * 1000);
            state.startStage2();
            Thread.sleep(180 * 1000);
            state.startFinalWave();

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
