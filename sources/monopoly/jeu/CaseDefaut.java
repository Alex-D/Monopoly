package monopoly.jeu;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

import monopoly.proprietes.Propriete;
import monopoly.evenements.Evenement;



/**
 * Implémentation par défaut des cases
 */
public class CaseDefaut implements Case
{
    private int numero;          // Numero unique de la case
    private String nom;          // Intitulé de la case
    private Propriete propriete; // Propriété éventuelle
    private Evenement evenement; // Evènement éventuel
    
    private static Map<Integer, Case> cases = new HashMap<Integer, Case>(); // Map des cases du plateau
    private static int numeroMax;// Numéro maximum parmi les cases
    
    
    
    /**
     * Crée une case du plateau du Monopoly
     */
    public CaseDefaut(int numero, String nom)
    {
        this.numero = numero;
        this.nom    = nom;
        
        cases.put(numero, this);
        
        if (numeroMax < numero)
            numeroMax = numero;
        
        this.evenement = null;
    }
    
    
    
    public int numero()
    {
        return numero;
    }
    
    public Case get(int numero)
    {
        return cases.get((numero + cases.size() - 1) % 40 + 1);
    }

    public Case getRelative(int nombre)
    {
        return get(numero + nombre);
    }
    
    public int nombreCases()
    {
        return numeroMax;
    }
      
    public Case suivante()
    {
        return cases.get(
            numero < cases.size()
                ? numero+1
                : 1
        );
    }
    
    public String nom()
    {
        return nom;
    }
    
    public ArrayList<Case> groupe()
    {
        ArrayList<Case> list = new ArrayList<Case>();
        for(Case c : cases.values()) {
            try {
                if(c.propriete().groupe() == propriete().groupe() )
                    list.add(c);
            } catch ( NullPointerException e ) {}
        }
        return list;
    }
    
    public Propriete propriete()
    {
        return propriete;
    }
    
    public Evenement evenement()
    {
        return evenement;
    }
    
    public void declenche(Evenement evenement)
    {
        this.evenement = evenement;
    }
    
    public void possede(Propriete propriete)
    {
        this.propriete = propriete;
    }
    
    public Iterator<Case> iterator()
    {
        return cases.values().iterator();
    }
    
    public static void completerCases()
    {
        for (int i = 1; i < numeroMax; i++)
            if (cases.get(i) == null)
                new CaseDefaut(i, "Case manquante");
    }
    
    /**
     * Retourne la case prison
     */
    public static Case prison()
    {
        Case tmp;
        Iterator it = cases.values().iterator();
        
        while (it.hasNext()) {
            tmp = (Case) it.next();
             
            if (tmp.nom().equals("Prison"))
                return tmp;
        }

        return null;
    }


    
    public String toString()
    {
        String s = nom;

        if (propriete != null)
            s += "\n" + propriete;

        if (evenement != null)
            s += "\n" + evenement;

        return s;
    }
}
