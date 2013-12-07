package monopoly.evenements ;

import java.util.*;

import monopoly.jeu.Joueur ;
import monopoly.jeu.Case ;
import monopoly.proprietes.Propriete;

public class Depense extends AbstractEvent
{
	private int somme;
	
    public Depense(String nom, Joueur cible, int somme)
    {
		super(nom, cible);
		this.somme = somme;
	}
    
    public Depense(String nom, int somme)
    {
		super(nom);
		this.somme = somme;
	}

	public void executer()
	{
		if (this.cible.payer(this.somme))
		{
			System.out.println(this.cible.nom()+" a payé "+somme+" euros");
		}
		else
		{
			int diff = this.somme - this.cible.especes();
			System.out.println("Il manque "+diff+" euros à "+this.cible.nom());
			if (this.cible.titres().size() == 0)
			{
				this.cible.eliminer();
				System.out.println(this.cible.nom()+" n'a pas les fonds suffisants, il est éliminé !!!");
			}
			else
			{
				Iterator<Propriete> it = this.cible.titres().iterator();
				Propriete p = it.next();
				while (this.cible.especes() <= this.somme)
				{
					// @todo : Hypothéquer en fonction de la valeur d'achat.
					while(p.hypotheque())
					{
						p = it.next();
					}
					p.hypothequer();
					System.out.println(this.cible.nom()+" hypothèque "+p.nom()+" et récupère "+p.prixAchat() / 2+" euros.");
				}
				if (this.cible.payer(this.somme))
				{
					System.out.println(this.cible.nom()+" a payé "+somme+" euros");
				}
				else
				{
					diff = this.somme - this.cible.especes();
					System.out.println("Il manque "+diff+" euros à "+this.cible.nom());
					this.cible.eliminer();
				}
			}
		}
	}
	
	public String toString()
	{
		return this.nom+". Somme à débourser : "+this.somme;
	}
}