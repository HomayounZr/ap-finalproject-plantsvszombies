package helpers.threads;

import views.GameState;

public class ResumeGameStageThread implements Runnable {

    private GameState state;
    private int lastStage;
    private int passedTime;
    public ResumeGameStageThread(GameState state,int lastStage,int passedTime){
        this.state = state;
        this.lastStage = lastStage;
        this.passedTime = passedTime;
    }

    @Override
    public void run() {
        try{

            if(lastStage == 0){
                state.setTotalTimePassed(passedTime);
                state.startCollectingStage();
                Thread.sleep((50 - passedTime) * 1000);
                state.startStage1();
                Thread.sleep(150 * 1000);
                state.startStage2();
                Thread.sleep(180 * 1000);
                state.startFinalWave();
                Thread.sleep(150 * 1000);
            } else if(lastStage == 1) {
                state.setTotalTimePassed(50 + passedTime);
                state.startStage1();
                Thread.sleep((150 - passedTime) * 1000);
                state.startStage2();
                Thread.sleep(180 * 1000);
                state.startFinalWave();
                Thread.sleep(150 * 1000);
            } else if(lastStage == 2){
                state.setTotalTimePassed(200 + passedTime);
                state.startStage2();
                Thread.sleep((180 - passedTime) * 1000);
                state.startFinalWave();
                Thread.sleep(150 * 1000);
            } else {
                state.setTotalTimePassed(380 + passedTime);
                state.startFinalWave();
                Thread.sleep((150 - passedTime) * 1000);
            }
            state.checkIfWon();

        } catch (Exception ex){

        }
    }
}
