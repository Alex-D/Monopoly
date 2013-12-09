package monopoly.jeu;

import java.util.List;
import java.util.ArrayList;

import monopoly.gui.Plateau;
import monopoly.gui.PlateauTexte;



/**
 * Classe principale du monopoly
 * Crée le plateau, les cases et les joueurs
 */
public class Monopoly
{
    private Plateau plateau;
    private Case depart;
    private List<Joueur> joueurs;
    
    
    
    /**
     * Construit le jeu et ses joueurs
     */
    public Monopoly(int nbJoueurs)
    {
        creerPlateau();
        chargerCases();
        creerJoueurs(nbJoueurs);
    }
    
    
    
    /**
     * Construit la représentation graphique du plateau
     */
    public void creerPlateau()
    {
        plateau = new PlateauTexte(this);
    }
    
    /**
     * Construit les cases depuis le CSV
     */
    // TODO: charger les cases depuis le CSV
    public void chargerCases()
    {
        depart = new CaseDefaut(1, "Départ");
        
        for(int i = 2; i <= 40; i+=2)
            new CaseDefaut(i, "Case " + i);
        
        CaseDefaut.completerCases();
    }
    
    /**
     * Crée et place le nombre de joueurs demandés
     */
    public void creerJoueurs(int nbJoueurs)
    {
        joueurs = new ArrayList<Joueur>();
        
        for(int i = 1; i <= nbJoueurs; i++)
            joueurs.add(new JoueurDefaut(i, "Joueur " + i, depart));
    }
    
    
    
    /**
     * Retourne la case de départ
     */
    public Case getDepart()
    {
        return depart;
    }
    
    /**
     * Retourne la liste des joueurs
     */
    public List<Joueur> getJoueurs()
    {
        return joueurs;
    }
    
    public String toString()
    {
        return plateau.toString();
    }
    
    
    
    public static void main(String[] args)
    {
        System.out.println("Monopoly !");
        System.out.println("==========\n");
        System.out.println(new Monopoly(2));
    }
}
