package monopoly.gui;

import monopoly.jeu.Monopoly;
import monopoly.jeu.Joueur;
import monopoly.jeu.JoueurDefaut;
import monopoly.jeu.Case;

import java.util.Observer;
import java.util.Observable;
import java.util.ArrayList;

import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;



public class PlateauGraphique extends JFrame implements Observer
{
    private Monopoly m;
    private ArrayList<CaseGraphique> cases;
    
    private int largeur, hauteur;
    
    private PanneauCentral pc;
    
    
    
    public PlateauGraphique(Monopoly m)
    {
        this.m      = m;
        cases       = new ArrayList<CaseGraphique>();
        
        largeur     = 650;
        hauteur     = 650;
        
        initialiser();
        
        addComponents(getContentPane());
        
        pack();
        setVisible(true);
        
        for( Joueur j : m.getJoueurs() ) {
            ((JoueurDefaut)j).addObserver(this);
        }
    }
    
    
    
    public void initialiser()
    {
        setTitle("MONOPOLY");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(182, 211, 189));
    }
    
    public int largeur()
    {
        return largeur;
    }
    
    public int hauteur()
    {
        return hauteur;
    }
    
    public ArrayList<CaseGraphique> cases()
    {
        return cases;
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
        CaseGraphique caseG = new CaseGraphique(lc, hc, curr, 0, m);
        caseG.setLast();
        p.add(caseG, c);
        curr    = curr.suivante();
        cases.add(caseG.clone());
        
        lc = largeur / 11;
        for (int i = 9; i > 0; i--) {
            c.gridx = i;
            caseG = new CaseGraphique(lc, hc, curr, 0, m);
            p.add(caseG, c);
            curr    = curr.suivante();
            cases.add(caseG.clone());
        }
        
        lc      = largeur / 8;
        c.gridy = 10;
        c.gridx = 0;
        caseG    = new CaseGraphique(lc, hc, curr, 90, m);
        caseG.setLast();
        p.add(caseG, c);
        curr    = curr.suivante();
        cases.add(caseG.clone());
        
        lc      = largeur / 11;
        for (int i = 9; i>0; i--) {
            c.gridy = i;
            caseG = new CaseGraphique(lc, hc, curr, 90, m);
            p.add(caseG, c);
            curr = curr.suivante();
            cases.add(caseG.clone());
        }
        
        lc      = largeur / 8;
        c.gridy = 0;
        c.gridx = 0;
        caseG    = new CaseGraphique(lc, hc, curr, 180, m);
        caseG.setLast();
        p.add(caseG, c);
        curr    = curr.suivante();
        cases.add(caseG.clone());
        
        lc      = largeur / 11;
        for (int i = 1; i < 10; i++) {
            c.gridx = i;
            caseG = new CaseGraphique(lc, hc, curr, 180, m);
            p.add(caseG, c);
            curr    = curr.suivante();
            cases.add(caseG.clone());
        }
        
        lc      = largeur / 8;
        c.gridy = 0;
        c.gridx = 10;
        caseG    = new CaseGraphique(lc, hc, curr, -90, m);
        caseG.setLast();
        p.add(caseG, c);
        curr    = curr.suivante();
        cases.add(caseG.clone());
        
        lc      = largeur / 11;
        for (int i = 1; i< 10; i++) {
            c.gridy = i;
            caseG = new CaseGraphique(lc, hc, curr, -90, m);
            p.add(caseG, c);
            curr    = curr.suivante();
            cases.add(caseG.clone());
        }
        
        pc = new PanneauCentral(largeur * 9 / 11, hauteur * 9 / 11, m);
        pc.setPreferredSize(new Dimension(largeur * 9 / 11, hauteur * 9 / 11));
        pc.setMaximumSize(new Dimension(largeur * 9 / 11, hauteur * 9 / 11));
        c.gridy         = 1;
        c.gridx         = 1;
        c.fill          = GridBagConstraints.BOTH;
        c.gridwidth     = 9;
        c.gridheight    = 9;
        p.add(pc, c);
    }
    
    public void update(Observable o, Object arg)
    {
        if(arg == null) {
            repaint();
        } else {
            pc.setAction((String)arg);
        }
    }
}
