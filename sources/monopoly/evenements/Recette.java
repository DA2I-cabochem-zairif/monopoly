package monopoly.evenements ;

import java.util.Iterator;

import monopoly.jeu.Joueur ;
import monopoly.proprietes.Propriete;

public class Recette extends AbstractEvent
{
	private int somme;
	
    public Recette(String nom, Joueur cible, int somme)
    {
		super(nom, cible);
		this.somme = somme;
	}
    
    public Recette(String nom, int somme)
    {
    	super(nom);
    	this.somme = somme;
    }

	public void executer()
	{
		System.out.println("Versement de "+this.somme+" à "+this.cible.nom());
		this.cible.verser(this.somme);
		System.out.println(this.cible.nom()+" possède "+this.cible.especes()+" euros.");
		Iterator<Propriete> it = this.cible.titres().iterator();
		Propriete p = it.next();
		while (p.deshypothequer())
		{
			System.out.println(this.cible.nom()+" a récupéré son bien : "+p.nom());
			if (it.hasNext())
				p = it.next();
		}
	}
}