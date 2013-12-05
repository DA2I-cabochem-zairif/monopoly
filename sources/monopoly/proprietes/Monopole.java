package monopoly.proprietes;

import monopoly.jeu.Joueur;
import monopoly.jeu.Case;

public abstract class Monopole extends UnePropriete{

    public Monopole(Case pos, int prix, Groupe groupe, int[] loyer) {
		super(pos, prix, groupe, loyer);
		// TODO Auto-generated constructor stub
	}

	public boolean constructible(){
	return false;
    }

    public boolean construire(){
	return false;
    }
    public boolean detruire(){
	return false;
    }
    public abstract int loyer();
}