package monopoly.evenements;



/**
 * Enumération des évènements supportés
 */
public enum MetaEvenement {
    CHOIX               ("choix",               "Choix"),
    CADEAU              ("cadeau",              "Cadeau"),
    TIRERDES            ("tirer des",           "TirerDes",     false),
    EMPRISONNER         ("prison",              "Emprisonner",  false),
    DEPENSER            ("depense",             "Depenser"),
    FRAISIMMO           ("frais immo",          "FraisImmo"),
    RECETTE             ("recette",             "Recette"),
    DEPLACEMENT         ("aller",               "Deplacement"),
    REVENIR             ("revenir",             "Deplacement"),
    DEPLACEMENTRELATIF  ("deplacement relatif", "DeplacementRelatif"),
    CARTE               ("carte",               "TirerCarte");



    public final String nom;                    // Nom de l'évènement
    public final String nomClasse;              // Nom de la classe à instancier
    public final boolean necessiteParametres;   // Si l'évènement a besoin de paramètre-s-



    /**
     * Définition de chaque évènement
     */
    MetaEvenement(String nom, String nomClasse)
    {
        this(nom, nomClasse, true);
    }
    MetaEvenement(String nom, String nomClasse, boolean necessiteParametres)
    {
        this.nom = nom;
        this.nomClasse = nomClasse;
        this.necessiteParametres = necessiteParametres;
    }



    /**
     * Crée un évènement en fonction de son nom et des paramètres
     */
    public static Evenement creer(String evenement, String parametres)
    {
        evenement = evenement.replace("é", "e");

        try {
            for (MetaEvenement e : MetaEvenement.values()) {
                if (e.nom.equals(evenement)) {
                    Class evenementClass = Class.forName("monopoly.evenements." + e.nomClasse);

                    if (e.necessiteParametres)
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