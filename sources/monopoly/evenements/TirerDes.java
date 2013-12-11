package monopoly.evenements;

import monopoly.jeu.Joueur;

import java.util.Random;



public class TirerDes extends EvenementAbstrait
{
    private Random generator;   // Generateur de jet de dés
    
    
    
    /**
     * Construit l'évènement TirerDes
     */
    public TirerDes()
    {
        super("Tirer dés");

        generator = new Random();
    }
    
    
    
    /**
     * Génère deux lancers de dés. Si le joueur est en prison et que les deux jets
     * sont égaux, libère le joueur. Sinon, ajoute un mouvement de valeur de la
     * somme des deux jets à la liste d'actions du joueur, et en ajoute un second si
     * les deux premiers jets étaient égaux
     */
    public void executer()
    {
        int tir1 = (int) ( generator.nextDouble() * 6 ) + 1;
        int tir2 = (int) ( generator.nextDouble() * 6 ) + 1;
        
        if(cible().enPrison() &&  tir1 == tir2)
            cible().liberer();
        else if(!cible().enPrison()) {
            Deplacement d = new Deplacement(tir1 + tir2 );
            d.cibler(cible());
            cible().chosesAFaire().add(d);
            
            if(tir1 == tir2) {
                 d = new Deplacement((int) (generator.nextFloat() * 6) + 1);
                 d.cibler(cible());
                 cible().chosesAFaire().add(d);
            }
        }
    }
}