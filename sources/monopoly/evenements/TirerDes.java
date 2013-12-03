package monopoly.evenements ;

import monopoly.jeu.* ;

/** Cette Classes abstraite implémente les fonctionnalités associées aux
 * événements du jeu */
public class TirerDes extends AbstractEvent
{
    private int lancer;
    public static int DERNIER_LANCER;
    
    public TirerDes(String nom, Joueur cible)
    {
	super(nom, cible);
	double lancer1 = Math.random() * 6 + 1;
	double lancer2 = Math.random() * 6 + 1;
	lancer = (int)lancer1 + (int)lancer2;
	TirerDes.DERNIER_LANCER = lancer;
    }

    public void executer()
    {
	Case ancienne = this.cible.position();
	int numAncienne = ancienne.numero();
	int numNouvelle = numAncienne + this.lancer;
	Case nouvelle = ancienne.get(numNouvelle);
	this.cible.placerSur(nouvelle);
    }
    
    public int valeur()
    {
	return this.lancer;
    }
    
    public static void main(String [] args)
    {
	TirerDes td = new TirerDes("osef", new PersoJoueur(1, "osef"));
	
	System.out.println(td.valeur());
    }
}