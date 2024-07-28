package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerName extends JFrame {
    private JTextField player1Field;
    private JTextField player2Field;
    private JButton startButton;
    private JButton homeButton;
    private Database db;

    public PlayerName() {
        db = new Database();
        initComponents();
    }

    private void initComponents() {
        setTitle("Enter Player Names");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        player1Field = new JTextField();
        player2Field = new JTextField();
        startButton = new JButton("Start Game");
        homeButton = new JButton("Home");

        panel.add(new JLabel("Player 1:"));
        panel.add(player1Field);
        panel.add(new JLabel("Player 2:"));
        panel.add(player2Field);
        panel.add(startButton);
        panel.add(homeButton);

        add(panel);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String player1 = player1Field.getText();
                String player2 = player2Field.getText();

                if (!player1.isEmpty() && !player2.isEmpty()) {
                    // Chuyển sang class Play
                    new ChooseSize(player1, player2, db).setVisible(true);
                    dispose(); // Đóng cửa sổ nhập tên người chơi
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter names for both players.");
                }
            }
        });

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home().setVisible(true); // Chuyển sang PlayerName
                dispose(); // Đóng cửa sổ hiện tại
            }
        });
    }
}
