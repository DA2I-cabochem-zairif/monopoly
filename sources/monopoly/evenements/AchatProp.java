package monopoly.evenements;

import monopoly.jeu.*;
import monopoly.proprietes.*;

public class AchatProp extends AbstractEvent
{
    private Propriete prop;
    
    public AchatProp(Propriete prop, String nom, Joueur cible)
    {
	super(nom, cible);
	this.prop = prop;
    }
    
    public void executer()
    {
	if (this.cible.payer(this.prop.prixAchat()))
	{
	    this.cible.titres().add(prop);
	    this.prop.setProprietaire(this.cible);
	}
    }
    
    public Propriete propriete()
    {
	return this.prop;
    }
    
    public String toString()
    {
	return "Propriété concernée : \n"+this.prop+"\nJoueur concerné : "+this.cible+"\nNom de l'achat : "+this.nom;
    }
}