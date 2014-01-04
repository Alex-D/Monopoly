package monopoly.gui;

import monopoly.jeu.Case;

import java.util.HashMap;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;



public class CaseGraphique extends JPanel
{
    private int largeur, hauteur, rotation;
    private Color groupe;
    private boolean last;
    private HashMap<String, Color> groupeCouleurs;
    
    
    
    public CaseGraphique(int largeur, int hauteur, Case c, int rotation)
    {
        creerCouleur();
        
        this.largeur    = largeur;
        this.hauteur    = hauteur;
        this.rotation   = rotation;
        this.last       = false;
        
        if(rotation==90 || rotation==-90)
            setPreferredSize(new Dimension(hauteur, largeur));
        else
            setPreferredSize(new Dimension(largeur, hauteur));
        
        try {
            groupe = groupeCouleurs.get(c.propriete().groupe().nom());
        } catch( NullPointerException e ) {
            groupe = null;
        }
    }
    
    
    
    public void setLast()
    {
        last = true;
    }
    
    public void creerCouleur()
    {
        groupeCouleurs = new HashMap<String, Color>();
        groupeCouleurs.put("mauve", new Color(141, 6, 2));
        groupeCouleurs.put("bleu ciel", new Color(1, 176, 243));
        groupeCouleurs.put("violet", new Color(251, 73, 123));
        groupeCouleurs.put("orange", new Color(255, 194, 5));
        groupeCouleurs.put("rouge", new Color(249, 22, 16));
        groupeCouleurs.put("jaune", new Color(248, 244, 12));
        groupeCouleurs.put("vert", new Color(9, 151, 7));
        groupeCouleurs.put("bleu roi", new Color(2, 79, 149));
    }
    
    public void paintComponent(Graphics g)
    {
        if(rotation!=0) {
            Graphics2D g2d = ((Graphics2D)g);
            switch(rotation) {
                case 90 : g2d.translate(hauteur, 0); break;
                case 180 : g2d.translate(largeur, hauteur); break;
                case -90 : g2d.translate(0, largeur); break;
            }
            
            g2d.rotate(rotation*Math.PI/180);
        }
        
        if(groupe != null) {
            // Rectangle de couleur du groupe
            g.setColor(groupe);
            g.fillRect(0, 0, largeur, ((int)(0.20 * hauteur)));
            
            // Trait en bas du groupe
            g.setColor(Color.black);
            g.fillRect(0, ((int)(0.20 * hauteur)), largeur, 3);
        }
        
        // Bord sur le côté de la case ( dépend de l'orientation )
        g.setColor(Color.black);
        g.fillRect(0, 0, 3, hauteur);
        
        if( !last ) {
            // Bord supérieur de la case
            g.fillRect(0, 0, largeur, 3);
        }
    }
}
