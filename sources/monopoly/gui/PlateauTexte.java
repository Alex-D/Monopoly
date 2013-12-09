package monopoly.gui;

import monopoly.jeu.Monopoly;
import monopoly.jeu.Joueur;
import monopoly.jeu.Case;



/**
 * Interface texte simple en console
 */
public class PlateauTexte extends Plateau
{
    /**
     * Initialise le plateau
     */
    public PlateauTexte(Monopoly monopoly)
    {
        super(monopoly);
    }
    
    
    /**
     * Commence l'affichage en mode texte
     */
    public void afficher()
    {
        System.out.println(toString());
    }
}
