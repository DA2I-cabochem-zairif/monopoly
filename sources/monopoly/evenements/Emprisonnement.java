package monopoly.evenements ;

import monopoly.jeu.Joueur ;
import monopoly.jeu.Case ;
import java.util.*;

public class Emprisonnement extends AbstractEvent
{	
	public Emprisonnement(String nom, Joueur cible)
    {
		super(nom, cible);
	}

	public void executer()
	{
		this.cible.emprisonner();
	}
}