package monopoly.evenements ;

import monopoly.jeu.Joueur ;
import monopoly.jeu.Case ;

/** Cette Classes abstraite implémente les fonctionnalités associées aux
 * événements du jeu */
public class AbstractEvent implements Evenement {
    /** Intitulé de l'événement (i.e. en principe de la case associée
     * ou de la carte) */
    public String nom;
    public Joueur cible;
    public AbstractEvent(String nom, Joueur cible)
    {
	this.nom=nom;
	this.cible=cible;
    }
    public String nom(){
	return nom;
    }
    public Joueur cible(){
	return cible;
    }   
}
