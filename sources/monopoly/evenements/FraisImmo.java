package monopoly.evenements;

import monopoly.jeu.Joueur;
import monopoly.proprietes.Propriete;



public class FraisImmo extends EvenementAbstrait
{
    private int fraisMaison;    // Frais pour maison
    private int fraisHotel;     // Frais pour maison
    
    
    
    /**
     * Construit l'évènement FraisImmo en fonction d'une somme
     */
    public FraisImmo(int fraisMaison, int fraisHotel)
    {
        super("Frais Immo");

        this.fraisMaison = fraisMaison;
        this.fraisHotel = fraisHotel;
    }

    public FraisImmo(String parametres)
    {
        this(Integer.parseInt(parametres.split(",")[0]), Integer.parseInt(parametres.split(",")[1]));
    }
    
    
    
    /**
     * Retire la somme d'argent au joueur
     */
    public void executer()
    {
        int somme = 0;

        for(Propriete t : cible().titres())
            if(t.niveauImmobilier() == 5)
                somme += fraisHotel;
            else if(t.niveauImmobilier() != 0)
                somme += fraisMaison;

        cible().payer(somme);
    }
}