package Grafika;

import Enumy.KURSOR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class Panel_przyciskow extends JPanel implements ActionListener
{
    Glowne_okno okno_ref;
    JButton zapisz,wczytaj, umiejetnosc, gora, dol, prawo, lewo, gora_lewo,gora_prawo, dol_lewo,dol_prawo;
    Panel_przyciskow(Glowne_okno okno_ref)
    {
        this.okno_ref=okno_ref;
        zapisz = new JButton();
        zapisz.setIcon(new ImageIcon("save.png"));
        wczytaj = new JButton();
        wczytaj.setIcon(new ImageIcon("load.png"));
        umiejetnosc = new JButton();
        umiejetnosc.setIcon(new ImageIcon("burn_it.jpg"));

        prawo = new JButton();
        prawo.setIcon(new ImageIcon("prawo.jpg"));
        lewo = new JButton();
        lewo.setIcon(new ImageIcon("lewo.jpg"));
        if(okno_ref.get_hexy()==true)
        {
            gora_lewo = new JButton();
            gora_lewo.setIcon(new ImageIcon("gora_lewo.jpg"));
            gora_prawo = new JButton();
            gora_prawo.setIcon(new ImageIcon("gora_prawo.jpg"));
            dol_lewo = new JButton();
            dol_lewo.setIcon(new ImageIcon("dol_lewo.jpg"));
            dol_prawo = new JButton();
            dol_prawo.setIcon(new ImageIcon("dol_prawo.jpg"));
            gora_prawo.addActionListener(this);
            dol_prawo.addActionListener(this);
            gora_lewo.addActionListener(this);
            dol_lewo.addActionListener(this);
        }
        else
        {
            gora = new JButton();
            gora.setIcon(new ImageIcon("gora.jpg"));
            dol = new JButton();
            dol.setIcon(new ImageIcon("dol.jpg"));
            gora.addActionListener(this);
            dol.addActionListener(this);
        }
        this.setLayout(new FlowLayout());
        zapisz.addActionListener(this);
        wczytaj.addActionListener(this);
        umiejetnosc.addActionListener(this);
        prawo.addActionListener(this);
        lewo.addActionListener(this);
        this.add(zapisz);
        this.add(wczytaj);
        this.add(umiejetnosc);
        if(okno_ref.get_hexy()==true)
        {
            this.add(gora_prawo);
            this.add(dol_prawo);
            this.add(gora_lewo);
            this.add(dol_lewo);
        }
        else {
            this.add(gora);
            this.add(dol);
        }
        this.add(lewo);
        this.add(prawo);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object zrodlo = e.getSource();
        if(zrodlo == gora)okno_ref.swiat_ref.nowa_tura(KURSOR.GORA);
        if(zrodlo == dol)okno_ref.swiat_ref.nowa_tura(KURSOR.DOL);
        if(zrodlo == gora_lewo)okno_ref.swiat_ref.nowa_tura(KURSOR.GORA_LEWO);
        if(zrodlo == gora_prawo)okno_ref.swiat_ref.nowa_tura(KURSOR.GORA_PRAWO);
        if(zrodlo == dol_lewo)okno_ref.swiat_ref.nowa_tura(KURSOR.DOL_LEWO);
        if(zrodlo == dol_prawo)okno_ref.swiat_ref.nowa_tura(KURSOR.DOL_PRAWO);
        if(zrodlo == lewo)okno_ref.swiat_ref.nowa_tura(KURSOR.LEWO);
        if(zrodlo == prawo)okno_ref.swiat_ref.nowa_tura(KURSOR.PRAWO);
        if(zrodlo == umiejetnosc)okno_ref.swiat_ref.nowa_tura(KURSOR.MOC);
        if(zrodlo == zapisz)
        {
            try { okno_ref.swiat_ref.zapisz(); }
            catch (FileNotFoundException e1)
            { e1.printStackTrace(); okno_ref.swiat_ref.przekaz_powiadomienie("Błąd zapisu pliku"); }
        }
        if(zrodlo == wczytaj)
        {
            try { okno_ref.swiat_ref.wczytaj();}
            catch (FileNotFoundException e1)
            { e1.printStackTrace(); okno_ref.swiat_ref.przekaz_powiadomienie("Błąd wczytanie pliku"); }
        }
        okno_ref.napraw_focus();
    }
}
