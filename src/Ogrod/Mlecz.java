package Ogrod;

import Glowne_klasy.Organizmy;
import Glowne_klasy.Rosliny;
import Glowne_klasy.Swiat;

import java.awt.*;

public class Mlecz  extends Rosliny
{
    public Mlecz(Swiat swiat_ref,int x, int y)
    {
        super(swiat_ref,x,y);
        this.sila = 0;
        this.nazwa = "Mlecz";
        this.prawdopodobienstwo = 5;
        this.kolor= Color.decode("#fff200");
    }
    @Override
    protected boolean obrona(Organizmy agresor)
    {
        return false;
    }
    @Override
    public void akcja()
    {
        for (int i = 0; i < 3; i++)
        {
            super.akcja();
        }
    }
}
