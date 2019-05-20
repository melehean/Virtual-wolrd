package Ogrod;

import Glowne_klasy.Organizmy;
import Glowne_klasy.Pair;
import Glowne_klasy.Rosliny;
import Glowne_klasy.Swiat;

import java.awt.*;

import static Enumy.powiadomienia_obopulne.WZMOCNIENIE;

public class Guarana extends Rosliny
{
    public Guarana(Swiat swiat_ref, int x, int y)
    {
        super(swiat_ref,x,y);
        this.sila = 0;
        this.nazwa = "Guarana";
        this.prawdopodobienstwo = 5;
        this.kolor = Color.decode("#1B1464");
    }
    @Override
    protected boolean obrona(Organizmy agresor)
    {
        Pair<String, String> czlony = new Pair(this.nazwa, agresor.get_nazwa());
        swiat_ref.przekaz_powiadomienie(czlony, WZMOCNIENIE);
        agresor.set_sila(agresor.get_sila() + 3);
        return false;
    }
}
