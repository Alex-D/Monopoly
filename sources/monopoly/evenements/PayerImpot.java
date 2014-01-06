package monopoly.evenements;

import monopoly.jeu.Joueur;



public class PayerImpot extends EvenementAbstrait
{
    private Joueur destinataire;
    private int valeur;
    
    
    
    /**
     * Construit un évènement PayerImpot en fonction du destinataire et de la valeur de cet impôt
     */
    public PayerImpot(Joueur destinataire, int valeur)
    {
        super("Payer un impot d'une valeur de " + valeur + "F au joueur " + destinataire + ".");
        this.destinataire = destinataire;
        this.valeur = valeur;
    }
    
    
    
    /**
     * Fait payer un impôt au joueur cible,
     * impot qui revient au joueur destinataire
     */
    public void executer()
    {
        if (cible() != destinataire) {
            Evenement d = new Depenser(valeur);
            d.cibler(cible());
            cible().chosesAFaire().add(d);
            
            destinataire.verser(valeur);
        }
    }
}
