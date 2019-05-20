package Grafika;

import Enumy.KURSOR;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

public class Klawiatura_hexy implements KeyListener
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

            case KeyEvent.VK_W:
                zrodlo.idz_do_swiata().nowa_tura(KURSOR.GORA_LEWO);
                break;

            case KeyEvent.VK_X:
                zrodlo.idz_do_swiata().nowa_tura(KURSOR.DOL_PRAWO);
                break;

            case KeyEvent.VK_A:
                zrodlo.idz_do_swiata().nowa_tura(KURSOR.LEWO);
                break;

            case KeyEvent.VK_D:
                zrodlo.idz_do_swiata().nowa_tura(KURSOR.PRAWO);
                break;

            case KeyEvent.VK_E:
                zrodlo.idz_do_swiata().nowa_tura(KURSOR.GORA_PRAWO);
                break;

            case KeyEvent.VK_Z:
                zrodlo.idz_do_swiata().nowa_tura(KURSOR.DOL_LEWO);
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