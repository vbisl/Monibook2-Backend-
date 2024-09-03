package Model;

public class Kategori {
    private int kategoriID;        // Kategorinin benzersiz ID'si
    private String kategoriAdı;    // Kategorinin adı
    private String aciklama;       // Kategorinin açıklaması

    // Getters and Setters
    public int getKategoriID() {
        return kategoriID;
    }

    public void setKategoriID(int kategoriID) {
        this.kategoriID = kategoriID;
    }

    public String getKategoriAdi() {
        return kategoriAdı;
    }

    public void setKategoriAdi(String kategoriAdi) {
        this.kategoriAdı = kategoriAdı;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
}
