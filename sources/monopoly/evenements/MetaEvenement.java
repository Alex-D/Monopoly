package monopoly.evenements;

import java.util.Arrays;



/**
 * Enumération des évènements supportés
 */
public enum MetaEvenement {
	CHOIX		(new String[]{ "choix" }, "Choix"),
	TIRERDES	(new String[]{ "tirer des" }, "TirerDes", false),
	DEPENSER	(new String[]{ "dépense" }, "Depenser"),
	EMPRISONER	(new String[]{ "prison" }, "Emprisoner", false),
	RECETTE		(new String[]{ "recette" }, "Recette"),
	DEPLACEMENT	(new String[]{ "aller", "déplacement relatif" }, "Deplacement"),
	CARTE		(new String[]{ "carte" }, "TirerCarte");



	private String[] noms; 		// Liste des noms de l'évènement
	private String nomClasse; 	// Nom de la classe à instancier
	private boolean necessiteParametre; // Si l'évènement a besoin de paramètre-s-


	/**
	 * Définition de chaque évènement
	 */
	MetaEvenement(String[] noms, String nomClasse)
	{
		this.noms = noms;
		this.nomClasse = nomClasse;
		this.necessiteParametre = true;
	}
	MetaEvenement(String[] noms, String nomClasse, boolean necessiteParametre)
	{
		this.noms = noms;
		this.nomClasse = nomClasse;
		this.necessiteParametre = necessiteParametre;
	}



	public String nomClasse()
	{
		return nomClasse;
	}

	public boolean est(String nom)
	{
		return (Arrays.binarySearch(noms, nom) >= 0);
	}

	public boolean necessiteParametre()
	{
		return necessiteParametre;
	}



	/**
	 * Crée un évènement en fonction de son nom et des paramètres
	 */
	public static Evenement creer(String evenement, String parametres)
	{
		try {
	        for(MetaEvenement e : MetaEvenement.values()){
	            if(e.est(evenement)){
	                Class evenementClass = Class.forName(e.nomClasse());

	                if(e.necessiteParametre())
	                    return (Evenement) evenementClass.getDeclaredConstructor(String.class).newInstance(parametres);
	                else
	                    return (Evenement) evenementClass.newInstance();
	            }
	        }
	    } catch(Exception e){}

        return null;
    }
}