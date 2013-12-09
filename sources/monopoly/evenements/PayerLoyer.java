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
    	if (!this.cible.nom().equals(this.prop.proprietaire().nom()))
    	{
    		new Depense("Verser loyer", this.cible, this.prop.loyer()).executer();
    		new Recette("Percevoir loyer", this.prop.proprietaire(), this.prop.loyer()).executer();
    	}
    }
}