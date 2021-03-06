package monopoly.evenements;

import monopoly.jeu.Joueur;
import monopoly.jeu.CaseDefaut;



public class Emprisonner extends EvenementAbstrait
{
    /**
     * Construit l'évènement Emprisonner
     */
    public Emprisonner()
    {
        super("Emprisonner");
    }
    
    
    
    /**
     * Envoi le joueur cible en prison
     */
    public void executer()
    {
        cible().emprisonner();
        Evenement d = new Deplacement( CaseDefaut.prison().numero() );
        d.cibler( cible() );
        cible().chosesAFaire().add( d );
    }
}
