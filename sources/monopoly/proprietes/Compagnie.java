package monopoly.proprietes;

import monopoly.evenements.TirerDes;
import monopoly.jeu.Joueur;
import monopoly.jeu.Case;

public class Compagnie extends Monopole
{	
    public Compagnie(Case pos, int prix, Groupe groupe, int[] loyer)
    {
		super(pos, prix, groupe, loyer);
	}

	public int loyer()
    {
		int loyer = 400;
		Joueur prop = this.proprietaire();
		int nb = 0;
		for (Propriete p : this.groupe().composition())
		{
			if (p.proprietaire().nom().equals(prop) && !p.hypotheque())
				nb++;
		}
		
		if (nb == 2)
			loyer = 1000;
		
		return loyer * TirerDes.DERNIER_LANCER;
    }
}