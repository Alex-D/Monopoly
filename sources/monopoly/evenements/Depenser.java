package monopoly.evenements;

import monopoly.jeu.Joueur;



public class Depenser extends EvenementAbstrait
{
    private int depense;    // Le dépense que le joueur doit effectuer
    
    
    
    /**
     * Construis l'évènement Depenser, en fonction de son nom,
     * de son Joueur cible, et d'une somme depense
     */
    public Depenser(String nom, int depense)
    {
        super(nom);
        this.depense = depense;
    }
    
    
    
    /**
     * Retire depense de la somme d'argent que possède le joueur
     */
    public void executer()
    {
        cible().payer(depense);
    }
}
