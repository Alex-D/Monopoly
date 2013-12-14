package monopoly.jeu;

import java.util.List;
import java.util.ArrayList;

import monopoly.gui.Plateau;
import monopoly.gui.PlateauTexte;
import monopoly.evenements.Carte;

import tools.GestionnaireCSV;
import tools.LigneCSV;



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

        chargerCartes();

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
    public void chargerCases()
    {
        depart = null;
        
        ArrayList<LigneCSV> liste = GestionnaireCSV.chargerCSV("monopoly.csv");
        
        for(LigneCSV ligne : liste){
            Case tmp = new CaseDefaut(ligne.getInt(0), ligne.getString(1));
            
            if(depart == null)
                depart = tmp;
        }
        
        CaseDefaut.completerCases();
    }

    /**
     * Construit les tas de carte depuis le CSV
     */
    public void chargerCartes()
    {
        ArrayList<LigneCSV> liste = GestionnaireCSV.chargerCSV("cartes.csv");
        
        for(LigneCSV c : liste)
            new Carte(c.getString(1), c.getString(2), c.getString(3), c.getString(4));
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