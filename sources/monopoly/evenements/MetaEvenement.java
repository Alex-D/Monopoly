package monopoly.evenements;

import java.util.Arrays;



/**
 * Enumération des évènements supportés
 */
public enum MetaEvenement {
	CHOIX		(new String[]{ "choix" }, "Choix"),
	TIRERDES	(new String[]{ "tirer des" }, "TirerDes", false),
	DEPENSER	(new String[]{ "dépense" }, "Depenser"),
	EMPRISONNER	(new String[]{ "prison" }, "Emprisonner", false),
	RECETTE		(new String[]{ "recette" }, "Recette"),
	DEPLACEMENT	(new String[]{ "aller", "déplacement relatif" }, "Deplacement"),
	CARTE		(new String[]{ "carte" }, "TirerCarte");



	public final String[] noms; 				// Liste des noms de l'évènement
	public final String nomClasse; 				// Nom de la classe à instancier
	public final boolean necessiteParametres;  	// Si l'évènement a besoin de paramètre-s-



	/**
	 * Définition de chaque évènement
	 */
	MetaEvenement(String[] noms, String nomClasse)
	{
		this(noms, nomClasse, true);
	}
	MetaEvenement(String[] noms, String nomClasse, boolean necessiteParametres)
	{
		this.noms = noms;
		this.nomClasse = nomClasse;
		this.necessiteParametres = necessiteParametres;
	}



	/**
	 * Crée un évènement en fonction de son nom et des paramètres
	 */
	public static Evenement creer(String evenement, String parametres)
	{
		try {
	        for(MetaEvenement e : MetaEvenement.values()){
	            if(Arrays.binarySearch(e.noms, evenement) >= 0){
	                Class evenementClass = Class.forName("monopoly.evenements." + e.nomClasse);

	                if(e.necessiteParametres)
	                    return (Evenement) evenementClass.getDeclaredConstructor(String.class).newInstance(parametres);
	                else
	                    return (Evenement) evenementClass.newInstance();
	            }
	        }
	    } catch(Exception e){
	    	e.printStackTrace();
	    }

        return null;
    }
}