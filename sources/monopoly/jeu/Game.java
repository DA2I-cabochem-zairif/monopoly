package monopoly.jeu;

import java.io.*;

import javax.swing.*;

import java.util.*;

import monopoly.evenements.*;
import monopoly.proprietes.*;

public class Game
{
	private List<Case> lesCases = new ArrayList<Case>();
	public static List<Carte> lesCartesChances = new ArrayList<Carte>();
	public static List<Carte> lesCartesCC = new ArrayList<Carte>();
	private List<Groupe> lesGroupes = new ArrayList<Groupe>();
	private List<Joueur> lesJoueurs = new ArrayList<Joueur>();
	private List<String[]> paramsMonop = new ArrayList<String[]>();
	private List<String[]> paramsCartes = new ArrayList<String[]>();
	private List<String[]> joueursSaved = new ArrayList<String[]>();
	private List<String[]> titresSaved = new ArrayList<String[]>();
	public static Case PRISON;
	public static Case DEPART;
	public static List<Case> LES_CASES;
	public static List<Carte> LES_CHANCES;
	public static List<Carte> LES_CC;
	
	public Game()
	{
		this.creerParamsMonop("cartes.csv", this.paramsCartes);
		this.creerParamsMonop("monopoly.csv", this.paramsMonop);
		this.creerParamsMonop("partie.joueurs", this.joueursSaved);
		this.creerParamsMonop("partie.titres", this.titresSaved);
		this.creerGroupes();
		this.creerCases();
		this.creerCartes();
		this.creerEvents();
		this.creerJoueurs();
		this.editTitres();
	}
	
	public List<Carte> lesChances()
	{
		return this.lesCartesChances;
	}
	
	public List<Carte> lesCC()
	{
		return this.lesCartesCC;
	}
	
