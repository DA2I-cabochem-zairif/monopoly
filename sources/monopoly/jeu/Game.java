package monopoly.jeu;

import java.io.*;
import java.util.*;

import monopoly.evenements.*;
import monopoly.proprietes.*;

public class Game
{
	private List<Case> lesCases = new ArrayList<Case>();
	private List<Groupe> lesGroupes = new ArrayList<Groupe>();
	private List<Joueur> lesJoueurs = new ArrayList<Joueur>();
	private List<Propriete> lesProps = new ArrayList<Propriete>();
	private List<String[]> paramsMonop = new ArrayList<String[]>();
	
	private int nbCases;
	
	public Game(int nbCases)
	{
		this.nbCases = nbCases;
	}
	
	public void creerCases()
	{
		/*for (int i = 1 ; i < this.nbCases / 4 ; i++)
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
		}*/
		for (String[] list : this.paramsMonop)
		{
			this.lesCases.add(new MonoCase(Integer.parseInt(list[0]), list[1], this));
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
		this.creerParamsMonop("monopoly.csv", this.paramsMonop);
		this.creerCases();
		this.creerJoueurs();
		this.creerGroupes();
		this.creerProprietes();
		for (Case c : this.lesCases)
		{
			System.out.println(c+"\n");
		}
		/*Joueur j = this.lesJoueurs.get(0);
		System.out.println(j+"\n");
		AchatProp acheterBd = new AchatProp(this.lesProps.get(0), "Achat du boulevard truc", j);
		acheterBd.executer();
		System.out.println(j+"\n");*/
		
		/*for (String[] listS : this.paramsMonop)
		{
			for (int i = 0 ; i < listS.length ; i++)
			{
				System.out.print(listS[i]+" ");
			}
			System.out.println("\nTaille : "+listS.length);
		}*/
	}
	
	public static ArrayList<String> readFile(File file)
	{
        ArrayList<String> result = new ArrayList<String>();

        FileReader fr = null;
		try
		{
			fr = new FileReader(file);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
        BufferedReader br = new BufferedReader(fr);

        try
        {
			for (String line = br.readLine(); line != null; line = br.readLine())
			{
			    result.add(line);
			}
		}
        catch (IOException e)
        {
			e.printStackTrace();
		}

        try
        {
			br.close();
		}
        catch (IOException e)
		{
			e.printStackTrace();
		}
        try
        {
			fr.close();
		}
        catch (IOException e)
        {
			e.printStackTrace();
		}

        return result;
    }
	
	public void creerParamsMonop(String fileName, List<String[]> list)
	{
		String path = System.getProperty("user.dir" );
		File file = new File(path+"/config/"+fileName);
		ArrayList<String> csvToString = Game.readFile(file);
		for (String s : csvToString)
		{
			String[] params = s.split(";");
			//this.paramsMonop.add(params);
			list.add(params);
		}
		//this.paramsMonop.remove(0);
		list.remove(0);
	}
}