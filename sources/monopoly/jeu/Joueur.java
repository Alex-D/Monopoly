package monopoly.jeu;

import monopoly.proprietes.Propriete;

import monopoly.evenements.Evenement;

import java.util.List;
import java.util.Stack;

import java.awt.Color;



/**
 * Cette interface décrit les fonctionnalités que doit présenter une
 * classe représentant un joueur de Monopoly
 */
public interface Joueur
{
    /**
     * Le numéro du joueur
     */
    int numero();
    
    /**
     * Le nom du joueur
     */
    String nom();
    
    /**
     * Verifie qu'il ne reste plus d'actions pour le joueur courant
     */
    void verifierAction();
    
    /**
     * Lance le tour du joueur, et retourne vrai si la partie continue,
     * faux sinon.
     */
    boolean joue();
    
    /**
     * Indique si le joueur est emprisonné
     */
    boolean enPrison();
    
    /**
     * Emprisonne le joueur
     */
    void emprisonner();
    
    /**
     * Libère le joueur
     */
    void liberer();
    
    /**
     * Indique si le joueur est éliminé
     */
    boolean elimine();
    
    /**
     * Élimine le joueur
     */
    void eliminer();
    
    /**
     * Liquidités possédées par le joueur
     */
    int especes();
    
    /**
     * Impose au joueur le paiement de la somme spécifiée
     * @return true si le joueur a pu payer, false sinon
     */
    boolean payer(int somme);
    
    /**
     * Hypotheque
     */
    void hypothequer();
    
    /**
     * Renvoi la valeur du dernier lancé de dés
     */
    int dernierTir();
        
    /**
     * Range la valeur du dernier lancé de dés
     */
    public void setDernierTir(int dernierTir);
    
    /**
     * Verse au joueur la somme spécifier
     */
    void verser(int somme);
    
    /**
     * Donne la case sur laquelle le joueur est placé
     */
    Case position();
    
    /**
     * Donne la couleur de jeton du joueurs
     */
    Color couleur();
    
    /**
     * Place le joueur sur la case spécifiée
     */
    void placerSur(Case c);
    
    /**
     * Donne la liste des autres joueurs encore en lice (tous sauf
     * <code>this</code> et les éliminés)
     */
    List<Joueur> adversaires();
    
    /**
     * Titres de propriétés possédés par le joueur
     */
    List<Propriete> titres();
    
    /**
     * Cartes conservées par le joueur
     */
    List<Evenement> cartes();
    
    /**
     * La pile d'événements que le joueur doit subir pendant son tour
     * de jeu : si la pile est vide, le joueur a terminé son tour ; sinon il doit
     * dépiler un événement pour l'exécuter. Il peut arriver qu'un événement manipule
     * cette pile (par exemple "Aller en prison" termine le tour du joueur même s'il lui
     * restait théoriquement des choses à faire)
     */
    Stack<Evenement> chosesAFaire();
}
