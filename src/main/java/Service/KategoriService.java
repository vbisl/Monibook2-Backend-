package Service;

import DAO.KategoriDAO;
import Model.Kategori;
import java.sql.SQLException;
import java.util.List;

public class KategoriService {
    private KategoriDAO kategoriDAO;

    public KategoriService() {
        this.kategoriDAO = new KategoriDAO();
    }

    // Tüm kategorileri getirir
    public List<Kategori> getAllKategoriler() {
        try {
            return kategoriDAO.getAllKategoriler();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Belirli bir kategori ID'sine sahip kategoriyi getirir
    public Kategori getKategoriById(int id) {
        try {
            return kategoriDAO.getKategoriById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Yeni bir kategori ekler
    public boolean createKategori(Kategori kategori) {
        try {
            return kategoriDAO.createKategori(kategori);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Mevcut bir kategoriyi günceller
    public boolean updateKategori(Kategori kategori) {
        try {
            return kategoriDAO.updateKategori(kategori);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Belirli bir kategori ID'sine sahip kategoriyi siler
    public boolean deleteKategori(int id) {
        try {
            return kategoriDAO.deleteKategori(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
