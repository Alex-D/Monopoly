package monopoly.evenements;

import monopoly.jeu.Joueur;



public class Recette extends EvenementAbstrait
{
    private int recette;   // Gain d'argent du joueur



    /**
     * Construis l'évènement Recette en fonction de son nom,
     * de son Joueur cible, et d'une somme recette à ajouter au joueur
     */
    public Recette(String nom, int recette)
    {
        super(nom);
        this.recette = recette;
    }
    
    
    
    /**
     * Ajout recette à la somme d'argent du joueur
     */
    public void executer()
    {
        cible().verser(recette);
    }
}
