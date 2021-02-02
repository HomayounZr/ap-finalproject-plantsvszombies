package helpers;

import javax.swing.*;
import java.io.File;

public class ImageIcons {

    public static ImageIcon plant_sunflower;
    public static ImageIcon plant_sunflower_dying;
    public static ImageIcon plant_peashooter;
    public static ImageIcon plant_peashooter_dying;
    public static ImageIcon plant_snowpeashooter;
    public static ImageIcon plant_snowpeashooter_dying;
    public static ImageIcon plant_giantwallnut;
    public static ImageIcon plant_giantwallnut_dying;
    public static ImageIcon plant_cherrybomb;
    public static ImageIcon plant_cherrybomb_dying;

    public static ImageIcon sun;

    public static ImageIcon card_sunflower;
    public static ImageIcon card_peashooter;
    public static ImageIcon card_snowpeashooter;
    public static ImageIcon card_giantwallnut;
    public static ImageIcon card_cherrybomb;

    public static ImageIcon zombie_normal;
    public static ImageIcon zombie_normal_dying;
    public static ImageIcon zombie_conehead;
    public static ImageIcon zombie_conehead_dying;
    public static ImageIcon zombie_buckethead;
    public static ImageIcon zombie_buckethead_dying;
    public static ImageIcon zombie_football;
    public static ImageIcon zombie_football_dying;

    public static ImageIcon lawn_mower;
    public static ImageIcon lawn_mower_active;

    public static ImageIcon bullet_normal;
    public static ImageIcon bullet_frozen;

    public static void init(){
        try{

            plant_sunflower = new ImageIcon(BufferedImages.plant_sunflower);
            plant_sunflower_dying = new ImageIcon(BufferedImages.plant_sunflower_dying);
            plant_peashooter = new ImageIcon(BufferedImages.plant_peashooter);
            plant_peashooter_dying = new ImageIcon(BufferedImages.plant_peashooter_dying);
            plant_snowpeashooter = new ImageIcon(BufferedImages.plant_snowpeashooter);
            plant_snowpeashooter_dying = new ImageIcon(BufferedImages.plant_snowpeashooter_dying);
            plant_giantwallnut = new ImageIcon(BufferedImages.plant_giantwallnut);
            plant_giantwallnut_dying = new ImageIcon(BufferedImages.plant_giantwallnut_dying);
            plant_cherrybomb = new ImageIcon(BufferedImages.plant_cherrybomb);
            plant_cherrybomb_dying = new ImageIcon(BufferedImages.plant_cherrybomb_dying);

            sun = new ImageIcon(BufferedImages.sun);

            card_sunflower = new ImageIcon(BufferedImages.card_sunflower);
            card_peashooter = new ImageIcon(BufferedImages.card_peashooter);
            card_snowpeashooter = new ImageIcon(BufferedImages.card_snowpeashooter);
            card_giantwallnut = new ImageIcon(BufferedImages.card_giantwallnut);
            card_cherrybomb = new ImageIcon(BufferedImages.card_cherrybomb);

            zombie_normal = new ImageIcon(BufferedImages.zombie_normal);
            zombie_normal_dying = new ImageIcon(BufferedImages.zombie_normal_dying);
            zombie_conehead = new ImageIcon(BufferedImages.zombie_conehead);
            zombie_conehead_dying = new ImageIcon(BufferedImages.zombie_conehead_dying);
            zombie_buckethead = new ImageIcon(BufferedImages.zombie_buckethead);
            zombie_buckethead_dying = new ImageIcon(BufferedImages.zombie_buckethead_dying);
            zombie_football = new ImageIcon(BufferedImages.zombie_football);
            zombie_football_dying = new ImageIcon(BufferedImages.zombie_football_dying);

            lawn_mower = new ImageIcon(BufferedImages.lawn_mower);
            lawn_mower_active = new ImageIcon(BufferedImages.lawn_mower_active);

            bullet_normal = new ImageIcon(BufferedImages.bullet_normal);
            bullet_frozen = new ImageIcon(BufferedImages.bullet_frozen);

        } catch (Exception ex){
            System.err.println("Could load image\n" + ex.getMessage());
        }
    }

}
