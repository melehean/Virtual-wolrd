package Grafika;

import Glowne_klasy.Pair;
import Glowne_klasy.Swiat;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mysz extends MouseAdapter
{
    private Pair<Integer,Integer>polozenie;
    private Swiat swiat_ref;
    private JMenuItem[] rozwijane_menu;
    private JPopupMenu menu;
    public Mysz(Pair<Integer,Integer> polozenie, Swiat swiat_ref)
    {
        this.swiat_ref=swiat_ref;
        this.polozenie = polozenie;
    }

    private void stworz_menu()
    {
        rozwijane_menu = new JMenuItem[10];
        rozwijane_menu[0] = new JMenuItem("Wilk");
        rozwijane_menu[1] = new JMenuItem("Owca");
        rozwijane_menu[2] = new JMenuItem("Lis");
        rozwijane_menu[3] = new JMenuItem("Zolw");
        rozwijane_menu[4] = new JMenuItem("Antylopa");
        rozwijane_menu[5] = new JMenuItem("Trawa");
        rozwijane_menu[6] = new JMenuItem("Mlecz");
        rozwijane_menu[7] = new JMenuItem("Barszcz");
        rozwijane_menu[8] = new JMenuItem("Guarana");
        rozwijane_menu[9] = new JMenuItem("Jagody");
        for(int i=0;i<10;i++)
        {
            menu.add(rozwijane_menu[i]);
            rozwijane_menu[i].addActionListener(new Dodaj_organizm(polozenie,this.swiat_ref,rozwijane_menu[i].getText()));
        }
    }

    @Override public void mouseClicked(MouseEvent event)
    {
        if(MouseEvent.BUTTON3==event.getButton())
        {
            menu=new JPopupMenu();
            stworz_menu();
            menu.show(event.getComponent(),event.getX(),event.getY());
        }
    }
}
