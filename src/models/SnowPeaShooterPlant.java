package models;

public class SnowPeaShooterPlant extends Plant {

    public SnowPeaShooterPlant(Coordinate coordinate){
        super(
                "./images/Gifs/freezepeashooter.gif",
                coordinate,
                100,
                1
        );
    }

    public void changeImageToDying(){
        super.changeImageToDying("./images/Gifs/freezepeashooter.gif");
    }

    @Override
    public void doAction(){
        // coed to generate frozen pee
        Bullet bullet = new Bullet(
                "./images/freezepea.png",
                35,
                super.getCoordinate()
        );
        /*
        create thread for moving bullet in Helper folder
        haven't said anything special about bullet speed
        choose whatever you like for sleep time in thread
        something between 0.1 and 1 seconds per state is logical
         */
    }

}
