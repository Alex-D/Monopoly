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
    
    public void cibler(Joueur cible)
    {
        this.cible = cible;
    }

    public abstract void executer();



    public String toString()
    {
        return ">>[Evènement] " + nom + " | " + cible;
    }
}