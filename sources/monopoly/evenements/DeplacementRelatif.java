package monopoly.evenements;

import java.util.*;

import monopoly.jeu.*;

public class DeplacementRelatif extends AbstractEvent
{
	private int changement;
	private List<Case> lesCases;
	
	public DeplacementRelatif(String nom, int changement, List<Case> lesCases)
	{
		super(nom);
		this.lesCases = lesCases;
		this.changement = changement;
	}
	
	public DeplacementRelatif(String nom, Joueur cible, int changement)
	{
		super(nom, cible);
		this.changement = changement;
	}

	public void executer()
	{
		int indexActuel = this.cible.position().numero();
	    int dep = this.changement;
	    int indexCible = 0;
	    int taille = this.lesCases.size();
	    if (indexActuel + dep < 1)
	    {
	    	for (int i = indexActuel ; i > 0 ; i--)
	    	{
	    		dep++;
	    	}
	    	indexActuel = taille + dep;
	    }
	    else if (indexActuel + dep > taille)
	    {
	    	for (int i = indexActuel ; i < taille ; i++)
	    	{
	    		dep--;
	    	}
	    	indexActuel = dep;
	    }
	    else
	    {
	    	indexCible = indexActuel + dep;
	    }
	    
	    this.cible.placerSur(this.lesCases.get(indexCible - 1));
	}
	
	public String toString()
	{
		return this.nom+". Nouvelle case : ";
	}
}