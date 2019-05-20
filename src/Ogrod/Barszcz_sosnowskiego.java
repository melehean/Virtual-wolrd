package Ogrod;

import Glowne_klasy.Organizmy;
import Glowne_klasy.Pair;
import Glowne_klasy.Rosliny;
import Glowne_klasy.Swiat;

import java.awt.*;

import static Enumy.powiadomienia_obopulne.POPARZENIE;

public class Barszcz_sosnowskiego extends Rosliny
{
    public Barszcz_sosnowskiego(Swiat swiat_ref, int x, int y)
    {
        super(swiat_ref,x,y);
        this.sila = 10;
        this.nazwa = "Barszcz";
        this.prawdopodobienstwo = 3;
        this.kolor = Color.decode("#c0392b");
    }
    @Override
    public void akcja()
    {
        super.akcja();
        int i, j;
        for (i = -1; i < 2; i++)
        {
            for (j = -1; j < 2; j++)
            {
                if ((i != 0 || j != 0) && swiat_ref.puste_miejsce(new Pair(this.polozenie.getFirst()+i,this.polozenie.getSecond()+j))==false)
                {
                    if(swiat_ref.get_hexy()==true&&(i!=1||j!=1)&&(i!=-1||j!=-1)|| swiat_ref.get_hexy()==false)
                    {
                        if (swiat_ref.czy_zwierze_na_planszy(new Pair(this.polozenie.getFirst()+i,this.polozenie.getSecond()+j)) == true
                                &&swiat_ref.poza_tablice(new Pair(this.polozenie.getFirst()+i,this.polozenie.getSecond()+j))==false)
                        {
                            Pair<String, String>czlony =
                                    new Pair(this.nazwa,swiat_ref.get_nazwa_na_planszy(new Pair(this.polozenie.getFirst()+i,this.polozenie.getSecond()+j)));
                            swiat_ref.przekaz_powiadomienie(czlony, POPARZENIE);
                            swiat_ref.usun_organizm(new Pair(this.polozenie.getFirst()+i,this.polozenie.getSecond()+j));
                        }
                    }
                }
            }
        }
    }
    @Override
    protected boolean obrona(Organizmy agresor)
    {
        Pair<String, String>czlony = new Pair(this.nazwa,agresor.get_nazwa());
        swiat_ref.przekaz_powiadomienie(czlony, POPARZENIE);
        swiat_ref.usun_organizm(agresor);
        return true;
    }
}
