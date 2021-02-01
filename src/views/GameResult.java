package views;

import appStart.GameManagement;
import helpers.threads.AudioPlayer;
import helpers.threads.AudioThreadPool;
import models.User;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * GameResult Class Which is Shown At The End of Every Game
 * To Show Who Won and Who Lost
 */
public class GameResult {

    private int newScore;
    private boolean won;
    private User user;

    private JFrame mainFrame;
    private JPanel mainPanel;

    private AudioPlayer music;
    /**
     * Constructor of The Class
     * @param newScore Score Of the Player
     * @param won Whether He/She Has Lost or Won The Game
     */
    public GameResult(int newScore,boolean won){
        music = new AudioPlayer("./sounds/game_end.wav",7,true);
        AudioThreadPool.execute(music);
        this.newScore = newScore;
        this.won = won;
        this.user = GameManagement.userController.getUser();

        mainFrame = new JFrame("Game Finished");
        mainFrame.setSize(300,300);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout());

        GridLayout centerLayout = new GridLayout(4,1);
        centerLayout.setVgap(10);
        JPanel centerPanel = new JPanel(centerLayout);
        centerPanel.setBorder(new EmptyBorder(10,10,10,10));
        String color = "#db3236";
        String text = "You Lose";
        if(won){
            color = "#1976d2";
            text = "You won";
        }
        JLabel labelTitle = new JLabel(text);
        labelTitle.setForeground(Color.decode(color));
        labelTitle.setFont(new Font(null,Font.BOLD,20));

        centerPanel.add(labelTitle);
        centerPanel.add(new JLabel("Easy Wins: " + user.getWins_easy() + " - Easy Loses: " + user.getLoses_easy()));
        centerPanel.add(new JLabel("Hard Wins: " + user.getWins_hard() + " - Hard Loses: " + user.getLoses_hard()));
        JLabel labelScore = new JLabel("Your total score: " + (user.getTotalScore() + newScore));
        labelScore.setForeground(Color.decode("#1976d2"));
        labelScore.setFont(new Font(null,Font.BOLD,16));
        centerPanel.add(labelScore);

        mainPanel.add(centerPanel,BorderLayout.CENTER);

        addSubmitButton();

        mainFrame.setContentPane(mainPanel);
    }

    /**
     * Setting The Result Frame Visible
     */

    public void show(){
        mainFrame.setVisible(true);
    }

    private void addSubmitButton(){
        GridLayout bottomLayout = new GridLayout(1,2);
        bottomLayout.setHgap(10);
        JPanel bottomPanel = new JPanel(bottomLayout);

        ButtonHandler handler = new ButtonHandler();
        JButton buttonUsername = new JButton("Change Username");
        buttonUsername.addMouseListener(handler);
        buttonUsername.setPreferredSize(new Dimension(140,40));
        JButton buttonSubmit = new JButton("Submit data & Close");
        buttonSubmit.addMouseListener(handler);
        buttonSubmit.setPreferredSize(new Dimension(140,40));
        buttonSubmit.setBackground(Color.decode("#2196f3"));
        buttonSubmit.setForeground(Color.decode("#ffffff"));

        bottomPanel.add(buttonUsername);
        bottomPanel.add(buttonSubmit);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Button Handler For The Result Frame
     * 2 Items : Save Your Score For Rankings And Closing
     * Or Changing Your UserName
     */

    private class ButtonHandler extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            try{

                JButton source = (JButton) e.getSource();
                switch (source.getText()){
                    case "Change Username":
                        changeUsername();
                        break;
                    case "Submit data & Close":
                        submitData();
                        break;
                    default:
                        break;
                }

            } catch (Exception ex){

            }
        }
    }

    /**
     * The Player Can Change His/Her Username In The End Of the Game
     * Check For The Unity Of The Chosen UserNAME
     */

    private void changeUsername(){
        boolean result = false;
        do{
            String input = JOptionPane.showInputDialog("Enter your username...");
            while(input == null || input.length() == 0)
                return;
//                input = JOptionPane.showInputDialog("Enter your username...");

            result = GameManagement.userController.changeUsername(user.getUsername(),input);
            if(!result)
                JOptionPane.showMessageDialog(null,"Username exists try again or check your connection");
        } while (!result);
        JOptionPane.showMessageDialog(null,"New username saved");
    }

    /**
     * Saving Players Data for The Ranking List
     */

    private void submitData(){
        GameManagement.userController.saveNewData(newScore);
        // save to server
        boolean result = false;
        while(!result){
            result = GameManagement.userController.submitDataToServer();
            if(!result)
                JOptionPane.showMessageDialog(null,"Failed to save data, check your connection");
        }
        music.stop();
        GameMenu.audioPlayer = new AudioPlayer("./sounds/menu.wav",8.5,true);
        mainFrame.dispose();
    }

}
