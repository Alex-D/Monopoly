package monopoly.jeu;

import java.util.HashMap;
import java.util.Iterator;

import monopoly.proprietes.Propriete;
import monopoly.evenements.Evenement;



public class CaseDefaut implements Case
{
    private int numero;          // Numero unique de la case
    private String nom;          // Intitulé de la case
    private Propriete propriete; // Propriété éventuelle
    private Evenement evenement; // Evènement éventuel
    private static HashMap<Integer, Case> cases = new HashMap<Integer, Case>(); // Map des cases du plateau
    private static int numeroMax;
    
    
    
    /**
     * Crée une case du plateau du Monopoly
     */
    public CaseDefaut(int numero, String nom)
    {
        this.numero = numero;
        this.nom    = nom;
        cases.put(numero, this);
        
        if(numeroMax < numero)
            numeroMax = numero;
    }
    
    
    
    public int numero()
    {
        return numero;
    }
    
    public Case get(int numero)
    {
        return cases.get(numero);
    }
    
    /**
     * Retourne la case suivante
     */
    public Case getSuivante()
    {
        return cases.get(numero < cases.size()
            ? cases.get(numero+1)
            : cases.get(1)
        );
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
    
    
    
    public Iterator<Case> iterator()
    {
        return CaseDefaut.cases.values().iterator();
    }
    
    
    public static void completerCases()
    {
        for(int i = 1; i < numeroMax; i++)
            if(cases.get(i) == null)
                new CaseDefaut(i, "Case manquante");
    }
    
    
    public String toString()
    {
        return nom;
    }
}
