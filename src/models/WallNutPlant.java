package models;

public class WallNutPlant extends Plant {

    public WallNutPlant(Coordinate coordinate){
        super(
                "./images/Gifs/walnut_full_life.gif",
                coordinate,
                150,
                1
        );
    }

    public void changeImageToDying(){
        super.changeImageToDying("./images/Gifs/wallnut_half_life.gif");
    }

    @Override
    public void doAction(){

    }

}
