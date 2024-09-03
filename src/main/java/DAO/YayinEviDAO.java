package DAO;

import Model.YayinEvi;
import Server.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class YayinEviDAO {

    public List<YayinEvi> getAllYayinEvleri() throws SQLException {
        List<YayinEvi> yayinEvleri = new ArrayList<>();
        String sql = "SELECT * FROM yayinevleri";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                YayinEvi yayinEvi = new YayinEvi();
                yayinEvi.setYayinEviID(rs.getInt("yayinEviID"));
                yayinEvi.setYayinEviAdi(rs.getString("yayinEviAdi"));
                yayinEvi.setAdres(rs.getString("adres"));
                yayinEvi.setTelefonNumarasi(rs.getString("telefonNumarasi"));
                yayinEvi.setEposta(rs.getString("eposta"));
                yayinEvleri.add(yayinEvi);
            }
        }
        return yayinEvleri;
    }

    public YayinEvi getYayinEviById(int id) throws SQLException {
        YayinEvi yayinEvi = null;
        String sql = "SELECT * FROM yayinevleri WHERE yayinEviID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    yayinEvi = new YayinEvi();
                    yayinEvi.setYayinEviID(rs.getInt("yayinEviID"));
                    yayinEvi.setYayinEviAdi(rs.getString("yayinEviAdi"));
                    yayinEvi.setAdres(rs.getString("adres"));
                    yayinEvi.setTelefonNumarasi(rs.getString("telefonNumarasi"));
                    yayinEvi.setEposta(rs.getString("eposta"));
                }
            }
        }
        return yayinEvi;
    }

    public boolean createYayinEvi(YayinEvi yayinEvi) throws SQLException {
        String sql = "INSERT INTO yayinevleri (yayinEviID, yayinEviAdi, adres, telefonNumarasi, eposta) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, yayinEvi.getYayinEviID());
            pstmt.setString(2, yayinEvi.getYayinEviAdi());
            pstmt.setString(3, yayinEvi.getAdres());
            pstmt.setString(4, yayinEvi.getTelefonNumarasi());
            pstmt.setString(5, yayinEvi.getEposta());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean updateYayinEvi(YayinEvi yayinEvi) throws SQLException {
        String sql = "UPDATE yayinevleri SET yayinEviAdi = ?, adres = ?, telefonNumarasi = ?, eposta = ? WHERE yayinEviID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, yayinEvi.getYayinEviAdi());
            pstmt.setString(2, yayinEvi.getAdres());
            pstmt.setString(3, yayinEvi.getTelefonNumarasi());
            pstmt.setString(4, yayinEvi.getEposta());
            pstmt.setInt(5, yayinEvi.getYayinEviID());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean deleteYayinEvi(int id) throws SQLException {
        String sql = "DELETE FROM yayinevleri WHERE yayinEviID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
