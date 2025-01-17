package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class PlayWithEz4 extends JFrame {
    private Database db;
    private JTextField playerScoreField;
    private JLabel turnLabel;
    private JButton[][] buttons;
    private String player;
    private int playerScore;
    private boolean playerTurn;
    private Random random;

    public PlayWithEz4(String player, Database db) {
        this.player = player;
        this.db = db;
        random = new Random();
        initComponents();
        startGame();
    }

    private void initComponents() {
        setTitle("Tic Tac Toe - Easy AI");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2, 2));
        playerScoreField = new JTextField("0");
        playerScoreField.setEditable(false);

        turnLabel = new JLabel();
        topPanel.add(new JLabel(player + ":"));
        topPanel.add(playerScoreField);
        topPanel.add(turnLabel);
        add(topPanel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(4, 4));
        buttons = new JButton[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                boardPanel.add(buttons[i][j]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton suggestButton = new JButton("Suggest");
        JButton restartButton = new JButton("Restart");
        JButton resetButton = new JButton("Reset");
        JButton saveButton = new JButton("Save");
        JButton homeButton = new JButton("Home");
        JButton exitButton = new JButton("Exit");

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

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home().setVisible(true);
                dispose();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        suggestButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean suggested = false;
            for (int i = 0; i < 4; i++) {
              for (int j = 0; j < 4; j++) {
                if (buttons[i][j].getText().equals("")) {
                  buttons[i][j].setText("X");
                  if (checkWin()) {
                    buttons[i][j].setText("");
                    buttons[i][j].setBackground(Color.yellow);
                    suggested = true;
                    break;
                  } else {
                    buttons[i][j].setText("");
                  }
                }
              }
              if (suggested) {
                break;
              }
            }
            if (!suggested) {
              int x, y;
              do {
                x = random.nextInt(4);
                y = random.nextInt(4);
              } while (!buttons[x][y].getText().equals(""));
              buttons[x][y].setBackground(Color.yellow);
            }
          }
        });
        bottomPanel.add(suggestButton);
        
        bottomPanel.add(restartButton);
        bottomPanel.add(resetButton);
        bottomPanel.add(saveButton);
        bottomPanel.add(homeButton);
        bottomPanel.add(exitButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void startGame() {
        if (!db.playerExists(player)) {
            db.addPlayer(player);
        }

        playerScore = db.getPlayerEzAIScore(player);
        playerScoreField.setText(String.valueOf(playerScore));
        playerTurn = true;
        updateTurnLabel();
        resetBoard();
    }

    private void updateTurnLabel() {
        if (playerTurn) {
            turnLabel.setText("Turn: " + player);
        } else {
            turnLabel.setText("Turn: AI");
        }
    }

    private void resetBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
                buttons[i][j].setBackground(null);
            }
        }
    }

    private void restartGame() {
        resetBoard();
    }

    private void resetGame() {
        resetBoard();
        playerScore = 0;
        playerScoreField.setText("0");
    }

    private void saveScores() {
        db.updatePlayerEzAIScore(player, playerScore);
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
                buttons[x][y].setText("X");
                buttons[x][y].setEnabled(false);
                if (checkWin()) {
                    playerScore++;
                    playerScoreField.setText(String.valueOf(playerScore));
                    JOptionPane.showMessageDialog(PlayWithEz4.this, player + " wins!");
                    resetBoard();
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(PlayWithEz4.this, "It's a draw!");
                    resetBoard();
                } else {
                    playerTurn = !playerTurn;
                    updateTurnLabel();
                    if (!playerTurn) {
                        aiMove();
                    }
                }
            }
        }
    }

    private void aiMove() {
        int x, y;
        do {
            x = random.nextInt(4);
            y = random.nextInt(4);
        } while (!buttons[x][y].getText().equals(""));

        buttons[x][y].setText("O");
        buttons[x][y].setEnabled(false);

        if (checkWin()) {
            JOptionPane.showMessageDialog(PlayWithEz4.this, "AI wins!");
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(PlayWithEz4.this, "It's a draw!");
            resetBoard();
        } else {
            playerTurn = !playerTurn;
            updateTurnLabel();
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 4; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) && 
                buttons[i][1].getText().equals(buttons[i][2].getText()) &&
                buttons[i][1].getText().equals(buttons[i][3].getText()) && 
                !buttons[i][0].getText().equals("")) {
                return true;
            }
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) && 
                buttons[1][i].getText().equals(buttons[2][i].getText()) &&
                buttons[1][i].getText().equals(buttons[3][i].getText()) && 
                !buttons[0][i].getText().equals("")) {
                return true;
            }
        }

        if (buttons[0][0].getText().equals(buttons[1][1].getText()) && 
            buttons[1][1].getText().equals(buttons[2][2].getText()) &&
            buttons[2][2].getText().equals(buttons[3][3].getText()) &&
            !buttons[0][0].getText().equals("")) {
            return true;
        }

        if (buttons[0][3].getText().equals(buttons[1][2].getText()) && 
            buttons[1][2].getText().equals(buttons[2][1].getText()) &&
            buttons[2][1].getText().equals(buttons[3][0].getText()) && 
            !buttons[0][3].getText().equals("")) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
}
