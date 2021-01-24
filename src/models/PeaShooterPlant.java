package models;

public class PeaShooterPlant extends Plant {

    private int bulletDamage;

    public PeaShooterPlant(Coordinate coordinate){
        super(
                "./images/Gifs/pea_shooter.gif",
                coordinate,
                70,
                1
        );
    }

    public void changeImageToDying(){
        super.changeImageToDying("./images/Gifs/pea_shooter_dying.gif");
    }

    @Override
    public void doAction(){
        // code to generate normal pee
        Bullet bullet = new Bullet(
                "./images/pea.png",
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
