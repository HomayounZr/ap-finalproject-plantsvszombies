package helpers.threads;

import javax.swing.*;

public class ZombieGuiThread implements Runnable {

    private JLabel zombieLabel;

    public ZombieGuiThread(JLabel label){
        this.zombieLabel = label;
    }

    @Override
    public void run() {

    }
}
