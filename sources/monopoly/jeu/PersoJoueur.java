package monopoly.jeu;

import java.util.*;

import monopoly.evenements.Evenement;
import monopoly.proprietes.Propriete;

public class PersoJoueur
{
	private int numero;
	private String nom;
	private boolean estEnPrison = false;
	private boolean elimine = false;
	private int especes = 150000;
	private Case position;
	private List<Propriete> titres = null;
	private List<Evenement> cartes = null;
	private Stack<Evenement> chosesAFaire = null;
	
	public PersoJoueur(int numero, String nom)
	{
		this.numero = numero;
		this.nom = nom;
	}
	
	/** Le numéro du joueur */
	public int numero()
	{
		return this.numero;
	}
	
    /** Le nom du joueur */
    public String nom()
    {
    	return nom;
    }
    
    /** Indique si le joueur est emprisonné */
    public boolean enPrison()
    {
    	return this.estEnPrison;
    }
    
    /** Emprisonne le joueur */
    public void emprisonner()
    {
    	this.estEnPrison = true;
    }
    
    /** Indique si le joueur est éliminé */
    public boolean elimine()
    {
    	return this.elimine;
    }
    
    /** Élimine le joueur */
    public void eliminer()
    {
    	this.elimine = false;
    }
    
    /** Liquidités possédées par le joueur */
    public int especes()
    {
    	return this.especes;
    }
    
    /** Impose au joueur le paiement de la somme spécifiée
     * @return true si le joueur a pu payer, false sinon
     */
    public boolean payer(int somme)
    {
    	boolean paye = false;
    	if (this.especes - somme >= 0)
    	{
    		paye = true;
    		this.especes -= somme;
    	}
    	
    	return paye;
    }
    
    /** Verse au joueur la somme spécifier */
    public void verser(int somme)
    {
    	this.especes += somme;
    }
    
    /** Donne la case sur laquelle le joueur est placé */
    public Case position()
    {
		return this.position;
	}
    
    /** Place le joueur sur la case spécifiée */
    public void placerSur(Case c)
    {
    	this.position = c;
	}
    
    /** Donne la liste des autres joueurs encore en lice (tous sauf
     * <code>this</code> et les éliminés)*/
    public List<Joueur> adversaires()
    {
		return null;
	}
    
    /** Titres de propriétés possédés par le joueur */
    public List<Propriete> titres()
    {
		return this.titres;
	}
    
    /** Cartes conservées par le joueur */
    public List<Evenement> cartes()
    {
		return this.cartes;
	}
    
    /** La pile d'événements que le joueur doit subir pendant son tour
     * de jeu : si la pile est vide, le joueur a terminé son tour ; sinon il doit
     * dépiler un événement pour l'exécuter. Il peut arriver qu'un événement manipule
     * cette pile (par exemple "Aller en prison" termine le tour du joueur même s'il lui
     * restait théoriquement des choses à faire) */
    public Stack<Evenement> chosesAFaire()
    {
		return this.chosesAFaire;
	}
}