	public void editTitres()
	{
		for (String[] list : this.titresSaved)
		{
			UnePropriete p = (UnePropriete)this.lesCases.get(0).get(Integer.parseInt(list[0])).propriete();
			if (list[1].equals("oui"))
			{
				p.hypothequer();
				p.proprietaire().payer(p.prixAchat() / 2);
			}
			p.setNiveauImmobilier(Integer.parseInt(list[2]));
		}
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
			if (c.nom().equals("Prison"))
			{
				Game.PRISON = c;
			}
			if (c.nom().equals("Allez en prison"))
			{
				c.setEvent(new Emprisonnement("Go prison"));
			}
			if (c.nom().equals("Départ"))
			{
				Game.DEPART = c;
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
				if (list[1].equals("CC"))
				{
					this.lesCartesCC.add(new Carte(Integer.parseInt(list[0]), list[1], list[2], d, list[3]));
				}
				else
				{
					this.lesCartesChances.add(new Carte(Integer.parseInt(list[0]), list[1], list[2], d, list[3]));
				}
			}
			else if (list[3].equals("dépense"))
			{
				Depense d = new Depense(list[2], Integer.parseInt(list[4]));
				if (list[1].equals("CC"))
				{
					this.lesCartesCC.add(new Carte(Integer.parseInt(list[0]), list[1], list[2], d, list[3]));
				}
				else
				{
					this.lesCartesChances.add(new Carte(Integer.parseInt(list[0]), list[1], list[2], d, list[3]));
				}
			}
			else if (list[3].equals("déplacement relatif"))
			{
				DeplacementRelatif d = new DeplacementRelatif(list[2], Integer.parseInt(list[4]), this.lesCases, this);
				if (list[1].equals("CC"))
				{
					this.lesCartesCC.add(new Carte(Integer.parseInt(list[0]), list[1], list[2], d, list[3]));
				}
				else
				{
					this.lesCartesChances.add(new Carte(Integer.parseInt(list[0]), list[1], list[2], d, list[3]));
				}
			}
			else if (list[3].equals("prison"))
			{
				Emprisonnement e = new Emprisonnement(list[2]);
				if (list[1].equals("CC"))
				{
					this.lesCartesCC.add(new Carte(Integer.parseInt(list[0]), list[1], list[2], e, list[3]));
				}
				else
				{
					this.lesCartesChances.add(new Carte(Integer.parseInt(list[0]), list[1], list[2], e, list[3]));
				}
			}
			else if (list[3].equals("recette"))
			{
				Recette r = new Recette(list[2], Integer.parseInt(list[4]));
				if (list[1].equals("CC"))
				{
					this.lesCartesCC.add(new Carte(Integer.parseInt(list[0]), list[1], list[2], r, list[3]));
				}
				else
				{
					this.lesCartesChances.add(new Carte(Integer.parseInt(list[0]), list[1], list[2], r, list[3]));
				}
			}
			else if (list[3].equals("frais immo"))
			{
				String[] frais = list[4].split(",");
				int sommes[] = new int[2];
				sommes[0] = Integer.parseInt(frais[0]);
				sommes[1] = Integer.parseInt(frais[1]);
				FraisImmo fr = new FraisImmo(list[2], sommes);
				if (list[1].equals("CC"))
				{
					this.lesCartesCC.add(new Carte(Integer.parseInt(list[0]), list[1], list[2], fr, list[3]));
				}
				else
				{
					this.lesCartesChances.add(new Carte(Integer.parseInt(list[0]), list[1], list[2], fr, list[3]));
				}
			}
			else if (list[3].equals("cadeau"))
			{
				Cadeau c = new Cadeau(list[2], Integer.parseInt(list[4]));
				if (list[1].equals("CC"))
				{
					this.lesCartesCC.add(new Carte(Integer.parseInt(list[0]), list[1], list[2], c, list[3]));
				}
				else
				{
					this.lesCartesChances.add(new Carte(Integer.parseInt(list[0]), list[1], list[2], c, list[3]));
				}
			}
			else if (list[3].equals("bonus"))
			{
				
			}
			else if (list[3].equals("choix"))
			{
				
			}
		}
	}
	
	public void creerJoueurs()
	{
		if (JOptionPane.showConfirmDialog(null, "Reprendre une partie sauvegardée ?") != JOptionPane.YES_OPTION)
		{
			this.lesJoueurs.clear();
			this.joueursSaved.clear();
			this.titresSaved.clear();
		}
		if (this.joueursSaved.size() == 0)
		{
			String listeJoueurs = "";
			int num = 1;
			while (JOptionPane.showConfirmDialog(null, "Créer un joueur ?\nJoueurs présents :\n"+listeJoueurs) == JOptionPane.YES_OPTION)
			{
				String nom = JOptionPane.showInputDialog("Nom du joueur : ");
				JOptionPane.showMessageDialog(null, "Création joueur "+nom);
				listeJoueurs += "- "+nom+"\n";
				this.lesJoueurs.add(new PersoJoueur(num, nom, this));
				num++;
			}
		}
		else
		{
			for (String[] list : this.joueursSaved)
			{
				PersoJoueur pj = new PersoJoueur(Integer.parseInt(list[0]), list[1], this.lesCases.get(Integer.parseInt(list[3]) - 1), this);
				if (list[4].equals("oui"))
				{
					pj.emprisonner();
				}
				pj.setEspeces(Integer.parseInt(list[2]));
				String[] prop = list[5].split(",");
				for (String s : prop)
				{
					pj.titres().add(this.lesCases.get(Integer.parseInt(s) - 1).propriete());
					this.lesCases.get(Integer.parseInt(s) - 1).propriete().setProprietaire(pj);
				}
				this.lesJoueurs.add(pj);
			}
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
		int cpt = 0;
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
							Depense d = new Depense(paramsEvent[0], Integer.parseInt(paramsEvent[1]), paramsEvent[0]);
							this.lesCases.get(index).setEvent(d);
						}
						else if (paramsEvent[0].equals("recette"))
						{
							Recette r = new Recette(paramsEvent[0], Integer.parseInt(paramsEvent[1]), paramsEvent[0]);
							this.lesCases.get(index).setEvent(r);
						}
						else if (paramsEvent[0].equals("carte"))
						{
							Collections.shuffle(lesCartesCC);
							Collections.shuffle(lesCartesChances);
							Carte c = null;
							if (paramsEvent[1].equals("chance"))
							{
								c = this.lesCartesChances.get(cpt);
							}
							else if (paramsEvent[1].equals("CC"))
							{
								c = this.lesCartesCC.get(cpt);
							}
							this.lesCases.get(index).setEvent(c);
						}
					}
					else
					{
						
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
		System.out.println(j.nom()+" est en prison : "+j.enPrison());
		if (j.enPrison())
		{
			Emprisonnement.TAB_PRISON.put(j, Emprisonnement.TAB_PRISON.get(j) + 1);
			if (Emprisonnement.TAB_PRISON.get(j) >= 3)
			{
				j.liberer();
				Emprisonnement.TAB_PRISON.put(j, 0);
			}
		}
		if (!j.enPrison())
		{
			TirerDes td = new TirerDes("Tirage de dés de "+j.nom(), j, this);
			td.executer();
			System.out.println(j.nom()+" est sur la case "+j.position().numero()+" : "+j.position().nom()+" et possède "+j.especes());
			System.out.println(td);
			DeplacementRelatif dr = new DeplacementRelatif("Déplace", j, td.valeur(), this.lesCases, this);
			dr.executer();
			System.out.println(j.nom()+" est sur la case "+j.position().numero()+" : "+j.position().nom());
			if (j.position().propriete() == null)
			{
				if (j.position().evenement() != null)
				{
					j.position().evenement().setCible(j);
					System.out.println(j.position().evenement());
					j.position().evenement().executer();
				}
				else
				{
					System.out.println("Bienvenue à la visite de "+j.position().nom());
				}
			}
			else
			{
				if (j.position().propriete().proprietaire() == null)
				{
					System.out.println("Voulez-vous acheter "+j.position().propriete().nom()+" ?");
					if (sc.nextLine().equals("o"))
					{
						new AchatProp(j.position().propriete(), "acheter", j).executer();
					}
				}
				else if (!j.position().propriete().proprietaire().nom().equals(j.nom()))
				{
					PayerLoyer pl = new PayerLoyer(j.position().propriete(), "Raquer", j);
					pl.executer();
				}
				else
				{
					System.out.println("Voulez-vous construire une maison ?");
					if (sc.nextLine().equals("o"))
					{
						System.out.println("Ancien loyer : "+j.position().propriete().loyer());
						if (j.position().propriete().construire())
						{
							System.out.println("Bravo vous avez une maison");
							System.out.println("Nouveau loyer : "+j.position().propriete().loyer());
						}
						else
						{
							System.out.println("Tu n'as pas assez de pognon, vive le capitalisme (ou bien tu as un monopole et tu ne peux pas construire dessus !!!)");
						}
					}
				}
			}
			if (td.faitUnDouble())
			{
				System.out.println("Vous avez fait un double : rejouez !");
				System.out.println("============================================================================");
				this.initialiserTour(j);
			}
		}
	}
	
	public void play()
	{
		/*for (Groupe g : this.lesGroupes)
		{
			System.out.println(g);
		}*/
		/*for (Carte carte : this.lesCartesCC)
		{
			System.out.println(carte+"\n");
		}
		for (Carte carte : this.lesCartesChances)
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
		
		Game.LES_CASES = this.lesCases;
		Game.LES_CC = this.lesCartesCC;
		Game.LES_CHANCES = this.lesCartesChances;
		int tour = 1;
		boolean jouer = true;
		while (jouer)
		{
			JOptionPane.showMessageDialog(new JFrame(), "====================================\n========== Début du tour "+tour+"==========\n====================================");
			for (Joueur j : this.lesJoueurs)
			{
				if (j.elimine())
					JOptionPane.showMessageDialog(new JFrame(), j.nom()+" est éliminé !");
				else
					this.jouerTour(j);
			}
			JOptionPane.showMessageDialog(new JFrame(), "====================================\n========== Fin du tour "+tour+"==========\n====================================");
			tour++;
			jouer = false;
			for (Joueur j : this.lesJoueurs)
			{
				if (!j.elimine())
				{
					jouer = true;
				}
			}
		}
	}
	
	public void jouerTour(Joueur j)
	{
		if (!j.elimine())
		{
			j.chosesAFaire().push(new TirerDes("test", j, this));
			while (!j.chosesAFaire().empty())
			{
				j.chosesAFaire().pop().executer();
			}
			JOptionPane.showMessageDialog(new JFrame(), "Topo de "+j.nom()+" : \n"+j);
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