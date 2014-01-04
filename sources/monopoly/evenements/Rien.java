package monopoly.evenements;

import monopoly.jeu.Case;



public class Rien extends EvenementAbstrait
{
    
    
    
    /**
     * Construit un évènement consistant à ne rien faire
     */
    public Rien()
    {
        super("ne rien faire");
    }
    
    
    
    public void executer() {}
    
    
    
    public String toString()
    {
        return nom();
    }
}
