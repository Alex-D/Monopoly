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
            if (!c.propriete().groupe().nom().equals("Gares")
                && !c.propriete().groupe().nom().equals("Compagnies")) {
                boolean doubleImpot = true;
                for(Case g : c.groupe()) {
                    try {
                        if(!g.propriete().proprietaire().equals(destinataire))
                            doubleImpot = false;
                    } catch ( NullPointerException e ) {}
                }
                int somme = (doubleImpot) ?
                            c.propriete().loyer()*2 : 
                            c.propriete().loyer()
                ;
                cible().chosesAFaire().add(new Depenser(somme));
                
                destinataire.chosesAFaire().add(new Recette(somme));
            } else if (c.propriete().groupe().nom().equals("Gares")) {
                int nbGares = 0;
                for(Case g : c.groupe()) {
                    try {
                        if(g.propriete().proprietaire().equals(destinataire))
                            nbGares++;
                    } catch ( NullPointerException e ) {}
                }
                int somme = c.propriete().loyer() * nbGares;
                cible().chosesAFaire().add(new Depenser(somme));
                
                destinataire.chosesAFaire().add(new Recette(somme));
            } else if (c.propriete().groupe().nom().equals("Compagnies")) {
                boolean toutesCompagnies = true;
                for(Case g : c.groupe()) {
                    try {
                        if(!g.propriete().proprietaire().equals(destinataire))
                            toutesCompagnies = false;
                    } catch ( NullPointerException e ) {}
                }
                int somme = toutesCompagnies
                            ?cible().dernierTir()*1000
                            :cible().dernierTir()*400
                ;
                cible().chosesAFaire().add(new Depenser(somme));
                
                destinataire.chosesAFaire().add(new Recette(somme));
            }
        }
    }
}
