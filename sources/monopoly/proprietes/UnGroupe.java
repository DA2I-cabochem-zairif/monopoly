package monopoly.proprietes ;

import java.util.*;

public class UnGroupe implements Groupe
{
    private String nom;
    private int coutImmo;
    private List<Propriete> lesProp = new ArrayList<Propriete>();
    
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
	return null;
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
}