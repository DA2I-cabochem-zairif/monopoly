package monopoly.evenements ;

import java.util.*;
import javax.swing.*;

import monopoly.jeu.*;

public class Deplacement extends AbstractEvent
{
	private Case caseCible;
	
    public Deplacement(String nom, Joueur cible, Case caseCible)
    {
		super(nom, cible);
		this.caseCible = caseCible;
	}
    
    public Deplacement(String nom, Case caseCible)
    {
		super(nom);
		this.caseCible = caseCible;
	}
    
    public void setCible(Joueur j)
    {
    	this.cible = j;
    }
    
	public void executer()
	{
		this.cible.placerSur(this.caseCible);
		//System.out.println(this.cible.nom()+" est maintenant sur "+this.cible.position().nom());
		JOptionPane.showMessageDialog(new JFrame(), this.cible.nom()+" est maintenant sur "+this.cible.position().nom());
	    Scanner sc = new Scanner(System.in);
	    if (this.cible.position().propriete() != null)
	    {
	    	if (this.cible.position().propriete().proprietaire() == null)
	    	{
	    		System.out.println("Voulez-vous acheter "+this.cible.position().propriete().nom()+" pour "+this.cible.position().propriete().prixAchat()+" ? (Liquidités disponibles : "+this.cible.especes()+" euros)");
				if (sc.nextLine().equals("o"))
				{
					this.cible.chosesAFaire().push(new AchatProp(this.cible.position().propriete(), "acheter", this.cible));
				}
	    	}
	    	else if (!this.cible.position().propriete().proprietaire().nom().equals(this.cible.nom()))
			{
	    		if (this.cible.position().propriete().hypotheque())
	    		{
	    			//System.out.println(this.cible.position().propriete().nom()+" est hypothéqué ! Pas de loyer à payer");
	    			JOptionPane.showMessageDialog(new JFrame(), this.cible.position().propriete().nom()+" est hypothéqué ! Pas de loyer à payer");
	    		}
	    		else
	    		{
	    			PayerLoyer pl = new PayerLoyer(this.cible.position().propriete(), "Raquer", this.cible);
	    			this.cible.chosesAFaire().push(pl);
	    		}
			}
			else
			{
				if (this.cible.position().propriete().constructible())
				{
					System.out.println("Voulez-vous construire une maison ?");
					if (sc.nextLine().equals("o"))
					{
						int ancienLoyer = this.cible.position().propriete().loyer();
						if (this.cible.position().propriete().construire())
						{
							System.out.println("Bravo vous avez une maison");
							System.out.println("Ancien loyer : "+ancienLoyer);
							System.out.println("Nouveau loyer : "+this.cible.position().propriete().loyer());
						}
						else
						{
							System.out.println("Tu n'as pas assez de pognon, vive le capitalisme !");
						}
					}
				}
			}
	    }
	    else
	    {
			if (this.cible.position().evenement() != null)
			{
				if (this.cible.position().evenement().getClass().toString().equals("class monopoly.evenements.Carte"))
				{
					Carte c = (Carte)this.cible.position().evenement();
					if (c.type().equals("chance"))
					{
						Collections.shuffle(Game.LES_CHANCES);
						c = Game.LES_CHANCES.get(0);
					}
					else
					{
						Collections.shuffle(Game.LES_CC);
						c = Game.LES_CC.get(0);
					}
					this.cible.position().setEvent(c);
				}
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
	
	public String toString()
	{
		return "Déplacement à la case numéro "+this.caseCible.numero()+" : "+this.caseCible.nom();
	}
}