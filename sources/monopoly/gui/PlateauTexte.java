package monopoly.gui;

import java.util.Scanner;

import monopoly.jeu.Monopoly;
import monopoly.jeu.Case;
import monopoly.jeu.Joueur;
import monopoly.evenements.Evenement;



/**
 * Interface texte simple en console
 */
public class PlateauTexte extends Plateau
{
    /**
     * Initialise le plateau
     */
    public PlateauTexte(Monopoly monopoly)
    {
        super(monopoly);
    }
    
    
    
    /**
     * Commence l'affichage en mode texte
     */
    public void afficher()
    {
        System.out.println(toString());
    }
    


    /**
     * Propose de faire un choix entre les deux évènements, via le terminal
     */
    public static Evenement faireChoix(Evenement a, Evenement b)
    {
        System.out.println("Pour " + a + " tapez 1, ou pour " + b + " tapez 2 : ");
        Scanner sc = new Scanner(System.in);
        
        return (sc.nextInt() == 1)
            ? a
            : b
        ;
    }
}
