package monopoly.evenements ;

import monopoly.jeu.Game;
import monopoly.jeu.Joueur ;
import monopoly.jeu.Case ;
import java.util.*;

public class Emprisonnement extends AbstractEvent
{	
	public Emprisonnement(String nom, Joueur cible)
    {
		super(nom, cible);
	}
	
	public Emprisonnement(String nom)
    {
		super(nom);
	}
	
	public void setJoueur(Joueur j)
	{
		this.cible = j;
	}

	public void executer()
	{
		this.cible.emprisonner();
		this.cible.placerSur(Game.PRISON);
	}
	
	public String toString()
	{
		return this.nom;
	}
}