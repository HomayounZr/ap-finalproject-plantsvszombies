package models;

public class CherryBombPlant extends Plant {

    public CherryBombPlant(Coordinate coordinate){
        super(
                "./images/Gifs/beetroot.gif", // didn't have cherry bomb gif
                coordinate,
                70,
                1
        );
    }

    public void changeImageToDying(){
        super.changeImageToDying("./images/Gifs/beetroot_dying.gif");
    }

    @Override
    public void doAction(){

    }

}
