package monopoly.proprietes;

import monopoly.jeu.Joueur;
import monopoly.jeu.Case;

public abstract class Monopole extends UnePropriete{

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