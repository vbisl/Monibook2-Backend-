package Model;

public class Musteri {
    private int musteriID;
    private String ad;
    private String soyad;
    private String eposta;
    private String sifre;
    private String adres;
    private String telefonNumarasi;

    // Getters and Setters
    public int getMusteriID() {
        return musteriID;
    }

    public void setMusteriID(int musteriID) {
        this.musteriID = musteriID;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTelefonNumarasi() {
        return telefonNumarasi;
    }

    public void setTelefonNumarasi(String telefonNumarasi) {
        this.telefonNumarasi = telefonNumarasi;
    }

    @Override
    public String toString() {
        return "Musteri{" +
                "musteriID=" + musteriID +
                ", ad='" + ad + '\'' +
                ", soyad='" + soyad + '\'' +
                ", eposta='" + eposta + '\'' +
                ", sifre='" + sifre + '\'' +
                ", adres='" + adres + '\'' +
                ", telefonNumarasi='" + telefonNumarasi + '\'' +
                '}';
    }
}
