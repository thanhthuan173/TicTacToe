package test;

import java.sql.*;
import org.sqlite.SQLiteErrorCode;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:D:\\HUFLIT\\Nam2_Ky3\\LT\\Java\\test\\src\\tictactoe.db";
    private static final int MAX_RETRIES = 5;

    public Database() {
        try {
            // Đăng ký driver SQLite
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load SQLite JDBC driver.");
            e.printStackTrace();
        }
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public boolean playerExists(String playerName) {
        String sql = "SELECT * FROM players WHERE playerName = ?";
        for (int i = 0; i < MAX_RETRIES; i++) {
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, playerName);
                ResultSet rs = pstmt.executeQuery();
                return rs.next();
            } catch (SQLException e) {
                if (e.getErrorCode() == SQLiteErrorCode.SQLITE_BUSY.code) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public void addPlayer(String playerName) {
        String sql = "INSERT INTO players (playerName, playerScore, EzAI, HardAI) VALUES (?, 0, 0, 0)";
        for (int i = 0; i < MAX_RETRIES; i++) {
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, playerName);
                pstmt.executeUpdate();
                return;
            } catch (SQLException e) {
                if (e.getErrorCode() == SQLiteErrorCode.SQLITE_BUSY.code) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getPlayerScore(String playerName) {
        String sql = "SELECT playerScore FROM players WHERE playerName = ?";
        for (int i = 0; i < MAX_RETRIES; i++) {
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, playerName);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt("playerScore");
                }
            } catch (SQLException e) {
                if (e.getErrorCode() == SQLiteErrorCode.SQLITE_BUSY.code) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    public void updatePlayerScore(String playerName, int score) {
        String sql = "UPDATE players SET playerScore = ? WHERE playerName = ?";
        for (int i = 0; i < MAX_RETRIES; i++) {
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, score);
                pstmt.setString(2, playerName);
                pstmt.executeUpdate();
                return;
            } catch (SQLException e) {
                if (e.getErrorCode() == SQLiteErrorCode.SQLITE_BUSY.code) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getPlayerEzAIScore(String playerName) {
        String sql = "SELECT EzAI FROM players WHERE playerName = ?";
        for (int i = 0; i < MAX_RETRIES; i++) {
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, playerName);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt("EzAI");
                }
            } catch (SQLException e) {
                if (e.getErrorCode() == SQLiteErrorCode.SQLITE_BUSY.code) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    public void updatePlayerEzAIScore(String playerName, int score) {
        String sql = "UPDATE players SET EzAI = ? WHERE playerName = ?";
        for (int i = 0; i < MAX_RETRIES; i++) {
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, score);
                pstmt.setString(2, playerName);
                pstmt.executeUpdate();
                return;
            } catch (SQLException e) {
                if (e.getErrorCode() == SQLiteErrorCode.SQLITE_BUSY.code) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getPlayerHardAIScore(String playerName) {
        String sql = "SELECT HardAI FROM players WHERE playerName = ?";
        for (int i = 0; i < MAX_RETRIES; i++) {
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, playerName);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt("HardAI");
                }
            } catch (SQLException e) {
                if (e.getErrorCode() == SQLiteErrorCode.SQLITE_BUSY.code) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    public void updatePlayerHardAIScore(String playerName, int score) {
        String sql = "UPDATE players SET HardAI = ? WHERE playerName = ?";
        for (int i = 0; i < MAX_RETRIES; i++) {
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, score);
                pstmt.setString(2, playerName);
                pstmt.executeUpdate();
                return;
            } catch (SQLException e) {
                if (e.getErrorCode() == SQLiteErrorCode.SQLITE_BUSY.code) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    e.printStackTrace();
                }
            }
        }
    }
}
