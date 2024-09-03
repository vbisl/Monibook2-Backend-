package Service;

import DAO.YayinEviDAO;
import Model.YayinEvi;

import java.sql.SQLException;
import java.util.List;

public class YayinEviService {
    private YayinEviDAO yayinEviDAO;

    public YayinEviService() {
        this.yayinEviDAO = new YayinEviDAO();
    }

    public List<YayinEvi> getAllYayinEvleri() {
        try {
            return yayinEviDAO.getAllYayinEvleri();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public YayinEvi getYayinEviById(int id) {
        try {
            return yayinEviDAO.getYayinEviById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createYayinEvi(YayinEvi yayinEvi) {
        try {
            return yayinEviDAO.createYayinEvi(yayinEvi);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateYayinEvi(YayinEvi yayinEvi) {
        try {
            return yayinEviDAO.updateYayinEvi(yayinEvi);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteYayinEvi(int id) {
        try {
            return yayinEviDAO.deleteYayinEvi(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
