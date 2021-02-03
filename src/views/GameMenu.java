package views;

import appStart.Configurations;
import appStart.GameManagement;
import helpers.BufferedImages;
import helpers.GifHelper;
import helpers.ImageIcons;
import helpers.threads.AudioPlayer;
import helpers.threads.AudioThreadPool;
import helpers.threads.ThreadPool;
import models.GameSave;
import models.GameSaves;
import models.User;
import myComponents.ImagePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * GameMenu Class Is for the Start of the Game
 * First Screen that the PLayer Can Change How He/She Likes to Play The game
 * Access Settings , Username Input , Level Of The Game
 */
public class GameMenu {

    private JFrame mainFrame;
    private ImagePanel mainPanel;

    public static AudioPlayer audioPlayer;

    /**
     * Constructor Of the Class
     */
    public GameMenu(){
        AudioThreadPool.init();
        // play audio if sound is on
        if(Configurations.hasSound) {
            audioPlayer = new AudioPlayer("./sounds/menu.wav", 8.5, true);
            AudioThreadPool.execute(audioPlayer);
        }

        mainFrame = new JFrame();
        mainFrame.setTitle("Plants vs Zombies");
        mainFrame.setSize(600,500);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel = new ImagePanel("./images/background.jpg",600);

        // check that if user exists
        User user = GameManagement.userController.getUser();
        if(user == null){
            // ask for a new username
            boolean result = false;
            do {
                String username = askUsername();
//                System.out.println(username);
                result = GameManagement.userController.newUser(username);
            } while(!result);
            JOptionPane.showMessageDialog(
                    null,
                    "Username changed",
                    "Saved",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

        mainPanel.add(addButtons());

        mainFrame.setContentPane(mainPanel);
    }

    /**
     * Setting the MainFrame Visible
     */
    public void show(){
        mainFrame.setVisible(true);
    }

    /**
     * Adding Menu Buttons To the Main Frame
     * @return buttons in the Frame
     */
    private JPanel addButtons(){
        JPanel flowPanel = new JPanel(new FlowLayout());

        flowPanel.setPreferredSize(new Dimension(250,400));
        flowPanel.setBorder(new EmptyBorder(50,0,0,0));
        flowPanel.setBackground(new Color(255,255,255,0));

        MenuButtonHandler mouseHandler = new MenuButtonHandler();

        JButton btnNewGame = new JButton("New Game");
        btnNewGame.setPreferredSize(new Dimension(250,40));
        btnNewGame.addMouseListener(mouseHandler);

        JButton btnLoadGame = new JButton("Load Game");
        btnLoadGame.setPreferredSize(new Dimension(250,40));
        btnLoadGame.addMouseListener(mouseHandler);

        JButton btnRanking = new JButton("Rankings");
        btnRanking.setPreferredSize(new Dimension(250,40));
        btnRanking.addMouseListener(mouseHandler);

        JButton btnSettings = new JButton("Settings");
        btnSettings.setPreferredSize(new Dimension(250,40));
        btnSettings.addMouseListener(mouseHandler);

        JButton btnQuit = new JButton("Quit");
        btnQuit.setPreferredSize(new Dimension(250,40));
        btnQuit.addMouseListener(mouseHandler);

        flowPanel.add(btnNewGame);
        flowPanel.add(btnLoadGame);
        flowPanel.add(btnRanking);
        flowPanel.add(btnSettings);
        flowPanel.add(btnQuit);

        return flowPanel;
    }

    /**
     * ButtonHandler For The Menu Items
     */
    private class MenuButtonHandler extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            try {
                JButton source = (JButton) e.getSource();
                String sourceText = source.getText();
                if (sourceText.equals("Quit")) {
                    System.exit(0);
                } else if (sourceText.equals("Settings")) {
                    GameSettings settings = new GameSettings();
                    settings.show();
                } else if (sourceText.equals("Rankings")){
                    RankingsPage rankings = new RankingsPage();
                    rankings.show();
                } else if (sourceText.equals("New Game")) {
                    if(Configurations.hasSound)
                        audioPlayer.stop();

                    // initializing buffered images
                    BufferedImages.init();
//                    GifHelper.init();
                    // Initialize the global thread-pool
                    ThreadPool.init();

                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            GameFrame frame = new GameFrame("PvZ Starting...");
                            frame.setLocationRelativeTo(null); // put frame at center of screen
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            frame.setVisible(true);
                            frame.initBufferStrategy();
                            // Create and execute the game-loop
                            GameLoop game = new GameLoop(frame);
                            game.init(null);
                            ThreadPool.execute(game);
                            // and the game starts ...
                        }
                    });

                } else {
                    openSaves();
                }

            } catch (ClassCastException ex){

            }
        }
    }

    /**
     * Ask a new username with JOptionPane
     * @return String username
     */
    private String askUsername(){
        return JOptionPane.showInputDialog(
                null,
                "Choose a username...",
                "New User",
                JOptionPane.QUESTION_MESSAGE
        );
    }

    /**
     * Showing All the Saved Games Of the Player On the Pc or Server
     */
    private void openSaves(){
        ShowGameSaves showGameSaves = new ShowGameSaves();
        showGameSaves.show();
    }

    /**
     * Loading the Chosen Game
     * @param save GameState That Was Saved Before
     */
    public static void loadGame(GameSave save){
        // stop current audio
        if(Configurations.hasSound)
            audioPlayer.stop();
        // initializing buffered images
        BufferedImages.init();
//        GifHelper.init();
        // Initialize the global thread-pool
        ThreadPool.init();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameFrame frame = new GameFrame("PvZ Starting...");
                frame.setLocationRelativeTo(null); // put frame at center of screen
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
                frame.initBufferStrategy();
                // Create and execute the game-loop
                GameLoop game = new GameLoop(frame);
                game.init(save);
                ThreadPool.execute(game);
                // and the game starts ...
            }
        });
    }
}