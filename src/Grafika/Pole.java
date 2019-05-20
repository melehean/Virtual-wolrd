package Grafika;

import Glowne_klasy.Swiat;
import Glowne_klasy.Pair;
import javax.swing.*;
import java.awt.*;

public class Pole extends JPanel
{
    private JLabel nazwa;
    private Swiat swiat_ref;
    private Pair<Integer, Integer>polozenie;
    Pole(Pair<Integer,Integer>polozenie, Swiat swiat_ref)
    {
        this.swiat_ref=swiat_ref;
        Mysz dodaj_organizm = new Mysz(polozenie,swiat_ref);
        addMouseListener(dodaj_organizm);
        this.polozenie = new Pair(polozenie.getFirst(),polozenie.getSecond());
        nazwa = new JLabel("");
        this.setLayout(new GridBagLayout());
        GridBagConstraints opis = new GridBagConstraints();
        setBackground(Color.decode("#596275"));
        this.setPreferredSize(new Dimension(50,50));
        add(nazwa,opis);
    }
    public void czysc_pole()
    {
        nazwa.setText("");
        setBackground(Color.decode("#596275"));
    }
    public void wstaw_pole()
    {
        this.nazwa.setText(swiat_ref.nazwa_organizmu_na_planszy(this.polozenie));
        this.setBackground(swiat_ref.kolor_organizmu_na_planszy(this.polozenie));
        this.nazwa.setForeground(Color.WHITE);
        Pair<Integer, Integer> pom = new Pair(swiat_ref.get_rozmiar().getFirst(),swiat_ref.get_rozmiar().getSecond());
        if(Math.max(pom.getFirst(),pom.getSecond())<5)this.nazwa.setFont(new Font("Arial", Font.PLAIN, 40));
        else if(Math.max(pom.getFirst(),pom.getSecond())<10)this.nazwa.setFont(new Font("Arial", Font.PLAIN, 30));
        else if(Math.max(pom.getFirst(),pom.getSecond())<20)this.nazwa.setFont(new Font("Arial", Font.PLAIN, 15));
    }
}
