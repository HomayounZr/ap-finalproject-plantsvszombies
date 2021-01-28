package models;

import helpers.ImageIcons;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class LawnMower {

    private String imageUri;
    private int row;
    private BufferedImage image;
    private ImageIcon imageIcon;

    public LawnMower(int row){
        imageUri = "./images/Gifs/lawn_mower.gif";
        this.row = row;
        imageIcon = ImageIcons.lawn_mower;
    }

    public void activate(){
        imageIcon = ImageIcons.lawn_mower_active;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public int getRow() {
        return row;
    }

}
