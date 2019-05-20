package Ogrod;

import Glowne_klasy.Organizmy;
import Glowne_klasy.Pair;
import Glowne_klasy.Rosliny;
import Glowne_klasy.Swiat;

import java.awt.*;

import static Enumy.powiadomienia_obopulne.ZATRUCIE;

public class Wilcza_jagoda extends Rosliny
{
    public Wilcza_jagoda(Swiat swiat_ref, int x, int y)
    {
        super(swiat_ref,x,y);
        this.sila = 99;
        this.nazwa = "Jagoda";
        this.prawdopodobienstwo = 3;
        this.kolor = Color.decode("#6D214F");
    }
    @Override
    protected boolean obrona(Organizmy agresor)
    {
        Pair<String, String> czlony = new Pair(this.nazwa, agresor.get_nazwa());
        swiat_ref.przekaz_powiadomienie(czlony, ZATRUCIE);
        swiat_ref.usun_organizm(agresor);
        return true;
    }
}
