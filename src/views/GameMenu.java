package views;

import appStart.GameManagement;
import helpers.BufferedImages;
import helpers.ImageIcons;
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

public class GameMenu {

    private JFrame mainFrame;
    private ImagePanel mainPanel;

    public GameMenu(){
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

    public void show(){
        mainFrame.setVisible(true);
    }

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

                    // initializing buffered images
                    BufferedImages.init();
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

    private String askUsername(){
        return JOptionPane.showInputDialog(
                null,
                "Choose a username...",
                "New User",
                JOptionPane.QUESTION_MESSAGE
        );
    }

    private void openSaves(){
        ShowGameSaves showGameSaves = new ShowGameSaves();
        showGameSaves.show();
    }

    public static void loadGame(GameSave save){
        // initializing buffered images
        BufferedImages.init();
        ImageIcons.init();
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