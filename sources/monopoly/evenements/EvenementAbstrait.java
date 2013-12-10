package monopoly.evenements;

import monopoly.jeu.Joueur;
import monopoly.jeu.Case;



/**
 * Cette interface décrit les fonctionnalités associées aux
 * événements du jeu
 */
public abstract class EvenementAbstrait implements Evenement
{
    private String nom;		// Nom de l'évènement
    private Joueur cible;	// Cible (joueur) qui subit l'évènement



    /**
     * Construit un évènement nommé et ciblé
     */
    public EvenementAbstrait(String nom)
    {
    	this.nom = nom;
    }



    public String nom()
    {
    	return nom;
    }

    public Joueur cible()
    {
    	return cible;
    }

    /**
     * A surcharcher dans les enfants
     */
    public void declencher(Joueur cible)
    {
    	this.cible = cible;
    }
}