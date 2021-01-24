package appStart;


import controllers.*;

public class GameManagement {

    public static UserController userController;
    public static GameController gameController;
    public static void initializeGame(){

        userController = new UserController();
        gameController = new GameController();

    }

}
