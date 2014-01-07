package monopoly.gui;

import monopoly.jeu.Monopoly;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.awt.Graphics;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Insets;



public class PanneauCentral extends JPanel
{
    private Monopoly            m;
    private Color               fond;
    
    private JLabel              joueurNom;
    private JLabel              joueurArgent;
    private JButton             des;
    
    
    
    public PanneauCentral(Monopoly m)
    {
        this.m          = m;
        
        fond = new Color(182, 211, 189);
        
        setBackground(fond);
        
        initComponents();
    }
    
    
    
    public void initComponents()
    {
        setLayout(new BorderLayout());
        
        JPanel left     = new JPanel();
        PannelNom pn    = new PannelNom(m);
        JScrollPane jsp = new JScrollPane(pn, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        left            .add(jsp);
        left            .setBackground(fond);
        jsp             .setPreferredSize(new Dimension(150, 100));
        jsp             .setBackground(fond);
        
        JPanel jpn              = new JPanel();
        GridBagConstraints gbc  = new GridBagConstraints(); 
        
        jpn             .setBackground(fond);
        jpn             .setLayout(new GridBagLayout());
        
        JLabel nom      = new JLabel("Joueur courant : ");
        joueurNom       = new JLabel(m.courant().nom());
        JLabel argent   = new JLabel("Argent restant : ");
        joueurArgent    = new JLabel(m.courant().especes() + "");
        des             = new JButton("Lancer les Dés");
        
        des             .addActionListener(new ButtonListener(m, this));
        
        nom             .setPreferredSize(new Dimension(150, 25));
        joueurNom       .setPreferredSize(new Dimension(150, 25));
        argent          .setPreferredSize(new Dimension(150, 25));
        joueurArgent    .setPreferredSize(new Dimension(150, 25));
        des             .setPreferredSize(new Dimension(150, 25));
        
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
        
        add(left, BorderLayout.WEST);
        add(jpn, BorderLayout.CENTER);
    }
    
    /**
     *  Desactive le bouton Des
     */
    public void griseDes()
    {
        des.setEnabled(false);
    }
    
    /**
     * Rafraichit l'ensemble du panneau
     */
    public void refresh() {
        joueurNom       .setText(m.courant().nom());
        joueurArgent    .setText(m.courant().especes() + "");
    }
}
