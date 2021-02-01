package views;

import appStart.GameManagement;
import models.BoardItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Ranking Page Class
 * Shows The Ranking Of the Players Based Of Their Score
 */

public class RankingsPage {

    private JFrame mainFrame;
    private JPanel mainPanel;

    private ArrayList<BoardItem> items;

    /**
     * Constructor of The Class
     */

    public RankingsPage(){
        mainFrame = new JFrame("Rankings");
        mainFrame.setSize(550,400);
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

        refreshData();
        getTable();

        mainPanel.add(southPanel,BorderLayout.SOUTH);

        mainFrame.setContentPane(mainPanel);
    }

    /**
     * Setting The Frame Visible
     */

    public void show(){
        mainFrame.setVisible(true);
    }

    /**
     * Button Handler for the Frame
     * Only A Refresh Button For Renewing The Data
     *
     */

    private class ButtonClickHandler extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            JButton source = (JButton) e.getSource();
            if(source.getText().equals("Refresh Data")){
//                System.out.println("Refreshing...");
                refreshData();
                getTable();
            } else {
                mainFrame.dispose();
            }
        }
    }

    /**
     * Refreshing Data Method TO Renew the Info in The Ranking List
     */

    private void refreshData(){
        items = GameManagement.userController.getScoreBoard();
//        for(BoardItem item: items){
//            System.out.println(item.getUsername());
//        }
    }

    /**
     * Ranking Table Elements included
     */

    public void getTable(){
//        JPanel centerPanel = new JPanel();

        String columns[] = {"Username","Score","Easy Wins","Easy Loses","Hard Wins","Hard Loses"};
        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        for(BoardItem item: items){
            Object[] rowData = {
                    item.getUsername(),
                    item.getTotalScore(),
                    item.getWins_easy(),
                    item.getLoses_easy(),
                    item.getWins_hard(),
                    item.getLoses_hard()
            };
            tableModel.addRow(rowData);
        }

        JTable table = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JScrollPane scrollPane = new JScrollPane(table);
//        centerPanel.add(scrollPane);
        mainPanel.add(scrollPane,BorderLayout.CENTER);
    }

}
