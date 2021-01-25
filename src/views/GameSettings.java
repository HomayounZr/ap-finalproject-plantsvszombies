package views;

import appStart.Configurations;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.module.Configuration;

public class GameSettings {

    private JFrame mainFrame;
    private JPanel mainPanel;

    private boolean isEasy;
    private boolean hasSound;

    public GameSettings(){
        mainFrame = new JFrame("Game Settings");
        mainFrame.setSize(300,250);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        GridLayout mainPanelLayout = new GridLayout(3,2);
        mainPanelLayout.setVgap(10);
        mainPanelLayout.setHgap(5);
        mainPanel = new JPanel(mainPanelLayout);

        mainPanel.setBorder(new EmptyBorder(20,10,20,10));

        JLabel labelMode = new JLabel("Game Mode: ");
        JComboBox comboBoxModes = new JComboBox(new String[]{"Easy","Hard"});
        comboBoxModes.setPreferredSize(new Dimension(150,30));
        comboBoxModes.addActionListener(new ComboModeItemListener());

        mainPanel.add(labelMode);
        mainPanel.add(comboBoxModes);

        JLabel labelSound = new JLabel("Has Sound: ");
        JCheckBox checkBoxSound = new JCheckBox();
        checkBoxSound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox source = (JCheckBox) e.getSource();
                hasSound = checkBoxSound.isSelected();
//                System.out.println(hasSound);
            }
        });

        mainPanel.add(labelSound);
        mainPanel.add(checkBoxSound);

        JButton btnClose = new JButton("Close");
        btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                mainFrame.dispose();
            }
        });
        mainPanel.add(btnClose);

        mainFrame.setContentPane(mainPanel);
    }

    public void show(){
        mainFrame.setVisible(true);
    }

    private class ComboModeItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                JComboBox source = (JComboBox)e.getSource();
//              System.out.println(source.getSelectedItem());
                if(source.getSelectedItem().equals("Easy")){
                    Configurations.changeToEasy();
                    isEasy = true;
                } else {
                    Configurations.changeToHard();
                    isEasy = false;
                }
            } catch (ClassCastException ex){

            }
        }
    }
}
