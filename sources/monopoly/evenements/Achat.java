package monopoly.evenements;

import monopoly.jeu.Case;
import monopoly.proprietes.ProprieteDefaut;



/**
 * Achète le contenu d'une case pour la somme indiquée sur cette case
 */
public class Achat extends EvenementAbstrait
{
    private Case c;
    private boolean peutPayer;
    
    

    /**
     * Construit un événement Achat pour une case donnée
     */
    public Achat(Case c)
    {
        super("Acheter \"" + c.nom() + "\" pour " + c.propriete().prixAchat() + "F");
        this.c      = c;
        peutPayer   = false;
    }


    
    public boolean peutPayer()
    {
        return peutPayer;
    }
    
    public int somme()
    {
        return c.propriete().prixAchat();
    }
    
    /**
     * Retire la somme de l'achat au joueur
     * Puis, s'il a assez d'argent,
     * défini le joueur comme étant le propriétaire
     */
    public void executer()
    {
        if (cible().payer(c.propriete().prixAchat())) {
            peutPayer = true;
            
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
}
