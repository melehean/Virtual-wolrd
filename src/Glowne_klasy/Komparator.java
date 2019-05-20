package Glowne_klasy;

import java.util.Comparator;

public class Komparator implements Comparator<Organizmy>
{
    @Override public int compare(Organizmy a, Organizmy b)
    {
        if (a.get_inicjatywa() == b.get_inicjatywa())
        {
            if(a.get_wiek() > b.get_wiek())return -1;
            else if(a.get_wiek() < b.get_wiek())return 1;
            else return 0;
        }
        else if(a.get_inicjatywa() > b.get_inicjatywa())return -1;
        else return 1;
    }
}
