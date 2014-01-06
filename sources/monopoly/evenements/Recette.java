package monopoly.evenements;

import monopoly.jeu.Joueur;



public class Recette extends EvenementAbstrait
{
    private int somme;   // Gain d'argent du joueur



    /**
     * Construit l'évènement Recette en fonction d'une somme à ajouter
     */
    public Recette(int somme)
    {
        super("Recette");

        this.somme = somme;
    }

    public Recette(String parameters)
    {
        this(Integer.parseInt(parameters));
    }
    
    
    
    /**
     * Retourne la somme gagnée
     */
    public int somme()
    {
        return somme;
    }
    
    /**
     * Ajoute la somme d'argent au joueur
     */
    public void executer()
    {
        cible().verser(somme);
    }
}
