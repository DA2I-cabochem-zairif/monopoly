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
	
	public DeplacementRelatif(String nom, Joueur cible, int changement, List<Case> lesCases)
	{
		super(nom, cible);
		this.lesCases = lesCases;
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
	    	indexCible = taille + dep;
	    }
	    else if (indexActuel + dep > taille)
	    {
	    	for (int i = indexActuel ; i < taille ; i++)
	    	{
	    		dep--;
	    	}
	    	Game.DEPART.evenement().setCible(this.cible);
	    	this.cible.chosesAFaire().push(Game.DEPART.evenement());
	    	indexCible = dep;
	    }
	    else
	    {
	    	indexCible = indexActuel + dep;
	    }
	    
	    new Deplacement(this.nom, this.cible, this.lesCases.get(indexCible - 1)).executer();
	    Scanner sc = new Scanner(System.in);
	    if (this.cible.position().propriete() != null)
	    {
	    	if (this.cible.position().propriete().proprietaire() == null)
	    	{
	    		System.out.println("Voulez-vous acheter "+this.cible.position().propriete().nom()+" ?");
				if (sc.nextLine().equals("o"))
				{
					this.cible.chosesAFaire().push(new AchatProp(this.cible.position().propriete(), "acheter", this.cible));
				}
	    	}
	    	else if (!this.cible.position().propriete().proprietaire().nom().equals(this.cible.nom()))
			{
				PayerLoyer pl = new PayerLoyer(this.cible.position().propriete(), "Raquer", this.cible);
				this.cible.chosesAFaire().push(pl);
			}
			else
			{
				System.out.println("Voulez-vous construire une maison ?");
				if (sc.nextLine().equals("o"))
				{
					System.out.println("Ancien loyer : "+this.cible.position().propriete().loyer());
					if (this.cible.position().propriete().construire())
					{
						System.out.println("Bravo vous avez une maison");
						System.out.println("Nouveau loyer : "+this.cible.position().propriete().loyer());
					}
					else
					{
						System.out.println("Tu n'as pas assez de pognon, vive le capitalisme (ou bien tu as un monopole et tu ne peux pas construire dessus !!!)");
					}
				}
			}
	    }
	    else
	    {
			if (this.cible.position().evenement() != null)
			{
				this.cible.position().evenement().setCible(this.cible);
				System.out.println(this.cible.position().evenement());
				this.cible.chosesAFaire().push(this.cible.position().evenement());
			}
			else
			{
				System.out.println("Bienvenue à la visite de "+this.cible.position().nom());
			}
	    }
	}
	
	public void setCible(Joueur j)
	{
		this.cible = j;
	}
	
	public String toString()
	{
		return this.nom+". Nouvelle case : ";
	}
}