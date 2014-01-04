package monopoly.gui;

import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JButton;

import monopoly.jeu.Monopoly;
import monopoly.jeu.Case;



public class PlateauGraphique extends JFrame
{
    private Monopoly m;
    
    private int largeur, hauteur;
    
    
    
    public PlateauGraphique(Monopoly m)
    {
        this.m      = m;
        
        largeur     = 800;
        hauteur     = 800;
        
        initialiser();
        
        addComponents(getContentPane());
        
        pack();
        setVisible(true);
    }
    
    
    
    public void initialiser()
    {
        setTitle("Monopoly !!");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(182, 211, 189));
    }
    
    /**
     * Crée les cases une à une
     */
    public void addComponents(Container p)
    {
        Case curr = m.getDepart();
        String groupe;
        
        p.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        int lc  = largeur / 8;
        int hc  = hauteur / 8;
        
        c.gridy = 10;
        c.gridx = 10;
        CaseGraphique coin = new CaseGraphique(lc, hc, curr, 0);
        coin.setLast();
        p.add(coin, c);
        curr    = curr.suivante();
        
        lc = largeur / 11;
        for (int i = 9; i > 0; i--) {
            c.gridx = i;
            p.add(new CaseGraphique(lc, hc, curr, 0), c);
            curr    = curr.suivante();
        }
        
        lc      = largeur / 8;
        c.gridy = 10;
        c.gridx = 0;
        coin    = new CaseGraphique(lc, hc, curr, 90);
        coin.setLast();
        p.add(coin, c);
        curr    = curr.suivante();
        
        lc      = largeur / 11;
        for (int i = 9; i>0; i--) {
            c.gridy = i;
            p.add(new CaseGraphique(lc, hc, curr, 90), c);
            curr = curr.suivante();
        }
        
        lc      = largeur / 8;
        c.gridy = 0;
        c.gridx = 0;
        coin    = new CaseGraphique(lc, hc, curr, 180);
        coin.setLast();
        p.add(coin, c);
        curr    = curr.suivante();
        
        lc      = largeur / 11;
        for (int i = 1; i < 10; i++) {
            c.gridx = i;
            p.add(new CaseGraphique(lc, hc, curr, 180), c);
            curr    = curr.suivante();
        }
        
        lc      = largeur / 8;
        c.gridy = 0;
        c.gridx = 10;
        coin    = new CaseGraphique(lc, hc, curr, -90);
        coin.setLast();
        p.add(coin, c);
        curr    = curr.suivante();
        
        lc      = largeur / 11;
        for (int i = 1; i< 10; i++) {
            c.gridy = i;
            p.add(new CaseGraphique(lc, hc, curr, -90), c);
            curr    = curr.suivante();
        }
    }
}
