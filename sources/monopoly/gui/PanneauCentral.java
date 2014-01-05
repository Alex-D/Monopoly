package monopoly.gui;

import monopoly.jeu.Monopoly;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.awt.Graphics;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Insets;



public class PanneauCentral extends JPanel
{
    private Monopoly    m;
    private int         largeur,
                        hauteur;
    private Color       fond;
    
    private JLabel      joueurNom;
    private JLabel      joueurArgent;
    private JTextArea   action;
    
    
    
    public PanneauCentral(int largeur, int hauteur, Monopoly m)
    {
        this.largeur    = largeur;
        this.hauteur    = hauteur;
        this.m          = m;
        
        fond = new Color(182, 211, 189);
        
        setBackground(fond);
        
        initComponents();
    }
    
    
    
    public void setAction(String s)
    {
        action.setText(s);
    }
    
    public void initComponents()
    {
        setLayout(new BorderLayout());
        PannelNom pn    = new PannelNom(m);
        
        JPanel jpn              = new JPanel();
        GridBagConstraints gbc  = new GridBagConstraints(); 
        
        jpn             .setBackground(fond);
        jpn             .setLayout(new GridBagLayout());
        
        JLabel nom          = new JLabel("Joueur courant : ");
        joueurNom    = new JLabel(m.courant().nom());
        JLabel argent       = new JLabel("Argent restant : ");
        joueurArgent = new JLabel(m.courant().especes() + "");
        JButton des         = new JButton("Lancer les Dés");
        action              = new JTextArea();
        
        des                 .addActionListener(new DesListener(m, this){
            
        });
        
        action          .setEditable(false);
        
        nom             .setPreferredSize(new Dimension(150, 25));
        joueurNom       .setPreferredSize(new Dimension(150, 25));
        argent          .setPreferredSize(new Dimension(150, 25));
        joueurArgent    .setPreferredSize(new Dimension(150, 25));
        des             .setPreferredSize(new Dimension(150, 25));
        action          .setPreferredSize(new Dimension(350, 100));
        
        gbc.gridy       = 0;
        gbc.gridx       = 0;
        jpn             .add(nom, gbc);
        
        gbc.gridx       = 1;
        jpn             .add(joueurNom, gbc);
        
        gbc.gridy       = 1;
        gbc.gridx       = 0;
        jpn             .add(argent, gbc);
        
        gbc.gridx       = 1;
        jpn             .add(joueurArgent, gbc);
        
        gbc.gridy       = 2;
        gbc.gridx       = 0;
        jpn             .add(des, gbc);
        
        gbc.gridy       = 3;
        gbc.gridx       = 0;
        gbc.gridwidth   = 2;
        gbc.insets      = new Insets(10, 0, 0, 0);
        jpn             .add(action, gbc);
        
        add(pn, BorderLayout.WEST);
        add(jpn, BorderLayout.CENTER);
    }
    
    public void refresh() {
        joueurNom       .setText(m.courant().nom());
        joueurArgent    .setText(m.courant().especes() + "");
    }
}
