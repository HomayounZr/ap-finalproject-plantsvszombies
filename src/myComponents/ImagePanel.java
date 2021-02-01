package myComponents;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * This Class Is for Handling the Source Images Of the game
 * To fit The Inside the Game Frame
 * Increasing Or Reducing The Size Of Any Source Picture
 */

public class ImagePanel extends JPanel {
    //Image Path
    private String imageUri;
    private int width;

    public double getScaleFactor(int iMasterSize, int iTargetSize) {

        double dScale = 1;
        if (iMasterSize > iTargetSize) {

            dScale = (double) iTargetSize / (double) iMasterSize;

        } else {

            dScale = (double) iTargetSize / (double) iMasterSize;

        }

        return dScale;

    }

    public double getScaleFactorToFit(Dimension original, Dimension toFit) {

        double dScale = 1d;

        if (original != null && toFit != null) {

            double dScaleWidth = getScaleFactor(original.width, toFit.width);
            double dScaleHeight = getScaleFactor(original.height, toFit.height);

            dScale = Math.min(dScaleHeight, dScaleWidth);

        }

        return dScale;

    }

    public double getScaleFactorToFill(Dimension masterSize, Dimension targetSize) {

        double dScaleWidth = getScaleFactor(masterSize.width, targetSize.width);
        double dScaleHeight = getScaleFactor(masterSize.height, targetSize.height);

        double dScale = Math.max(dScaleHeight, dScaleWidth);

        return dScale;

    }

    /**
     * Constructor of the Class
     * @param imageUri Image Path in the Source File
     * @param width Image Width
     */
    public ImagePanel(String imageUri,int width){
//        super(new BorderLayout());
        this.imageUri = imageUri;
        this.width = width;
    }

    /**
     * Second Constructor For Some Extra Information of The Layout Of The Frame
     * @param imageUri Image Path in the Source File
     * @param width Image Width
     * @param layout The Main Layout of The Game
     */

    public ImagePanel(String imageUri,int width,LayoutManager layout){
        super(layout);
        this.imageUri = imageUri;
        this.width = width;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        File imageFile = new File(this.imageUri);
        BufferedImage image = null;
        try{
            image = ImageIO.read(imageFile);
        } catch (Exception ex){
            ex.printStackTrace();
        }
//        g.drawImage(backgroundImage.getScaledInstance(width, -1, Image.SCALE_SMOOTH), 0, 0, this);
//        g.drawImage(backgroundImage,0,0,this);

        double scaleFactor = Math.min(1d, getScaleFactorToFill(new Dimension(image.getWidth(), image.getHeight()), getSize()));

        int scaleWidth = (int) Math.round(image.getWidth() * scaleFactor);
        int scaleHeight = (int) Math.round(image.getHeight() * scaleFactor);

        Image scaled = image.getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);

        int width = getWidth() - 1;
        int height = getHeight() - 1;

        int x = (width - scaled.getWidth(this)) / 2;
        int y = (height - scaled.getHeight(this)) / 2;

        g.drawImage(scaled, x, y, this);
    }
}
