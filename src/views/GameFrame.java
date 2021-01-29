/*** In The Name of Allah ***/
package views;

import helpers.BufferedImages;
import helpers.ImageIcons;
import models.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

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

    private BufferStrategy bufferStrategy;

    private JPanel plantingPanel;

    public GameFrame(String title) {
        super(title);
        setResizable(false);
        setSize(GAME_WIDTH, GAME_HEIGHT);
        //
        // Initialize the JFrame ...
        //
        plantingPanel = null;
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
        // Get a new graphics context to render the current frame
        Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
        try {
            // Do the rendering
            doRendering(graphics, state);
        } finally {
            // Dispose the graphics, because it is no more needed
            graphics.dispose();
        }
        // Display the buffer
        bufferStrategy.show();
        // Tell the system to do the drawing NOW;
        // otherwise it can take a few extra ms and will feel jerky!
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Rendering all game elements based on the game state.
     */
    private void doRendering(Graphics2D g2d, GameState state) {
        //
        // Draw all game elements according
        //  to the game 'state' using 'g2d' ...
        //
//        this.removeAll();
        g2d.drawImage(BufferedImages.background,0,0,this);
        if(plantingPanel == null){
            plantingPanel = new JPanel();
            plantingPanel.setBackground(new Color(255,255,255,0));
            plantingPanel.setBounds(80,100,846,600);
            plantingPanel.addMouseListener(state.getMouseListener());
            this.add(plantingPanel);
        }

        createCardsPanelG2D(g2d,state);

        addLawnMowersG2D(g2d,state);

        addPlantsG2D(g2d,state);

        addBulletsG2D(g2d,state);

        addSunsG2D(g2d,state);

        addZombiesG2D(g2d,state);

        /*
            for gifs -->>
            Graphics2D g2d = (Graphics2D) g;
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image image = toolkit.getImage("a.gif");
            g2d.drawImage(image, 100, 150, this);
         */

//        g2d.drawImage(BufferedImages.bullet_normal,200,200,this);
//        currentPanel = new JPanel(new BorderLayout());
//
//        addUselessPanels();
//
//        createCardsPanel(state,state.getCards());
//
//        addLawnMowers(state,state.getLawnMowers());
//
//        addCenterPanel(state);
//
//        setContentPane(currentPanel);
    }

    private void createCardsPanelG2D(Graphics2D g,GameState state){
        g.setColor(Color.BLACK);
        g.setFont(new Font(null,Font.BOLD,18));
        g.drawString("" + state.getPlayerSuns(),45,90);

        int i = 1;
        for(Card card: state.getCards()){
                JLabel label = new JLabel(card.getImageIcon());
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        super.mousePressed(e);

                        Card selectedCard = card;
                        state.changeSelectedCard(card);
                    }
                });
                label.setEnabled(card.getIsEnable());
                label.setBackground(Color.WHITE);
                label.setBounds(i * 64 + 50,10,64,90);
                this.add(label);
                g.drawImage(card.getImage(),i * 64 + 50,10,this);
                i++;
        }
    }

    private void addLawnMowersG2D(Graphics2D g,GameState state){
        int i = 0;
        for(LawnMower lawnMower: state.getLawnMowers()){
            if(i == lawnMower.getRow()){
                g.drawImage(lawnMower.getImage(),0,(i + 1) * 120,this);
            }
            i++;
        }
    }

    private void addPlantsG2D(Graphics2D g,GameState state){
        for(int i = 0;i < 5;i++){
            for(int j = 0;j < 9;j++){
                Plant cellPlant = state.checkPlantExist(j,i);
                if(cellPlant != null){
                    g.drawImage(cellPlant.getImage(),cellPlant.getLocationX(),cellPlant.getLocationY(),this);
                }
            }
        }
    }

    private void addBulletsG2D(Graphics2D g,GameState state){
        synchronized (state.getBullets()){
            for(Bullet bullet: state.getBullets()){
                g.drawImage(bullet.getImage(),bullet.getLocationX(), bullet.getLocationY(),this);
            }
        }
    }

    private void addSunsG2D(Graphics2D g,GameState state){
        synchronized (state.getSuns()){
            for(Sun sun: state.getSuns()){
                g.drawImage(BufferedImages.sun,sun.getLocationX(),sun.getLocationY(),this);
                JLabel label = new JLabel();
                label.setPreferredSize(new Dimension(50,50));
                label.setBounds(sun.getLocationX(),sun.getLocationY(),50,50);

//                plantingPanel.add(label);
//                label.addMouseListener(new MouseAdapter() {
//                    @Override
//                    public void mousePressed(MouseEvent e) {
//                        super.mousePressed(e);
//                        synchronized (state.getSuns()){
//                            state.collectSun(sun);
//                        }
//                        plantingPanel.remove(label);
//                        plantingPanel.revalidate();
//                    }
//                });
            }
        }
    }

    private void addZombiesG2D(Graphics2D g,GameState state){
        synchronized (state.getZombies()){
            for(Zombie zombie: state.getZombies()){
                g.drawImage(
                        zombie.getImage(),
                        zombie.getLocationX(),
                        zombie.getLocationY(),
                        this
                );
            }
        }
    }

