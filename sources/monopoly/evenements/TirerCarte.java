package monopoly.evenements;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import monopoly.jeu.Joueur;



/**
 * Défini le comportement évènementiel d'une carte
 */
public class TirerCarte extends EvenementAbstrait
{
    private Carte carte;
    private List<Carte> tas;



    /**
     * Construit l'évèment tirer une carte
     */
    public TirerCarte(String nomTas)
    {
        super("Tirer carte " + nomTas);

        tas = Carte.tas(nomTas);
    }



    /**
     * Déclenche l'action de la carte sur le joueur
     */
    public void executer()
    {
        Collections.shuffle(tas);
        this.carte = tas.get(0);
        cible().chosesAFaire().push(carte);
    }
    
    public Carte carte()
    {
        return carte;
    }
}
