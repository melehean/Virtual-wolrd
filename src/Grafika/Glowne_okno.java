package Grafika;

import javax.swing.*;
import javax.swing.border.Border;

import Glowne_klasy.Pair;
import Glowne_klasy.Swiat;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Glowne_okno extends JFrame
{
    Swiat swiat_ref;
    private Panel_swiata pole_gry;
    private Panel_swiata_hexy pole_gry_hexy;
    private Panel_przyciskow pole_przyciskow;
    private Panel_powiadomien pole_powiadomien;
    private JScrollPane scroll_swiat, scroll_powiadomienia, scroll_swiat_hexy;
    private Klawiatura klawiatura;
    private Klawiatura_hexy klawiatura_hexy;
    private boolean hexy;
    public Glowne_okno(Pair<Integer, Integer> rozmiar, Swiat swiat_ref, Panel_powiadomien pole_powiadomien, boolean hexy)
    {
        this.swiat_ref=swiat_ref;
        this.hexy=hexy;
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1900,1000);
        setTitle("Wirtualny Swiat");
        if(hexy==false)
        {
            pole_gry = new Panel_swiata(swiat_ref,rozmiar);
            scroll_swiat = new JScrollPane(pole_gry);
            scroll_swiat.setPreferredSize(new Dimension(1480,500));
            add(scroll_swiat,BorderLayout.WEST);
        }
        else
        {
            pole_gry_hexy = new Panel_swiata_hexy(swiat_ref, rozmiar);
            scroll_swiat_hexy = new JScrollPane(pole_gry_hexy);
            add(scroll_swiat_hexy,BorderLayout.WEST);
        }
        pole_przyciskow = new Panel_przyciskow(this);
        add(pole_przyciskow, BorderLayout.SOUTH);
        this.pole_powiadomien = pole_powiadomien;
        scroll_powiadomienia = new JScrollPane(pole_powiadomien);
        scroll_powiadomienia.setPreferredSize(new Dimension(400,500));
        add(scroll_powiadomienia,BorderLayout.EAST);
        if(swiat_ref.get_hexy()==false)
        {
            klawiatura = new Klawiatura();
            this.addKeyListener(klawiatura);
        }
        else
        {
            klawiatura_hexy = new Klawiatura_hexy();
            this.addKeyListener(klawiatura_hexy);
        }
        setVisible(true);
        this.toFront();
        this.requestFocus();
    }
    public Swiat idz_do_swiata()
    {
        return swiat_ref;
    }
    public void wstaw_pole_na_planszy(Pair<Integer,Integer> wspolrzedne)
    {
        if(hexy==true)pole_gry_hexy.wstaw_pole_planszy(wspolrzedne);
        else pole_gry.wstaw_pole_planszy(wspolrzedne);
    }
    public void wyswietl_komunikaty(){pole_powiadomien.wyswietl_komunikaty();}
    public void zmien_rozmiar(Pair<Integer, Integer> nowy_rozmiar, boolean hex_obecny, boolean hex_nowy)
    {
        this.hexy=hex_nowy;
        if(hex_obecny==false)
        {
            this.remove(scroll_swiat);
            this.removeKeyListener(klawiatura);
        }
        else
        {
            this.remove(scroll_swiat_hexy);
            this.removeKeyListener(klawiatura_hexy);
        }
        if(hex_nowy==false)
        {
            pole_gry = new Panel_swiata(swiat_ref, nowy_rozmiar);
            scroll_swiat = new JScrollPane(pole_gry);
            scroll_swiat.setPreferredSize(new Dimension(1480,500));
            add(scroll_swiat,BorderLayout.WEST);
            klawiatura = new Klawiatura();
            this.addKeyListener(klawiatura);
        }
        else
        {
            pole_gry_hexy = new Panel_swiata_hexy(swiat_ref, nowy_rozmiar);
            scroll_swiat = new JScrollPane(pole_gry_hexy);
            add(scroll_swiat,BorderLayout.WEST);
            klawiatura_hexy = new Klawiatura_hexy();
            this.addKeyListener(klawiatura_hexy);
        }
        this.remove(pole_przyciskow);
        pole_przyciskow = new Panel_przyciskow(this);
        add(pole_przyciskow, BorderLayout.SOUTH);
    }
    public void czysc_swiat()
    {
        if(hexy==true)pole_gry_hexy.czysc_swiat();
        else pole_gry.czysc_swiat();
    }
    public void napraw_focus()
    {
        setVisible(true);
        this.toFront();
        this.requestFocus();
    }
    public boolean get_hexy()
    {
        return this.hexy;
    }
}
