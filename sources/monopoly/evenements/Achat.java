package monopoly.evenements;

import monopoly.jeu.Case;
import monopoly.proprietes.ProprieteDefaut;



/**
 * Achète le contenu d'une case pour la somme indiquée sur cette case
 */
public class Achat extends EvenementAbstrait
{
    private Case c;
    
    

    /**
     * Construit un événement Achat pour une case donnée
     */
    public Achat(Case c)
    {
        super("Acheter \"" + c.nom() + "\" pour " + c.propriete().prixAchat() + "F");
        this.c = c;
    }


    
    /**
     * Retire la somme de l'achat au joueur
     * Puis, s'il a assez d'argent,
     * défini le joueur comme étant le propriétaire
     */
    public void executer()
    {
        if (cible().payer(c.propriete().prixAchat())) {
            c.propriete().setProprietaire(cible());

            ((ProprieteDefaut) c.propriete())
                .setNom(c.propriete().nom().replace(
                    "Libre",
                    "Possédée par " + c.propriete().proprietaire()
                ))
            ;

            c.declenche(new PayerImpot(cible(), c.propriete().loyer()));
        }
    }
    

    
    public String toString()
    {
        return nom();
    }
}
