package monopoly.proprietes ;

import monopoly.jeu.Joueur ;
import monopoly.jeu.Case ;

public class Terrain extends UnePropriete
{
    private int prixMaison;
    
    public Terrain(int prixMaison, Case pos, int prix, Groupe groupe, int[] loyer)
    {
	super(pos, prix, groupe, loyer);
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
	if (this.proprietaire().payer(this.prixMaison) && this.niveauImmobilier() < 5)
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
	    this.proprietaire().verser(this.prixMaison / 2);
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
}