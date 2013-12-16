package monopoly.proprietes ;

import monopoly.evenements.Recette;
import monopoly.jeu.*;

public class Terrain extends UnePropriete implements Comparable
{
    private int prixMaison;
    
    public Terrain(String nom, int prixMaison, Case pos, int prix, Groupe groupe, int[] loyer)
    {
	super(nom, pos, prix, groupe, loyer);
	this.prixMaison = prixMaison;
    }
    
    public Terrain(String nom, int prixMaison, int prix, Groupe groupe, int[] loyer)
    {
	super(nom, prix, groupe, loyer);
	this.prixMaison = prixMaison;
    }
    
    /** Indique si la propriété est constructible */
    public boolean constructible()
    {
    	return this.groupe().proprietaireUnique() && !this.hypotheque();
    }
    
    /** Construit un bâtiment sur cette propriété si c'est possible
     * (cf. règles de constructibilité et liquidités du joueur).
     * @return true si la construction a pu être réalisée, false
     * sinon */
    public boolean construire()
    {
	boolean constr = false;
	if (this.proprietaire().payer(this.prixMaison) && this.niveauImmobilier() < 5 && this.constructible())
	{
	    constr = true;
	    this.niveauImmo++;
	}
	
	return constr;
    }
    
    /** Détruit un bâtiment sur cette propriété si c'est possible (et
     * reverse alors au joueur la moitié du prix d'achat des
     * bâtiments) 
     * @return true si un bâtiment a été détruit, false sinon */
    public boolean detruire()
    {
	boolean detruire = false;
	
	if (this.niveauImmobilier() > 0)
	{
	    this.niveauImmo--;
	    new Recette("detruire", this.proprietaire(), this.prixMaison / 2).executer();
	    detruire = true;
	}
	
	return detruire;
    }

     /** Montant du loyer à percevoir */
    public int loyer()
    {
	if(this.groupe().proprietaireUnique()){
	    if(niveauImmo > 0){
		return this.loyer[niveauImmo];
	    }
	    return this.loyer[niveauImmo]*2;
	}else{
	    return this.loyer[niveauImmo];
	}
    }
    
    public String toString()
    {
    	return super.toString()+"\nPrix de la maison : "+this.prixMaison+"\n";
    }
    
    public int prixMaison()
    {
    	return this.prixMaison;
    }
    
	public int compareTo(Object o)
	{
		int result = 0;
		
		Terrain t = (Terrain)o;
		if (this.prixAchat() < t.prixAchat())
		{
			result = -1;
		}
		else if (this.prixAchat() > t.prixAchat())
		{
			result = 1;
		}
		
		return result;
	}
}