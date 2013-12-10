package monopoly.evenements;

import monopoly.jeu.Joueur;



public class Deplacement extends EvenementAbstrait
{
    private int nbMouvements;   // Nombre de cases duquel la cible doit se déplacer
    
    
    
    /**
     * Construis l'évènement Deplacement en fonction de son nom,
     * de son Joueur cible, et d'un nombre de mouvement
     */
    public Deplacement(String nom, int nbMouvements)
    {
        super(nom);
        this.nbMouvements = nbMouvements;
    }
    
    
    
    /**
     * Déplacer le joueur cible sur la case se trouvant nbMouvements plus loin que la sienne
     */
    public void executer()
    {
        cible().placerSur(cible().position().get(cible().position().numero() + nbMouvements));
    }
}
