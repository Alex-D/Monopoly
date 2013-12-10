package monopoly.evenements;

import monopoly.jeu.Joueur;



public class Emprisonner extends EvenementAbstrait
{
    /**
     * Construis l'évènement Emprisonner en fonction de son nom,
     * et de son Joueur cible
     */
    public Emprisonner(String nom)
    {
        super(nom);
    }
    
    
    
    /**
     * Envoi le joueur cible en prison
     */
    public void executer()
    {
        cible().emprisonner();
    }
}
