package monopoly.proprietes;

import monopoly.jeu.Joueur;
import monopoly.jeu.Case;



/**
 * Cette interface décrit les fonctionnalités associées à tout titre
 * de propriété
 */
public class ProprieteDefaut implements Propriete
{
    private Case position;          // Référence vers la case qui a cette propriété
    private String nom;             // Nom de la propriété
    private boolean hypotheque;     // Décrit si la propriété est hypothéquée ou non
    private int prixAchat;          // Prix d'achat de la propriété
    private Groupe groupe;          // Groupe d'appartenance de la propriété
    private boolean constructible;  // Décrit sur la propriete est constructible
    private Joueur proprietaire;    // Propriétaire de la propriété
    private int loyer;              // Loyer à payer au propriétaire
    private int niveauImmobilier;   // Niveau de construction de 1 à 5
    
    
    
    /**
     * Défini une propriété selon sa position, prixAchat, loyer et son nom
     */
    public ProprieteDefaut(Case position, int prixAchat, int loyer, String nom)
    {
        this.position = position;
        this.prixAchat = prixAchat;
        this.loyer = loyer;
        this.nom = nom;
        
        hypotheque = false;
        niveauImmobilier = 0;
    }
    
    
    
    public Case position()
    {
        return position;
    }
    
    public String nom()
    {
        return nom;
    }
    
    public boolean hypotheque()
    {
        return hypotheque;
    }
    
    public void hypothequer()
    {
        hypotheque = true;
    }
    
    public boolean deshypothequer()
    {
        hypotheque = false;
        
        // TODO: return
        return true;
    }
    
    public int prixAchat()
    {
        return prixAchat;
    }  
    
    public Groupe groupe()
    {
        return groupe;
    }
    
    public boolean constructible()
    {
        return constructible;
    }
    
    public boolean construire()
    {
        constructible = false;
        
        // TODO: return
        return true;
    }
    
    public boolean detruire()
    {
        constructible = true;
        
        // TODO: return
        return true;
    }
    
    public Joueur proprietaire()
    {
        return proprietaire;
    }
    
    public void setProprietaire(Joueur proprietaire)
    {
        this.proprietaire = proprietaire;
    }
    
    public int loyer()
    {
        return loyer;
    }
    
    public int niveauImmobilier()
    {
        return niveauImmobilier;
    }



    public String toString()
    {
        return ">>[Propriete] " + nom + " | prixAchat : " + prixAchat + " | loyer : " + loyer + "F";
    }
}
