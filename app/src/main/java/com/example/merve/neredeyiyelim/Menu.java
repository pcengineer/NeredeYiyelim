package com.example.merve.neredeyiyelim;

/**
 * Created by Merve on 25.08.2017.
 */

public class Menu {

    private String menuAdi,fiyat;

    public Menu(){}

    public Menu(String menuAdi, String fiyat) {
        this.menuAdi = menuAdi;
        this.fiyat = fiyat;
    }

    public String getMenuAdi() {
        return menuAdi;
    }

    public void setMenuAdi(String menuAdi) {
        this.menuAdi = menuAdi;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }
}
