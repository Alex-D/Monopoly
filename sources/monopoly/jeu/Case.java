package monopoly.jeu;

import monopoly.proprietes.Propriete;
import monopoly.evenements.Evenement;

import java.util.ArrayList;



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
     * Donne la case associée de façon relative
     */
    Case getRelative(int nombre);
    
    /**
     * Retourne la case suivante
     */
    Case suivante();
    
    
    /**
     * Retourne toutes les cases du groupe de la case
     */
    ArrayList<Case> groupe();
    
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
     * Événement susceptible de se déclencher à l'arrivée sur cette
     * case (éventuellement <code>null</code>)
     */
    Evenement evenement();

    /**
     * Défini l'événement susceptible de se déclencher à l'arrivée 
     * sur cette case
     */
    void declenche(Evenement evenement);
}
