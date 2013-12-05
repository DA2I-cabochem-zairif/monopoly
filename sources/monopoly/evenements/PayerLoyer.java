package monopoly.evenements ;

import monopoly.jeu.Joueur ;
import monopoly.jeu.Case ;
import monopoly.proprietes.Propriete;

public class PayerLoyer extends AbstractEvent
{
    private Propriete prop;
    
    public PayerLoyer(Propriete prop, String nom, Joueur cible)
    {
	super(nom, cible);
	this.prop = prop;
    }
    
    public void executer()
    {
    	if (this.cible.payer(this.prop.loyer()))
    	{
    		this.prop.proprietaire().verser(this.prop.loyer());
    	}
    }
}