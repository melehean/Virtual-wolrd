package Grafika;

import Glowne_klasy.Pair;
import Glowne_klasy.Swiat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dodaj_organizm implements ActionListener
{
    private Swiat swiat_ref;
    private Pair<Integer,Integer>polozenie;
    private String napis;
    public Dodaj_organizm(Pair<Integer,Integer> polozenie, Swiat swiat_ref, String napis)
    {
        this.swiat_ref=swiat_ref;
        this.polozenie=polozenie;
        this.napis=napis;
    }
    @Override public void actionPerformed(ActionEvent e)
    {
        if(swiat_ref.puste_miejsce(polozenie)) swiat_ref.dodaj_organizm(polozenie, napis);
        else swiat_ref.przekaz_powiadomienie("Nie można dostać organizmu");
        swiat_ref.rysuj_swiat();
    }
}
