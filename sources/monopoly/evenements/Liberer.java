package monopoly.evenements ;

import monopoly.jeu.*;
import java.util.*;

public class Liberer extends AbstractEvent
{
	public Liberer(String nom, Joueur cible)
    {
		super(nom, cible);
	}
	
	public Liberer(String nom)
    {
		super(nom);
	}
	
	public void setJoueur(Joueur j)
	{
		this.cible = j;
	}

	public void executer()
	{
		this.cible.liberer();
	}
	
	public String toString()
	{
		return this.nom;
	}
}