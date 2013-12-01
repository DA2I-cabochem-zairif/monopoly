package monopoly.jeu;


/** Classe permettant d'effecuter des methodes sur le joueurs **/

public classe UnJoueur implements Joueur
{
    private int numero;
    private String nom;
    private boolean enPrison;
    private int espece;
    private Case caseActuelle;

    /** Instancie un joueur
     * @param numero : numero du joueur
     * @param nom : nom du joueur
     * @param enPrison : indique si le joueur est en prison
     **/
    public unJoueur(int numero, String nom, boolean enPrison){
	this.numero = numero;
	this.nom = nom;
	this.enPrison = enPrison;
    }

    /** Instancie un joueur non emprisonner
     * @param numero : numero du joueur
     * @param nom : nom du joueur
     **/
    public unJoueur(int numero, String nom){
	this(numero,nom,false);
    }
    /** Retourne le numero du joueur **/
    public int numero(){
	return numero;
    }
    /** Retourne le nom du joueur **/
    public String nom(){
	reurn nom;
    }
    /** Indique si le joueur est en prison **/
    public boolean enPrison(){
	return enPrison;
    }
    
    /** Met le joueur en prison **/
    public void emprisonner(){
	enPrison = true;
    }
 
    /** Indique si le joueur est eliminer **/
    public boolean elimine(){
	if(espece==0)
	    return true;
	return false;
    }
    
    /** Elimine le joueur **/
    public void elimine(){
	espece = 0;
    }

    public boolean payer(int somme){
	if(espece >= somme){
	    espece -= somme;
	    return true;
	}
	return false;
    }
    

    /** Verse au joueur la somme spécifié **/
    public void verser(int somme){
	espece += somme;
    }

    /** Donne la case actuelle surlequel le joueur est placé **/
    public Case position(){
	return caseActuelle;
    }

    /** Positionne le joueur sur la case passé en paramatre **/
    public void placerSur(Case c){
	caseActuelle = c;
    }

}