package monopoly.gui;

import monopoly.jeu.Monopoly;
import monopoly.jeu.Joueur;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;



public class PannelNom extends JPanel
{
    private Monopoly m;
    
    private final Font font = new Font("Arial", Font.PLAIN, 10);
    
    
    public PannelNom(Monopoly m)
    {
        this.m  = m;
        
        setPreferredSize(new Dimension(150, 400));
    }
    
    
    
    public void paintComponent(Graphics g)
    {
        int decalage = 20;
        for(Joueur j : m.getJoueurs()) {
            g.setColor(Color.black);
            g.drawString(j.nom(), 10, decalage);
            
            g.setColor(j.couleur().darker());
            g.fillRect( getFontMetrics(font).stringWidth(j.nom()) + 27, 
                        decalage - 12, 
                        60, 
                        15
            );
            g.setColor(j.couleur());
            g.fillRect( getFontMetrics(font).stringWidth(j.nom()) + 30, 
                        decalage - 9, 
                        54, 
                        9
            );
            
            decalage += 20;
        }
    }
}
