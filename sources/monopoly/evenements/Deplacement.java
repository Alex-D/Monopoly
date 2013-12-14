package monopoly.evenements;

import monopoly.jeu.Joueur;



public class Deplacement extends EvenementAbstrait
{
    private int numeroCase;   // Numero de la case de destination
    
    
    
    /**
     * Construit l'évènement Deplacement en fonction d'un numero de case
     */
    public Deplacement(int numeroCase)
    {
        super("Déplacement");
        this.numeroCase = numeroCase;
    }

    public Deplacement(String parametres)
    {
        this(Integer.parseInt(parametres));
    }
    
    
    
    /**
     * Déplacer le joueur cible sur la case numeroCase
     */
    public void executer()
    {
        cible().placerSur(numeroCase);
    }
}