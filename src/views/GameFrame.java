package views;

import models.*;
import models.enums.PlantType;
import myComponents.ImagePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameFrame {

    private JFrame mainFrame;
    private ImagePanel mainPanel;
    private final int GAME_HEIGHT = 752;
    private final int GAME_WIDTH = 1000;
    private Card selectedCard;

    private ArrayList<Card> cards = new ArrayList<>();
//    private ArrayList<JLabel>
    

    public GameFrame(){
        mainFrame = new JFrame("Game...");
        mainFrame.setSize(GAME_WIDTH,GAME_HEIGHT);
        mainFrame.setLocationRelativeTo(null);
//        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        cards.add(new Card(PlantType.SUNFLOWER));
        cards.add(new Card(PlantType.PEASHOOTER));
        cards.add(new Card(PlantType.SNOWPEASHOOTER));
        cards.add(new Card(PlantType.GIANTWALLNUT));
        cards.add(new Card(PlantType.CHERRYBOMB));

        mainPanel = new ImagePanel("./images/mainBG.png",GAME_WIDTH,new BorderLayout());

        JPanel cardsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cardsPanel.setPreferredSize(new Dimension(64*5,90));
        cardsPanel.setBorder(new EmptyBorder(0,30,0,0));
//        cardsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardsPanel.setBackground(new Color(255,255,255,1));
        cardsPanel.setAlignmentY(FlowLayout.LEADING);

        JLabel labelSuns = new JLabel("00000");
        labelSuns.setPreferredSize(new Dimension(64,90));
        labelSuns.setBorder(new EmptyBorder(50,0,0,0));
        cardsPanel.add(labelSuns);

        for(Card card: cards){
            try{
                System.out.println(card.getImageUri());
                BufferedImage image = ImageIO.read(new File(card.getImageUri()));
                ImageIcon icon = new ImageIcon(image);
                JLabel label = new JLabel(icon);
                label.setPreferredSize(new Dimension(64,90));
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        super.mousePressed(e);
                        System.out.println(card.getName() + " clicked");
                        selectedCard = card;
                    }
                });
                cardsPanel.add(label);
            } catch (Exception ex){

            }
        }

        mainPanel.add(cardsPanel,BorderLayout.NORTH);


        JPanel westPanel = new JPanel(new GridLayout(5,1));
        westPanel.setPreferredSize(new Dimension(75,662));
        westPanel.setBackground(new Color(255,255,255,0));

        for(int i = 0;i < 5;i++){
            try{
                BufferedImage image = ImageIO.read(new File("./images/Lawn_Mower.png"));
                ImageIcon icon = new ImageIcon(image);
                JLabel label = new JLabel(icon);
                label.setPreferredSize(new Dimension(75,90));
                label.setBorder(new EmptyBorder(0,0,20,0));
                westPanel.add(label);

            } catch (Exception ex){
                ex.printStackTrace();
            }
        }

        mainPanel.add(westPanel,BorderLayout.WEST);

        JPanel eastPanel = new JPanel(new GridLayout(5,1));
        eastPanel.setPreferredSize(new Dimension(75,662));
        eastPanel.setBackground(new Color(255,255,255,1));

        mainPanel.add(eastPanel,BorderLayout.EAST);

//        GridLayout centerLayout = new GridLayout(5,9);
//        centerLayout.setHgap(10);
//        centerLayout.setVgap(5);
//        JPanel centerPanel = new JPanel();

        GridBagLayout centerLayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = (double)1 / 9;
        gbc.weighty = (double)1 / 5;
