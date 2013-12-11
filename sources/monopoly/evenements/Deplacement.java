package monopoly.evenements;

import monopoly.jeu.Joueur;



public class Deplacement extends EvenementAbstrait
{
    private int nbMouvements;   // Nombre de cases duquel la cible doit se déplacer
    
    
    
    /**
     * Construit l'évènement Deplacement en fonction d'un nombre de cases
     */
    public Deplacement(int nbMouvements)
    {
        super("Déplacement");
        this.nbMouvements = nbMouvements;
    }

    public Deplacement(String parametres)
    {
        this(Integer.parseInt(parametres));
    }
    
    
    
    /**
     * Déplacer le joueur cible sur la case se trouvant nbMouvements plus loin que la sienne
     */
    public void executer()
    {
        cible().placerSur(cible().position().get(cible().position().numero() + nbMouvements));
    }
}