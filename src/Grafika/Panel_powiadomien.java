package Grafika;

import Enumy.*;
import Glowne_klasy.*;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

import static Enumy.przypadek.BIERNIK;
import static Enumy.przypadek.NARZEDNIK;
import static Enumy.rodzaj.MESKI;
import static Enumy.rodzaj.ZENSKI;

public class Panel_powiadomien extends JPanel
{
    private Queue<JPanel>kolejka;
    private JLabel nazwa;
    private JPanel pom;
    private GridBagConstraints opis;
    public Panel_powiadomien()
    {
        kolejka = new LinkedList<>();
    }
    public void wyswietl_komunikaty()
    {
        this.removeAll();
        this.revalidate();
        this.repaint();
        setLayout(new GridLayout(kolejka.size(),1,1,1));
        while(kolejka.isEmpty()==false)
        {
            this.add(kolejka.peek());
            kolejka.remove();
        }
    }
    public void powiadomienia(Pair<String, String> czlony, powiadomienia_obopulne wybor)
    {
        String komunikat = new String("");
        komunikat += czlony.getFirst();
        switch (wybor)
        {
            case WALKA:
                komunikat += " walczy z ";
                komunikat+=odmien(NARZEDNIK, czlony.getSecond());
                break;
            case OPOR:
                komunikat += " odparł ";
                komunikat+=odmien(BIERNIK, czlony.getSecond());
                break;
            case WZMOCNIENIE:
                komunikat += " wzmocniła ";
                komunikat+=odmien(BIERNIK, czlony.getSecond());
                break;
            case ZATRUCIE:
                komunikat += " zatruła ";
                komunikat+=odmien(BIERNIK, czlony.getSecond());
                break;
            case POPARZENIE:
                komunikat += " poparzył ";
                komunikat+=odmien(BIERNIK, czlony.getSecond());
                break;
            case UCIECZKA:
                komunikat += " uciekła przed ";
                komunikat+=odmien(NARZEDNIK, czlony.getSecond());
                break;
        }
        dodaj_do_kolejki(komunikat);
    }
    public void powiadomienia(String czlon, powiadomienia_pojedyncze wybor)
    {
        String komunikat = new String("");
        switch (wybor)
        {
            case SMIERC:
                if (spr_rodzaj(czlon) == ZENSKI)komunikat += "Umarła ";
                else komunikat += "Umarł ";
                break;
            case NARODZINY:
                if (spr_rodzaj(czlon) == ZENSKI)komunikat += "Urodziła sie ";
                else komunikat += "Urodził się ";
                break;
            case SPALENIE:
                if (spr_rodzaj(czlon) == ZENSKI)komunikat += "Spłonęła ";
                else komunikat += "Spłonął ";
                break;
        }
        komunikat += czlon;
        dodaj_do_kolejki(komunikat);
    }
    public void powiadomienia(String komunikat)
    {
        dodaj_do_kolejki(komunikat);
    }
    rodzaj spr_rodzaj(String symbol)
    {
        switch (symbol)
        {
            case "Człowiek":
                return MESKI;
            case "Antylopa":
                return ZENSKI;
            case "Barszcz":
                return MESKI;
            case "Guarana":
                return ZENSKI;
            case "Wilk":
                return MESKI;
            case "Lis":
                return MESKI;
            case "Trawa":
                return ZENSKI;
            case "Mlecz":
                return MESKI;
            case "Owca":
                return ZENSKI;
            case "Jagoda":
                return ZENSKI;
            case "Zolw":
                return MESKI;
        }
        return ZENSKI;
    }
    String odmien(przypadek gramatyczny, String nazwa)
    {
        switch (nazwa)
        {
            case "Człowiek":
                if(gramatyczny==BIERNIK)return "Człowieka";
                if(gramatyczny==NARZEDNIK)return "Człowiekiem";
            case "Antylopa":
                if(gramatyczny==BIERNIK)return "Antylopę";
                if(gramatyczny==NARZEDNIK)return "Antylopą";
            case "Barszcz":
                if(gramatyczny==BIERNIK)return "Barszczu";
                if(gramatyczny==NARZEDNIK)return "Barszczem";
            case "Guarana":
                if(gramatyczny==BIERNIK)return "Guarany";
                if(gramatyczny==NARZEDNIK)return "Guaraną";
            case "Wilk":
                if(gramatyczny==BIERNIK)return "Wilka";
                if(gramatyczny==NARZEDNIK)return "Wilkiem";
            case "Lis":
                if(gramatyczny==BIERNIK)return "Lisa";
                if(gramatyczny==NARZEDNIK)return "Lisem";
            case "Trawa":
                if(gramatyczny==BIERNIK)return "Trawę";
                if(gramatyczny==NARZEDNIK)return "Trawą";
            case "Mlecz":
                if(gramatyczny==BIERNIK)return "Mlecz";
                if(gramatyczny==NARZEDNIK)return "Mleczem";
            case "Owca":
                if(gramatyczny==BIERNIK)return "Owcę";
                if(gramatyczny==NARZEDNIK)return "Owcą";
            case "Jagoda":
                if(gramatyczny==BIERNIK)return "Jagodę";
                if(gramatyczny==NARZEDNIK)return "Jagodą";
            case "Zolw":
                if(gramatyczny==BIERNIK)return "Żółwia";
                if(gramatyczny==NARZEDNIK)return "Żółwiem";
        }
        return "";
    }
    private void dodaj_do_kolejki(String komunikat)
    {
        pom = new JPanel();
        pom.setLayout(new GridBagLayout());
        pom.setBackground(Color.decode("#596275"));
        pom.setPreferredSize(new Dimension(350,50));
        opis = new GridBagConstraints();
        nazwa = new JLabel(komunikat);
        nazwa.setFont(new Font("Arial", Font.PLAIN, 20));
        pom.add(nazwa,opis);
        this.kolejka.add(pom);
    }
    public void czysc()
    {
        while(kolejka.isEmpty()==false) kolejka.remove();
    }
}
