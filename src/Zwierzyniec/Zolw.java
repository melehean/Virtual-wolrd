package Zwierzyniec;

import Glowne_klasy.Organizmy;
import Glowne_klasy.Pair;
import Glowne_klasy.Swiat;
import Glowne_klasy.Zwierzeta;

import java.awt.*;
import java.util.Random;

import static Enumy.powiadomienia_obopulne.OPOR;

public class Zolw extends Zwierzeta
{
    public Zolw(Swiat swiat_ref, int x, int y)
    {
        super(swiat_ref,x,y);
        this.sila = 2;
        this.inicjatywa = 1;
        this.nazwa = "Zolw";
        this.kolor = Color.decode("#20bf6b");
    }
    @Override
    public void akcja()
    {
        if (mozliwosc_wykonania_ruchu() == true)super.akcja();
	    else return;
    }

    private boolean mozliwosc_wykonania_ruchu()
    {
        Random generator = new Random();
        int losowa = generator.nextInt(100);
        if (losowa < 75)return false;
        else return true;
    }

    protected boolean obrona(Organizmy agresor)
{
    if (agresor.get_sila() < 5 && agresor.get_sila()>0)
    {
        Pair<String, String> czlony = new Pair(this.nazwa, agresor.get_nazwa());
        swiat_ref.przekaz_powiadomienie(czlony, OPOR);
        return true;
    }
    return false;
}
}
