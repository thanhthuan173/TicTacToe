package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class ChooseSize extends JFrame{
    private JButton btnSize3;
    private JButton btnSize4;
    private JButton btnSize5;
    private JButton homeButton;
    private String player1;
    private String player2;
    private Database db;
    
    
    public ChooseSize(String player1, String player2, Database db){
        this.player1 = player1;
        this.player2 = player2;
        this.db = db;
        initComponents();
    }
    
    private void initComponents(){
       setTitle("Choose Size");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        btnSize3 = new JButton("Size 3");
        btnSize4 = new JButton("Size 4");
        btnSize5 = new JButton("Size 5");
        homeButton = new JButton("Home");
        
        panel.add(btnSize3);
        panel.add(btnSize4);
        panel.add(btnSize5);
        panel.add(homeButton);
        add(panel);

        btnSize3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 new Play(player1, player2, db).setVisible(true);
                dispose();
            }
        });
        
        btnSize4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 new Play4(player1, player2, db).setVisible(true);
                dispose();
            }
        });
 
        btnSize5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 new Play5(player1, player2, db).setVisible(true);
                dispose();
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
