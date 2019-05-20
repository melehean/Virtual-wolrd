package Ogrod;

import Glowne_klasy.Organizmy;
import Glowne_klasy.Rosliny;
import Glowne_klasy.Swiat;

import java.awt.*;

public class Trawa extends Rosliny
{
    public Trawa(Swiat swiat_ref, int x, int y)
    {
        super(swiat_ref,x,y);
        this.sila = 0;
        this.nazwa = "Trawa";
        this.prawdopodobienstwo = 7;
        this.kolor = Color.decode("#009432");
    }
    @Override
    protected boolean obrona(Organizmy agresor)
    {
        return false;
    }
}
