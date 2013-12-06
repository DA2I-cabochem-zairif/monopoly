package monopoly.jeu;

import java.util.*;

import monopoly.evenements.AchatProp;
import monopoly.proprietes.*;

public class Game
{
	private List<Case> lesCases = new ArrayList<Case>();
	private List<Groupe> lesGroupes = new ArrayList<Groupe>();
	private List<Joueur> lesJoueurs = new ArrayList<Joueur>();
	private List<Propriete> lesProps = new ArrayList<Propriete>();
	private int nbCases;
	
	public Game(int nbCases)
	{
		this.nbCases = nbCases;
	}
	
	public void creerCases()
	{
		for (int i = 1 ; i < this.nbCases / 4 ; i++)
		{
			MonoCase mc = new MonoCase(i, "Voici la case "+i, this);
			this.lesCases.add(mc);
		}
		
		for (int i = this.nbCases / 4 ; i < this.nbCases / 2 ; i++)
		{
			MonoCase mc = new MonoCase(i, "Voici la case "+i, this);
			this.lesCases.add(mc);
		}
		
		for (int i = this.nbCases / 2 ; i <= this.nbCases ; i++)
		{
			MonoCase mc = new MonoCase(i, "Voici la case "+i, this);
			this.lesCases.add(mc);
		}
	}
	
	public void creerGroupes()
	{
		Groupe bleuCiel = new UnGroupe("Bleu Ciel", 100);
		Groupe bleuRoi = new UnGroupe("Bleu Ciel", 200);
		Groupe compagnies = new UnGroupe("Compagnies", 300);
		Groupe gares = new UnGroupe("Gares", 400);
		Groupe jaune = new UnGroupe("Jaune", 500);
		Groupe mauve = new UnGroupe("Mauve", 600);
		Groupe orange = new UnGroupe("Orange", 700);
		Groupe rouge = new UnGroupe("Rouge", 800);
		Groupe vert = new UnGroupe("Vert", 900);
		Groupe violet = new UnGroupe("Violet", 1000);
		
		this.lesGroupes.add(bleuCiel);
		this.lesGroupes.add(bleuRoi);
		this.lesGroupes.add(compagnies);
		this.lesGroupes.add(gares);
		this.lesGroupes.add(jaune);
		this.lesGroupes.add(mauve);
		this.lesGroupes.add(orange);
		this.lesGroupes.add(rouge);
		this.lesGroupes.add(vert);
		this.lesGroupes.add(violet);
	}
	
	public void creerProprietes()
	{
		int[] loyers = new int[5];
		loyers[0] = 100; loyers[1] = 200; loyers[2] = 300; loyers[3] = 400; loyers[4] = 500;
		int[] loyers2 = new int[5];
		loyers2[0] = 100; loyers2[1] = 200; loyers2[2] = 300; loyers2[3] = 400; loyers2[4] = 500;
		Terrain bd = new Terrain(1500, this.lesCases.get(4), 500, this.lesGroupes.get(0), loyers);
		Terrain av = new Terrain(1800, this.lesCases.get(16), 700, this.lesGroupes.get(0), loyers2);
		this.lesProps.add(bd);
		this.lesProps.add(av);
	}
	
	public void creerEvents()
	{
		
	}
	
	public void creerJoueurs()
	{
		for (int i = 1 ; i <= 4 ; i++)
		{
			this.lesJoueurs.add(new PersoJoueur(i, "Joueur "+i, this.lesCases.get(0), this));
		}
	}
	
	public List<Case> lesCases()
	{
		return this.lesCases;
	}
	
	public List<Joueur> lesJoueurs()
	{
		return this.lesJoueurs;
	}
	
	public void play()
	{
		this.creerCases();
		this.creerJoueurs();
		this.creerGroupes();
		this.creerProprietes();
		Joueur j = this.lesJoueurs.get(0);
		System.out.println(j+"\n");
		AchatProp acheterBd = new AchatProp(this.lesProps.get(0), "Achat du boulevard truc", j);
		acheterBd.executer();
	}
}