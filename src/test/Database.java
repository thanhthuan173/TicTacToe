package test;
import java.sql.*;

public class Database {
    private Connection conn;

    public Database() {
        try {
            // Đăng ký driver SQLite
            Class.forName("org.sqlite.JDBC");
            // Kết nối đến cơ sở dữ liệu
            conn = DriverManager.getConnection("jdbc:sqlite:D:\\HUFLIT\\Nam2_Ky3\\LT\\Java\\test\\src\\tictactoe.db");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load SQLite JDBC driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean playerExists(String playerName) {
        try {
            String sql = "SELECT * FROM players WHERE playerName = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, playerName);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addPlayer(String playerName) {
        try {
            String sql = "INSERT INTO players (playerName, playerScore, EzAI, HardAI) VALUES (?, 0, 0, 0)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, playerName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getPlayerScore(String playerName) {
        try {
            String sql = "SELECT playerScore FROM players WHERE playerName = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, playerName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("playerScore");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updatePlayerScore(String playerName, int score) {
        try {
            String sql = "UPDATE players SET playerScore = ? WHERE playerName = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, score);
            pstmt.setString(2, playerName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
