package monopoly.evenements;

import monopoly.jeu.Joueur;



public class DeplacementRelatif extends EvenementAbstrait
{
    private int nbMouvements;   // Nombre de cases duquel la cible doit se déplacer
    
    
    
    /**
     * Construit l'évènement DeplacementRelatif en fonction d'un nombre de cases
     */
    public DeplacementRelatif(int nbMouvements)
    {
        super("Déplacement relatif");
        this.nbMouvements = nbMouvements;
    }

    public DeplacementRelatif(String parametres)
    {
        this(Integer.parseInt(parametres));
    }
    
    
    
    /**
     * Déplacer le joueur cible sur la case se trouvant nbMouvements plus loin que la sienne
     */
    public void executer()
    {
        if (!cible().enPrison()) {
            if (cible().position().numero() > cible().position().getRelative(nbMouvements).numero()
                    && nbMouvements != -3 )
                cible().chosesAFaire().add(cible().position().get(1).evenement());
            cible().placerSur(cible().position().getRelative(nbMouvements));
        }
    }
}
