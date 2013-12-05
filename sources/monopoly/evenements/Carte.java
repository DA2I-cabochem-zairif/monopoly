package monopoly.evenements ;

import monopoly.jeu.Joueur ;
import monopoly.jeu.Case ;

public class Carte extends AbstractEvent
{
	private Evenement e;
	
    public Carte(String nom, Joueur cible, Evenement e)
    {
		super(nom, cible);
		this.e = e;
	}

	public void executer()
	{
		e.executer();
	}
}