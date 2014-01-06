package monopoly.evenements;

import monopoly.jeu.Joueur;
import monopoly.jeu.Case;



public class PayerImpot extends EvenementAbstrait
{
    private Joueur destinataire;
    private Case c;
    
    
    
    /**
     * Construit un évènement PayerImpot en fonction du destinataire et de la valeur de cet impôt
     */
    public PayerImpot(Joueur destinataire, Case c)
    {
        super("Payez un impot au joueur " + destinataire + ".");
        this.destinataire   = destinataire;
        this.c              = c;
    }
    
    
    
    /**
     * Retourne le destinataire de l'impot
     */
    public Joueur destinataire()
    {
        return destinataire;
    }
    
    /**
     * Fait payer un impôt au joueur cible,
     * impot qui revient au joueur destinataire
     */
    public void executer()
    {
        if (cible() != destinataire) {
            boolean doubleImpot = true;
            for(Case g : c.groupe()) {
                System.out.println(g);
                if(g.propriete().proprietaire().equals(cible()))
                    doubleImpot = false;
            }
            int somme = (doubleImpot) ?
                        c.propriete().loyer()*2 : 
                        c.propriete().loyer();
            System.out.println(somme + "");
            cible().chosesAFaire().add(new Depenser(somme));
            
            destinataire.chosesAFaire().add(new Recette(somme));
        }
    }
}
