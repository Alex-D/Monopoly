package monopoly.evenements;

import monopoly.jeu.Joueur;



/**
 * TODO: Un bonus est gardé en main jusqu'à ce qu'il puisse être executé
 */
public class Bonus extends Carte
{
    /**
     * Construit l'évènement Bonus en fonction d'une somme
     */
    public Bonus(String nomTas, String nom, String evenement, String parametres)
    {
        super(nomTas, nom, evenement, parametres);
    }
}