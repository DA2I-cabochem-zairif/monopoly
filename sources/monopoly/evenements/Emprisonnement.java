package monopoly.evenements ;

import monopoly.jeu.*;
import java.util.*;

public class Emprisonnement extends AbstractEvent
{
	public static HashMap<Joueur, Integer> TAB_PRISON = new HashMap<Joueur, Integer>();
	
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
		this.cible.chosesAFaire().clear();
	}
	
	public String toString()
	{
		return this.nom;
	}
}