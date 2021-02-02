import appStart.GameManagement;
import views.GameMenu;

import javax.swing.*;

/**
 * Plant vs Zombies game with java
 * Ap Final Project
 *
 * @author HomayounZarei_SeyedSaeedShafie
 */
public class Main {

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

//        ThreadPool.init();

        GameManagement.initializeGame();
        GameMenu gameMenu = new GameMenu();
        gameMenu.show();
    }
}
