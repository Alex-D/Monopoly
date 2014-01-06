package monopoly.proprietes;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import monopoly.jeu.Joueur;
import monopoly.jeu.Case;



/**
 * Implémentation des groupes (couleurs, gares, ...)
 */
public class GroupeDefaut implements Groupe
{
    private String nom;     // Nom du groupe
    private int coutImmo;   // Cout de l'immobilier pour les propriétés du groupe
    private List<Propriete> composition         = new ArrayList<Propriete>();       // Liste des propriétés contenues dans le groupe
    
    private static Map<String, Groupe> groupes  = new HashMap<String, Groupe>();    // Liste des groupes du jeu
    
    
    
    /**
     * Constructeur par défaut pour définir un nouveau groupe
     */
    public GroupeDefaut(String nom, int coutImmo)
    {
        this.nom        = nom;
        this.coutImmo   = coutImmo;
        
        groupes.put(nom, this);
    }
    
    
    
    public String nom()
    {
        return nom;
    }
    
    public int coutImmo()
    {
        return coutImmo;
    }

    public List<Propriete> composition()
    {
        return composition;
    }

    public Groupe get(String nom)
    {
        return groupes.get(nom);
    }

    public boolean proprietaireUnique()
    {
        Joueur j = null;
        
        for (Propriete p : composition)
            if (j == null)
                j = p.proprietaire();
            else if (p.proprietaire() == null || !p.proprietaire().equals(j))
                return false;
        
        return true;
    }

    public int niveauImmobilierLePlusBas()
    {
        int niveau = 0;

        for (Propriete p : composition)
            if (p.niveauImmobilier() < niveau)
                niveau = p.niveauImmobilier();

        return niveau;
    }


    
    public static Map<String, Groupe> groupes()
    {
        return groupes;
    }



    public String toString()
    {
        return "groupe " + nom + " | coût immo : " + coutImmo + "F";
    }
}
