package appStart;


import controllers.*;

/**
 * This is class is used for initializing game controllers
 */
public class GameManagement {

    public static UserController userController;
    public static GameController gameController;
    public static boolean isEasy;
    public static void initializeGame(){

        userController = new UserController();
        gameController = new GameController();
        isEasy = true;

    }

}
