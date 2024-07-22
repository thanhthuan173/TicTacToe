package test;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Construction extends JFrame {

    public Construction() {
        setTitle("Hướng Dẫn Trò Chơi");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addComponents();
    }
    
    private void addComponents() {
        String huongDan = "Để bắt đầu trò chơi, sau khi chọn chế độ bạn cần nhập tên của mình để có thể lưu thành tích vào lần chơi sau.\n" +
                          "Có 3 kích thước chơi gồm: 3x3, 4x4, 5x5\n" +
                          "Đối với 3x3 thì điều kiện thắng là có 3 dấu liên tiếp hàng ngang hoặc hàng dọc hoặc hàng chéo, " +
                          "4x4 thì điều kiện thắng là 4 dấu và 5x5 là 5 dấu liền tiếp.";
        
        JTextArea textArea = new JTextArea(huongDan);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        
        JButton backButton = new JButton("Trở về");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home().setVisible(true);
                setVisible(false);
            }
        });
        add(backButton, BorderLayout.SOUTH);
    }
}
