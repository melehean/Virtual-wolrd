package Grafika;

import Glowne_klasy.Pair;
import Glowne_klasy.Swiat;

import javax.swing.*;
import java.awt.*;

public class Pole_hexy extends JPanel
{
    private Swiat swiat_ref;
    private Pair<Integer, Integer> polozenie;
    private boolean czy_organizm;
    Pole_hexy(Pair<Integer,Integer>polozenie, Swiat swiat_ref)
    {
        this.swiat_ref=swiat_ref;
        Mysz dodaj_organizm = new Mysz(polozenie,swiat_ref);
        addMouseListener(dodaj_organizm);
        this.polozenie = polozenie;
        this.setLayout(new GridBagLayout());
        setBackground(Color.decode("#596275"));
        this.setPreferredSize(new Dimension(50,50));
    }
    public void rysuj()
    {
        repaint();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++) p.addPoint((int) (
                25 + 25 * Math.cos(i * 2 * Math.PI / 6+Math.PI / 6)),
                (int) (25 + 25 * Math.sin(i * 2 * Math.PI / 6+Math.PI / 6)));
        if(czy_organizm==true)
        {
            g.setColor(swiat_ref.kolor_organizmu_na_planszy(this.polozenie));
            g.fillPolygon(p);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 10));
            g.drawString(swiat_ref.get_nazwa_na_planszy(polozenie), 5, 25);
        }
        else
        {
            g.setColor(Color.BLACK);
            g.drawPolygon(p);
        }
    }
    public void czysc_pole()
    {
        czy_organizm=false;
        rysuj();
    }
    public void wstaw_pole()
    {
        this.czy_organizm=true;
        rysuj();
    }
}
