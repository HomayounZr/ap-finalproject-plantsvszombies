package views;

import helpers.threads.ThreadPool;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PauseMenu {

    private GameState state;

    private JFrame mainFrame;
    private JPanel mainPanel;

    public PauseMenu(GameState state){
        this.state = state;

        mainFrame = new JFrame("Pause Menu");
        mainFrame.setSize(250,300);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        GridLayout layout = new GridLayout(3,1);
        layout.setVgap(10);
        mainPanel = new JPanel(layout);
        mainPanel.setBorder(new EmptyBorder(20,20,20,20));

        ButtonHandler handler = new ButtonHandler();
        JButton resumeButton = new JButton("Resume current game");
        resumeButton.addMouseListener(handler);
        JButton saveButton = new JButton("Save game and exit");
        saveButton.addMouseListener(handler);
        JButton exitButton = new JButton("Exit to main menu");
        exitButton.addMouseListener(handler);

        mainPanel.add(resumeButton);
        mainPanel.add(saveButton);
        mainPanel.add(exitButton);

        mainFrame.setContentPane(mainPanel);
    }

    public void show(){
        mainFrame.setVisible(true);
    }

    private class ButtonHandler extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);

            try{

                JButton source = (JButton) e.getSource();
                switch (source.getText()){
                    case "Resume current game":
                        resumeGame();
                        break;
                    case "Save game and exit":
                        saveAndExit();
                        break;
                    case "Exit to main menu":
                        exit();
                        break;
                    default:
                        break;
                }

            } catch (ClassCastException ex){

            }
        }
    }

    private void exit(){
        ThreadPool.shutdownNow();
        // close frame and state and loop
    }

    private void resumeGame(){
//        ThreadPool.resumePool();
        mainFrame.dispose();
    }

    private void saveAndExit(){
        state.saveCurrentGame();
        // save the data to file
        ThreadPool.shutdownNow();
    }


}
