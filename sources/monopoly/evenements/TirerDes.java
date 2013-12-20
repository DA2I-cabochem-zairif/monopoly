package monopoly.evenements ;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import monopoly.jeu.* ;

/** Cette Classes abstraite implémente les fonctionnalités associées aux
 * événements du jeu */
public class TirerDes extends AbstractEvent
{
    private int lancer;
    private boolean faitUnDouble = false;
    private int nbLancer = 1;
    public static int DERNIER_LANCER;
    
    public TirerDes(String nom, Joueur cible)
    {
    	super(nom, cible);
    }
    
    public void executer()
    {
    	String listeAdv = "Liste des adversaires de "+this.cible.nom()+" :\n";
    	for (Joueur j : this.cible.adversaires())
    	{
    		listeAdv += j.nom()+"\n";
    	}
    	JOptionPane.showMessageDialog(null, listeAdv);
    	double lancer1 = Math.random() * 6 + 1;
    	double lancer2 = Math.random() * 6 + 1;
    	if ((int)lancer1 == (int)lancer2)
    	{
    		this.faitUnDouble = true;
    	}
    	this.lancer = (int)lancer1 + (int)lancer2;
    	//this.lancer = 4;
    	JOptionPane.showMessageDialog(new JFrame(), this.cible.nom()+" est sur "+this.cible.position().nom()+"\n"+"Dé 1 : "+(int)lancer1+" et dé 2 : "+(int)lancer2+"\n"+this.cible.nom()+" fait "+this.lancer);
    	TirerDes.DERNIER_LANCER = this.lancer;
		if (this.cible.enPrison())
		{
			Emprisonnement.TAB_PRISON.put(this.cible, Emprisonnement.TAB_PRISON.get(this.cible) + 1);
			if (Emprisonnement.TAB_PRISON.get(this.cible) >= 3)
			{
				this.cible.liberer();
				Emprisonnement.TAB_PRISON.put(this.cible, 0);
				JOptionPane.showMessageDialog(new JFrame(), this.cible.nom()+" sort de prison !");
			}
		}
    	if (this.faitUnDouble())
    	{
    		if (this.cible.enPrison())
    		{
    			this.cible.liberer();
    			JOptionPane.showMessageDialog(new JFrame(), this.cible.nom()+" a fait un double, il sort de prison !");
    		}
    		else
    		{
    			this.faitUnDouble = false;
    			this.cible.chosesAFaire().push(this);
    			JOptionPane.showMessageDialog(new JFrame(), this.cible.nom()+" a fait un double, il va donc rejouer !\nNb de lancers : "+this.nbLancer);
	    		this.nbLancer++;
	    		if (this.nbLancer == 3)
	    		{
	    			this.cible.chosesAFaire().clear();
	    			this.cible.chosesAFaire().push(new Emprisonnement("Trop de doubles tue le double", this.cible));
	    		}
    		}
    	}
    	if (!this.cible.enPrison())
    	{
    		this.cible.chosesAFaire().push(new DeplacementRelatif(this.nom, this.cible, this.lancer, Game.LES_CASES));
    	}
    }
    
    public boolean faitUnDouble()
    {
    	return this.faitUnDouble;
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