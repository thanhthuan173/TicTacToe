package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModeAI extends JFrame {
    private JButton easyButton;
    private JButton hardButton;
    private String playerName;
    private Database db;

    public ModeAI(String playerName, Database db) {
        this.playerName = playerName;
        this.db = db;
        initComponents();
    }

    private void initComponents() {
        setTitle("Select Mode");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        easyButton = new JButton("Easy");
        hardButton = new JButton("Hard");

        panel.add(easyButton);
        panel.add(hardButton);

        add(panel);

//        easyButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new PlayWithEz(playerName, db).setVisible(true);
//                dispose();
//            }
//        });
//
//        hardButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new PlayWithHard(playerName, db).setVisible(true);
//                dispose();
//            }
//        });
    }
}
