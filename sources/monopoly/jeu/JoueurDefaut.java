package monopoly.jeu;

import monopoly.proprietes.Propriete;

import monopoly.evenements.Evenement;
import monopoly.evenements.TirerDes;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Observable;

import java.awt.Color;

import java.util.Random;



/**
  * Cette classe représente un joueur humain,
  * qui se contrôle manuellement donc ( entrée/sortie )
  */
public class JoueurDefaut extends Observable implements Joueur
{
    private Monopoly m;
    private int numero;         // Numero du joueur
    private int especes;        // Espèces possédées par le joueur
    private String nom;         // Nom du joueur
    private Case position;      // Case sur laquelle se situe le joueur
    private Color c;            // Couleur du joueur
    private boolean enPrison;   // Vrai si le joueur est en prison, faux sinon
    private int nombreTour;     // Nombre de tour a passer en prison
    private int dernierTir;     // Valeur du dernier lancer de dé du joueur
    private boolean elimine;    // Vrai si le joueur est éliminé, faux sinon
    private List<Propriete> titres;         // Les titres de propriétés du joueur
    private List<Evenement> cartes;         // Les cartes conservées par le joueur
    private Stack<Evenement> chosesAFaire;  // Les actions que le joueur doit exécuter
    
    private static List<Joueur> joueurs = new ArrayList<Joueur>();  // La liste des joueurs, sauf soi-même et les éliminés
    
    
    
    /**
      * Crée un joueur en fonction d'un numéro d'identification,
      * de son nom, ainsi que de sa case de départ.
      */
    public JoueurDefaut(int numero, String nom, Case position, Monopoly m)
    {
        this.m          = m;
        this.numero     = numero;
        this.nom        = nom;
        this.position   = position;
        
        Random g = new Random();
        int red     =   (int)(g.nextFloat()*256),
            green   =   (int)(g.nextFloat()*256),
            blue    =   (int)(g.nextFloat()*256);
        c = new Color(red, green, blue);
        
        especes         = 200000;
        dernierTir      = 0;
        enPrison        = false;
        titres          = new ArrayList<Propriete>();
        cartes          = new ArrayList<Evenement>();
        chosesAFaire    = new Stack<Evenement>();
        
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
    
    public void verifierAction()
    {
        while (!chosesAFaire.empty()) {
            Evenement e = chosesAFaire.pop();
            if (e != null) {
                e.cibler(this);
                
                e.executer();
                
                setChanged();
                notifyObservers(e);
            }
        }
    }
    
    public boolean joue()
    {
        if(adversaires().size() == 0) {
            setChanged();
            notifyObservers("winner");
            return false;
        } else {
            if(enPrison && nombreTour != 0)
                nombreTour --;
            else if(enPrison)
                liberer();
            else {
                chosesAFaire().add(new TirerDes(this));

                while (!chosesAFaire.empty() && joueurs.contains(this)) {
                    Evenement e = chosesAFaire.pop();
                    if (e != null) {
                        e.cibler(this);
                        
                        e.executer();
                        
                        if(joueurs.size() != 1) {
                            setChanged();
                            notifyObservers(e);
                        }
                    }
                }
            }
            if(joueurs.size() == 1) {
                System.out.println("Coucou");
                joueurs.get(0).joue();
            } else {
                setChanged();
                notifyObservers("suivant");
            }
            return true;
        }
    }
    
    public boolean enPrison()
    {
        return enPrison;
    }
    
    public void emprisonner()
    {
        enPrison = true;
        nombreTour = 2;
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
		m.getJoueurs().remove(this);
        elimine = true;
        chosesAFaire = new Stack<Evenement>();
        Case curr = position.get(1);
        curr.reinitialiser();
        curr = curr.suivante();
        while(curr.numero() != 1) {
            curr.reinitialiser();
            curr = curr.suivante();
        }
        setChanged();
        notifyObservers();
    }
    
    public int especes()
    {
        return especes;
    }
    
    public boolean payer(int somme)
    {
        if (especes() >= somme) {
            especes -= somme;
            
            setChanged();
            notifyObservers("argent");
            
            return true;
        }

        return false;
    }
    
    public void hypothequer()
    {
        eliminer();
        
        setChanged();
        notifyObservers("elimine");
    }
    
    public int dernierTir()
    {
        return dernierTir;
    }
    
    public void setDernierTir(int dernierTir)
    {
        this.dernierTir = dernierTir;
    }
    
    public void verser(int somme)
    {
        especes += somme;
            
        setChanged();
        notifyObservers("argent");
    }
    
    public Case position()
    {
        return position;
    }
    
    public Color couleur()
    {
        return c;
    }
    
    public void placerSur(Case c)
    {
        position = c;
        if (c.evenement() != null) {
            Evenement e = c.evenement();
            e.cibler(this);
            chosesAFaire.add(e);
        }
        setChanged();
        notifyObservers(null);
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
