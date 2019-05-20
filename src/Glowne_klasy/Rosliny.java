package Glowne_klasy;

import java.util.Random;

public abstract class Rosliny extends Organizmy
{
    protected int prawdopodobienstwo;
    @Override
    protected abstract boolean obrona(Organizmy agresor);
    protected Rosliny(Swiat swiat_ref, int x, int y)
    {
        super(swiat_ref,x,y);
        this.inicjatywa = 0;
    }
    @Override
    public void kolizja(Organizmy osobnik_obronny)
    {
        return;
    }
    @Override
    public void akcja()
    {
        if (this.mozliwosc_rozmnazania())
        {
            Random generator = new Random();
            Pair<Integer, Integer> przesuniecie = new Pair(0,0);
            Pair<Integer,Integer> nowa_lokalizacja = new Pair(this.polozenie.getFirst(),this.polozenie.getSecond());
            while (przesuniecie.getFirst() == 0 && przesuniecie.getSecond() == 0 || swiat_ref.puste_miejsce(nowa_lokalizacja)==false)
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
            swiat_ref.dodaj_organizm(nowa_lokalizacja, this.nazwa);
        }
    }

    protected boolean mozliwosc_rozmnazania()
    {
        Random generator=new Random();
        int szansa = generator.nextInt(100);
        boolean czy_jest_miejsce_na_nasiona = false;
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                if ((i!=0 || j!=0) && swiat_ref.puste_miejsce(new Pair(this.polozenie.getFirst()+i,this.polozenie.getSecond()+j)))
                    if(swiat_ref.get_hexy()==true&&(i!=1||j!=1)&&(i!=-1||j!=-1)||swiat_ref.get_hexy()==false)
                        czy_jest_miejsce_na_nasiona = true;

        if (szansa <this.prawdopodobienstwo && czy_jest_miejsce_na_nasiona)return true;
        return false;
    }
}
