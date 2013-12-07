package monopoly.evenements ;

import monopoly.jeu.* ;
import monopoly.jeu.* ;

/** Cette Classes abstraite implémente les fonctionnalités associées aux
 * événements du jeu */
public abstract class AbstractEvent implements Evenement
{
    /** Intitulé de l'événement (i.e. en principe de la case associée
     * ou de la carte) */
    protected String nom;
    protected Joueur cible;
    
    public AbstractEvent(String nom, Joueur cible)
    {
	this.nom=nom;
	this.cible=cible;
    }
    
    public AbstractEvent(String nom)
    {
	this.nom=nom;
    }
    
    public String nom()
    {
	return nom;
    }
    
    public Joueur cible()
    {
	return cible;
    }
    
    public void setCible(Joueur j)
    {
    	this.cible = j;
    }
    
    public abstract void executer();
}
