package Glowne_klasy;

import Enumy.KURSOR;
import Enumy.powiadomienia_obopulne;
import Enumy.powiadomienia_pojedyncze;
import Grafika.Glowne_okno;
import Grafika.Panel_powiadomien;
import Ogrod.*;
import Zwierzyniec.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import static Enumy.powiadomienia_pojedyncze.NARODZINY;
import static java.lang.Integer.max;
import static jdk.nashorn.internal.objects.NativeMath.min;

public class Swiat
{
    private Pair<Integer,Integer> rozmiar;
    private Organizmy plansza[][];
    private Vector<Organizmy> ruch;
    private int rozmiar_ruchu,iterator_organizmow;
    private Glowne_okno grafika;
    private Czlowiek czlowiek;
    private Panel_powiadomien moje_powiadomienia;
    private boolean hexy;
    public Swiat(Pair<Integer, Integer> rozmiar, boolean hexy)
    {
        this.rozmiar=rozmiar;
        plansza = new Organizmy[this.rozmiar.getSecond()][];
        for (int i = 0; i < this.rozmiar.getSecond(); i++)
            plansza[i] = new Organizmy[this.rozmiar.getFirst()];

        for (int i = 0; i < this.rozmiar.getSecond(); i++)
            for (int j = 0; j < this.rozmiar.getFirst(); j++)
                plansza[i][j] = null;
        ruch=new Vector();
        moje_powiadomienia = new Panel_powiadomien();
        this.hexy=hexy;
        grafika = new Glowne_okno(rozmiar,this, moje_powiadomienia,hexy);


    }
    public Pair<Integer,Integer> get_rozmiar()
    {
        return rozmiar;
    }
    public boolean poza_tablice(Pair<Integer, Integer> wspolrzedne)
    {
        if (wspolrzedne.getFirst() < 0 || wspolrzedne.getSecond() < 0 || wspolrzedne.getFirst() >= rozmiar.getFirst() || wspolrzedne.getSecond() >= rozmiar.getSecond())
            return true;
        return false;
    }

    public boolean ruch_zwyciescy(Pair<Integer,Integer> wspolrzedne, int sila)
    {
        if ((!poza_tablice(wspolrzedne)
                && (plansza[wspolrzedne.getSecond()][wspolrzedne.getFirst()] == null
                || (plansza[wspolrzedne.getSecond()][wspolrzedne.getFirst()] != null
                && plansza[wspolrzedne.getSecond()][wspolrzedne.getFirst()].get_sila() <= sila))))
        return true;
        return false;
    }

    public boolean puste_miejsce(Pair<Integer, Integer> wspolrzedne)
    {
        if (!(poza_tablice(wspolrzedne)) && plansza[wspolrzedne.getSecond()][wspolrzedne.getFirst()] == null)return true;
        return false;
    }
    public boolean czy_zwierze_na_planszy(Pair<Integer, Integer>wspolrzedne)
    {
        if (this.poza_tablice(wspolrzedne) == true)return false;
        if (plansza[wspolrzedne.getSecond()][wspolrzedne.getFirst()] == null)return false;
        if (plansza[wspolrzedne.getSecond()][wspolrzedne.getFirst()] instanceof Zwierzeta) return true;
        return false;
    }

