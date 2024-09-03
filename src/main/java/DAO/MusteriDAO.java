package DAO;

import Model.Musteri;
import Server.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusteriDAO {

    public List<Musteri> getAllMusteriler() throws SQLException {
        List<Musteri> musteriler = new ArrayList<>();
        String sql = "SELECT * FROM musteriler";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Musteri musteri = new Musteri();
                musteri.setMusteriID(rs.getInt("musteriID"));
                musteri.setAd(rs.getString("ad"));
                musteri.setSoyad(rs.getString("soyad"));
                musteri.setEposta(rs.getString("eposta"));
                musteri.setSifre(rs.getString("sifre"));
                musteri.setAdres(rs.getString("adres"));
                musteri.setTelefonNumarasi(rs.getString("telefonNumarasi"));
                musteriler.add(musteri);
            }
        }
        return musteriler;
    }

    public Musteri getMusteriById(int id) throws SQLException {
        Musteri musteri = null;
        String sql = "SELECT * FROM musteriler WHERE musteriID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    musteri = new Musteri();
                    musteri.setMusteriID(rs.getInt("musteriID"));
                    musteri.setAd(rs.getString("ad"));
                    musteri.setSoyad(rs.getString("soyad"));
                    musteri.setEposta(rs.getString("eposta"));
                    musteri.setSifre(rs.getString("sifre"));
                    musteri.setAdres(rs.getString("adres"));
                    musteri.setTelefonNumarasi(rs.getString("telefonNumarasi"));
                }
            }
        }
        return musteri;
    }

    public boolean createMusteri(Musteri musteri) throws SQLException {
        String sql = "INSERT INTO musteriler (ad, soyad, eposta, sifre, adres, telefonNumarasi,musteriID) VALUES (?, ?, ?, ?, ?, ?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, musteri.getAd());
            pstmt.setString(2, musteri.getSoyad());
            pstmt.setString(3, musteri.getEposta());
            pstmt.setString(4, musteri.getSifre());
            pstmt.setString(5, musteri.getAdres());
            pstmt.setString(6, musteri.getTelefonNumarasi());
            pstmt.setInt(7, musteri.getMusteriID());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean updateMusteri(Musteri musteri) throws SQLException {
        String sql = "UPDATE musteriler SET ad = ?, soyad = ?, eposta = ?, sifre = ?, adres = ?, telefonNumarasi = ? WHERE musteriID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, musteri.getAd());
            pstmt.setString(2, musteri.getSoyad());
            pstmt.setString(3, musteri.getEposta());
            pstmt.setString(4, musteri.getSifre());
            pstmt.setString(5, musteri.getAdres());
            pstmt.setString(6, musteri.getTelefonNumarasi());
            pstmt.setInt(7, musteri.getMusteriID());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean deleteMusteri(int id) throws SQLException {
        String sql = "DELETE FROM musteriler WHERE musteriID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
