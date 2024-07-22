package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseLevel extends JFrame {
    private JButton btnEasy;
    private JButton btnHard;
    private JButton btnHome;
    private String player;
    private Database db;

    public ChooseLevel(String player, Database db) {
        this.player = player;
        this.db = db;
        initComponents();
    }

    private void initComponents() {
        setTitle("Choose Level");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        btnEasy = new JButton("Easy");
        btnHard = new JButton("Hard");
        btnHome = new JButton("Home");

        panel.add(btnEasy);
        panel.add(btnHard);
        panel.add(btnHome);

        add(panel);

        btnEasy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlayWithEz(player, db).setVisible(true);
                dispose();
            }
        });

        btnHard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlayWithHard(player, db).setVisible(true);
                dispose();
            }
        });

        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home().setVisible(true);
                dispose();
            }
        });
    }
}
