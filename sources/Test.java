import monopoly.evenements.*;
import monopoly.jeu.*;
import monopoly.proprietes.*;

public class Test
{
	public static void main(String[] args)
	{
		// Tests sur les évenements et les joueurs
		/*PersoJoueur pj = new PersoJoueur(1, "Bob");
		PersoJoueur pj2 = new PersoJoueur(2, "Lennon");
		
		int[] loyers = new int[5];
		loyers[0] = 100 ; loyers[1] = 200 ; loyers[2] = 300 ; loyers[3] = 400 ; loyers[4] = 500 ; 
		Terrain t = new Terrain(300, new MonoCase(2, "Case 2"), 1500, new UnGroupe("Nom d'un groupe", 1000), loyers);
		//MonoCase c2 = new MonoCase(6, "Case 6");
		
		AchatProp ap = new AchatProp(t, "Transaction", pj);
		Depense d = new Depense("Fric", pj, 148501);
		System.out.println(pj);
		ap.executer();
		d.executer();
		//Deplacement d = new Deplacement("Dep", pj, new MonoCase(3, "Viens ici mon mignon"));
		/*TirerDes td = new TirerDes("Lance", pj);
		td.executer();*/
		//d.executer();
		/*System.out.println(pj);
		Recette r = new Recette("Recette", pj, 10000);
		r.executer();
		System.out.println(pj);
		Depense test = new Depense("Je suis une carte", pj, 1000);
		Carte c = new Carte(1, new UnGroupe("CC", 10), "La carte", pj, test);
		c.executer();
		System.out.println(pj);
		/*System.out.println(pj2);
		PayerLoyer pl = new PayerLoyer(t, "Raquer", pj2);
		pl.executer();
		System.out.println(pj);
		System.out.println(pj2);*/
		/*Emprisonnement e = new Emprisonnement("Prison", pj);
		e.executer();
		System.out.println(pj.enPrison());*/
		Game g = new Game(20);
		g.play();
	}
}