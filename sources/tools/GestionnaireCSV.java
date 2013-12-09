package tools;
 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;


public class GestionnaireCSV
{

    private static ArrayList<LigneCSV> chargerCSV(String fichier)
    {
        BufferedReader br = null;
        ArrayList<LigneCSV> liste = new ArrayList<LigneCSV>();
        LigneCSV ligne;
 
		try {
			String ligneCourante;
            
            System.out.println( System.getProperty("user.dir") );
 
			br = new BufferedReader(new FileReader( "config/"+fichier ));
			br.readLine();
			
			while ((ligneCourante = br.readLine()) != null){
			    ligneCourante = ligneCourante.replaceAll(";;", "; ;").replaceAll(";;", "; ;");
			    System.out.println(ligneCourante);
				String[] t = ligneCourante.split(";");
                ligne = new LigneCSV();
				for(String s : t)
				    ligne.ajouter(s);
				liste.add(ligne);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null)
				    br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return liste;
    }
    
    public static void main(String arg[])
    {
        ArrayList<LigneCSV> l = GestionnaireCSV.chargerCSV("monopoly.csv");
        for(LigneCSV li : l)
        {
            System.out.println(li.getInt(0) + " : " + li.getString(1) + " : " + li.getString(2) + " : " + li.getString(3) + " : " + li.getString(4) + " : " + li.getString(5) + " : " + li.getString(6));
        }
    }
}