//        gbc.gridwidth = GAME_WIDTH / (double)9;
//        gbc.gridheight = GAME_HEIGHT / (double)5;
        JPanel centerPanel = new JPanel(centerLayout);
        centerPanel.setPreferredSize(new Dimension(925,662));
        centerPanel.setBackground(new Color(255,255,255,0));

        for(int i = 0;i < 5;i++){
            for(int j = 0;j < 9;j++){
                try{
                    BufferedImage image = ImageIO.read(new File("./images/Gifs/sunflower.gif"));
                    ImageIcon icon = new ImageIcon(image);
//                    JLabel label = new JLabel(icon);
                    JLabel label = new JLabel();
                    gbc.gridx = j;
                    gbc.gridy = i;
                    label.setPreferredSize(new Dimension(65,65));
                    label.setBackground(Color.decode("#1976d2"));
                    label.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            super.mousePressed(e);

                            System.out.println("" + gbc.gridx + " " + gbc.gridy);

                            Plant newPlant = null;
                            switch (selectedCard.getPlantType()){
                                case SUNFLOWER:
                                    newPlant = new SunFlowerPlant(new Coordinate(0,0));
                                    break;
                                case PEASHOOTER:
                                    newPlant = new PeaShooterPlant(new Coordinate(0,0));
                                    break;
                                case SNOWPEASHOOTER:
                                    newPlant = new SnowPeaShooterPlant(new Coordinate(0,0));
                                    break;
                                case GIANTWALLNUT:
                                    newPlant = new WallNutPlant(new Coordinate(0,0));
                                    break;
                                case CHERRYBOMB:
                                    newPlant = new CherryBombPlant(new Coordinate(0,0));
                                    break;
                                default:
                                    break;
                            }

                            BufferedImage image = null;
                            try {
                                image = ImageIO.read(new File(newPlant.getImageUri()));
                            } catch (Exception ex){

                            }
                            ImageIcon icon = new ImageIcon(image);

                            label.setIcon(icon);
                        }
                    });
//                    label.setBorder(new EmptyBorder(50,50,50,50));
                    centerLayout.setConstraints(label,gbc);
                    centerPanel.add(label);
                } catch (Exception ex){

                }
            }
        }
//        try{
//            BufferedImage image = ImageIO.read(new File("./images/Gifs/sunflower.gif"));
//            ImageIcon icon = new ImageIcon(image);
//            JLabel label = new JLabel(icon);
//            gbc.gridx = 2;
//            gbc.gridy = 2;
//            centerLayout.setConstraints(label,gbc);
//            centerPanel.add(label);
//
//            label = new JLabel(icon);
//            gbc.gridx = 0;
//            gbc.gridy = 0;
//            centerLayout.setConstraints(label,gbc);
//            centerPanel.add(label);
//        } catch (Exception ex){
//
//        }

        mainPanel.add(centerPanel,BorderLayout.CENTER);

//        JPanel zombiesPanel = new JPanel();
//
//        zombiesPanel.setPreferredSize(new Dimension(925,662));
//        zombiesPanel.setBackground(new Color(255,255,255,0));

//        ExecutorService service = Executors.newCachedThreadPool();
//        try{
//            BufferedImage image = ImageIO.read(new File("./images/Gifs/zombie_normal.gif"));
//            ImageIcon icon = new ImageIcon(image);
//            JLabel zombieLabel = new JLabel(icon);
//            zombieLabel.setPreferredSize(new Dimension(90,90));
//            zombieLabel.setLocation(752,400);
//            centerPanel.add(zombieLabel);
//
//            ZombieThread moveThread = new ZombieThread(zombieLabel);
//            service.execute(moveThread);
//        } catch (Exception ex){
//            ex.printStackTrace();
//        }

//        mainPanel.add(zombiesPanel,BorderLayout.CENTER);

        mainFrame.setContentPane(mainPanel);
    }

    public void show(){
        mainFrame.setVisible(true);
    }


}


//class ZombieThread implements Runnable{
//
//    private JLabel label;
//    public ZombieThread(JLabel label){
//        this.label = label;
//    }
//
//    @Override
//    public void run() {
//        try{
//            while(true){
//                label.setLocation((int)label.getLocation().getX() - 5,(int)label.getLocation().getY());
//                Thread.sleep(100);
//            }
//        } catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//}