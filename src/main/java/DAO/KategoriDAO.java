package DAO;

import Model.Kategori;
import Server.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KategoriDAO {

    // Tüm kategorileri getirir
    public List<Kategori> getAllKategoriler() throws SQLException {
        List<Kategori> kategoriler = new ArrayList<>();
        String sql = "SELECT * FROM kategoriler";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Kategori kategori = new Kategori();
                kategori.setKategoriID(rs.getInt("KategoriID"));
                kategori.setKategoriAdi(rs.getString("KategoriAdi"));
                kategori.setAciklama(rs.getString("Aciklama"));
                kategoriler.add(kategori);
            }
        }
        return kategoriler;
    }

    // Belirli bir kategori ID'sine sahip kategoriyi getirir
    public Kategori getKategoriById(int id) throws SQLException {
        Kategori kategori = null;
        String sql = "SELECT * FROM kategoriler WHERE KategoriID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    kategori = new Kategori();
                    kategori.setKategoriID(rs.getInt("KategoriID"));
                    kategori.setKategoriAdi(rs.getString("KategoriAdi"));
                    kategori.setAciklama(rs.getString("Aciklama"));
                }
            }
        }
        return kategori;
    }

    // Yeni bir kategori ekler
    public boolean createKategori(Kategori kategori) throws SQLException {
        String sql = "INSERT INTO kategoriler (KategoriAdi, Aciklama,kategoriID) VALUES (?, ?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, kategori.getKategoriAdi());
            pstmt.setString(2, kategori.getAciklama());
            pstmt.setInt(3, kategori.getKategoriID());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    // Mevcut bir kategoriyi günceller
    public boolean updateKategori(Kategori kategori) throws SQLException {
        String sql = "UPDATE kategoriler SET KategoriAdi = ?, Aciklama = ? WHERE KategoriID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, kategori.getKategoriAdi());
            pstmt.setString(2, kategori.getAciklama());
            pstmt.setInt(3, kategori.getKategoriID());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    // Belirli bir kategori ID'sine sahip kategoriyi siler
    public boolean deleteKategori(int id) throws SQLException {
        String sql = "DELETE FROM kategoriler WHERE KategoriID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
