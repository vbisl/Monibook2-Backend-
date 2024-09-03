package Service;

import DAO.KitapDAO;
import Model.Kitap;

import java.sql.SQLException;
import java.util.List;

public class KitapService {
    private KitapDAO kitapDAO;

    public KitapService() {
        this.kitapDAO = new KitapDAO();
    }

    public List<Kitap> getAllKitaplar() {
        try {
            return kitapDAO.getAllKitaplar();
        } catch (SQLException e) {
            e.printStackTrace();
            // Hata yönetimi veya özel istisna işleme yapılabilir
        }
        return null;
    }

    public Kitap getKitapById(int id) {
        try {
            return kitapDAO.getKitapById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            // Hata yönetimi veya özel istisna işleme yapılabilir
        }
        return null;
    }
    public boolean createKitap(Kitap kitap) {
        try {
            return kitapDAO.createKitap(kitap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateKitap(Kitap kitap) {
        try {
            return kitapDAO.updateKitap(kitap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteKitap(int id) {
        try {
            return kitapDAO.deleteKitap(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Diğer CRUD işlemleri için metodlar eklenebilir (create, update, delete)




}
