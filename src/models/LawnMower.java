package models;

public class LawnMower {

    private String imageUrl;
    private int row;

    public LawnMower(int row){
        imageUrl = "./images/Gifs/lawn_mower.gif";
        this.row = row;
    }

    public void activate(){
        imageUrl = "./images/Gifs/lawnmowerActivated.gif";
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getRow() {
        return row;
    }

}
