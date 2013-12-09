package monopoly.evenements ;

import monopoly.jeu.Joueur ;
import monopoly.jeu.Case ;
import monopoly.proprietes.Groupe;

public class Carte extends AbstractEvent
{
	private Evenement e;
	private int num;
	private String type;
	private String action;
	
    public Carte(int  num, String type, String nom, Joueur cible, Evenement e, String action)
    {
		super(nom, cible);
		this.e = e;
		this.num = num;
		this.type = type;
		this.action = action;
	}
    
    public Carte(int  num, String type, String nom, Evenement e, String action)
    {
		super(nom);
		this.e = e;
		this.num = num;
		this.type = type;
		this.action = action;
	}
    
    public Carte(int  num, String type, String nom, String action)
    {
		super(nom);
		this.num = num;
		this.type = type;
		this.action = action;
	}

	public void executer()
	{
		e.setCible(this.cible);
		e.executer();
	}
	
	public void setCible(Joueur j)
	{
		this.cible = j;
	}
	
	public void setEvenement(Evenement e)
	{
		this.e = e;
	}
	
	public String type()
	{
		return this.type;
	}
	
	public int num()
	{
		return this.num;
	}
	
	public String toString()
	{
		return this.nom;
	}
}