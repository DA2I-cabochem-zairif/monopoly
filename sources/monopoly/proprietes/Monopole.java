package monopoly.proprietes;

import monopoly.jeu.Joueur;
import monopoly.jeu.Case;

public abstract class Monopole extends UnePropriete
{

	public Monopole(String nom, Case pos, int prix, Groupe groupe, int[] loyer)
    {
		super(nom, pos, prix, groupe, loyer);
	}
	
	public Monopole(String nom, int prix, Groupe groupe, int[] loyer)
    {
		super(nom, prix, groupe, loyer);
	}

	public boolean constructible()
	{
	return false;
    }

    public boolean construire()
    {
	return false;
    }
    public boolean detruire()
    {
	return false;
    }
    public abstract int loyer();
}