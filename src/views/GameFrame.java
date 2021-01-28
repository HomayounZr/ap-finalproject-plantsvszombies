/*** In The Name of Allah ***/
package views;

import myComponents.ImagePanel;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.border.Border;

/**
 * The window on which the rendering is performed.
 * This example uses the modern BufferStrategy approach for double-buffering, 
 * actually it performs triple-buffering!
 * For more information on BufferStrategy check out:
 *    http://docs.oracle.com/javase/tutorial/extra/fullscreen/bufferstrategy.html
 *    http://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferStrategy.html
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class GameFrame extends JFrame {

    public static final int GAME_HEIGHT = 752;                  // 720p game resolution
    public static final int GAME_WIDTH = 1000;  // wide aspect ratio
    private BufferedImage bg;
    private BufferedImage pea;
    private BufferedImage pea2;
    private BufferedImage icePea;
    private BufferedImage icePea2;
    private BufferedImage walnut;
    private BufferedImage walnut2;
    private BufferedImage sunflower;
    private BufferedImage sunflower2;
    private BufferedImage cherry;
    private BufferedImage cherry2;


    //uncomment all /*...*/ in the class for using Tank icon instead of a simple circle
    /*private BufferedImage image;*/

    private long lastRender;
    private ArrayList<Float> fpsHistory;

    private BufferStrategy bufferStrategy;

    private ImagePanel mainPanel;

    public GameFrame(String title) {
        super(title);

        setResizable(false);

        setSize(GAME_WIDTH, GAME_HEIGHT);
        try {
            bg = ImageIO.read(new File("background.jpg"));
            pea = ImageIO.read(new File("pea.png"));
            pea2 = ImageIO.read(new File("pea2.png"));

            icePea = ImageIO.read(new File("icePea.png"));
            icePea2 = ImageIO.read(new File("Snow.png"));

            sunflower = ImageIO.read(new File("sunflower.png"));
            sunflower2 = ImageIO.read(new File("sunflower2.png"));

            walnut = ImageIO.read(new File("wal.png"));
            walnut2 = ImageIO.read(new File("wal2.png"));

            cherry = ImageIO.read(new File("cherry.png"));
            cherry2 = ImageIO.read(new File("cherry2.png"));
        } catch (IOException e) {
        }

        setContentPane(mainPanel);
        setVisible(true);
    }

    /**
     * This must be called once after the JFrame is shown:
     *    frame.setVisible(true);
     * and before any rendering is started.
     */
    public void initBufferStrategy() {
        // Triple-buffering
        createBufferStrategy(3);
        bufferStrategy = getBufferStrategy();
    }


    /**
     * Game rendering with triple-buffering using BufferStrategy.
     */
    public void render(GameState state) {
        // Render single frame
        do {
            // The following loop ensures that the contents of the drawing buffer
            // are consistent in case the underlying surface was recreated
            do {
                // Get a new graphics context every time through the loop
                // to make sure the strategy is validated
                Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
                try {
                    doRendering(graphics, state);
                } finally {
                    // Dispose the graphics
                    graphics.dispose();
                }
                // Repeat the rendering if the drawing buffer contents were restored
            } while (bufferStrategy.contentsRestored());

            // Display the buffer
            bufferStrategy.show();
            // Tell the system to do the drawing NOW;
            // otherwise it can take a few extra ms and will feel jerky!
            Toolkit.getDefaultToolkit().sync();

            // Repeat the rendering if the drawing buffer was lost
        } while (bufferStrategy.contentsLost());
    }

    /**
     * Rendering all game elements based on the game state.
     */
    private void doRendering(Graphics2D g2d, GameState state) {
//        // Draw background
//        g2d.setColor(Color.GRAY);
//        g2d.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
//        // Draw ball
//        g2d.setColor(Color.BLACK);
//        g2d.fillOval(state.locX, state.locY, state.diam, state.diam);


        /*		g2d.drawImage(image,state.locX,state.locY,null);*/


        // Print FPS info
//        long currentRender = System.currentTimeMillis();
//        lastRender = currentRender;
        // Print user guide

        // Draw GAME OVER
//        if (state.gameOver) {
//            String str = "GAME OVER";
//            g2d.setColor(Color.WHITE);
//            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(64.0f));
//            int strWidth = g2d.getFontMetrics().stringWidth(str);
//            g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT / 2);
//        }
    }

}
