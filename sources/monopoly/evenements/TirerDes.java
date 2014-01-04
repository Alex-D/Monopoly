package monopoly.evenements;

import java.util.Random;

import monopoly.jeu.Joueur;



/**
 * Lace un dé 6 face au hasard
 */
public class TirerDes extends EvenementAbstrait
{
    private static Random generator = new Random();   // Generateur de jet de dés
    
    
    
    /**
     * Construit l'évènement TirerDes
     */
    public TirerDes(Joueur j)
    {
        super("Tirer dés");
        cibler(j);
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
            System.out.println("Vous êtes libéré !");
        } else if( !cible().enPrison() ) {
            System.out.println("Vous avez fait " + tir1 + " et " + tir2 + ".");
            DeplacementRelatif d = new DeplacementRelatif(tir1 + tir2);
            d.cibler(cible());
            
            if(tir1 == tir2){
                tir1 = (int) (generator.nextFloat() * 6) + 1;
                System.out.println("Double ! Vous avancez de " + tir1 + " cases en plus.");
                DeplacementRelatif d2 = new DeplacementRelatif(tir1);
                cible().chosesAFaire().add( d2 );
            }
            cible().chosesAFaire().add( d );
        }
    }
}
