package monopoly.gui;

import monopoly.jeu.Monopoly;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class ButtonListener implements ActionListener
{
    private Monopoly m;
    private PanneauCentral pc;
    
    
    
    public ButtonListener(Monopoly m, PanneauCentral pc)
    {
        this.m  = m;
        this.pc = pc;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(m.courant().joue()) {
            m   .suivant();
            pc  .refresh();
            m   .courant().verifierAction();
        } else {
            m.finirPartie();
            pc.griseDes();
        }
    }
}
