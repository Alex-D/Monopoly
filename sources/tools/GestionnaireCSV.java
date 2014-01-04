package tools;
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.ArrayList;



/**
 * Gère la lecture d'un CSV ligne par ligne
 */
public class GestionnaireCSV
{
    public static ArrayList<LigneCSV> chargerCSV(String fichier)
    {
        BufferedReader br 			= null;
        ArrayList<LigneCSV> liste 	= new ArrayList<LigneCSV>();
        LigneCSV ligne;
 
		try {
			String ligneCourante;
			
			br = new BufferedReader(
				new InputStreamReader(
					new FileInputStream("config/" + fichier),
					"UTF8"
				)
			);
			br.readLine();
			
			while ((ligneCourante = br.readLine()) != null) {
			    ligneCourante 	= ligneCourante.replaceAll(";;", "; ;").replaceAll(";;", "; ;");
				String[] t 		= ligneCourante.split(";");
                ligne 			= new LigneCSV();

				for (String s : t)
				    ligne.ajouter(s);

				liste.add(ligne);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
				    br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return liste;
    }
}