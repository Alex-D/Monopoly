package monopoly.jeu;

import monopoly.proprietes.Propriete;
import monopoly.evenements.Evenement;



/**
 * Cette interface décrit une case du plateau de jeu du Monopoly
 */
public interface Case extends Iterable<Case>
{
    /**
     * Numéro de la case
     */
    int numero();
    
    /**
     * Donne la case associée au numéro spécifié
     */
    Case get(int numero);
    
    /**
     * Donne la case suivante
     */
    Case suivante();
    
    /**
     * Donne le nombre de cases total
     */
    int nombreCases();
    
    /**
     * Intitulé de la case
     */
    String nom();
    
    /**
     * Titre de propriété associé à la case (éventuellement
     * <code>null</code>)
     */
    Propriete propriete();
    
    /**
     * Événément susceptible de se déclencher à l'arrivée sur cette
     * case (éventuellement <code>null</code>)
     */
    Evenement evenement();
}
