package monopoly.jeu;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import monopoly.gui.*;
import monopoly.evenements.*;
import monopoly.proprietes.*;

import tools.*;



/**
 * Classe principale du monopoly
 * Crée le plateau, les cases et les joueurs
 */
public class Monopoly
{
    private final static String MONOPOLY_CSV   = "monopoly.csv";
    private final static String CARTES_CSV     = "cartes.csv";

    private Plateau plateau;
    private Case depart;
    private List<Joueur> joueurs;
    private boolean finDePartie;
    
    
    
    /**
     * Construit le jeu et ses joueurs
     */
    public Monopoly(int nbJoueurs)
    {
        creerPlateau();

        chargerCartes();
        chargerCases();

        creerJoueurs(nbJoueurs);
        
        finDePartie = false;
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
        
        ArrayList<LigneCSV> liste = GestionnaireCSV.chargerCSV(MONOPOLY_CSV);
        
        for (LigneCSV ligne : liste) {
            CaseDefaut tmp = new CaseDefaut(ligne.getInt(0), ligne.getString(1));

            if (!ligne.getString(2).equals(" ")) {
                // Si c'est une case qui déclenche un évènement fixe
                String evenement;
                String param = null;

                if (ligne.getString(2).contains(",")) {
                    evenement   = ligne.getString(2).split(",")[0];
                    param       = ligne.getString(2).split(",")[1];
                } else {
                    evenement = ligne.getString(2);
                }

                tmp.declenche(MetaEvenement.creer(evenement, param));
            } else if (ligne.getString(3) != null && (ligne.getString(3).equals("terrain") || ligne.getString(3).equals("monopole"))) {
                Propriete p = null;

                if (ligne.getString(3).equals("terrain")) {
                    // Si c'est un terrain
                    Groupe g = (GroupeDefaut.groupes().get(ligne.getString(4)) != null)
                        ? GroupeDefaut.groupes().get(ligne.getString(4))
                        : new GroupeDefaut(ligne.getString(4), ligne.getInt(6))
                    ;
                    
                    p = new ProprieteDefaut(tmp, ligne.getInt(5), ligne.getString(7).split(","), "Libre", g, true);
                } else if(ligne.getString(3).equals("monopole")) {
                    // Si c'est un monopole (gare, etc.)
                    String[] loyer = { "0" };

                    if (ligne.getString(7) != null)
                        loyer[0] = ligne.getString(7).split(",")[0];

                    p = new ProprieteDefaut(tmp, ligne.getInt(5), loyer, "Libre", null, false);
                }

                tmp.possede(p);
                Choix c = new Choix(new Achat(tmp), new Rien());
                tmp.declenche(c);
            }
            
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
        ArrayList<LigneCSV> liste = GestionnaireCSV.chargerCSV(CARTES_CSV);
        
        for (LigneCSV c : liste)
            new Carte(c.getString(1), c.getString(2), c.getString(3), c.getString(4));
    }
    
    /**
     * Crée et place le nombre de joueurs demandés
     */
    public void creerJoueurs(int nbJoueurs)
    {
        joueurs = new ArrayList<Joueur>();
        
        for (int i = 1; i <= nbJoueurs; i++)
            joueurs.add(new JoueurDefaut(i, "Joueur " + i, depart));
    }
    
    /**
     * Retourne true si la partie est finie, faux sinon
     */
    public boolean finDePartie()
    {
        return finDePartie;
    }
    
    /**
     * Indique que la partie est finie
     */
    public void finirPartie()
    {
        finDePartie = true;
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
        Monopoly m = new Monopoly(2);
        Fenetre f = new Fenetre(m);
        
        /*
        Monopoly m = new Monopoly(2);
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Monopoly !");
        System.out.println("==========\n");
        
        while (!m.finDePartie()) {
            System.out.println(m);

            for (Joueur j : m.getJoueurs()) {
                System.out.println("\n\nTour du joueur : " + j + " :");
                System.out.println("Tapez 1 pour jouer, 2 pour arrêter la partie");

                if (sc.nextInt() == 1) {
                    System.out.println("Vous êtes sur la case : " + j.position().nom() + "." );
                    System.out.println("Vous possédez : " + j.especes() + "F.");
                    
                    j.chosesAFaire().add(new TirerDes(j));

                    while (!j.chosesAFaire().empty()) {
                        Evenement e = j.chosesAFaire().pop();
                        e.cibler(j);
                        System.out.println(e);
                        e.executer();
                    }
                } else {
                    m.finirPartie();
                    break;
                }
            }
        }
        */
    }
}
