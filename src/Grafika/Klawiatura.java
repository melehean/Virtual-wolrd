package Grafika;

import Enumy.KURSOR;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

public class Klawiatura implements KeyListener
{


    @Override
    public void keyPressed(KeyEvent e)
    {
        Glowne_okno zrodlo=(Glowne_okno) e.getSource();
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_ESCAPE:
                zrodlo.dispose();
                return;

            case KeyEvent.VK_UP:
                zrodlo.idz_do_swiata().nowa_tura(KURSOR.GORA);
                break;

            case KeyEvent.VK_DOWN:
                zrodlo.idz_do_swiata().nowa_tura(KURSOR.DOL);
                break;

            case KeyEvent.VK_LEFT:
                zrodlo.idz_do_swiata().nowa_tura(KURSOR.LEWO);
                break;

            case KeyEvent.VK_RIGHT:
                zrodlo.idz_do_swiata().nowa_tura(KURSOR.PRAWO);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
    @Override
    public void keyTyped(KeyEvent e)
    {

    }
}
