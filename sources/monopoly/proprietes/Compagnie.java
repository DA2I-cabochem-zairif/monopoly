package monopoly.proprietes;

import monopoly.jeu.Joueur;
import monopoly.jeu.Case;

public abstract class Monopole extends UnePropriete{
    public int loyer(){
	return chosesAFaire;
    }
}