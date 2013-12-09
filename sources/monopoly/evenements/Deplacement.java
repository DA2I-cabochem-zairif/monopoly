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
    
    public Deplacement(String nom, Case caseCible)
    {
		super(nom);
		this.caseCible = caseCible;
	}
    
    public void setCible(Joueur j)
    {
    	this.cible = j;
    }
    
	public void executer()
	{
		this.cible.placerSur(this.caseCible);
		/*if (this.cible.position().evenement() != null)
		{
			this.cible.position().evenement().executer();
		}*/
	}
	
	public String toString()
	{
		return "Déplacement à la case numéro "+this.caseCible.numero()+" : "+this.caseCible.nom();
	}
}