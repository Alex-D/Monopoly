package tools;

import java.util.ArrayList;



/**
 * Permet de manipuler une ligne d'un CSV
 */
public class LigneCSV
{
    private ArrayList<String> ligne;
    
    
    
    public LigneCSV()
    {
        ligne = new ArrayList<String>();
    }
    
    public void ajouter(String val)
    {
        ligne.add(val);
    }
    
    public Integer getInt(int col)
    {
        try {
            return Integer.parseInt(ligne.get(col));
        } catch(Exception e) {
            return null;
        }
    }
    
    public String getString(int col)
    {
        try {
            return ligne.get(col);
        } catch(Exception e) {
            return null;
        }
    }
}