    public void dodaj_organizm(Pair<Integer,Integer>polozenie, String wybor)
    {
        switch (wybor.charAt(0))
        {
            case 'C':
                plansza[polozenie.getSecond()][polozenie.getFirst()] = new Czlowiek(this, polozenie.getFirst(),polozenie.getSecond());
                break;
            case 'W':
                plansza[polozenie.getSecond()][polozenie.getFirst()] = new Wilk(this, polozenie.getFirst(),polozenie.getSecond());
                break;
            case 'O':
                plansza[polozenie.getSecond()][polozenie.getFirst()] = new Owca(this, polozenie.getFirst(),polozenie.getSecond());
                break;
            case 'L':
                plansza[polozenie.getSecond()][polozenie.getFirst()] = new Lis(this, polozenie.getFirst(),polozenie.getSecond());
                break;
            case 'Z':
                plansza[polozenie.getSecond()][polozenie.getFirst()] = new Zolw(this, polozenie.getFirst(),polozenie.getSecond());
                break;
            case 'A':
                plansza[polozenie.getSecond()][polozenie.getFirst()] = new Antylopa(this, polozenie.getFirst(),polozenie.getSecond());
                break;
            case 'T':
                plansza[polozenie.getSecond()][polozenie.getFirst()] = new Trawa(this, polozenie.getFirst(),polozenie.getSecond());
                break;
            case 'M':
                plansza[polozenie.getSecond()][polozenie.getFirst()] = new Mlecz(this, polozenie.getFirst(),polozenie.getSecond());
                break;
            case 'G':
                plansza[polozenie.getSecond()][polozenie.getFirst()] = new Guarana(this, polozenie.getFirst(),polozenie.getSecond());
                break;
            case 'J':
                plansza[polozenie.getSecond()][polozenie.getFirst()] = new Wilcza_jagoda(this, polozenie.getFirst(),polozenie.getSecond());
                break;
            case 'B':
                plansza[polozenie.getSecond()][polozenie.getFirst()] = new Barszcz_sosnowskiego(this, polozenie.getFirst(),polozenie.getSecond());
                break;
        }
        ruch.add(plansza[polozenie.getSecond()][polozenie.getFirst()]);
        przekaz_powiadomienie(plansza[polozenie.getSecond()][polozenie.getFirst()].get_nazwa(), NARODZINY);
    }
    public void wykonaj_ruch(Pair<Integer, Integer> wspolrzedne_obecne, Pair<Integer, Integer>wspolrzedne_nowe)
    {
        if (plansza[wspolrzedne_nowe.getSecond()][wspolrzedne_nowe.getFirst()] == null)
        {
            plansza[wspolrzedne_nowe.getSecond()][wspolrzedne_nowe.getFirst()] = plansza[wspolrzedne_obecne.getSecond()][wspolrzedne_obecne.getFirst()];
            plansza[wspolrzedne_obecne.getSecond()][wspolrzedne_obecne.getFirst()]=null;
            if(plansza[wspolrzedne_nowe.getSecond()][wspolrzedne_nowe.getFirst()]!=null)
                plansza[wspolrzedne_nowe.getSecond()][wspolrzedne_nowe.getFirst()].set_polozenie(wspolrzedne_nowe);
        }
        else
        {
            if(plansza[wspolrzedne_obecne.getSecond()][wspolrzedne_obecne.getFirst()]!=null)
                plansza[wspolrzedne_obecne.getSecond()][wspolrzedne_obecne.getFirst()].kolizja(plansza[wspolrzedne_nowe.getSecond()][wspolrzedne_nowe.getFirst()]);
        }
    }
    public void usun_organizm(Organizmy organizm)
    {
        if (organizm instanceof Czlowiek)czlowiek = null;
        for (int i = 0; i < ruch.size(); i++)
        if (organizm == ruch.elementAt(i))
        {
            ruch.remove(ruch.elementAt(i)); //usuwanie zjedzonego organizmu z kolejki
            if (this.rozmiar_ruchu > i) //jesli zjedzone zostalo dziecko narodzone w tej turze to rozmiar vectora sie nie zmienia
            this.rozmiar_ruchu--;
            if (iterator_organizmow >= i) //jesli zjedzony organizm jest wczesniej w kolejce to vector sie spisuje do tylu wiec i wskaznik trzeba przesunac
             iterator_organizmow--;
            break;
        }
        plansza[organizm.get_polozenie().getSecond()][organizm.get_polozenie().getFirst()] = null;
    }
    public void usun_organizm(Pair<Integer, Integer> wspolrzedne)
    {
        if (plansza[wspolrzedne.getSecond()][wspolrzedne.getFirst()] instanceof Czlowiek)czlowiek = null;
        for (int i = 0; i < ruch.size(); i++)
        if (plansza[wspolrzedne.getSecond()][wspolrzedne.getFirst()] == ruch.elementAt(i))
        {
            ruch.remove(ruch.elementAt(i)); //usuwanie zjedzonego organizmu z vectora
            if (this.rozmiar_ruchu > i) //jesli zjedzone zostalo dziecko narodzone w tej turze to rozmiar vectora sie nie zmienia
            this.rozmiar_ruchu--;
            if (iterator_organizmow >= i) //vector sie spisuje do tylu wiec iterator trzeba przesunac
                iterator_organizmow--;
            break;
        }
        plansza[wspolrzedne.getSecond()][wspolrzedne.getFirst()] = null;
    }
    public void zmien_na_planszy(Pair<Integer, Integer>obecne, Pair<Integer, Integer>nowe)
    {
        plansza[nowe.getSecond()][nowe.getFirst()] = plansza[obecne.getSecond()][obecne.getFirst()];
        plansza[obecne.getSecond()][obecne.getFirst()] = null;
        if(plansza[nowe.getSecond()][nowe.getFirst()]!=null)plansza[nowe.getSecond()][nowe.getFirst()].set_polozenie(nowe);
    }
    private void dodaj_poczatkowe_organizmy()
    {
        Random generator = new Random();
        //jezeli byloby mniej niz 10x10 to nie pojawilyby sie zwierzeta na plaszy, gdyby nie warunek i ustawienie na 1
        int wspolczynnik = this.rozmiar.getFirst()*this.rozmiar.getSecond() < 100 ? 1 : this.rozmiar.getFirst()*this.rozmiar.getSecond() / 100;
        //aby przy malych rozmiarach plansz nie bylo za duzo organizmow
        int losowanie = this.rozmiar.getFirst()*this.rozmiar.getSecond() >= 100 ? 10 : max(this.rozmiar.getFirst(), this.rozmiar.getSecond());
        int dodanie = this.rozmiar.getFirst()*this.rozmiar.getSecond() >= 100 ? 5 : (int) min(5, min(this.rozmiar.getFirst(), this.rozmiar.getSecond()));
        int poczatkowa_liczba_organizmow = (generator.nextInt(losowanie) + dodanie)*wspolczynnik;
        Pair<Integer, Integer> polozenie = new Pair(0,0);
        this.dodaj_organizm(new Pair(0,0), "C");
        czlowiek = (Czlowiek)(plansza[0][0]);
        String typ=new String();
        String symbole = "WOLZATMGJB";
        for (int i = 0; i < poczatkowa_liczba_organizmow; i++)
        {
            polozenie.set(0,0);
            while ((polozenie.getFirst() == 0 && polozenie.getSecond()== 0) || (!this.puste_miejsce(polozenie)))
            {
                polozenie.setFirst(generator.nextInt(this.rozmiar.getFirst()));
                polozenie.setSecond(generator.nextInt(this.rozmiar.getSecond()));
                typ = String.valueOf(symbole.charAt(i % 10));//aby na planszy byly wszystkie organizmy
            }
            this.dodaj_organizm(polozenie, typ);
        }
    }
    void wykonaj_ture()
    {
        Komparator porownaj = new Komparator();
        ruch.sort(porownaj);
        this.rozmiar_ruchu = ruch.size();
        for (iterator_organizmow = 0; iterator_organizmow < rozmiar_ruchu; iterator_organizmow++)
            this.ruch.elementAt(iterator_organizmow).akcja();
        for (iterator_organizmow = 0; iterator_organizmow < rozmiar_ruchu; iterator_organizmow++)
            this.ruch.elementAt(iterator_organizmow).postarz_organizm();
        this.rysuj_swiat();
    }
    public String nazwa_organizmu_na_planszy(Pair<Integer, Integer> wspolrzedne)
    {
        return plansza[wspolrzedne.getSecond()][wspolrzedne.getFirst()].get_nazwa();
    }
    public Color kolor_organizmu_na_planszy(Pair<Integer, Integer> wspolrzedne)
    {
        return plansza[wspolrzedne.getSecond()][wspolrzedne.getFirst()].get_kolor();
    }
    public void start()
    {
        this.dodaj_poczatkowe_organizmy();
        this.rysuj_swiat();
    }
    public void rysuj_swiat()
    {
        grafika.czysc_swiat();
        grafika.napraw_focus();
        int i,j;
        for(i=0;i<rozmiar.getSecond();i++)
            for(j=0;j<rozmiar.getFirst();j++)
                if(plansza[i][j]!=null)
                    grafika.wstaw_pole_na_planszy(new Pair(j,i));
        grafika.wyswietl_komunikaty();
       // komunikat.Wyczysc_komunikaty();
    }
    public void nowa_tura(KURSOR kursor)
    {
        if(czlowiek!=null)
        {
            czlowiek.set_kursor(kursor);
            wykonaj_ture();
        }
    }
    public void przekaz_powiadomienie(Pair<String, String>czlony, powiadomienia_obopulne wybor)
    {
        moje_powiadomienia.powiadomienia(czlony, wybor);
    }
    public void przekaz_powiadomienie(String czlon, powiadomienia_pojedyncze wybor)
    {
        moje_powiadomienia.powiadomienia(czlon, wybor);
    }
    public void przekaz_powiadomienie(String napis)
    {
        moje_powiadomienia.powiadomienia(napis);
    }
    public String get_nazwa_na_planszy(Pair<Integer, Integer> wspolrzedne)
    {
        return plansza[wspolrzedne.getSecond()][wspolrzedne.getFirst()].get_nazwa();
    }
    public void zapisz() throws FileNotFoundException
    {
        int i,j;
        PrintWriter out = new PrintWriter("opowiesc.txt");
        out.println(rozmiar.getFirst());
        out.println(rozmiar.getSecond());
        out.println(hexy);
        out.println(ruch.size());
        for (i = 0; i < this.rozmiar.getSecond(); i++)
        {
            for (j = 0; j < this.rozmiar.getFirst(); j++)
            {
                if (plansza[i][j] != null)
                {
                    out.println(j);out.println(i);
                    out.println(plansza[i][j].get_inicjatywa());
                    out.println(plansza[i][j].get_sila());
                    out.println(plansza[i][j].get_nazwa());
                    out.println(plansza[i][j].get_wiek());
                    if (plansza[i][j] instanceof Czlowiek)
                    {
                        out.println(czlowiek.get_czy_moc());
                        out.println(czlowiek.get_licznik());
                    }
                }
            }
        }
        out.close();
    }
    public void wczytaj() throws FileNotFoundException
    {
        int second,first,inicjatywa,sila,wiek, licznik,i;
        String nazwa;
        boolean czy_moc;
        File file=new File("opowiesc.txt");
        Scanner in = new Scanner(file);
        moje_powiadomienia.czysc();
        ruch.clear();
        int x=in.nextInt();
        int y=in.nextInt();
        boolean hex = in.nextBoolean();
        rozmiar.set(x,y);
        czlowiek=null;
        int ile_organizmow = in.nextInt();
        plansza = new Organizmy[this.rozmiar.getSecond()][];
        for (i = 0; i < this.rozmiar.getSecond(); i++)
            plansza[i] = new Organizmy[this.rozmiar.getFirst()];
        for (i = 0; i < this.rozmiar.getSecond(); i++)
            for (int j = 0; j < this.rozmiar.getFirst(); j++)
                plansza[i][j] = null;
        grafika.zmien_rozmiar(rozmiar, hexy,hex);
        hexy =hex;
        for(i=0;i<ile_organizmow;i++)
        {
            first=in.nextInt();
            second=in.nextInt();
            inicjatywa=in.nextInt();
            sila=in.nextInt();
            nazwa=in.next();
            wiek=in.nextInt();
            this.dodaj_organizm(new Pair(first,second),nazwa);
            plansza[second][first].set_inicjatywa(inicjatywa);
            plansza[second][first].set_polozenie(new Pair(first,second));
            plansza[second][first].set_sila(sila);
            plansza[second][first].set_nazwa(nazwa);
            plansza[second][first].set_wiek(wiek);
            if ("Człowiek".equals(nazwa))
            {
                czlowiek=(Czlowiek)plansza[second][first];
                czy_moc=in.nextBoolean();
                licznik=in.nextInt();
                czlowiek.set_czy_moc(czy_moc);
                czlowiek.set_licznik(licznik);
            }
        }


        in.close();
        this.rysuj_swiat();
    }
    public boolean get_hexy()
    {
        return this.hexy;
    }
}
