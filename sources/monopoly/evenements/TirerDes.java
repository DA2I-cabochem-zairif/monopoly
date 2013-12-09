package monopoly.evenements ;

import monopoly.jeu.* ;

/** Cette Classes abstraite implémente les fonctionnalités associées aux
 * événements du jeu */
public class TirerDes extends AbstractEvent
{
    private int lancer;
    private boolean faitUnDouble = false;
    public static int DERNIER_LANCER;
    
    public TirerDes(String nom, Joueur cible)
    {
    	super(nom, cible);
    }
    
    public void executer()
    {
    	double lancer1 = Math.random() * 6 + 1;
    	double lancer2 = Math.random() * 6 + 1;
    	if ((int)lancer1 == (int)lancer2)
    	{
    		this.faitUnDouble = true;
    	}
    	System.out.println("Dé 1 : "+(int)lancer1+" et dé 2 : "+(int)lancer2);
    	this.lancer = (int)lancer1 + (int)lancer2;
    	//this.lancer = 30;
    	TirerDes.DERNIER_LANCER = this.lancer;
    	this.cible.chosesAFaire().push(new DeplacementRelatif(this.nom, this.cible, this.lancer, Game.LES_CASES));
    }
    
    public boolean faitUnDouble()
    {
    	return this.faitUnDouble;
    }
    
    public int valeur()
    {
	return this.lancer;
    }
    
    public String toString()
    {
    	return this.cible.nom()+" fait "+this.lancer;
    }
}