package monopoly.proprietes;

import monopoly.jeu.Joueur;
import monopoly.jeu.Case;

public abstract class Gare extends UnePropriete{
    public int loyer(){
	return loyer*Math.pow(2,NiveauImmo);
    }
}