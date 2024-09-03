package Service;

import DAO.MusteriDAO;
import Model.Musteri;
import java.sql.SQLException;
import java.util.List;

public class MusteriService {
    private MusteriDAO musteriDAO;

    public MusteriService() {
        this.musteriDAO = new MusteriDAO();
    }

    public List<Musteri> getAllMusteriler() {
        try {
            return musteriDAO.getAllMusteriler();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Musteri getMusteriById(int id) {
        try {
            return musteriDAO.getMusteriById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createMusteri(Musteri musteri) {
        try {
            return musteriDAO.createMusteri(musteri);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateMusteri(Musteri musteri) {
        try {
            return musteriDAO.updateMusteri(musteri);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMusteri(int id) {
        try {
            return musteriDAO.deleteMusteri(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
