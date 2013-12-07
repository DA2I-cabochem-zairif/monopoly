package monopoly.proprietes ;

import java.util.*;

import monopoly.jeu.Case;
import monopoly.jeu.Game;

public class UnGroupe implements Groupe
{
    private String nom;
    private int coutImmo;
    private Game g;
    private List<Propriete> lesProp = new ArrayList<Propriete>();
    
    public UnGroupe(String nom, int cout)
    {
	this.nom = nom;
	this.coutImmo = cout;
    }
    
    public UnGroupe(String nom, int cout, Game g)
    {
	this.nom = nom;
	this.coutImmo = cout;
	this.g = g;
    }
    
    /** L'intitulé du groupe */
    public String nom()
    {
	return this.nom;
    }
    
    /** Le prix des constructions pour ce groupe */
    public int coutImmo()
    {
	return this.coutImmo;
    }
    
    /** La liste des propriétés qui le composent */
    public List<Propriete> composition()
    {
	return this.lesProp;
    }
    
    /** Retourne le groupe dont le nom est spécifié */
    public Groupe get(String nom)
    {
    	Groupe dest = null;
    	for (Groupe g : this.g.lesGroupes())
    	{
    		if (g.nom() == nom)
    			dest = g;
    	}
    	return dest;
    }
    
    /** Indique si le groupe appartient entièrement à un seul joueur */
    public boolean proprietaireUnique()
    {
	boolean prop = true;
	for (int i = 1 ; i < lesProp.size() ; i++)
	{
	    if (lesProp.get(i).proprietaire().nom().equals(lesProp.get(i - 1).proprietaire().nom()))
	    {
		prop = false;
	    }
	}
	
	return prop;
    }
    
    public String toString()
    {
	return "Nom du groupe : "+this.nom+"\nCout immobilier : "+this.coutImmo;
    }
    
    public static void main(String [] args)
    {
	UnGroupe ug = new UnGroupe("Nom d'un groupe", 1000);
	System.out.println(ug);
    }
}