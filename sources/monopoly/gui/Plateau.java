package monopoly.gui;

import monopoly.jeu.Monopoly;
import monopoly.jeu.Case;



/**
 * Interface décrivant le plateau de jeu pour l'affichage
 */
public abstract class Plateau
{
    Monopoly monopoly;  // Permet d'avoir accès au jeu
    
    
    
    /**
     * Initialise le plateau
     */
    public Plateau(Monopoly monopoly)
    {
        this.monopoly = monopoly;
    }
    
    
    
    /**
     * Commence l'affichage graphique
     */
    public abstract void afficher();
    
    /**
     * Affiche le plateau en texte
     */
    public String toString()
    {
        String s = "";
        Case cases = monopoly.getDepart();
        
        for(Case c : cases){
            s += "-- "+ c.numero() +" -------------\n" +
                 "| " + c + "\n" +
                 "------------------\n";
        }
        
        return s;
    }
}
