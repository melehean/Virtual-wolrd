package Glowne_klasy;

import java.awt.*;

public abstract class Organizmy
{
    protected Pair<Integer,Integer> polozenie;
    protected int wiek,inicjatywa, sila;
    protected Color kolor;
    protected String nazwa;
    protected Swiat swiat_ref;
    public abstract void akcja();
    public abstract void kolizja(Organizmy agresor);
    protected abstract boolean obrona(Organizmy osobnik_obronny);
    Organizmy(Swiat swiat_ref, int x, int y)
    {
        this.swiat_ref=swiat_ref;
        this.polozenie = new Pair(x,y);
        this.wiek = 0;
    }

    public void postarz_organizm()
    {
        this.wiek++;
    }

    public Color get_kolor()
    {
        return this.kolor;
    }

   public int get_wiek()
    {
        return this.wiek;
    }
    public int get_inicjatywa()
    {
        return this.inicjatywa;
    }
    public int get_sila()
    {
        return this.sila;
    }
    Pair<Integer,Integer> get_polozenie()
    {
        return this.polozenie;
    }
    public String get_nazwa()
    {
        return this.nazwa;
    }
    public void set_wiek(int wiek)
    {
        this.wiek = wiek;
    }
    public void set_inicjatywa(int inicjatywa)
    {
        this.inicjatywa = inicjatywa;
    }
    public void set_sila(int sila)
    {
        this.sila = sila;
    }
    public void set_polozenie(Pair<Integer,Integer> polozenie)
    {
        this.polozenie = polozenie;
    }
    public void set_nazwa(String nazwa)
    {
        this.nazwa = nazwa;
    }
}

