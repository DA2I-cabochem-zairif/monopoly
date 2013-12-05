package monopoly.evenements ;

import monopoly.jeu.Joueur ;
import monopoly.jeu.Case ;
import monopoly.jeu.MonoCase;

public class Deplacement extends AbstractEvent
{
	private Case caseCible;
	
    public Deplacement(String nom, Joueur cible, Case caseCible)
    {
		super(nom, cible);
		this.caseCible = caseCible;
	}

	public void executer()
	{
		this.cible.placerSur(this.caseCible);
	}
}