package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerNameAI extends JFrame {
    private JTextField playerField;
    private JButton startButton;
    private JButton homeButton;
    private Database db;

    public PlayerNameAI() {
        db = new Database();
        initComponents();
    }

    private void initComponents() {
        setTitle("Enter Player Name");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        playerField = new JTextField();
        startButton = new JButton("Start Game");
        homeButton = new JButton("Home");

        panel.add(new JLabel("Player:"));
        panel.add(playerField);
        panel.add(startButton);
        panel.add(homeButton);

        add(panel);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String player = playerField.getText();
                if (!player.isEmpty()) {
                    new ChooseLevel(player, db).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter player name.");
                }
            }
        });

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home().setVisible(true);
                dispose();
            }
        });
    }
}
