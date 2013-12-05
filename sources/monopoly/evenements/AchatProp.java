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
    
    public static void main(String [] args)
    {
	PersoJoueur pj = new PersoJoueur(1, "Bob");
	
	int[] loyers = new int[5];
	loyers[0] = 100 ; loyers[1] = 200 ; loyers[2] = 300 ; loyers[3] = 400 ; loyers[4] = 500 ; 
	Terrain t = new Terrain(300, new MonoCase(2, "Case 2"), 1500, new UnGroupe("Nom d'un groupe", 1000), loyers);
	MonoCase c2 = new MonoCase(6, "Case 6");
	
	AchatProp ap = new AchatProp(t, "Transaction", pj);
	ap.executer();
	System.out.println(pj);
	pj.placerSur(c2);
	System.out.println(pj);
    }
}