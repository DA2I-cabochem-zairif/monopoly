package monopoly.evenements;

import monopoly.jeu.Game;
import monopoly.jeu.Joueur;
import monopoly.proprietes.Propriete;

public class FraisImmo extends AbstractEvent
{
	private int[] sommes;
	
	public FraisImmo(String nom, Joueur cible)
    {
		super(nom, cible);
	}
	
	public FraisImmo(String nom, int[] sommes)
    {
		super(nom);
		this.sommes = sommes;
	}
	
	public void setJoueur(Joueur j)
	{
		this.cible = j;
	}

	public void executer()
	{
		int sommeADebourser = 0;
		for (Propriete p : this.cible.titres())
		{
			if (p.niveauImmobilier() == 5)
			{
				sommeADebourser += this.sommes[1];
			}
			else
			{
				sommeADebourser += this.sommes[0] * p.niveauImmobilier();
			}
		}
		
		new Depense(this.nom, this.cible, sommeADebourser).executer();
	}
	
	public String toString()
	{
		return this.nom;
	}
}