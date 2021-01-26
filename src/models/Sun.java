package models;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Sun {
    protected Coordinate coordinate;
    protected int value;
    protected int displayTime;
    protected int randBornTime;

    protected BufferedImage image;

    public Sun(Coordinate coordinate){
        //Location needs to be Fixed
        displayTime = 10;
        value = 25;
        Random random = new Random();
        //Between 0 to 15 seconds random Sun Will be Generated in The Map
        //Try to Set Start Bound
        randBornTime = random.nextInt(15);
        try {
            image = ImageIO.read(new File("Sun.png"));
        } catch (IOException e) {
        }
    }
    //This For Random Suns In the Map , So There Shouldn't Be Any Exact Coordinates , Just Check For Within The Map
    public Sun(){
        displayTime = 10;
        value = 25;
        Random random = new Random();
        //Between 0 to 15 seconds random Sun Will be Generated in The Map
        //Try to Set Start Bound
        randBornTime = random.nextInt(15);
        try {
            image = ImageIO.read(new File("Sun.png"));
        } catch (IOException e) {
        }
    }
    //I Don't Think Any Methods Needed
}
