package test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {
    private Database db;
    private JTextField player1Field;
    private JTextField player2Field;
    private JTextField player1ScoreField;
    private JTextField player2ScoreField;
    private JButton[][] buttons;
    private String player1;
    private String player2;
    private int player1Score;
    private int player2Score;
    private boolean player1Turn;

    public TicTacToe() {
        db = new Database();
        initComponents();
    }

    private void initComponents() {
        // Khởi tạo các thành phần giao diện
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(3, 2));
        player1Field = new JTextField();
        player2Field = new JTextField();
        player1ScoreField = new JTextField("0");
        player1ScoreField.setEditable(false);
        player2ScoreField = new JTextField("0");
        player2ScoreField.setEditable(false);

        topPanel.add(new JLabel("Player 1:"));
        topPanel.add(player1Field);
        topPanel.add(new JLabel("Player 2:"));
        topPanel.add(player2Field);
        topPanel.add(player1ScoreField);
        topPanel.add(player2ScoreField);

        add(topPanel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                boardPanel.add(buttons[i][j]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton startButton = new JButton("Start");
        JButton restartButton = new JButton("Restart");
        JButton resetButton = new JButton("Reset");
        JButton saveButton = new JButton("Save");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveScores();
            }
        });

        bottomPanel.add(startButton);
        bottomPanel.add(restartButton);
        bottomPanel.add(resetButton);
        bottomPanel.add(saveButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void startGame() {
        player1 = player1Field.getText();
        player2 = player2Field.getText();

        if (!db.playerExists(player1)) {
            db.addPlayer(player1);
        }
        if (!db.playerExists(player2)) {
            db.addPlayer(player2);
        }

        player1Score = db.getPlayerScore(player1);
        player2Score = db.getPlayerScore(player2);

        player1ScoreField.setText(String.valueOf(player1Score));
        player2ScoreField.setText(String.valueOf(player2Score));

        player1Turn = true;
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
    }

    private void restartGame() {
        resetBoard();
    }

    private void resetGame() {
        resetBoard();
        player1Score = 0;
        player2Score = 0;
        player1ScoreField.setText("0");
        player2ScoreField.setText("0");
    }

    private void saveScores() {
        db.updatePlayerScore(player1, player1Score);
        db.updatePlayerScore(player2, player2Score);
        JOptionPane.showMessageDialog(this, "Scores saved!");
    }

    private class ButtonClickListener implements ActionListener {
        private int x;
        private int y;

        public ButtonClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[x][y].getText().equals("")) {
                buttons[x][y].setText(player1Turn ? "X" : "O");
                buttons[x][y].setEnabled(false);
                if (checkWin()) {
                    if (player1Turn) {
                        player1Score++;
                        player1ScoreField.setText(String.valueOf(player1Score));
                    } else {
                        player2Score++;
                        player2ScoreField.setText(String.valueOf(player2Score));
                    }
                    JOptionPane.showMessageDialog(TicTacToe.this,
                            (player1Turn ? player1 : player2) + " wins!");
                    resetBoard();
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(TicTacToe.this, "It's a draw!");
                    resetBoard();
                } else {
                    player1Turn = !player1Turn;
                }
            }
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                buttons[i][0].getText().equals(buttons[i][2].getText()) &&
                !buttons[i][0].getText().equals("")) {
                return true;
            }
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                buttons[0][i].getText().equals(buttons[2][i].getText()) &&
                !buttons[0][i].getText().equals("")) {
                return true;
            }
        }
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
            buttons[0][0].getText().equals(buttons[2][2].getText()) &&
            !buttons[0][0].getText().equals("")) {
            return true;
        }
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
            buttons[0][2].getText().equals(buttons[2][0].getText()) &&
            !buttons[0][2].getText().equals("")) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new TicTacToe().setVisible(true);
//            }
//        });
//    }
}

