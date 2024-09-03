package Service;

import DAO.YazarDAO;
import Model.Yazar;

import java.sql.SQLException;
import java.util.List;

public class YazarService {
    private YazarDAO yazarDAO;

    public YazarService() {
        this.yazarDAO = new YazarDAO();
    }

    public List<Yazar> getAllYazarlar() {
        try {
            return yazarDAO.getAllYazarlar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Yazar getYazarById(int id) {
        try {
            return yazarDAO.getYazarById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createYazar(Yazar yazar) {
        try {
            return yazarDAO.createYazar(yazar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateYazar(Yazar yazar) {
        try {
            return yazarDAO.updateYazar(yazar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteYazar(int id) {
        try {
            return yazarDAO.deleteYazar(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
