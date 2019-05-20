package Zwierzyniec;

import Enumy.KURSOR;
import Glowne_klasy.Organizmy;
import Glowne_klasy.Swiat;
import Glowne_klasy.Zwierzeta;
import Glowne_klasy.Pair;

import java.awt.*;

import static Enumy.powiadomienia_pojedyncze.SPALENIE;
import static Enumy.KURSOR.*;

public class Czlowiek extends Zwierzeta
{
    private boolean czy_moc=false;
    private int licznik_mocy;
    private KURSOR kursor;
    public Czlowiek(Swiat swiat_ref, int x, int y)
    {
        super(swiat_ref,x,y);
        this.inicjatywa = 4;
        this.sila = 5;
        this.nazwa = "Człowiek";
        this.kursor = BRAK;
        this.czy_moc = false;//domyslnie moc wylaczona
        this.licznik_mocy = -1;//tylko jak jest -1 mozna ustawic moc specjalna
        this.kolor = Color.decode("#3366CC");//lapis-lazuli
    }
    @Override
    public void akcja()
    {
        if (kursor == DOL || kursor == GORA || kursor == LEWO || kursor == PRAWO
                || kursor == DOL_LEWO || kursor == DOL_PRAWO || kursor == GORA_LEWO || kursor == GORA_PRAWO )
        {
            Pair<Integer, Integer>przesuniecie = new Pair(0,0);
            Pair<Integer, Integer>nowa_lokalizacja = new Pair(0,0);
            if (kursor == GORA||kursor==GORA_LEWO)przesuniecie.set(0,-1);
            else if (kursor == DOL||kursor==DOL_PRAWO) przesuniecie.set(0,1);
            else if (kursor == LEWO) przesuniecie.set(-1,0);
            else if (kursor == PRAWO) przesuniecie.set(1,0);
            else if(kursor == GORA_PRAWO)przesuniecie.set(1,-1);
            else if(kursor == DOL_LEWO)przesuniecie.set(-1,1);
            nowa_lokalizacja.setFirst(przesuniecie.getFirst() + this.polozenie.getFirst());
            nowa_lokalizacja.setSecond(przesuniecie.getSecond()+ this.polozenie.getSecond());
            if(swiat_ref.poza_tablice(nowa_lokalizacja)==false)swiat_ref.wykonaj_ruch(this.polozenie, nowa_lokalizacja);
        }
        if (kursor == MOC && this.licznik_mocy == -1)//uruchomienie mocy specjalnej
        {
            this.licznik_mocy = 0;
            czy_moc = true;
            this.swiat_ref.przekaz_powiadomienie("Aktywowano moc specjalna");
            this.moc_specjalna();
            return;
        }
        if (this.licznik_mocy == 5)//jezeli minelo 5 tur to moc juz nie jest aktywna
        {
            this.czy_moc = false;
            this.swiat_ref.przekaz_powiadomienie("Moc specjalna uległa dezaktywacji");
        }
        if (this.licznik_mocy >= 0 && licznik_mocy < 5 && czy_moc == true)//aktywna moc
        {
            this.licznik_mocy++;
            this.moc_specjalna();
        }
        if (this.czy_moc == false && this.licznik_mocy > 0)this.licznik_mocy--;//5 tur przerwy pomiedzy wywolaniami mocy
        if (this.czy_moc == false && this.licznik_mocy == 0)this.licznik_mocy = -1;//ponowna mozliwosc wlaczenia mocy
    }

    @Override
    protected boolean obrona(Organizmy agresor)//domyslnie czlowiek sie nie broni - moc specjalna zaimplementowana w akcji
    {
        return false;
    }


    private void moc_specjalna()
    {
        int i, j;
        for (i = -1; i < 2; i++)
        {
            for (j = -1; j < 2; j++)
            {
                if ((i != 0 || j != 0) && swiat_ref.puste_miejsce(new Pair( this.polozenie.getFirst() + i, this.polozenie.getSecond() + j )) == false)
                {
                    if(swiat_ref.get_hexy()==true&&(i!=1||j!=1)&&(i!=-1||j!=-1)||swiat_ref.get_hexy()==false)
                    {
                        if (swiat_ref.poza_tablice(new Pair( this.polozenie.getFirst() + i, this.polozenie.getSecond() + j )) == false)
                        {
                            String czlon = swiat_ref.get_nazwa_na_planszy(new Pair( this.polozenie.getFirst() + i, this.polozenie.getSecond() + j ));
                            swiat_ref.przekaz_powiadomienie(czlon, SPALENIE);
                            swiat_ref.usun_organizm(new Pair( this.polozenie.getFirst() + i, this.polozenie.getSecond() + j ));
                        }
                    }
                }
            }
        }
    }

    public void set_kursor(KURSOR kursor)
    {
        this.kursor=kursor;
    }

    public boolean get_czy_moc()
    {
        if (this == null)return false;
        return this.czy_moc;
    }

    public int get_licznik()
    {
        if (this == null)return 0;
        return this.licznik_mocy;
    }

    public void set_czy_moc(boolean czy_moc)
    {
        this.czy_moc = czy_moc;
    }
    public void set_licznik(int licznik)
    {
        this.licznik_mocy = licznik;
    }
}
