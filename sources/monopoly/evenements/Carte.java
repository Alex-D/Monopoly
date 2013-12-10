package monopoly.evenements;

import monopoly.jeu.Joueur;



/**
 * Défini le comportement évènementiel d'une carte
 */
public class Carte extends EvenementAbstrait
{
    /**
     * Construit une carte évènementielle
     */
    public Carte(String nom)
    {
        super(nom);
    }



    /**
     * Déclenche l'action de la carte sur le joueur
     */
    public void executer()
    {
        // TODO: action de la carte
    }
}