//    private void addUselessPanels(){
//        // this east panel is just for better alignment of other elements
//        JPanel eastPanel = new JPanel(new GridLayout(5,1));
//        eastPanel.setPreferredSize(new Dimension(77,600));
//        eastPanel.setBackground(new Color(255,255,255,0));
//
//        currentPanel.add(eastPanel,BorderLayout.EAST);
//
//        JPanel southPanel = new JPanel();
//        southPanel.setPreferredSize(new Dimension(1000,50));
//        southPanel.setBackground(new Color(255,255,255,0));
//
//        currentPanel.add(southPanel,BorderLayout.SOUTH);
//    }
//
//    private void createCardsPanel(GameState state,ArrayList<Card> cards){
//        JPanel cardsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        cardsPanel.setPreferredSize(new Dimension(64*5,102));
//        cardsPanel.setBorder(new EmptyBorder(0,30,0,0));
//        cardsPanel.setBackground(new Color(255,255,255,0));
//        cardsPanel.setAlignmentY(FlowLayout.LEADING);
//
//        JLabel labelSuns = new JLabel("" + state.getPlayerSuns());
//        labelSuns.setFont(new Font(null,Font.BOLD,20));
//        labelSuns.setPreferredSize(new Dimension(64,102));
//        labelSuns.setBorder(new EmptyBorder(60,0,0,0));
//        cardsPanel.add(labelSuns);
//
//        for(Card card: cards){
//            try{
//
////				ImageIcon icon = new ImageIcon(card.getImage());
//                JLabel label = new JLabel(card.getImageIcon());
//                label.setPreferredSize(new Dimension(64,102));
//                label.addMouseListener(new MouseAdapter() {
//                    @Override
//                    public void mousePressed(MouseEvent e) {
//                        super.mousePressed(e);
//
//                        Card selectedCard = card;
//                        state.changeSelectedCard(card);
//                    }
//                });
//                label.setEnabled(card.getIsEnable());
//                cardsPanel.add(label);
//
//            } catch (Exception ex){
//
//            }
//        }
//
//        currentPanel.add(cardsPanel,BorderLayout.NORTH);
//    }
//
//    private void addLawnMowers(GameState state,ArrayList<LawnMower> lawnMowers){
//        JPanel westPanel = new JPanel(new GridLayout(5,1));
//        westPanel.setPreferredSize(new Dimension(77,600));
//        westPanel.setBackground(new Color(255,255,255,0));
//
//        int i = 0;
//        for(LawnMower lawnMower: lawnMowers){
//            if(i == lawnMower.getRow()){
//                // create label for lawn mower
//                try{
//
////					BufferedImage image = ImageIO.read(new File(lawnMower.getImageUri()));
////					ImageIcon icon = new ImageIcon(ImageIcons.lawn_mower);
//                    JLabel label = new JLabel(lawnMower.getImageIcon());
//                    label.setPreferredSize(new Dimension(77,130));
//                    label.setBorder(new EmptyBorder(0,0,20,0));
//                    westPanel.add(label);
//
//                } catch (Exception ex){
//                    ex.printStackTrace();
//                }
//
//            }
//            i++;
//        }
//
//        currentPanel.add(westPanel,BorderLayout.WEST);
//    }
//
//    private void addCenterPanel(GameState state){
//        JLayeredPane layeredPane = new JLayeredPane();
//        layeredPane.setBounds(77,102,846,600);
//
//        GridBagLayout centerLayout = new GridBagLayout();
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.weightx = (double)1 / 9;
//        gbc.weighty = (double)1 / 5;
//        JPanel centerPanel = new JPanel(centerLayout);
//        centerPanel.setBounds(0,0,846,600);
//        centerPanel.setBackground(new Color(255,255,255,0));
//        centerPanel.setOpaque(true);
//
//        for(int i = 0;i < 5;i++){
//            for(int j = 0;j < 9;j++){
//                try{
//
//                    Plant cellPlant = state.checkPlantExist(j,i);
//                    if(cellPlant != null){
////						ImageIcon icon = new ImageIcon(cellPlant.getImage());
//                        JLabel label = new JLabel(cellPlant.getImageIcon());
//                        gbc.gridx = j;
//                        gbc.gridy = i;
//                        label.setPreferredSize(new Dimension(94,120));
//                        centerLayout.setConstraints(label,gbc);
//                        centerPanel.add(label);
//                    } else {
//                        JLabel label = new JLabel();
//                        gbc.gridx = j;
//                        gbc.gridy = i;
//                        label.setPreferredSize(new Dimension(94,120));
//                        centerLayout.setConstraints(label,gbc);
//                        centerPanel.add(label);
//                    }
//
//                } catch (Exception ex){
//                    ex.printStackTrace();
//                }
//            }
//        }
//
//        centerPanel.addMouseListener(state.getMouseListener());
//
//        layeredPane.add(centerPanel,Integer.valueOf(1));
//        layeredPane.add(generateSecondPanel(state),Integer.valueOf(2));
//
//        currentPanel.add(layeredPane,BorderLayout.CENTER);
//    }
//
//    private JPanel generateSecondPanel(GameState state){
//
//        JPanel panel = new JPanel(null);
//        panel.setBounds(0,0,846,600);
//        panel.setBackground(new Color(255,255,255,0));
//
//        // adding zombies
//        synchronized (state.getZombies()){
//            for(Zombie zombie: state.getZombies()){
//                try{
//
//                    ImageIcon icon = new ImageIcon(zombie.getImage());
//                    JLabel label = new JLabel(icon);
//                    label.setBackground(Color.BLUE);
//                    label.setBounds(zombie.getX(),zombie.getY(),150,150);
//                    panel.add(label);
//
//                } catch(Exception ex){
//                    ex.printStackTrace();
//                }
//            }
//        }
//
//        synchronized (state.getSuns()){
//            for(Sun sun: state.getSuns()){
//                try{
//
////					ImageIcon icon = new ImageIcon(BufferedImages.sun);
//                    JLabel label = new JLabel(ImageIcons.sun);
//                    label.setBackground(Color.BLUE);
//                    label.setBounds(sun.getLocationX(),sun.getLocationY(),50,50);
//
//                    label.addMouseListener(new MouseAdapter() {
//                        @Override
//                        public synchronized void mousePressed(MouseEvent e) {
//                            super.mousePressed(e);
//                            state.collectSun(sun);
//                            System.out.println("Hi");
//                        }
//                    });
//
//                    panel.add(label);
//
//                } catch(Exception ex){
//                    ex.printStackTrace();
//                }
//            }
//        }
//
//
//        // adding bullets
//        synchronized (state.getBullets()){
//            for(Bullet bullet: state.getBullets()){
//                try{
//
////					ImageIcon icon = new ImageIcon(bullet.getImage());
//                    JLabel label = new JLabel(bullet.getImageIcon());
//                    label.setBackground(Color.BLUE);
//                    label.setBounds(bullet.getLocationX(),bullet.getLocationY(),50,50);
//
//                    panel.add(label);
//
//                } catch(Exception ex){
//                    ex.printStackTrace();
//                }
//            }
//        }
//
//
//        panel.setOpaque(false);
//
////		if(previousZombiePanel != null){
////			previousZombiePanel.revalidate();
////			previousZombiePanel.repaint();
////		}
////		previousZombiePanel = panel;
//
//        return panel;
//    }

}
