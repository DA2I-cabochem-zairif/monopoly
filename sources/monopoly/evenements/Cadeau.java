package monopoly.evenements;

import monopoly.jeu.Game;
import monopoly.jeu.Joueur;

public class Cadeau extends AbstractEvent
{
	private int somme;
	
	public Cadeau(String nom, Joueur cible)
    {
		super(nom, cible);
	}
	
	public Cadeau(String nom, int somme)
    {
		super(nom);
		this.somme = somme;
	}
	
	public void setJoueur(Joueur j)
	{
		this.cible = j;
	}

	public void executer()
	{
		for (Joueur j : this.cible.adversaires())
		{
			new Depense(this.nom, j, this.somme).executer();
			new Recette(this.nom, this.cible, this.somme).executer();
		}
	}
	
	public String toString()
	{
		return this.nom;
	}
}