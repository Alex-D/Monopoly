package monopoly.evenements;

import monopoly.jeu.Joueur;



/**
 * Implémentation abstraite des fonctions communes à tous les évènements
 */
public abstract class EvenementAbstrait implements Evenement
{
    private String nom;		// Nom de l'évènement
    private Joueur cible;	// Cible - joueur - qui subit l'évènement



    /**
     * Construit un évènement nommé et ciblé
     */
    public EvenementAbstrait(String nom, Joueur cible)
    {
    	this.nom = nom;
        this.cible = cible;
    }



    public String nom()
    {
    	return nom;
    }

    public Joueur cible()
    {
    	return cible;
    }

    public abstract void executer();
}