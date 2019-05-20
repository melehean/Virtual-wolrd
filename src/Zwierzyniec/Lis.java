package Zwierzyniec;

import Glowne_klasy.Organizmy;
import Glowne_klasy.Swiat;
import Glowne_klasy.Zwierzeta;
import Glowne_klasy.Pair;

import java.awt.*;
import java.util.Random;

public class Lis extends Zwierzeta
{
    public Lis(Swiat swiat_ref, int x, int y)
    {
        super(swiat_ref,x,y);
        this.sila = 3;
        this.inicjatywa = 7;
        this.nazwa = "Lis";
        this.kolor = Color.decode("#fa8231");
    }

    public void akcja()
    {
    if (this.mozliwosc_wykonania_ruchu())
    {
        Random generator = new Random();
        Pair<Integer, Integer>przesuniecie = new Pair(0,0);
        Pair<Integer, Integer>nowa_lokalizacja = new Pair(this.polozenie.getFirst(),this.polozenie.getSecond());
        while ((przesuniecie.getFirst() == 0 && przesuniecie.getSecond() == 0) || (swiat_ref.poza_tablice(nowa_lokalizacja)==true)
                || (swiat_ref.ruch_zwyciescy(nowa_lokalizacja, this.sila)==false))
        {
            przesuniecie.setFirst(generator.nextInt(3)-1);
            przesuniecie.setSecond(generator.nextInt(3)-1);
            if(swiat_ref.get_hexy()==true
                    &&(przesuniecie.getFirst()!=1||przesuniecie.getSecond()!=1)
                    &&(przesuniecie.getFirst()!=-1||przesuniecie.getSecond()!=-1)
                    ||swiat_ref.get_hexy()==false)
            {
                nowa_lokalizacja.setFirst(przesuniecie.getFirst() + this.polozenie.getFirst());
                nowa_lokalizacja.setSecond(przesuniecie.getSecond() + this.polozenie.getSecond());
            }
        }
        swiat_ref.wykonaj_ruch(this.polozenie, nowa_lokalizacja);
    }
}
    private boolean mozliwosc_wykonania_ruchu()
    {
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                if ((i != 0 || j != 0) && swiat_ref.ruch_zwyciescy(new Pair(this.polozenie.getFirst()+i,this.polozenie.getSecond()+j), this.sila)==true)
                    if(swiat_ref.get_hexy()==true&&(i!=1||j!=1)&&(i!=-1||j!=-1)||swiat_ref.get_hexy()==false)return true;
        return false;
    }

    @Override
    protected boolean obrona(Organizmy agresor)
    {
        return false;
    }
}
