package monopoly.jeu;

import java.util.HashMap;
import monopoly.proprietes.Propriete;
import monopoly.evenements.Evenement;



public class CaseDefaut implements Case
{
    private int numero;          // Numero unique de la case
    private String nom;          // Intitulé de la case
    private Propriete propriete; // Propriété éventuelle
    private Evenement evenement; // Evènement éventuel
    private static HashMap<Integer, Case> cases = new HashMap<Integer, Case>(); // Map des cases du plateau
    
    
    
    /**
     * Crée une case du plateau du Monopoly
     */
    public CaseDefaut(int numero, String nom)
    {
        this.numero = numero;
        this.nom    = nom;
        cases.put(numero, this);
    }
    
    
    
    public int numero()
    {
        return numero;
    }
    
    public Case get(int numero)
    {
        return cases.get(numero);
    }
    
    public String nom()
    {
        return nom;
    }
    
    public Propriete propriete()
    {
        return propriete;
    }
    
    public Evenement evenement()
    {
        return evenement;
    }
    
    
    
    public String toString()
    {
        return nom;
    }
}
