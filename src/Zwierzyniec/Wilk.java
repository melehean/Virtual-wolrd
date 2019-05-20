package Zwierzyniec;

import Glowne_klasy.Organizmy;
import Glowne_klasy.Swiat;
import Glowne_klasy.Zwierzeta;

import java.awt.*;

public class Wilk extends Zwierzeta
{
    public Wilk(Swiat swiat_ref, int x, int y)
    {
        super(swiat_ref,x,y);
        this.sila = 9;
        this.inicjatywa = 5;
        this.nazwa = "Wilk";
        this.kolor = Color.decode("#2d3436");
    }
    @Override
    protected boolean obrona(Organizmy agresor)
    {
        return false;
    }
}
