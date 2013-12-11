package monopoly.evenements;

import monopoly.jeu.Joueur;



public class Emprisonner extends EvenementAbstrait
{
    /**
     * Construit l'évènement Emprisonner
     */
    public Emprisonner()
    {
        super("Emprisonner");
    }
    
    
    
    /**
     * Envoi le joueur cible en prison
     */
    public void executer()
    {
        cible().emprisonner();
    }
}