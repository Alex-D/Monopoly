package monopoly.gui;

import monopoly.jeu.Monopoly;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class DesListener implements ActionListener
{
    private Monopoly m;
    private PanneauCentral pc;
    
    
    
    public DesListener(Monopoly m, PanneauCentral pc)
    {
        this.m  = m;
        this.pc = pc;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        m   .courant().joue();
        m   .suivant();
        pc  .refresh();
    }
}
