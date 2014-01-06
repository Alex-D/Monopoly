package monopoly.gui;

import monopoly.jeu.Monopoly;
import monopoly.jeu.Joueur;
import monopoly.jeu.JoueurDefaut;
import monopoly.jeu.Case;

import monopoly.evenements.*;

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
import javax.swing.JOptionPane;

import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;



public class PlateauGraphique extends JFrame implements Observer, ComponentListener
{
    private Monopoly m;
    private ArrayList<CaseGraphique> cases;
    
    private int largeur, hauteur;
    
    private PanneauCentral pc;
    
    
    
    public PlateauGraphique(int largeur, int hauteur, Monopoly m)
    {
        
        this.m      = m;
        cases       = new ArrayList<CaseGraphique>();
        
        this.largeur    = largeur;
        this.hauteur    = hauteur;
        
        initialiser();
        
        addComponents(getContentPane());
        
        setVisible(true);
        
        for (Joueur j : m.getJoueurs()) {
            ((JoueurDefaut) j).addObserver(this);
        }
        
        pack();
        System.out.println("Panneau : " + getWidth() + "x" + getHeight());
        System.out.println("Cases : " + cases.get(0).getWidth() + "x" + cases.get(0).getHeight());
        System.out.println("Central : " + pc.getWidth() + "x" + pc.getHeight());
        
        //setSize(largeur, hauteur);
        //addComponentListener(this);
    }
    
    
    
    public void initialiser()
    {
        setTitle("MONOPOLY");
        //setResizable(false);
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
        caseG.setPreferredSize(new Dimension(lc, hc));
        caseG.setLast();
        p.add(caseG, c);
        curr    = curr.suivante();
        cases.add(caseG.clone());
        
        lc = largeur / 12;
        for (int i = 9; i > 0; i--) {
            c.gridx = i;
            caseG = new CaseGraphique(lc, hc, curr, 0, m);
            caseG.setPreferredSize(new Dimension(lc, hc));
            p.add(caseG, c);
            curr    = curr.suivante();
            cases.add(caseG.clone());
        }
        
        lc      = largeur / 8;
        c.gridy = 10;
        c.gridx = 0;
        caseG    = new CaseGraphique(lc, hc, curr, 90, m);
        caseG.setPreferredSize(new Dimension(lc, hc));
        caseG.setLast();
        p.add(caseG, c);
        curr    = curr.suivante();
        cases.add(caseG.clone());
        
        lc      = largeur / 12;
        for (int i = 9; i>0; i--) {
            c.gridy = i;
            caseG = new CaseGraphique(lc, hc, curr, 90, m);
            caseG.setPreferredSize(new Dimension(hc, lc));
            p.add(caseG, c);
            curr = curr.suivante();
            cases.add(caseG.clone());
        }
        
        lc      = largeur / 8;
        c.gridy = 0;
        c.gridx = 0;
        caseG    = new CaseGraphique(lc, hc, curr, 180, m);
        caseG.setPreferredSize(new Dimension(lc, hc));
        caseG.setLast();
        p.add(caseG, c);
        curr    = curr.suivante();
        cases.add(caseG.clone());
        
        lc      = largeur / 12;
        for (int i = 1; i < 10; i++) {
            c.gridx = i;
            caseG = new CaseGraphique(lc, hc, curr, 180, m);
            caseG.setPreferredSize(new Dimension(lc, hc));
            p.add(caseG, c);
            curr    = curr.suivante();
            cases.add(caseG.clone());
        }
        
        lc      = largeur / 8;
        c.gridy = 0;
        c.gridx = 10;
        caseG    = new CaseGraphique(lc, hc, curr, -90, m);
        caseG.setPreferredSize(new Dimension(lc, hc));
        caseG.setLast();
        p.add(caseG, c);
        curr    = curr.suivante();
        cases.add(caseG.clone());
        
        lc      = largeur / 12;
        for (int i = 1; i< 10; i++) {
            c.gridy = i;
            caseG = new CaseGraphique(lc, hc, curr, -90, m);
            caseG.setPreferredSize(new Dimension(hc, lc));
            p.add(caseG, c);
            curr    = curr.suivante();
            cases.add(caseG.clone());
        }
        
        pc = new PanneauCentral(m);
        pc.setPreferredSize(new Dimension(largeur * 9 / 12, hauteur * 9 / 12));
        c.gridy         = 1;
        c.gridx         = 1;
        c.fill          = GridBagConstraints.BOTH;
        c.gridwidth     = 9;
        c.gridheight    = 9;
        p.add(pc, c);
    }
    
    public static Evenement faireChoix(Evenement a, Evenement b)
    {
        Object[] options = new Object[2];
        String  ask,
                titre;
        
        if( b==null ) {
            options[0]  =   "Oui";
            options[1]  =   "Non" ;
            ask         =   "Souhaitez vous " + a + " ?";
            titre       =   "Mise en Vente";
        } else {
            options[0]  =   "Le premier";
            options[1]  =   "Le second";
            ask         =   "Que choisissez vous entre : \n\t- " + a +
                            "Et : \n\t- " + b +
                            "\n?";
            titre       =   "Faire un choix";
        }
        int choix = JOptionPane.showOptionDialog(null, ask, titre,
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);
        if( choix==0 )
            return a;
        return b;
    }
    
    /**
     * Méthode permettant de redimensionner tout le plateau
     */
    public void redimensionner(int largeur, int hauteur)
    {
        this.largeur    = largeur;
        this.hauteur    = hauteur;
        
        for(CaseGraphique cg : cases) {
            largeur = this.largeur;
            if( cg.last() )
                largeur = largeur/8;
            else
                largeur = largeur/12;
            System.out.println(largeur);
            cg.resize(largeur, hauteur/8);
            cg.setPreferredSize(new Dimension(largeur, hauteur/8));
        }
        largeur = this.largeur;
        
        pc.setPreferredSize(new Dimension(largeur * 9 / 12, hauteur * 9 / 12));
        
        pack();
        setVisible(true);
    }
    
    public void update(Observable o, Object arg)
    {
        repaint();

        if (arg == null)
            return;

        if (arg.equals("argent"))
            pc.refresh();
        else {
            if (arg instanceof TirerDes) {
                TirerDes e = (TirerDes) arg;
                JOptionPane.showMessageDialog(null, "Vous avez fait : " + e.tir1() + ".");
                if(e.doubletir() != 0)
                    JOptionPane.showMessageDialog(
                        null,
                        "Double ! Vous allez aussi avancer de : " + e.doubletir() + " cases."
                    );
            }
            else if (arg instanceof TirerCarte)
                JOptionPane.showMessageDialog(null, ((TirerCarte) arg).carte().nom());
            else if (arg instanceof Emprisonner)
                JOptionPane.showMessageDialog(null, "Allez en prison !");
            else if (arg instanceof PayerImpot)
                JOptionPane.showMessageDialog(null, ((PayerImpot)arg).nom());
            else if (!(arg instanceof DeplacementRelatif ||
                       arg instanceof Deplacement        ||
                       arg instanceof Achat              ||
                       arg instanceof Choix              ||
                       arg instanceof Depenser))
                pc.addAction(arg.toString());
        }
    }
    
    public void componentHidden(ComponentEvent e) {}

    public void componentMoved(ComponentEvent e) {}

    public void componentShown(ComponentEvent e) {}

    public void componentResized(ComponentEvent e) {
        redimensionner(getWidth(), getHeight());
    }
}
