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


    public void doAction(WallNutPlant walnut , State state){
        //If Chosen From The Cards
        while (!walnut.getIsAlive()){
            if(state.getCoordinate().equals(walnut.getCoordinate())){
              /*  if(state.checkHasZombie()){
                     state.removeZombie();
                    }
                    else{
                    }

                setAxis_x = walnut.getCoordinate().getAxis_x()++;

               */
            }
        }

    }

    @Override
    public void doAction(){

    }
}
