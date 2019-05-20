package Zwierzyniec;

import Glowne_klasy.Organizmy;
import Glowne_klasy.Pair;
import Glowne_klasy.Swiat;
import Glowne_klasy.Zwierzeta;

import java.awt.*;
import java.util.Random;

import static Enumy.powiadomienia_obopulne.UCIECZKA;

public class Antylopa extends Zwierzeta
{
    public Antylopa(Swiat swiat_ref, int x, int y)
    {
        super(swiat_ref,x,y);
        this.sila = 4;
        this.inicjatywa = 4;
        this.nazwa = "Antylopa";
        this.kolor = Color.decode("#ffa502");
    }
    @Override
    public void akcja()
    {
        Random generator = new Random();
        Pair<Integer, Integer> przesuniecie = new Pair(0,0);
        Pair<Integer,Integer> nowa_lokalizacja = new Pair(this.polozenie.getFirst(),this.polozenie.getSecond());
        while (przesuniecie.getFirst() == 0 && przesuniecie.getSecond() == 0 || swiat_ref.poza_tablice(nowa_lokalizacja))
        {
            przesuniecie.setFirst(generator.nextInt(5)-1);
            przesuniecie.setSecond(generator.nextInt(5)-1);
            if(swiat_ref.get_hexy()==true
                    &&(przesuniecie.getFirst()!=1||przesuniecie.getSecond()!=1)
                    &&(przesuniecie.getFirst()!=-1||przesuniecie.getSecond()!=-1)
                    &&(przesuniecie.getFirst()!=2||przesuniecie.getSecond()!=2)
                    &&(przesuniecie.getFirst()!=-2||przesuniecie.getSecond()!=-2)
                    ||swiat_ref.get_hexy()==false)
            {
                nowa_lokalizacja.setFirst(przesuniecie.getFirst() + this.polozenie.getFirst());
                nowa_lokalizacja.setSecond(przesuniecie.getSecond() + this.polozenie.getSecond());
            }
        }
        swiat_ref.wykonaj_ruch(this.polozenie, nowa_lokalizacja);
    }
    @Override
    protected boolean obrona(Organizmy agresor)
{
    if (this.mozliwosc_wykonania_ruchu())
    {
        super.akcja();
        Pair<String, String>czlony = new Pair(this.nazwa,agresor.get_nazwa());
        swiat_ref.przekaz_powiadomienie(czlony, UCIECZKA);
        return true;
    }
    return false;
}

    private boolean mozliwosc_wykonania_ruchu()
    {
        Random generator = new Random();
        int szansa = generator.nextInt(2);
        boolean czy_moze_sie_ruszyc = false;
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                if ((i != 0 || j != 0) && swiat_ref.puste_miejsce(new Pair(this.polozenie.getFirst()+i,this.polozenie.getSecond()+j)))
                    if(swiat_ref.get_hexy()==true&&(i!=1||j!=1)&&(i!=-1||j!=-1))czy_moze_sie_ruszyc = true;
                    else if(swiat_ref.get_hexy()==false)czy_moze_sie_ruszyc = true;
        if (szansa == 0 && czy_moze_sie_ruszyc)return true;
        return false;
    }
}
