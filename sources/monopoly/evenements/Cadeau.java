package monopoly.evenements;

import monopoly.jeu.Joueur;



/**
 * Tous les adversaires d'un joueur lui donnent une somme donnée
 */
public class Cadeau extends EvenementAbstrait
{
    private int somme;    // Le montant du cadeau
    
    
    
    /**
     * Construit l'évènement Cadeau en fonction d'une somme
     */
    public Cadeau(int somme)
    {
        super("Cadeau");

        this.somme = somme;
    }

    public Cadeau(String parametres)
    {
        this(Integer.parseInt(parametres));
    }
    
    
    
    /**
     * Retire la somme d'argent aux adversaires et le reverse 
     * au joueur courant
     */
    public void executer()
    {
        int total = 0;

        for(Joueur j : cible().adversaires())
            if(j.payer(somme))
                total += somme;

        cible().verser(total);
    }
}