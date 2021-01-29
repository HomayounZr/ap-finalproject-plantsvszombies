package helpers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class BufferedImages {

    public static BufferedImage plant_sunflower;
    public static BufferedImage plant_sunflower_dying;
    public static BufferedImage plant_peashooter;
    public static BufferedImage plant_peashooter_dying;
    public static BufferedImage plant_snowpeashooter;
    public static BufferedImage plant_snowpeashooter_dying;
    public static BufferedImage plant_giantwallnut;
    public static BufferedImage plant_giantwallnut_dying;
    public static BufferedImage plant_cherrybomb;
    public static BufferedImage plant_cherrybomb_dying;

    public static BufferedImage sun;

    public static BufferedImage card_sunflower;
    public static BufferedImage card_peashooter;
    public static BufferedImage card_snowpeashooter;
    public static BufferedImage card_giantwallnut;
    public static BufferedImage card_cherrybomb;

    public static BufferedImage zombie_normal;
    public static BufferedImage zombie_normal_dying;
    public static BufferedImage zombie_conehead;
    public static BufferedImage zombie_conehead_dying;
    public static BufferedImage zombie_buckethead;
    public static BufferedImage zombie_buckethead_dying;

    public static BufferedImage lawn_mower;
    public static BufferedImage lawn_mower_active;

    public static BufferedImage bullet_normal;
    public static BufferedImage bullet_frozen;

    public static BufferedImage background;

    public static void init(){
        try{

            plant_sunflower = ImageIO.read(new File("./images/Gifs/sun_flower.gif"));
            plant_sunflower_dying = ImageIO.read(new File("./images/Gifs/sun_flower_dying.gif"));
            plant_peashooter = ImageIO.read(new File("./images/Gifs/pea_shooter.gif"));
            plant_peashooter_dying = ImageIO.read(new File("./images/Gifs/pea_shooter_dying.gif"));
            plant_snowpeashooter = ImageIO.read(new File("./images/Gifs/freezepeashooter.gif"));
            plant_snowpeashooter_dying = ImageIO.read(new File("./images/Gifs/freezepeashooter.gif"));
            plant_giantwallnut = ImageIO.read(new File("./images/Gifs/walnut_full_life.gif"));
            plant_giantwallnut_dying = ImageIO.read(new File("./images/Gifs/walnut_dead.gif"));
            plant_cherrybomb = ImageIO.read(new File("./images/Gifs/beetroot.gif"));
            plant_cherrybomb_dying = ImageIO.read(new File("./images/Gifs/beetroot_dying.gif"));

            sun = ImageIO.read(new File("./images/Gifs/sun.gif"));

            card_sunflower = ImageIO.read(new File("./images/Cards/card_sunflower.png"));
            card_peashooter = ImageIO.read(new File("./images/Cards/card_peashooter.png"));
            card_snowpeashooter = ImageIO.read(new File("./images/Cards/card_freezepeashooter.png"));
            card_giantwallnut = ImageIO.read(new File("./images/Cards/card_wallnut.png"));
            card_cherrybomb = ImageIO.read(new File("./images/Cards/card_cherrybomb.png"));

            zombie_normal = ImageIO.read(new File("./images/Gifs/zombie_normal.gif"));
            zombie_normal_dying = ImageIO.read(new File("./images/Gifs/zombie_normal_dying.gif"));
            zombie_conehead = ImageIO.read(new File("./images/Gifs/coneheadzombie.gif"));
            zombie_conehead_dying = ImageIO.read(new File("./images/Gifs/coneheadzombie.gif"));
            zombie_buckethead = ImageIO.read(new File("./images/Gifs/bucketheadzombie.gif"));
            zombie_buckethead_dying = ImageIO.read(new File("./images/Gifs/bucketheadzombie.gif"));

            lawn_mower = ImageIO.read(new File("./images/Gifs/lawn_mower.gif"));
            lawn_mower_active = ImageIO.read(new File("./images/Gifs/lawnmowerActivated.gif"));

            bullet_normal = ImageIO.read(new File("./images/pea.png"));
            bullet_frozen = ImageIO.read(new File("./images/freezepea.png"));

            background = ImageIO.read(new File("./images/mainBG.png"));

        } catch (Exception ex){
            System.err.println("Could load image\n" + ex.getMessage());
        }
    }

}
