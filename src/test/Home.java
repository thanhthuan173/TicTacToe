package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {
    private JButton btnTwoPlayer;
    private JButton btnOnePlayer;
    private JButton btnConstruction;
    private JButton btnSettings;
    private JButton btnExit;

    public Home() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Home");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        btnTwoPlayer = new JButton("Two Player");
        btnOnePlayer = new JButton("One Player");
        btnConstruction = new JButton("Construction");
        btnSettings = new JButton("Settings");
        btnExit = new JButton("Exit");

        panel.add(btnTwoPlayer);
        panel.add(btnOnePlayer);
        panel.add(btnConstruction);
        panel.add(btnSettings);
        panel.add(btnExit);

        add(panel);

        btnTwoPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlayerName().setVisible(true); // Chuyển sang PlayerName
                dispose(); // Đóng cửa sổ hiện tại
            }
        });

        btnOnePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlayerNameAI().setVisible(true); // Chuyển sang ChooseLevel
                dispose(); // Đóng cửa sổ hiện tại
            }
        });

        btnConstruction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Construction().setVisible(true); // Chuyển sang Construction
                dispose(); // Đóng cửa sổ hiện tại
            }
        });

        btnSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Settings().setVisible(true); // Chuyển sang Settings
                dispose(); // Đóng cửa sổ hiện tại
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Thoát chương trình
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Home().setVisible(true);
        });
    }
}

