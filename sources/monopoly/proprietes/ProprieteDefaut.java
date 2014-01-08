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
    private boolean constructible;  // Décrit si la propriete est constructible
    private Joueur proprietaire;    // Propriétaire de la propriété
    private int[] loyer;            // Loyer à payer au propriétaire
    private int niveauImmobilier;   // Niveau de construction de 1 à 5
    
    
    
    /**
     * Défini une propriété selon sa position, son prix d'achat, son loyer, son nom et de sa constructabilite
     */
    public ProprieteDefaut(Case position, int prixAchat, String[] loyer, String nom, Groupe groupe, boolean constructible)
    {
        this.position       = position;
        this.prixAchat      = prixAchat;
        this.loyer          = new int[loyer.length];

        for (int i=0; i < loyer.length; i++)
            this.loyer[i] = Integer.parseInt(loyer[i]);

        this.nom            = nom;
        this.groupe         = groupe;
        this.constructible  = constructible;
        
        hypotheque          = false;
        niveauImmobilier    = 0;
        proprietaire        = null;
    }
    
    
    
    public void reinitialiser()
    {
        hypotheque          = false;
        niveauImmobilier    = 0;
        proprietaire        = null;
    }
    
    public Case position()
    {
        return position;
    }
    
    public String nom()
    {
        return nom;
    }
    
    public void setNom(String nom)
    {
        this.nom = nom;
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
        return groupe().proprietaireUnique() && niveauImmobilier() == groupe().niveauImmobilierLePlusBas();
    }
    
    public boolean construire()
    {
        if (constructible() && niveauImmobilier() < 5 && proprietaire().payer(groupe().coutImmo())) {
            niveauImmobilier++;
            return true;
        }

        return false;
    }
    
    public boolean detruire()
    {
        if (niveauImmobilier() > 0) {
            niveauImmobilier--;
            proprietaire().verser(groupe().coutImmo()/2);
            return true;
        }
        
        return false;
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
        return loyer[niveauImmobilier];
    }
    
    public int niveauImmobilier()
    {
        return niveauImmobilier;
    }



    public String toString()
    {
        String groupe = "";

        if (this.groupe != null)
            groupe = " | groupe : " + this.groupe;
            
        return (proprietaire == null)
            ? ">>[Propriete] " + nom + groupe + " | prix d'achat : " + prixAchat
            : ">>[Propriete] " + nom + groupe + " | loyer : " + loyer() + " | proprietaire : " + proprietaire
        ;
    }
}
