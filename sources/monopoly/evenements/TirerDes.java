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
    }

    public void executer()
    {
    	double lancer1 = Math.random() * 6 + 1;
    	double lancer2 = Math.random() * 6 + 1;
    	this.lancer = (int)lancer1 + (int)lancer2;
    	TirerDes.DERNIER_LANCER = this.lancer;
		/*Case ancienne = this.cible.position();
		int numAncienne = ancienne.numero();
		int numNouvelle = numAncienne + this.valeur();
		Case nouvelle = new MonoCase(3, "Viens ici mon mignon");//ancienne.get(numNouvelle);
		this.cible.placerSur(nouvelle);*/
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