package Model;

public class YayinEvi {
    private int yayinEviID;
    private String yayinEviAdi;
    private String adres;
    private String telefonNumarasi;
    private String eposta;

    // Getters and Setters
    public int getYayinEviID() {
        return yayinEviID;
    }

    public void setYayinEviID(int yayinEviID) {
        this.yayinEviID = yayinEviID;
    }

    public String getYayinEviAdi() {
        return yayinEviAdi;
    }

    public void setYayinEviAdi(String yayinEviAdi) {
        this.yayinEviAdi = yayinEviAdi;
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

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }
}
