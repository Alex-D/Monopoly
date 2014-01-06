package monopoly.evenements;

import monopoly.jeu.Joueur;



public class Depenser extends EvenementAbstrait
{
    private int somme;    // Le dépense que le joueur doit effectuer
    
    
    
    /**
     * Construit l'évènement Depenser en fonction d'une somme
     */
    public Depenser(int somme)
    {
        super("Dépenser");

        this.somme = somme;
    }

    public Depenser(String parametres)
    {
        this(Integer.parseInt(parametres));
    }
    
    
    
    /**
     * Retourne la somme perdue
     */
    public int somme()
    {
        return somme;
    }
    
    /**
     * Retire la somme d'argent au joueur
     */
    public void executer()
    {
        cible().payer(somme);
    }
}
