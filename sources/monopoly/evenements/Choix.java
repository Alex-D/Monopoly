package monopoly.evenements;

import monopoly.jeu.Joueur;

import monopoly.gui.PlateauTexte;



public class Choix extends EvenementAbstrait
{
    Evenement a, b; // Les deux choix
    
    
    
    /**
     * Construis l'évènement Choix, en fonction de son nom,
     * de son joueur cible, et de deux évènements
     */
     public Choix(String nom, Evenement a, Evenement b)
     {
        super(nom);
     }
     
     
     
    /**
     * Offre le choix au joueur entre les deux évènements,
     * et ajoute celui choisi à sa pile
     */
     public void executer()
     {
        cible().chosesAFaire().add(PlateauTexte.faireChoix(a, b));
     }
}
