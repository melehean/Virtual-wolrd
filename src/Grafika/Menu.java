package Grafika;

import Glowne_klasy.Pair;
import Glowne_klasy.Swiat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu extends JFrame implements ActionListener
{
    private JButton przycisk_nowa_gra, przycisk_wczytaj,przycisk_wyjdz, przycisk_start;
    private JPanel menu, rozmiar_p;
    private GridBagConstraints opis;
    private JLabel komunikat, szerokosc, wysokosc, tytul, rodzaj_planszy;
    private JTextArea szerokosc_t, wysokosc_t;
    private JRadioButton hex_radio, kwadrat_radio;
    private ButtonGroup radio_buttony;
    private Pair<Integer, Integer> rozmiar;
    private boolean hexy;
    public Menu()
    {
        opis = new GridBagConstraints();
        tytul = new JLabel("W  I  R  T  U  A  L  N  Y     Ś  W  I  A  T");
        koloruj_napis(tytul);
        this.setSize(1900, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public void pokaz_menu() {
        menu = new JPanel(new GridBagLayout());
        menu.setBackground(Color.decode("#596275"));

        opis.insets = new Insets(70, 10, 10, 10);
        opis.gridy = 1;
        opis.ipady = 100;
        opis.ipadx = 100;

        menu.add(tytul);
        przycisk_nowa_gra = new JButton("       Nowa gra       ");
        koloruj_przycisk(przycisk_nowa_gra);
        opis.gridy=2;

        menu.add(przycisk_nowa_gra, opis);
        przycisk_wczytaj = new JButton("     Wczytaj grę     ");
        koloruj_przycisk(przycisk_wczytaj);
        opis.gridy = 3;

        menu.add(przycisk_wczytaj, opis);
        przycisk_wyjdz = new JButton(" Zamknij program ");
        koloruj_przycisk(przycisk_wyjdz);
        opis.gridy = 4;

        menu.add(przycisk_wyjdz, opis);
        this.add(menu);

        przycisk_nowa_gra.addActionListener(this);
        przycisk_wczytaj.addActionListener(this);
        przycisk_wyjdz.addActionListener(this);

    }
    private void koloruj_przycisk(JButton przycisk)
    {
        przycisk.setFont(new Font("Arial", Font.PLAIN, 50));
        przycisk.setBackground(Color.decode("#1B1B1B"));
        przycisk.setForeground(Color.WHITE);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object zrodlo = e.getSource();
        if(zrodlo == przycisk_nowa_gra)wczytaj_rozmiar();
        else if(zrodlo == przycisk_wyjdz)this.dispose();
        else if(zrodlo == przycisk_wczytaj)
        {
            int x=2,y=2;
            File file=new File("opowiesc.txt");
            try
            {
                Scanner in = new Scanner(file);
                x=in.nextInt();
                y=in.nextInt();
                hexy=in.nextBoolean();
                in.close();
            }
            catch (FileNotFoundException e1)
            {
                e1.printStackTrace();
            }
            this.dispose();
            Swiat moj_swiat=new Swiat(new Pair(x,y),hexy);
            try
            {
                moj_swiat.wczytaj();
            }
            catch (FileNotFoundException e1)
            {
                e1.printStackTrace();
                moj_swiat.przekaz_powiadomienie("Błąd odczytu pliku");
            }
        }
        else if(zrodlo == przycisk_start)
        {
            int first=0,second=0;
            if(szerokosc_t.getText().isEmpty()==false&&wysokosc_t.getText().isEmpty()==false)
            {
                try { first = Integer.parseInt(szerokosc_t.getText()); }
                catch (NumberFormatException exep) { }
                try {second = Integer.parseInt(wysokosc_t.getText()); }
                catch (NumberFormatException exep) { }
                if(first>1&&second>1)
                {
                    rozmiar = new Pair<>(first,second);
                    hexy = hex_radio.isSelected();
                    Swiat moj_swiat = new Swiat(rozmiar,hexy);
                    moj_swiat.start();
                    this.dispose();
                }
            }
        }
    }
    private void wczytaj_rozmiar()
    {
        this.remove(menu);
        rozmiar_p = new JPanel(new GridBagLayout());
        rozmiar_p.setBackground(Color.decode("#596275"));

        przycisk_start = new JButton("Zatwierdź");
        koloruj_przycisk(przycisk_start);

        radio_buttony = new ButtonGroup();
        hex_radio = new JRadioButton("Sześciąkąty", false);
        koloruj_radio_button(hex_radio);
        kwadrat_radio = new JRadioButton("Kwadraty",true);
        koloruj_radio_button(kwadrat_radio);

        radio_buttony.add(hex_radio);
        radio_buttony.add(kwadrat_radio);

        opis.insets = new Insets(10, 0, 0, 0);
        opis.gridx=0;

        komunikat = new JLabel("Podaj rozmiar świata:");
        koloruj_napis(komunikat);

        szerokosc = new JLabel("Szerokość:");
        koloruj_napis(szerokosc);

        wysokosc = new JLabel("Wysokość:");
        koloruj_napis(wysokosc);

        rodzaj_planszy = new JLabel("Rodzaj planszy:");
        koloruj_napis(rodzaj_planszy);

        szerokosc_t = new JTextArea(1000,1000);
        wysokosc_t = new JTextArea(1000,1000);
        szerokosc_t.setFont(new Font("Arial", Font.PLAIN, 50));
        wysokosc_t.setFont(new Font("Arial", Font.PLAIN, 50));

        opis.ipady=10;
        opis.fill=GridBagConstraints.HORIZONTAL;
        opis.gridx=0;
        opis.gridy=1;
        rozmiar_p.add(komunikat,opis);

        opis.gridy=2;
        rozmiar_p.add(szerokosc,opis);

        opis.gridy=4;
        rozmiar_p.add(wysokosc,opis);

        opis.gridy=3;
        rozmiar_p.add(szerokosc_t,opis);

        opis.gridy=5;
        rozmiar_p.add(wysokosc_t,opis);

        opis.gridy=6;
        rozmiar_p.add(rodzaj_planszy,opis);

        opis.gridy=7;
        rozmiar_p.add(hex_radio,opis);
        opis.gridy=8;
        rozmiar_p.add(kwadrat_radio,opis);

        opis.gridy=9;
        rozmiar_p.add(przycisk_start,opis);
        przycisk_start.addActionListener(this);

        this.add(rozmiar_p);
        this.repaint();
        this.setVisible(true);
    }
    private void koloruj_napis(JLabel napis)
    {
        napis.setFont(new Font("Arial", Font.PLAIN, 50));
        napis.setForeground(Color.WHITE);
    }
    private void koloruj_radio_button(JRadioButton radio_button)
    {
        radio_button.setBackground(Color.decode("#596275"));
        radio_button.setFont(new Font("Arial", Font.PLAIN, 30));
        radio_button.setForeground(Color.WHITE);
    }
}

