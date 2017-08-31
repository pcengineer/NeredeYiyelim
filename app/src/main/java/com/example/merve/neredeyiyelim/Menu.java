package com.example.merve.neredeyiyelim;

/**
 * Created by Merve on 25.08.2017.
 */

public class Menu {

    private String menuAdi;
    private int fiyat;

    public Menu(){}

    public Menu(String menuAdi, int fiyat) {
        this.menuAdi = menuAdi;
        this.fiyat = fiyat;
    }

    public String getMenuAdi() {
        return menuAdi;
    }

    public void setMenuAdi(String menuAdi) {
        this.menuAdi = menuAdi;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }
}
