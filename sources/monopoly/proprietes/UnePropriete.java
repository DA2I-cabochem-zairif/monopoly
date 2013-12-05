package monopoly.proprietes ;

import monopoly.jeu.Joueur ;
import monopoly.jeu.Case ;

/** Cette interface décrit les fonctionnalités associées à tout titre
 * de propriété */
public abstract class UnePropriete implements Propriete
{
    private Case position;
    private String nom;
    private boolean estHypotheque;
    private Joueur proprietaire;
    protected int[] loyer;
    private int prix;
    private int valeurHypo;
    private Groupe groupe;
    protected int niveauImmo;
    
    public UnePropriete(Case pos, int prix, Groupe groupe, int[] loyer)
    {
	this.position = pos;
	this.nom = this.position.nom();
	this.estHypotheque = false;
	this.prix = prix;
	this.groupe = groupe;
	this.niveauImmo = 0;
	this.valeurHypo = this.prix / 2;
	this.loyer = loyer;
    }
    
    /** La case du plateau de jeu associée à ce titre de propriété */
    public Case position()
    {
	return this.position;
    }
    
    /** Nom de la propriété (le même que la case en principe) */
    public String nom()
    {
	return this.nom;
    }

    public void setProprietaire(Joueur j){
	this.proprietaire = j;
    }
    
    /** Indique si la propriété est hypothéquée */
    public boolean hypotheque()
    {
	return this.estHypotheque;
    }
    
    /** Hypothèque la propriété (en procurant ainsi des liquidités au
     * propriétaire pour une valeur moitié du prix d'achat) */
    public void hypothequer()
    {
	this.estHypotheque = true;
	this.proprietaire.verser(this.valeurHypo);
    }
    
    /** Lève l'hypothèque (si le joueur possède les liquidités
     * suffisantes soit la valeur hypothécaire + 10%)
     * @return true si l'hypothèque est levée, false sinon */
    public boolean deshypothequer()
    {
	boolean leve = false;
	if (this.proprietaire.especes() > (this.valeurHypo + (this.prix * 0.1)) && this.estHypotheque)
	{
	    leve = true;
	    this.estHypotheque = false;
	    this.proprietaire.payer((int)(this.valeurHypo + (this.prix * 0.1)));
	}
	
	return leve;
    }
    
    /** Prix d'achat */
    public int prixAchat()
    {
	return this.prix;
    }   
    
    /** Le groupe auquel est rattachée la propriété */
    public Groupe groupe()
    {
	return this.groupe;
    }
    
    /** Indique si la propriété est constructible */
    public abstract boolean constructible();
    
    /** Construit un bâtiment sur cette propriété si c'est possible
     * (cf. règles de constructibilité et liquidités du joueur).
     * @return true si la construction a pu être réalisée, false
     * sinon */
    public abstract boolean construire();
    
    /** Détruit un bâtiment sur cette propriété si c'est possible (et
     * reverse alors au joueur la moitié du prix d'achat des
     * bâtiments) 
     * @return true si un bâtiment a été détruit, false sinon */
    public abstract boolean detruire() ;
    
    /** Propriétaire du titre (éventuellement <code>null</code>) */
    public Joueur proprietaire()
    {
	return this.proprietaire;
    }
    
    /** Montant du loyer à percevoir */
    public abstract int loyer();
    
    /** Niveau des constructions (0 = terrain nu, 1 à 4 = nb de
     * maisons, 5 = hôtel)  */
    public int niveauImmobilier()
    {
	return this.niveauImmo;
    }
    
    public String toString()
    {
    	String prop = "Nom de la propriété : "+this.nom+"\nPrix : "+this.prix+"\nLa case : "+this.position+"\nLoyers : "+this.loyer+"\n";
    	if (this.proprietaire != null)
    		prop += this.proprietaire.nom();
    	return prop;
    }
}
