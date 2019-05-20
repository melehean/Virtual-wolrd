package Glowne_klasy;

import java.util.Random;

import static Enumy.powiadomienia_obopulne.WALKA;
import static Enumy.powiadomienia_pojedyncze.SMIERC;

public abstract class Zwierzeta extends Organizmy
{
    @Override
    protected abstract boolean obrona(Organizmy agresor);
    protected Zwierzeta(Swiat swiat_ref, int x, int y)
    {
        super(swiat_ref, x,y);
    }
    private void rozmnazanie(Pair<Integer,Integer> wspolrzedne)
    {
        Random generator = new Random();
        int i,j;
        boolean czy_jest_miejsce_atak=false;
        boolean czy_jest_miejsce_obrona=false;
        Pair<Integer,Integer> przesuniecie;
        Pair<Integer,Integer> nowa_lokalizacja;
        Pair<Integer,Integer> miejsce = new Pair(0,0);
        for (i = -1; i < 2; i++)
        {
            for (j = -1; j < 2; j++)
            {
                    if ((i != 0 || j != 0) && swiat_ref.puste_miejsce(new Pair( wspolrzedne.getFirst() + i,wspolrzedne.getSecond() + j ))==true)
                        if(swiat_ref.get_hexy()==true&&(i!=1||j!=1)&&(i!=-1||j!=-1)||swiat_ref.get_hexy()==false)czy_jest_miejsce_obrona = true;
                    if ((i != 0 || j != 0) && swiat_ref.puste_miejsce(new Pair( this.polozenie.getFirst() + i,this.polozenie.getSecond() + j ))==true)
                        if(swiat_ref.get_hexy()==true&&(i!=1||j!=1)&&(i!=-1||j!=-1)||swiat_ref.get_hexy()==false)czy_jest_miejsce_atak = true;
            }
        }
        if (czy_jest_miejsce_atak == true || czy_jest_miejsce_obrona ==true)
        {
            przesuniecie = new Pair<>(0,0);
            nowa_lokalizacja = new Pair(this.polozenie.getFirst(),this.polozenie.getSecond());
            if (czy_jest_miejsce_obrona==true)
            {
                miejsce.set(wspolrzedne.getFirst(),wspolrzedne.getSecond());
            }
            else if(czy_jest_miejsce_atak == true)
            {
                miejsce.set(this.polozenie.getFirst(), this.polozenie.getSecond());
            }
            while ((przesuniecie.getFirst() == 0 && przesuniecie.getSecond() == 0) ||
                    (!swiat_ref.puste_miejsce(nowa_lokalizacja)))
            {
                przesuniecie.setFirst(generator.nextInt(3)-1);
                przesuniecie.setSecond(generator.nextInt(3)-1);
                if(swiat_ref.get_hexy()==true
                        &&(przesuniecie.getFirst()!=1||przesuniecie.getSecond()!=1)
                        &&(przesuniecie.getFirst()!=-1||przesuniecie.getSecond()!=-1)
                        ||swiat_ref.get_hexy()==false)
                {
                    nowa_lokalizacja.setFirst(miejsce.getFirst() + przesuniecie.getFirst());
                    nowa_lokalizacja.setSecond(miejsce.getSecond() + przesuniecie.getSecond());
                }
            }
        swiat_ref.dodaj_organizm(nowa_lokalizacja,this.nazwa);
    }
}

    @Override
    public void akcja()
    {
        Random generator = new Random();
        Pair<Integer, Integer> przesuniecie = new Pair(0,0);
        Pair<Integer,Integer> nowa_lokalizacja = new Pair(this.polozenie.getFirst(),this.polozenie.getSecond());
        while (przesuniecie.getFirst() == 0 && przesuniecie.getSecond() == 0 || swiat_ref.poza_tablice(nowa_lokalizacja))
        {
            przesuniecie.setFirst(generator.nextInt(3)-1);
            przesuniecie.setSecond(generator.nextInt(3)-1);
            if(swiat_ref.get_hexy()==true
                    &&(przesuniecie.getFirst()!=1||przesuniecie.getSecond()!=1)
                    &&(przesuniecie.getFirst()!=-1||przesuniecie.getSecond()!=-1)
                    ||swiat_ref.get_hexy()==false)
            {
                nowa_lokalizacja.setFirst(przesuniecie.getFirst() + this.polozenie.getFirst());
                nowa_lokalizacja.setSecond(przesuniecie.getSecond() + this.polozenie.getSecond());
            }
        }
        swiat_ref.wykonaj_ruch(this.polozenie, nowa_lokalizacja);
}

    @Override
    public void kolizja(Organizmy osobnik_obronny)
    {
    if (this.getClass() == osobnik_obronny.getClass())
    {
        this.rozmnazanie(osobnik_obronny.get_polozenie());
    }
	else
    {
        if (osobnik_obronny.obrona(this) == true)//obroni sie
        {
            return;
        }
        else //nie ma wstepnej obrony
        {
            Pair<String, String>czlony = new Pair(this.nazwa, osobnik_obronny.get_nazwa());
            swiat_ref.przekaz_powiadomienie(czlony, WALKA);
            if (this.sila >= osobnik_obronny.get_sila())
            {
                swiat_ref.przekaz_powiadomienie(osobnik_obronny.get_nazwa(), SMIERC);
                Pair<Integer, Integer>pom = osobnik_obronny.get_polozenie();
                swiat_ref.usun_organizm(osobnik_obronny);
                swiat_ref.zmien_na_planszy(this.polozenie, pom);
            }
			else
            {
                swiat_ref.przekaz_powiadomienie(this.nazwa, SMIERC);
                swiat_ref.usun_organizm(this);
            }
        }
    }
}


}
