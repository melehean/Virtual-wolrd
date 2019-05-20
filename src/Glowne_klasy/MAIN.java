package Glowne_klasy;

import Grafika.Menu;

import java.awt.*;

public class MAIN
{
    public static void main(String argv[])
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                Grafika.Menu moje_menu = new Menu();
                moje_menu.pokaz_menu();

            }
        });
    }
}
