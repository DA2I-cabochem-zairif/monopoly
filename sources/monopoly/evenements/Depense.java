package monopoly.evenements ;

import java.util.*;

import javax.swing.JOptionPane;

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
    
    public void setCible(Joueur j)
    {
    	this.cible = j;
    }

	public void executer()
	{
		if (this.cible.payer(this.somme))
		{
			JOptionPane.showMessageDialog(null, this.cible.nom()+" a payé "+somme+" euros");
		}
		else
		{
			int diff = this.somme - this.cible.especes();
			JOptionPane.showMessageDialog(null, "Il manque "+diff+" euros à "+this.cible.nom());
			if (this.cible.titres().size() == 0)
			{
				this.cible.eliminer();
				JOptionPane.showMessageDialog(null, this.cible.nom()+" n'a pas les fonds suffisants, il est éliminé !!! car pas de prop");
			}
			else
			{
				// trier ici du prix d'achat de plus élevé au moins élevé
				/*ArrayList<Integer> prix = new ArrayList<Integer>();
				HashMap<Integer, Propriete> tri = new HashMap<Integer, Propriete>();
				ArrayList<Propriete> nouvelle = new ArrayList<Propriete>();
				for (Propriete p : this.cible.titres())
				{
					tri.put(p.prixAchat(), p);
					prix.add(p.prixAchat());
				}
				Collections.sort(prix, Collections.reverseOrder());
				for (Integer i : prix)
				{
					nouvelle.add(tri.get(i));
				}
				this.cible.titres().clear();
				this.cible.titres().addAll(nouvelle);*/
				Iterator<Propriete> it = this.cible.titres().iterator();
				Propriete p = it.next();
				while (this.cible.especes() <= this.somme && !this.cible.elimine())
				{
					if (it.hasNext())
					{
						p = it.next();
						while (p.niveauImmobilier() > 0 && this.cible.especes() <= this.somme && !p.hypotheque())
						{
							if (p.detruire())
							{
								JOptionPane.showMessageDialog(null, p.proprietaire().nom()+" vend une maison. Nb Maisons : "+p.niveauImmobilier());
							}
						}
						if (this.cible.especes() <= this.somme && !p.hypotheque())
						{
							p.hypothequer();
							JOptionPane.showMessageDialog(null, this.cible.nom()+" hypothèque "+p.nom()+" et récupère "+p.prixAchat() / 2+" euros.");
						}
					}
					else
					{
						this.cible.eliminer();
						JOptionPane.showMessageDialog(null, this.cible.nom()+" n'a pas les fonds suffisants, il est éliminé car plus il n'a plus rien à hypothéquer !");
					}
				}
				if (this.cible.payer(this.somme))
				{
					JOptionPane.showMessageDialog(null, this.cible.nom()+" a payé "+somme+" euros");
				}
				else
				{
					diff = this.somme - this.cible.especes();
					JOptionPane.showMessageDialog(null, "Il manque "+diff+" euros à "+this.cible.nom());
					if (!this.cible.elimine())
					{
						this.cible.eliminer();
						JOptionPane.showMessageDialog(null, this.cible.nom()+" n'a pas les fonds suffisants, il est éliminé car il n'a plus d'argent !");
					}
				}
			}
		}
	}
	
	public String toString()
	{
		return this.nom+". Somme à débourser : "+this.somme;
	}
}