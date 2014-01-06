package monopoly.gui;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;

import java.util.HashMap;

import monopoly.jeu.Monopoly;
import monopoly.jeu.Case;
import monopoly.jeu.Joueur;



enum CouleurGroupe {
    VIOLET    (251, 73,  123),
    BLEU_ROI  (2,   79,  149),
    BLEU_CIEL (1,   176, 243),
    VERT      (9,   151, 7),
    JAUNE     (248, 244, 12),
    ORANGE    (255, 194, 5),
    ROUGE     (249, 22,  16),
    MAUVE     (141, 6,   2);



    public final Color couleur; // Couleur du groupe



    /**
     * Construction de la couleur par rapport à
     * sa décomposition rouge, vert, bleu
     */
    CouleurGroupe(int r, int v, int b) {
        couleur = new Color(r, v, b);
    }



    /**
     * Retourne la couleur par rapport à son nom
     */
    public static Color get(String nomCouleur)
    {
        return CouleurGroupe.valueOf(nomCouleur.toUpperCase().replace(" ", "_")).couleur;
    }
}



public class CaseGraphique extends JPanel
{
    private int largeur,
                hauteur,
                rotation;
    private Color couleurGroupe;
    private boolean last;
    private Case c;
    private Point position;
    private Monopoly m;

    private final Font font = new Font("Arial", Font.PLAIN, 9);
    
    
    
    public CaseGraphique(int largeur, int hauteur, Case c, int rotation, Monopoly m)
    {
        this.largeur    = largeur;
        this.hauteur    = hauteur;
        this.c          = c;
        this.rotation   = rotation;
        this.last       = false;
        this.m          = m;
        
        if (rotation == 90 || rotation == -90)
            setPreferredSize(new Dimension(hauteur, largeur));
        else
            setPreferredSize(new Dimension(largeur, hauteur));
        
        try {
            couleurGroupe = CouleurGroupe.get(c.propriete().groupe().nom());
        } catch(Exception e) {
            couleurGroupe = null;
        }
    }
    
    
    
    public void setLast()
    {
        last = true;
    }
    
    public Point position()
    {
        return position;
    }
    
    public CaseGraphique clone()
    {
        return new CaseGraphique(largeur, hauteur, c, rotation, m);
    }
    
    public void paintComponent(Graphics g)
    {
        // Gestion de l'orientation de la case
        if (rotation != 0) {
            Graphics2D g2d = ((Graphics2D) g);

            switch (rotation) {
                case 90 :
                    g2d.translate(hauteur, 0);
                    break;
                case 180 :
                    g2d.translate(largeur, hauteur);
                    break;
                case -90 :
                    g2d.translate(0, largeur);
                    break;
            }
            
            g2d.rotate(rotation * Math.PI / 180);
        }
        
        // Si la case fait parti d'un groupe ayant une couleur
        if (couleurGroupe != null) {
            // Rectangle de couleur du groupe
            g.setColor(couleurGroupe);
            g.fillRect(0, 0, largeur, ((int)(0.20 * hauteur)));
            
            // Trait en bas du groupe
            g.setColor(Color.black);
            g.fillRect(0, ((int)(0.20 * hauteur)), largeur, 3);
        }
        
        // Bord sur le côté de la case ( dépend de l'orientation )
        g.setColor(Color.black);
        g.fillRect(0, 0, 3, hauteur);
        
        // Bord supérieur de la case
        if (!last)
            g.fillRect(0, 0, largeur, 3);



        // Intitulé de la case
        g.setFont(font);

        String l23 = c.nom()
            .replace("Rue ", "")
            .replace("Gare ", "")
            .replace("Avenue ", "")
            .replace("Place ", "")
            .replace("Faubourg ", "")
            .replace("Boulevard ", "")
            .replace("Caisse ", "")
            .replace("Compagnie ", "")
            .replace("des C", "C")
            .replace("de ", "")
            .replace("la ", "")
            .replace("Impôts sur ", "")
            .trim()
        ;
        String l1 = c.nom().replace(l23, "").trim();
        String l2 = l23
            .replace("des Eaux", "")
            .replace("d'électricité", "")
            .replace("Elysées", "")
            .trim()
        ;
        String l3 = l23.replace(l2, "").trim();

        g.drawString(
            l1,
            largeur/2 - (getFontMetrics(font).stringWidth(l1)/2),
            30
        );
        g.drawString(
            l2,
            largeur/2 - (getFontMetrics(font).stringWidth(l2)/2),
            42
        );
        g.drawString(
            l3,
            largeur/2 - (getFontMetrics(font).stringWidth(l3)/2),
            55
        );

        
        // Affichage des joueurs
        int decalage = 0;
        for (Joueur j : m.getJoueurs())
            if (j.position() == c) {
                decalage += 7;
                g.setColor(j.couleur().darker());
                g.fillOval(5 + decalage, 20 + decalage, 20, 20);
                g.setColor(j.couleur());
                g.fillOval(8 + decalage, 23 + decalage, 14, 14);
            }



        // Affichage des propriétés
        if (c.propriete() != null) {
            if (c.propriete().proprietaire() != null) {
                Color couleur = c.propriete().proprietaire().couleur();

                switch (c.propriete().niveauImmobilier()) {
                    case 5:
                        // Affichage de l'hotel
                        g.setColor(couleur.darker());
                        g.fillRect(largeur - 10, hauteur - 7, 20, 20);
                        g.setColor(couleur);
                        g.fillRect(largeur - 7, hauteur - 10, 14, 14);
                        break;

                    case 4:
                    case 3:
                    case 2:
                    case 1:
                        // Affichage des maisons
                        for (int i = 0; i < c.propriete().niveauImmobilier(); i++) {
                            g.setColor(couleur.darker());
                            g.fillRect(5 + i*20, hauteur - 7, 20, 20);
                            g.setColor(couleur);
                            g.fillRect(8 + i*20, hauteur - 10, 14, 14);
                        }
                        break;

                    case 0:
                        // Affichage de la propriété
                        g.setColor(couleur);
                        g.fillRect(3, hauteur - 5, largeur - 3, 5);
                        break;
                }
            }

            // Prix d'achat de la propriété
            String prix = "F " + c.propriete().prixAchat();
            g.setColor(Color.BLACK);
            g.drawString(
                prix,
                largeur/2 - (getFontMetrics(font).stringWidth(prix)/2),
                hauteur - 7
            );
        }
    }
}
