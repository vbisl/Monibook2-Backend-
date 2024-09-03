package Model;

import java.util.Date;

public class Kitap {
    private int kitapID;
    private String baslik;
    private int yazarID;
    private int kategoriID;
    private int yayineviID;
    private String isbn;
    private double fiyat;
    private Date yayinTarihi;
    private String aciklama;
    private int sayfaSayisi;
    private int stok;

    // Getters and Setters
    public int getKitapID() {
        return kitapID;
    }

    public void setKitapID(int kitapID) {
        this.kitapID = kitapID;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public int getYazarID() {
        return yazarID;
    }

    public void setYazarID(int yazarID) {
        this.yazarID = yazarID;
    }

    public int getKategoriID() {
        return kategoriID;
    }

    public void setKategoriID(int kategoriID) {
        this.kategoriID = kategoriID;
    }

    public int getYayineviID() {
        return yayineviID;
    }

    public void setYayineviID(int yayineviID) {
        this.yayineviID = yayineviID;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getFiyat() {
        return fiyat;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    public Date getYayinTarihi() {
        return yayinTarihi;
    }

    public void setYayinTarihi(Date yayinTarihi) {
        this.yayinTarihi = yayinTarihi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public int getSayfaSayisi() {
        return sayfaSayisi;
    }

    public void setSayfaSayisi(int sayfaSayisi) {
        this.sayfaSayisi = sayfaSayisi;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}
