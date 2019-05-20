package Grafika;

import Glowne_klasy.Pair;
import Glowne_klasy.Swiat;

import javax.swing.*;
import java.awt.*;

public class Panel_swiata extends JPanel
{
    private Pair<Integer, Integer> rozmiar;
    private Swiat swiat_ref;
    private Pole plansza[][];
    public Panel_swiata(Swiat swiat_ref, Pair<Integer, Integer> rozmiar)
    {
        this.swiat_ref=swiat_ref;
        this.rozmiar = new Pair(rozmiar.getFirst(), rozmiar.getSecond());
        setLayout(new GridLayout(rozmiar.getSecond(),rozmiar.getFirst(),1,1));
        plansza= new Pole[rozmiar.getSecond()][rozmiar.getFirst()];
        for(int i=0;i<rozmiar.getSecond();i++)
        {
            for(int j=0;j<rozmiar.getFirst();j++)
            {
                plansza[i][j]=new Pole(new Pair(j,i),swiat_ref);
                add(plansza[i][j]);
            }
        }
    }
    public void wstaw_pole_planszy(Pair<Integer,Integer> wspolrzedne)
    {
        plansza[wspolrzedne.getSecond()][wspolrzedne.getFirst()].wstaw_pole();
    }
    public void czysc_swiat()
    {
        int i,j;
        for(i=0;i<rozmiar.getSecond();i++)
            for(j=0;j<rozmiar.getFirst();j++)
                plansza[i][j].czysc_pole();
    }
}
