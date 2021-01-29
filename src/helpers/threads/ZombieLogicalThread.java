package helpers.threads;

import appStart.GameManagement;
import models.Plant;
import models.Zombie;

public class ZombieLogicalThread implements Runnable {

    private Zombie zombie;

    public ZombieLogicalThread(Zombie zombie){
        this.zombie = zombie;
    }

    @Override
    public void run() {
        try{

            while(true){
                // move the zombie by one coordinate to the left
                zombie.moveOneStateLeft();
                /*
                    check if zombie arrived to a plant
                    then break the loop
                    implement this in GameController, Map.java and State.java
                    then call and check here
                */
                Plant plant = GameManagement.gameController.checkHitPlant(zombie.getCoordinate());
                if(plant != null){
                    // start eating the plant

                    break;
                }

                // sleep the thread based on zombie's speed
                Thread.sleep((long)(zombie.getSpeed() * 1000));

            }

        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
    //Some Spark In The Mind , Not Clearly sure How Works
    class GroanThread implements Runnable{
        public int label = 0;

        @Override
        public void run() {

            while(true)
            {
                try {
                    Thread.sleep(12000+(int)(Math.random()*(10000-6000+1)));
                    label = (int)(Math.random()*6);
                    switch (label) {
                        case 0:
                            AudioPlayer t1 = new AudioPlayer("Resource/audio/groan1.mp3");
                            Thread T1 =new Thread(t1);
                            T1.start();break;
                        case 1:
                            AudioPlayer t2 = new AudioPlayer("Resource/audio/groan2.mp3");
                            Thread T2 =new Thread(t2);
                            T2.start();break;
                        case 2:
                            AudioPlayer t3 = new AudioPlayer("Resource/audio/groan3.mp3");
                            Thread T3 =new Thread(t3);
                            T3.start();break;
                        case 3:
                            AudioPlayer t4 = new AudioPlayer("Resource/audio/groan4.mp3");
                            Thread T4 =new Thread(t4);
                            T4.start();break;
                        case 4:
                            AudioPlayer t5 = new AudioPlayer("Resource/audio/groan5.mp3");
                            Thread T5 =new Thread(t5);
                            T5.start();break;
                        case 5:
                            AudioPlayer t6 = new AudioPlayer("Resource/audio/groan6.mp3");
                            Thread T6 =new Thread(t6);
                            T6.start();break;
                    }
                }catch(InterruptedException e) {
                    System.out.println(e);
                }
            }

        }

    }
}
