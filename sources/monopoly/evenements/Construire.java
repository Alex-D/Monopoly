package monopoly.evenements;

import monopoly.jeu.Case;
import monopoly.proprietes.ProprieteDefaut;



/**
 * Augmente le niveau de construction de la propriété
 */
public class Construire extends EvenementAbstrait
{
    private Case c;
    
    

    /**
     * Construit un événement Achat pour une case donnée
     */
    public Construire(Case c)
    {
        super("Construire sur \"" + c.nom() + "\" pour " + c.propriete().groupe().coutImmo() + "F");
        this.c = c;
    }


    
    /**
     * Retire la somme de l'achat au joueur
     * Puis, s'il a assez d'argent,
     * défini le joueur comme étant le propriétaire
     */
    public void executer()
    {
        if (c.propriete().proprietaire().equals(cible()) && c.propriete().construire())
            c.declenche(new PayerImpot(cible(), c.propriete().loyer()));
    }
}
