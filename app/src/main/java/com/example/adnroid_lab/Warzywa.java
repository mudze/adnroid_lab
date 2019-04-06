package com.example.adnroid_lab;

public class Warzywa {
    private int id;
    private String rodzaj;
    private String kolor;
    private Float wielkosc;
    private String opis;

    public Warzywa() {};

    public Warzywa(String rodzaj, String kolor, Float wielkosc, String opis) {
        super();
        this.rodzaj = rodzaj;
        this.kolor = kolor;
        this.wielkosc = wielkosc;
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "Warzymo: [id=" + id + ", rodzzaj=" + rodzaj + ", kolor=" + kolor + ", wielkosc=" + wielkosc + " ]";
    }

    public String getOpis() { return opis; }
    public  String getRodzaj () { return rodzaj; }
    public  String getKolor () { return kolor; }
    public float getWirlkosc () { return wielkosc; }
    public  int getId () { return id; }
    public void  setId (int id) { this.id = id; }
}
