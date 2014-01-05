package monopoly.evenements;

import monopoly.jeu.Joueur;
import monopoly.gui.PlateauGraphique;



/**
 * Evénement qui propose un choix entre deux autres événements
 */
public class Choix extends EvenementAbstrait
{
    private Evenement a, b; // Les deux choix
    
    
    
    /**
     * Construit l'événement Choix entre deux évènements
     */
    public Choix(Evenement a, Evenement b)
    {
        super("Choix");

        this.a = a;
        this.b = b;
    }

    public Choix(String parametres)
    {
        super("Choix");

        String[] choix = parametres.split(",");
        String[] choixA = choix[0].split(":");
        String[] choixB = choix[1].split(":");

        a = MetaEvenement.creer(choixA[0], choixA[1]);
        b = MetaEvenement.creer(choixB[0], choixB[1]);
    }
     
     
     
    /**
     * Offre le choix au joueur entre les deux événements,
     * et ajoute celui choisi à sa pile
     */
    public void executer()
    {
        cible().chosesAFaire().add(PlateauGraphique.faireChoix(a, b));
    }
}
