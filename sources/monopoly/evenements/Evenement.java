package monopoly.evenements;

import monopoly.jeu.Joueur;



/**
 * Cette interface décrit les fonctionnalités associées aux
 * événements du jeu
 */
public interface Evenement
{
    /**
     * Intitulé de l'événement (i.e. en principe de la case associée
     * ou de la carte)
     */
    String nom();

    /**
     * Le joueur qui subit l'événement
     */
    Joueur cible();

    /**
     * Cible un joueur
     */
    void cibler(Joueur cible);

    /**
     * Déclenche l'évènement
     */
    void executer();
}