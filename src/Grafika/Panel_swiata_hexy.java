package Grafika;

import Glowne_klasy.Pair;
import Glowne_klasy.Swiat;

import javax.swing.*;
import java.awt.*;

public class Panel_swiata_hexy extends JPanel
{
    private Pair<Integer, Integer> rozmiar;
    private Swiat swiat_ref;
    private Pole_hexy plansza[][];
    public Panel_swiata_hexy(Swiat swiat_ref, Pair<Integer, Integer> rozmiar)
    {
        this.swiat_ref=swiat_ref;
        this.setBackground(Color.decode("#596275"));
        setPreferredSize(new Dimension(1480,500));
        this.rozmiar = new Pair(rozmiar.getFirst(), rozmiar.getSecond());
        setLayout(null);
        plansza= new Pole_hexy[rozmiar.getSecond()][rozmiar.getFirst()];
        for(int i=0;i<rozmiar.getSecond();i++)
        {
            for(int j=0;j<rozmiar.getFirst();j++)
            {
                plansza[i][j]=new Pole_hexy(new Pair(j,i),swiat_ref);
                add(plansza[i][j]);
                plansza[i][j].setBounds(50*j+25*i,50*i,plansza[i][j].getPreferredSize().width, plansza[i][j].getPreferredSize().height);
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
