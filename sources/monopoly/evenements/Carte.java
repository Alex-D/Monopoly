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
public class Carte extends EvenementAbstrait
{
    private String nomTas;
    private Evenement evenement;

    private static Map<String, List<Carte>> tas = new HashMap<String, List<Carte>>();



    /**
     * Construit une carte évènementielle définie
     */
    public Carte(String nomTas, String nom, String evenement, String parametres)
    {
        super(nom);


        // Défini les tas de cartes
        if(tas.get(nomTas) == null)
            tas.put(nomTas, new ArrayList<Carte>());

        tas.get(nomTas).add(this);


        // Stock dans la carte son tas d'appartenance
        this.nomTas = nomTas;
        // Défini l'évènement à déclencher à l'execution de la carte
        this.evenement = MetaEvenement.creer(evenement, parametres);
    }



    /**
     * Retourne le tas au nom demandé
     */
    public static List<Carte> tas(String nomTas)
    {
        return tas.get(nomTas);
    }

    /**
     * Déclenche l'action de la carte sur le joueur
     */
    public void executer()
    {
        evenement.cibler(cible());
        evenement.executer();
    }



    public String toString()
    {
        return super.toString() + "\n\tEvenement : " + evenement;
    }
}