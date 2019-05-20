package Zwierzyniec;

import Glowne_klasy.Organizmy;
import Glowne_klasy.Swiat;
import Glowne_klasy.Zwierzeta;

import java.awt.*;

public class Owca extends Zwierzeta
{
    public Owca(Swiat swiat_ref, int x, int y)
    {
        super(swiat_ref,x,y);
        this.sila = 4;
        this.inicjatywa = 4;
        this.nazwa = "Owca";
        this.kolor = Color.decode("#fed330");
    }
    @Override
    protected boolean obrona(Organizmy agresor)
    {
        return false;
    }
}
