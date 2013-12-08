package monopoly.jeu;

import java.io.*;
import java.util.*;

import monopoly.evenements.*;
import monopoly.proprietes.*;

public class Game
{
	private List<Case> lesCases = new ArrayList<Case>();
	private List<Carte> lesCartes = new ArrayList<Carte>();
	private List<Groupe> lesGroupes = new ArrayList<Groupe>();
	private List<Joueur> lesJoueurs = new ArrayList<Joueur>();
	private List<String[]> paramsMonop = new ArrayList<String[]>();
	private List<String[]> paramsCartes = new ArrayList<String[]>();
	public static Case PRISON;
	
	public Game()
	{
		
	}
	
	public List<Groupe> lesGroupes()
	{
		return this.lesGroupes;
	}
	
	public void creerCases()
	{
		for (String[] list : this.paramsMonop)
		{
			Case c = new MonoCase(Integer.parseInt(list[0]), list[1], this);
			this.lesCases.add(c);
			if (c.nom().equals("Allez en prison"))
			{
				c.setEvent(new Emprisonnement("Go prison"));
				Game.PRISON = c;
			}
			if (list.length > 3)
			{
				if(list[3].equals("terrain"))
				{
					int[] loyer = new int[6];
					String[] stringLoyers = list[7].split(",");
					int cpt = 0;
					for (String s : stringLoyers)
					{
						loyer[cpt] = Integer.parseInt(s);
						cpt++;
					}
					Terrain t = new Terrain(list[1], Integer.parseInt(list[6]), c, Integer.parseInt(list[5]), this.lesGroupes.get(0).get(list[4]), loyer);
					c.setProp(t);
					this.lesGroupes.get(0).get(list[4]).composition().add(t);
				}
				else
				{
					Monopole m = null;
					if (list[4].equals("gares"))
					{
						int[] loyer = new int[1]; loyer[0] = 2500;
						m = new Gare(list[1], c, Integer.parseInt(list[5]), this.lesGroupes.get(0).get(list[4]), loyer);
					}
					else
					{
						int[] loyer = new int[1]; loyer[0] = 2500;
						m = new Compagnie(list[1], c, Integer.parseInt(list[5]), this.lesGroupes.get(0).get(list[4]), loyer);
					}
					c.setProp(m);
					this.lesGroupes.get(0).get(list[4]).composition().add(m);
				}
			}
		}
	}
	
	public void creerCartes()
	{
		for (String[] list : this.paramsCartes)
		{
			if (list[3].equals("aller") || list[3].equals("revenir"))
			{
				Deplacement d = new Deplacement(list[2], this.lesCases.get(Integer.parseInt(list[4]) - 1));
				this.lesCartes.add(new Carte(Integer.parseInt(list[0]), list[1], list[2], d));
			}
			else if (list[3].equals("dépense"))
			{
				Depense d = new Depense(list[2], Integer.parseInt(list[4]));
				this.lesCartes.add(new Carte(Integer.parseInt(list[0]), list[1], list[2], d));
			}
			else if (list[3].equals("déplacement relatif"))
			{
				DeplacementRelatif d = new DeplacementRelatif(list[2], Integer.parseInt(list[4]), this.lesCases);
				this.lesCartes.add(new Carte(Integer.parseInt(list[0]), list[1], list[2], d));
			}
			else if (list[3].equals("prison"))
			{
				Emprisonnement e = new Emprisonnement(list[2]);
				this.lesCartes.add(new Carte(Integer.parseInt(list[0]), list[1], list[2], e));
			}
			else if (list[3].equals("recette"))
			{
				Recette r = new Recette(list[2], Integer.parseInt(list[4]));
				this.lesCartes.add(new Carte(Integer.parseInt(list[0]), list[1], list[2], r));
			}
			else if (list[3].equals("bonus"))
			{
				
			}
			else if (list[3].equals("cadeau"))
			{
				
			}
			else if (list[3].equals("frais immo"))
			{
				
			}
			else if (list[3].equals("choix"))
			{
				
			}
		}
	}
	
	public void creerJoueurs()
	{
		for (int i = 1 ; i <= 4 ; i++)
		{
			this.lesJoueurs.add(new PersoJoueur(i, "Joueur "+i, this.lesCases.get(0), this));
		}
	}
	
