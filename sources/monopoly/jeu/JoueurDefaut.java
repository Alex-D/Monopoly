package monopoly.jeu;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

import monopoly.proprietes.Propriete;
import monopoly.evenements.Evenement;



/**
  * Cette classe représente un joueur humain,
  * qui se contrôle manuellement donc ( entrée/sortie )
  */
public class JoueurDefaut implements Joueur
{
    private int numero;         // Numero du joueur
    private int especes;        // Espèces possédées par le joueur
    private String nom;         // Nom du joueur
    private Case position;      // Case sur laquelle se situe le joueur
    private boolean enPrison;   // Vrai si le joueur est en prison, faux sinon
    private boolean elimine;    // Vrai si le joueur est éliminé, faux sinon
    private List<Propriete> titres;    // Les titres de propriétés du joueur
    private List<Evenement> cartes;    // Les cartes conservées par le joueur
    private Stack<Evenement> chosesAFaire;  // Les actions que le joueur doit exécuter
    
    private static List<Joueur> joueurs = new ArrayList<Joueur>();  // La liste des joueurs, sauf soi-même et les éliminés
    
    
    
    /**
      * Crée un joueur en fonction d'un numéro d'identification,
      * de son nom, ainsi que de sa case de départ.
      */
    public JoueurDefaut(int numero, String nom, Case position)
    {
        this.numero     = numero;
        this.nom        = nom;
        this.position   = position;
        
        especes         = 20000;
        enPrison        = false;
        titres          = new ArrayList<Propriete>();
        cartes          = new ArrayList<Evenement>();
        
        joueurs.add(this);
    }
    
    
    
    public int numero()
    {
        return numero;
    }
    
    public String nom()
    {
        return nom;
    }
    
    public boolean enPrison()
    {
        return enPrison;
    }
    
    public void emprisonner()
    {
        enPrison = true;
    }
    
    public void liberer()
    {
        enPrison = false;
    }
    
    public boolean elimine()
    {
        return elimine;
    }
    
    public void eliminer()
    {
		joueurs.remove(this);
        elimine = true;
    }
    
    public int especes()
    {
        return especes;
    }
    
    public boolean payer(int somme)
    {
        if(especes > somme){
            especes -= somme;
            return true;
        }
        return false;
    }
    
    public void verser(int somme)
    {
        especes += somme;
    }
    
    public Case position()
    {
        return position;
    }
    
    public void placerSur(Case c)
    {
        position = c;
    }
    
    public List<Joueur> adversaires()
    {
		List<Joueur> adversaires = new ArrayList<Joueur>(joueurs);
		adversaires.remove(this);
		return adversaires;
    }
    
    public List<Propriete> titres()
    {
        return titres;
    }
    
    public List<Evenement> cartes()
    {
        return cartes;
    }
    
    public Stack<Evenement> chosesAFaire()
    {
        return chosesAFaire;
    }

    public boolean equals(Object o)
    {
        return (o == this || (o instanceof JoueurDefaut && numero == ((JoueurDefaut) o).numero()));
    }

    public String toString()
    {
        return nom + " (" + numero + ")";
    }
}