package DAO;

import Model.Kitap;
import Server.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KitapDAO {

    public List<Kitap> getAllKitaplar() throws SQLException {
        List<Kitap> kitaplar = new ArrayList<>();
        String sql = "SELECT * FROM kitaplar";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Kitap kitap = new Kitap();
                kitap.setKitapID(rs.getInt("KitapID"));
                kitap.setBaslik(rs.getString("Baslik"));
                kitap.setYazarID(rs.getInt("YazarID"));
                kitap.setKategoriID(rs.getInt("KategoriID"));
                kitap.setYayineviID(rs.getInt("YayineviID"));
                kitap.setIsbn(rs.getString("ISBN"));
                kitap.setFiyat(rs.getDouble("Fiyat"));
                kitap.setYayinTarihi(rs.getDate("YayinTarihi"));
                kitap.setAciklama(rs.getString("Aciklama"));
                kitap.setSayfaSayisi(rs.getInt("SayfaSayisi"));
                kitap.setStok(rs.getInt("Stok"));
                kitaplar.add(kitap);
            }
        }
        return kitaplar;
    }

    public Kitap getKitapById(int id) throws SQLException {
        Kitap kitap = null;
        String sql = "SELECT * FROM kitaplar WHERE KitapID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    kitap = new Kitap();
                    kitap.setKitapID(rs.getInt("KitapID"));
                    kitap.setBaslik(rs.getString("Baslik"));
                    kitap.setYazarID(rs.getInt("YazarID"));
                    kitap.setKategoriID(rs.getInt("KategoriID"));
                    kitap.setYayineviID(rs.getInt("YayineviID"));
                    kitap.setIsbn(rs.getString("ISBN"));
                    kitap.setFiyat(rs.getDouble("Fiyat"));
                    kitap.setYayinTarihi(rs.getDate("YayinTarihi"));
                    kitap.setAciklama(rs.getString("Aciklama"));
                    kitap.setSayfaSayisi(rs.getInt("SayfaSayisi"));
                    kitap.setStok(rs.getInt("Stok"));
                }
            }
        }
        return kitap;
    }

    public boolean createKitap(Kitap kitap) throws SQLException {
        String sql = "INSERT INTO kitaplar (Baslik, YazarID, KategoriID, YayineviID, ISBN, Fiyat, YayinTarihi, Aciklama, SayfaSayisi, Stok,KitapID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, kitap.getBaslik());
            pstmt.setInt(2, kitap.getYazarID());
            pstmt.setInt(3, kitap.getKategoriID());
            pstmt.setInt(4, kitap.getYayineviID());
            pstmt.setString(5, kitap.getIsbn());
            pstmt.setDouble(6, kitap.getFiyat());
            pstmt.setDate(7, new java.sql.Date(kitap.getYayinTarihi().getTime()));
            pstmt.setString(8, kitap.getAciklama());
            pstmt.setInt(9, kitap.getSayfaSayisi());
            pstmt.setInt(10, kitap.getStok());
            pstmt.setInt(11, kitap.getKitapID());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean updateKitap(Kitap kitap) throws SQLException {
        String sql = "UPDATE kitaplar SET Baslik = ?, YazarID = ?, KategoriID = ?, YayineviID = ?, ISBN = ?, Fiyat = ?, YayinTarihi = ?, Aciklama = ?, SayfaSayisi = ?, Stok = ? WHERE KitapID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, kitap.getBaslik());
            pstmt.setInt(2, kitap.getYazarID());
            pstmt.setInt(3, kitap.getKategoriID());
            pstmt.setInt(4, kitap.getYayineviID());
            pstmt.setString(5, kitap.getIsbn());
            pstmt.setDouble(6, kitap.getFiyat());
            pstmt.setDate(7, new java.sql.Date(kitap.getYayinTarihi().getTime()));
            pstmt.setString(8, kitap.getAciklama());
            pstmt.setInt(9, kitap.getSayfaSayisi());
            pstmt.setInt(10, kitap.getStok());
            pstmt.setInt(11, kitap.getKitapID());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean deleteKitap(int id) throws SQLException {
        String sql = "DELETE FROM kitaplar WHERE KitapID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    // Diğer CRUD operasyonları (create, update, delete) da benzer şekilde eklenebilir
}
