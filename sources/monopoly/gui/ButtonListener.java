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
        if(e.getActionCommand().equals("Lancer les Dés")) {
            m   .courant().joue();
            pc  .activeFinir();
            pc  .griseDes();
        } else {
            m   .suivant();
            pc  .refresh();
            pc.resetAction();
            pc  .activeDes();
            pc  .griseFinir();
        }
    }
}
