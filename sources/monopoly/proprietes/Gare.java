package monopoly.proprietes;

import monopoly.jeu.Joueur;
import monopoly.jeu.Case;

public class Gare extends Monopole
{
	public static int LOYER_BASE = 2500;
	
    public Gare(String nom, Case pos, int prix, Groupe groupe, int[] loyer)
    {
		super(nom, pos, prix, groupe, loyer);
	}
    
    public Gare(String nom, int prix, Groupe groupe, int[] loyer)
    {
		super(nom, prix, groupe, loyer);
	}
    
	public int loyer()
	{
		int loyer = Gare.LOYER_BASE;
		Joueur prop = this.proprietaire();
		int nb = 0;
		for (Propriete p : this.groupe().composition())
		{
			if (p.proprietaire() != null)
			{
				if (p.proprietaire().nom().equals(prop.nom()) && !p.hypotheque())
				{
					System.out.println("coucou");
					nb++;
				}
			}
		}
		for (int i = 2 ; i <= nb ; i++)
			loyer *= 2;
		
		return loyer;
    }
}