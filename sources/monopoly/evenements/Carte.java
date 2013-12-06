package monopoly.evenements ;

import monopoly.jeu.Joueur ;
import monopoly.jeu.Case ;
import monopoly.proprietes.Groupe;

public class Carte extends AbstractEvent
{
	private Evenement e;
	private int num;
	private String type;
	
    public Carte(int  num, String type, String nom, Joueur cible, Evenement e)
    {
		super(nom, cible);
		this.e = e;
		this.num = num;
		this.type = type;
	}

	public void executer()
	{
		e.executer();
	}
}