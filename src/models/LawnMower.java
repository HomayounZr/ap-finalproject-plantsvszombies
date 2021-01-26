package models;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class LawnMower {

    private String imageUri;
    private int row;
    private BufferedImage image;

    public LawnMower(int row){
        imageUri = "./images/Gifs/lawn_mower.gif";
        this.row = row;

        BufferedImage _image = null;
        try{
            _image = ImageIO.read(new File(this.imageUri));
        } catch (Exception ex){
            ex.printStackTrace();
        }
        this.image = _image;
    }

    public void activate(){
        imageUri = "./images/Gifs/lawnmowerActivated.gif";
    }

    public String getImageUri() {
        return imageUri;
    }

    public int getRow() {
        return row;
    }

}
