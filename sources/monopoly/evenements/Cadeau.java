package monopoly.evenements;

import monopoly.jeu.Game;
import monopoly.jeu.Joueur;

public class Cadeau extends AbstractEvent
{
	public Cadeau(String nom, Joueur cible)
    {
		super(nom, cible);
	}
	
	public Cadeau(String nom)
    {
		super(nom);
	}
	
	public void setJoueur(Joueur j)
	{
		this.cible = j;
	}

	public void executer()
	{
		
	}
	
	public String toString()
	{
		return this.nom;
	}
}