	public void creerGroupes()
	{
		for (String[] list : this.paramsMonop)
		{
			if (list.length > 3)
			{
				if (this.lesGroupes.size() == 0)
				{
					int coutImmo = 0;
					if (list[3].equals("terrain"))
					{
						coutImmo = Integer.parseInt(list[6]);
					}
					this.lesGroupes.add(new UnGroupe(list[4], coutImmo, this));
				}
				else if (this.lesGroupes.get(0).get(list[4]) == null)
				{
					int coutImmo = 0;
					if (list[3].equals("terrain"))
					{
						coutImmo = Integer.parseInt(list[6]);
					}
					this.lesGroupes.add(new UnGroupe(list[4], coutImmo, this));
				}
			}
		}
	}
	
	public void creerEvents()
	{
		for (String[] list : this.paramsMonop)
		{
			if (list.length > 2)
			{
				if (list[2].length() > 0)
				{
					int index = Integer.parseInt(list[0]) - 1;
					if (list[2].contains(","))
					{
						String[] paramsEvent = list[2].split(",");
						if (paramsEvent[0].equals("dépense"))
						{
							Evenement d = new Depense(paramsEvent[0], Integer.parseInt(paramsEvent[1]));
							this.lesCases.get(index).setEvent(d);
						}
						else if (paramsEvent[0].equals("recette"))
						{
							Evenement r = new Recette(paramsEvent[0], Integer.parseInt(paramsEvent[1]));
							this.lesCases.get(index).setEvent(r);
						}
						else if (paramsEvent[0].equals("carte"))
						{
							//System.out.println(paramsEvent[0]+" et "+paramsEvent[1]);
						}
					}
					else
					{
						//System.out.println("créer l'event "+list[2]);
					}
				}
			}
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
	
	public void initialiserTour(Joueur j)
	{
		Scanner sc = new Scanner(System.in);
		TirerDes td = new TirerDes("Tirage de dés de "+j.nom(), j);
		td.executer();
		System.out.println(j.nom()+" est sur la case "+j.position().numero()+" : "+j.position().nom());
		System.out.println(td);
		DeplacementRelatif dr = new DeplacementRelatif("Déplace", j, td.valeur(), this.lesCases);
		dr.executer();
		System.out.println(j.nom()+" est sur la case "+j.position().numero()+" : "+j.position().nom());
		if (j.position().propriete() == null)
		{
			System.out.println("Rien à acheter ici");
			if (j.position().evenement() != null)
			{
				j.position().evenement().setCible(j);
				j.position().evenement().executer();
			}
		}
		else
		{
			if (j.position().propriete().proprietaire() == null)
			{
				System.out.println("Voulez-vous acheter "+j.position().propriete().nom()+" ?");
				if (sc.nextLine().equals("o"))
					new AchatProp(j.position().propriete(), "acheter", j).executer();
			}
			else if (!j.position().propriete().nom().equals(j.nom()))
			{
				PayerLoyer pl = new PayerLoyer(j.position().propriete(), "Raquer", j);
				pl.executer();
			}
			else
			{
				/* construire maison
				 * vérifier que le loyer change bien
				 */
			}
		}
	}
	
	public void play()
	{
		this.creerParamsMonop("monopoly.csv", this.paramsMonop);
		this.creerParamsMonop("cartes.csv", this.paramsCartes);
		this.creerGroupes();
		this.creerCases();
		this.creerCartes();
		this.creerEvents();
		this.creerJoueurs();
		/*for (Groupe g : this.lesGroupes)
		{
			System.out.println(g);
		}*/
		/*for (Carte carte : this.lesCartes)
		{
			System.out.println(carte+"\n");
		}*/
		/*for (Case c : this.lesCases)
		{
			System.out.println(c+"\n");
		}*/
		/*for (Joueur j : this.lesJoueurs)
		{
			System.out.println(j);
		}*/
		Joueur j1 = this.lesJoueurs.get(0);
		Joueur j2 = this.lesJoueurs.get(1);
		while (!j1.elimine() || !j2.elimine())
		{
			System.out.println(("========== Au tour de "+j1.nom()+" =========="));
			this.initialiserTour(j1);
			System.out.println();
			System.out.println("Topo : "+j1);
			System.out.println();
			System.out.println(("========== Au tour de "+j2.nom()+" =========="));
			this.initialiserTour(j2);
			System.out.println();
			System.out.println("Topo : "+j2);
			System.out.println();
		}
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
			list.add(params);
		}
		list.remove(0);
	}
}