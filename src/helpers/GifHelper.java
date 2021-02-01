package helpers;

import java.awt.*;

public class GifHelper {

    public static Image zombie_normal;
    public static Image zombie_normal_dying;
    public static Image zombie_conehead;
    public static Image zombie_conehead_dying;
    public static Image zombie_buckethead;
    public static Image zombie_buckethead_dying;

    public static void init(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        zombie_normal = toolkit.getImage("./images/Gifs/zombie_normal.gif");
        zombie_normal_dying = toolkit.getImage("./images/Gifs/zombie_normal_dying.gif");
        zombie_normal = toolkit.getImage("./images/Gifs/coneheadzombie.gif");
        zombie_conehead_dying = toolkit.getImage("./images/Gifs/zombie_conehead_dying.gif");
        zombie_normal = toolkit.getImage("./images/Gifs/bucketheadzombie.gif");
        zombie_buckethead_dying = toolkit.getImage("./images/Gifs/zombie_buckethead_dying.gif");
    }

}
