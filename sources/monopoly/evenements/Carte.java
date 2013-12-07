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
    
    public Carte(int  num, String type, String nom, Evenement e)
    {
		super(nom);
		this.e = e;
		this.num = num;
		this.type = type;
	}
    
    public Carte(int  num, String type, String nom)
    {
		super(nom);
		this.num = num;
		this.type = type;
	}

	public void executer()
	{
		e.executer();
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
		return "Num : "+this.num+"\nNom : "+this.nom+"\nType : "+this.type+"\nCible : "+this.cible+"\nEvenement : "+this.e;
	}
}