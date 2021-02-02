package views;

import appStart.GameManagement;
import models.GameSave;
import models.GameSaves;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicListUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ShowGameSaves {

    private JFrame mainFrame;
    private JPanel mainPanel;
    private JTable table;

    ArrayList<GameSave> gameSaves;

    public ShowGameSaves(){
        gameSaves = new ArrayList<>();
        mainFrame = new JFrame("Saves");
        mainFrame.setSize(400,400);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout());

        tablePanelLocal();

        mainPanel.add(southPanel(),BorderLayout.SOUTH);

        mainFrame.setContentPane(mainPanel);
    }

    public void show(){
        mainFrame.setVisible(true);
    }

    public void tablePanelLocal(){
        String[] columns = {"Save"};
        DefaultTableModel tableModel = new DefaultTableModel(columns,0);

        try{

            File file = new File("./data/gamesaves.txt");
            if(!file.exists())
                file.createNewFile();

            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
            GameSaves saves = (GameSaves) inputStream.readObject();
            if(saves != null)
                gameSaves = saves.getGameSaves();

        } catch (Exception ex){
            ex.printStackTrace();
        }

        for (int i = 0;i < gameSaves.size();i++){
            Object[] row = {
                  "Save " + (i + 1)
            };
            tableModel.addRow(row);
        }

        table = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.getSelectionModel().addListSelectionListener(new SaveSelectionListener());
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);

        mainPanel.add(scrollPane,BorderLayout.CENTER);
    }

    public void tablePanelServer(){
        String[] columns = {"Save"};
        DefaultTableModel tableModel = new DefaultTableModel(columns,0);

        try{

            GameManagement.userController.getSavesFromServer();

            File serverSaves = new File("./data/serversaves.txt");
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(serverSaves));
            GameSaves saves = (GameSaves) inputStream.readObject();
            if(saves != null)
                gameSaves = saves.getGameSaves();
            else
                gameSaves = new ArrayList<>();

        } catch (Exception ex){
            ex.printStackTrace();
        }

        for (int i = 0;i < gameSaves.size();i++){
            Object[] row = {
                    "Save " + (i + 1)
            };
            tableModel.addRow(row);
        }

        table = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.getSelectionModel().addListSelectionListener(new SaveSelectionListener());
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);

        mainPanel.add(scrollPane,BorderLayout.CENTER);
    }

    private JPanel southPanel(){
        GridLayout layout = new GridLayout(1,3);
        layout.setHgap(10);
        JPanel panel = new JPanel(layout);
        panel.setBorder(new EmptyBorder(5,5,5,5));

        ButtonHandler handler = new ButtonHandler();
        JButton btnLoadLocal = new JButton("Load Local");
        JButton btnLoadServer = new JButton("Load Server");
        JButton btnClose = new JButton("Close");

        btnLoadLocal.addMouseListener(handler);
        btnLoadServer.addMouseListener(handler);
        btnClose.addMouseListener(handler);
        btnLoadLocal.setPreferredSize(new Dimension(76,40));
        btnLoadServer.setPreferredSize(new Dimension(76,40));
        btnClose.setPreferredSize(new Dimension(76,40));

        panel.add(btnLoadLocal);
        panel.add(btnLoadServer);
        panel.add(btnClose);

        return panel;
    }

    private class ButtonHandler extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            try{

                JButton source = (JButton) e.getSource();
                if(source.getText().equals("Load Local")){
                    tablePanelLocal();
                } else if(source.getText().equals("Load Server")){
                    tablePanelServer();
                } else {
                    mainFrame.dispose();
                }

            } catch (Exception ex){

            }
        }
    }

    private class SaveSelectionListener implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int rowIndex = table.getSelectedRow();
            GameSave save = gameSaves.get(rowIndex);
            GameMenu.loadGame(save);
            mainFrame.dispose();
        }
    }

}
