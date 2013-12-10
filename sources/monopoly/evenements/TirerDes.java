package monopoly.evenements ;

import monopoly.jeu.* ;

/** Cette Classes abstraite implémente les fonctionnalités associées aux
 * événements du jeu */
public class TirerDes extends AbstractEvent
{
    private int lancer;
    private boolean faitUnDouble = false;
    private int nbLancer = 1;
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
    	System.out.println(this.cible.nom()+" est sur "+this.cible.position().nom());
    	System.out.println("Dé 1 : "+(int)lancer1+" et dé 2 : "+(int)lancer2);
    	this.lancer = (int)lancer1 + (int)lancer2;
    	//this.lancer = 4;
    	TirerDes.DERNIER_LANCER = this.lancer;
    	System.out.println(this.cible.nom()+" fait "+this.lancer);
		if (this.cible.enPrison())
		{
			Emprisonnement.TAB_PRISON.put(this.cible, Emprisonnement.TAB_PRISON.get(this.cible) + 1);
			if (Emprisonnement.TAB_PRISON.get(this.cible) >= 3)
			{
				this.cible.liberer();
				Emprisonnement.TAB_PRISON.put(this.cible, 0);
				System.out.println(this.cible.nom()+" sort de prison !");
			}
		}
    	if (this.faitUnDouble())
    	{
    		if (this.cible.enPrison())
    		{
    			this.cible.liberer();
    			System.out.println(this.cible.nom()+" a fait un double, il sort de prison !");
    		}
    		else
    		{
    			this.faitUnDouble = false;
    			this.cible.chosesAFaire().push(this);
	    		System.out.println(this.cible.nom()+" a fait un double, il va donc rejouer !");
	    		System.out.println("Nb de lancers : "+this.nbLancer);
	    		this.nbLancer++;
	    		if (this.nbLancer == 3)
	    		{
	    			this.cible.chosesAFaire().clear();
	    			this.cible.chosesAFaire().push(new Emprisonnement("Trop de doubles tue le double", this.cible));
	    		}
    		}
    		System.out.println();
    	}
    	if (!this.cible.enPrison())
    	{
    		this.cible.chosesAFaire().push(new DeplacementRelatif(this.nom, this.cible, this.lancer, Game.LES_CASES));
    	}
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