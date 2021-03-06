package monopoly.evenements;

import java.util.Random;

import monopoly.jeu.Joueur;



/**
 * Lace un dé 6 face au hasard
 */
public class TirerDes extends EvenementAbstrait
{
    private static Random generator = new Random();   // Generateur de jet de dés
    private int tir1;
    private int tirDouble;
    
    
    
    /**
     * Construit l'évènement TirerDes
     */
    public TirerDes(Joueur j)
    {
        super("Tirer dés");
        cibler(j);
        
        tir1        = 0;
        tirDouble   = 0;
    }
    
    
    
    /**
     * Génère deux lancers de dés. Si le joueur est en prison et que les deux jets
     * sont égaux, libère le joueur. Sinon, ajoute un mouvement de valeur de la
     * somme des deux jets à la liste d'actions du joueur, et en ajoute un second si
     * les deux premiers jets étaient égaux
     */
    public void executer()
    {
        int tir1 = (int) (generator.nextDouble() * 6) + 1;
        int tir2 = (int) (generator.nextDouble() * 6) + 1;
        
        if( cible().enPrison() &&  tir1 == tir2 ) {
            cible().liberer();
        } else if( !cible().enPrison() ) {
            DeplacementRelatif d = new DeplacementRelatif(tir1 + tir2);
            d.cibler(cible());
            this.tir1 = tir1 + tir2;
            
            if(tir1 == tir2){
                this.tirDouble = (int) (generator.nextFloat() * 6) + 1;
                DeplacementRelatif d2 = new DeplacementRelatif(tirDouble);
                cible().chosesAFaire().add( d2 );
            }
            cible().chosesAFaire().add( d );
            cible().setDernierTir(tir1 + tirDouble);
        }
    }
    
    public int tir1()
    {
        return tir1;
    }
    
    public int tirDouble()
    {
        return tirDouble;
    }
}
