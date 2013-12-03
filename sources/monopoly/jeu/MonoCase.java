package monopoly.jeu;

import monopoly.proprietes.Propriete ;
import monopoly.evenements.Evenement ;

public class MonoCase implements Case
{
    private int num;
    private String nom;
    private Propriete prop;
    private Evenement event;
    
    public MonoCase(int num, String nom, Propriete prop, Evenement event)
    {
	this.num = num;
	this.nom = nom;
	this.prop = prop;
	this.event = event;
    }
    
    /** Numéro de la case */
    public int numero()
    {
	return this.num;
    }
    
    /** Donne la case associée au numéro spécifié */
    public Case get(int numero)
    {
	return this;
    }
    
    /** Intitulé de la case */
    public String nom()
    {
	return this.nom;
    }
    
    /** Titre de propriété associé à la case (éventuellement
     * <code>null</code>)*/
    public Propriete propriete()
    {
	return this.prop;
    }
    
    /** Événément susceptible de se déclencher à l'arrivée sur cette
     * case (éventuellement <code>null</code>) */
    public Evenement evenement()
    {
	return this.event;
    }
}