package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RankingsPage {

    private JFrame mainFrame;
    private JPanel mainPanel;

    public RankingsPage(){
        mainFrame = new JFrame("Rankings");
        mainFrame.setSize(400,400);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout());

        GridLayout southPanelLayout = new GridLayout(1,2);
        southPanelLayout.setHgap(5);
        JPanel southPanel = new JPanel(southPanelLayout);
        southPanel.setBorder(new EmptyBorder(5,5,5,5));

        ButtonClickHandler buttonHandler = new ButtonClickHandler();

        JButton buttonRefresh = new JButton("Refresh Data");
        buttonRefresh.setPreferredSize(new Dimension(190,40));
        buttonRefresh.addMouseListener(buttonHandler);

        JButton buttonClose = new JButton("Close");
        buttonClose.setPreferredSize(new Dimension(190,40));
        buttonClose.addMouseListener(buttonHandler);

        southPanel.add(buttonRefresh);
        southPanel.add(buttonClose);

        mainPanel.add(southPanel,BorderLayout.SOUTH);

        mainFrame.setContentPane(mainPanel);
    }

    public void show(){
        mainFrame.setVisible(true);
    }

    private class ButtonClickHandler extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            JButton source = (JButton) e.getSource();
            if(source.getText().equals("Refresh Data")){
                System.out.println("Refreshing...");
            } else {
                mainFrame.dispose();
            }
        }
    }
}
