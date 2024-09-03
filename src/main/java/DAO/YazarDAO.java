package DAO;

import Model.Yazar;
import Server.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class YazarDAO {

    public List<Yazar> getAllYazarlar() throws SQLException {
        List<Yazar> yazarlar = new ArrayList<>();
        String sql = "SELECT * FROM yazarlar";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Yazar yazar = new Yazar();
                yazar.setYazarID(rs.getInt("YazarID"));
                yazar.setAd(rs.getString("Ad"));
                yazar.setSoyad(rs.getString("Soyad"));
                yazar.setBiyografi(rs.getString("Biyografi"));
                yazarlar.add(yazar);
            }
        }
        return yazarlar;
    }

    public Yazar getYazarById(int id) throws SQLException {
        Yazar yazar = null;
        String sql = "SELECT * FROM yazarlar WHERE YazarID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    yazar = new Yazar();
                    yazar.setYazarID(rs.getInt("YazarID"));
                    yazar.setAd(rs.getString("Ad"));
                    yazar.setSoyad(rs.getString("Soyad"));
                    yazar.setBiyografi(rs.getString("Biyografi"));
                }
            }
        }
        return yazar;
    }

    public boolean createYazar(Yazar yazar) throws SQLException {
        String sql = "INSERT INTO yazarlar (Ad, Soyad, Biyografi,YazarID) VALUES (?, ?, ?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, yazar.getAd());
            pstmt.setString(2, yazar.getSoyad());
            pstmt.setString(3, yazar.getBiyografi());
            pstmt.setInt(4, yazar.getYazarID());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean updateYazar(Yazar yazar) throws SQLException {
        String sql = "UPDATE yazarlar SET Ad = ?, Soyad = ?, Biyografi = ? WHERE YazarID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, yazar.getAd());
            pstmt.setString(2, yazar.getSoyad());
            pstmt.setString(3, yazar.getBiyografi());
            pstmt.setInt(4, yazar.getYazarID());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean deleteYazar(int id) throws SQLException {
        String sql = "DELETE FROM yazarlar WHERE YazarID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
