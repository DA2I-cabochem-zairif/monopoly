import monopoly.evenements.*;
import monopoly.jeu.*;
import monopoly.proprietes.*;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.Border;

public class Test
{
	private JFrame fen = new JFrame("Monopoly");
	
	public Test()
	{
		
	}
	
	public void actualiser(Game g)
	{
		this.init(g);
	}
	
	public void init(Game g)
	{
		int hauteurCase = 30;
		Test p = new Test();
	    fen.setSize(1100, 700);
	    fen.setLocationRelativeTo(null);
	    fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    GridBagLayout gb = new GridBagLayout();
	    JPanel pan = new JPanel();
	    pan.setLayout(gb);
	    GridBagConstraints c = new GridBagConstraints();
	    
	    // haut gauche à haut droit
	    int i = 0;
	    for (int cpt = 0 ; cpt <= g.lesCases().size() / 4 ; cpt++)
	    {
	    	JPanel lacase = new JPanel();
	    	GridLayout gl = new GridLayout(2, 1);
	    	JButton couleur = new JButton("");
	    	p.setColor(g.lesCases().get(cpt), couleur);
	    	couleur.setMinimumSize(new Dimension(10, 1));
	    	couleur.setMaximumSize(new Dimension(10, 1));
	    	String nom = g.lesCases().get(cpt).nom();
	    	String[] seq = nom.split(" ");
	    	nom = "<html><center>";
	    	int taille = 0;
	    	for (String s : seq)
	    	{
	    		taille += s.length();
	    		if (taille > 15)
	    		{
	    			nom += "<br>"+s;
	    			taille = s.length();
	    		}
	    		else
	    		{
	    			nom += " "+s;
	    		}
	    	}
	    	if (g.lesCases().get(cpt).propriete() != null)
	    	{
	    		couleur.setText(g.lesCases().get(cpt).propriete().prixAchat()+" F");
	    		if (couleur.getBackground().equals(Color.BLACK) || couleur.getBackground().equals(Color.BLUE))
	    		{
	    			couleur.setForeground(Color.WHITE);
	    		}
	    	}
	    	else if (g.lesCases().get(cpt).evenement() != null)
	    	{
	    		if (g.lesCases().get(cpt).evenement().type().equals("dépense"))
	    		{
	    			Depense d = (Depense)g.lesCases().get(cpt).evenement();
	    			couleur.setText(String.valueOf(d.somme())+" F");
	    		}
	    		else if (g.lesCases().get(cpt).evenement().type().equals("recette"))
	    		{
	    			Recette r = (Recette)g.lesCases().get(cpt).evenement();
	    			couleur.setText(String.valueOf(r.somme())+" F");
	    		}
	    		if (couleur.getBackground().equals(Color.BLACK) || couleur.getBackground().equals(Color.BLUE))
	    		{
	    			couleur.setForeground(Color.WHITE);
	    		}
	    	}
	    	nom += "</html>";
	    	for (Joueur j3 : g.lesJoueurs())
	    	{
	    		if (j3.position().numero() == g.lesCases().get(cpt).numero())
	    		{
	    			couleur.setText(couleur.getText()+" -> "+j3.numero());
	    			couleur.setFont(new Font("calibri", 10, 10));
	    		}
	    	}
	    	JLabel test = new JLabel(nom);
	    	test.setFont(new Font("calibri", 12, 12));
	    	test.setHorizontalAlignment(JLabel.CENTER);
	    	test.setVerticalAlignment(JLabel.CENTER);
	    	lacase.add(couleur);
	    	lacase.add(test);
	    	lacase.setMinimumSize(new Dimension(10, hauteurCase));
	    	lacase.setMaximumSize(new Dimension(10, hauteurCase));
	    	
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.weightx = 0.5;
		    c.ipady = 40;
		    c.gridx = i;
		    c.gridy = 0;
		    lacase.setLayout(gl);
		    lacase.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		    pan.add(lacase, c);
		    i++;
	    }
	    
	    // haut droit à bas droit
	    int j = 1;
	    for (int cpt = (g.lesCases().size() / 4)  + 1; cpt < g.lesCases().size() / 2 ; cpt++)
	    {
	    	JPanel lacase = new JPanel();
	    	GridLayout gl = new GridLayout(2, 1);
	    	JButton couleur = new JButton("");
	    	p.setColor(g.lesCases().get(cpt), couleur);
	    	couleur.setMinimumSize(new Dimension(10, 1));
	    	couleur.setMaximumSize(new Dimension(10, 1));
	    	String nom = g.lesCases().get(cpt).nom();
	    	String[] seq = nom.split(" ");
	    	nom = "<html><center>";
	    	int taille = 0;
	    	for (String s : seq)
	    	{
	    		taille += s.length();
	    		if (taille > 15)
	    		{
	    			nom += "<br>"+s;
	    			taille = s.length();
	    		}
	    		else
	    		{
	    			nom += " "+s;
	    		}
	    	}
	    	nom += "</html>";
	    	if (g.lesCases().get(cpt).propriete() != null)
	    	{
	    		couleur.setText(g.lesCases().get(cpt).propriete().prixAchat()+" F");
	    		if (couleur.getBackground().equals(Color.BLACK) || couleur.getBackground().equals(Color.BLUE))
	    		{
	    			couleur.setForeground(Color.WHITE);
	    		}
	    	}
	    	else if (g.lesCases().get(cpt).evenement() != null)
	    	{
	    		if (g.lesCases().get(cpt).evenement().type().equals("dépense"))
	    		{
	    			Depense d = (Depense)g.lesCases().get(cpt).evenement();
	    			couleur.setText(String.valueOf(d.somme())+" F");
	    		}
	    		else if (g.lesCases().get(cpt).evenement().type().equals("recette"))
	    		{
	    			Recette r = (Recette)g.lesCases().get(cpt).evenement();
	    			couleur.setText(String.valueOf(r.somme())+" F");
	    		}
	    		if (couleur.getBackground().equals(Color.BLACK) || couleur.getBackground().equals(Color.BLUE))
	    		{
	    			couleur.setForeground(Color.WHITE);
	    		}
	    	}
	    	for (Joueur j3 : g.lesJoueurs())
	    	{
	    		if (j3.position().numero() == g.lesCases().get(cpt).numero())
	    		{
	    			couleur.setText(couleur.getText()+" -> "+j3.numero());
	    			couleur.setFont(new Font("calibri", 10, 10));
	    		}
	    	}
	    	JLabel test = new JLabel(nom);
	    	test.setFont(new Font("calibri", 12, 12));
	    	lacase.add(couleur);
	    	lacase.add(test);
	    	test.setHorizontalAlignment(JLabel.CENTER);
	    	test.setVerticalAlignment(JLabel.CENTER);
	    	lacase.setMinimumSize(new Dimension(10, hauteurCase));
	    	lacase.setMaximumSize(new Dimension(10, hauteurCase));
		    c.fill = GridBagConstraints.BOTH;
		    c.weightx = 0.5;
		    c.ipady = 40;
		    c.gridx = i - 1;
		    c.gridy = j;
		    lacase.setLayout(gl);
		    pan.add(lacase, c);
		    lacase.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		    j++;
	    }
	    
	    // bas droit à bas gauche
	    int k = i;
	    for (int cpt = g.lesCases().size() / 2 ; cpt <= (g.lesCases().size() / 2 + (g.lesCases().size() / 4)) ; cpt++)
	    {
	    	JPanel lacase = new JPanel();
	    	GridLayout gl = new GridLayout(2, 1);
	    	JButton couleur = new JButton("");
	    	p.setColor(g.lesCases().get(cpt), couleur);
	    	couleur.setMinimumSize(new Dimension(10, 1));
	    	couleur.setMaximumSize(new Dimension(10, 1));
	    	String nom = g.lesCases().get(cpt).nom();
	    	String[] seq = nom.split(" ");
	    	nom = "<html><center>";
	    	int taille = 0;
	    	for (String s : seq)
	    	{
	    		taille += s.length();
	    		if (taille > 15)
	    		{
	    			nom += "<br>"+s;
	    			taille = s.length();
	    		}
	    		else
	    		{
	    			nom += " "+s;
	    		}
	    	}
	    	nom += "</html>";
	    	if (g.lesCases().get(cpt).propriete() != null)
	    	{
	    		couleur.setText(g.lesCases().get(cpt).propriete().prixAchat()+" F");
	    		if (couleur.getBackground().equals(Color.BLACK) || couleur.getBackground().equals(Color.BLUE))
	    		{
	    			couleur.setForeground(Color.WHITE);
	    		}
	    	}
	    	else if (g.lesCases().get(cpt).evenement() != null)
	    	{
	    		if (g.lesCases().get(cpt).evenement().type().equals("dépense"))
	    		{
	    			Depense d = (Depense)g.lesCases().get(cpt).evenement();
	    			couleur.setText(String.valueOf(d.somme())+" F");
	    		}
	    		else if (g.lesCases().get(cpt).evenement().type().equals("recette"))
	    		{
	    			Recette r = (Recette)g.lesCases().get(cpt).evenement();
	    			couleur.setText(String.valueOf(r.somme())+" F");
	    		}
	    		if (couleur.getBackground().equals(Color.BLACK) || couleur.getBackground().equals(Color.BLUE))
	    		{
	    			couleur.setForeground(Color.WHITE);
	    		}
	    	}
	    	for (Joueur j3 : g.lesJoueurs())
	    	{
	    		if (j3.position().numero() == g.lesCases().get(cpt).numero())
	    		{
	    			couleur.setText(couleur.getText()+" -> "+j3.numero());
	    			couleur.setFont(new Font("calibri", 10, 10));
	    		}
	    	}
	    	JLabel test = new JLabel(nom);
	    	test.setFont(new Font("calibri", 12, 12));
	    	lacase.add(couleur);
	    	lacase.add(test);
	    	test.setHorizontalAlignment(JLabel.CENTER);
	    	test.setVerticalAlignment(JLabel.CENTER);
	    	lacase.setMinimumSize(new Dimension(10, hauteurCase));
	    	lacase.setMaximumSize(new Dimension(10, hauteurCase));
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.weightx = 0.5;
		    c.ipady = 40;
		    c.gridx = k - 1;
		    c.gridy = j;
		    lacase.setLayout(gl);
		    pan.add(lacase, c);
		    lacase.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		    k--;
	    }
	    
	    // bas gauche à haut gauche
	    int l = j;
	    for (int cpt = (g.lesCases().size() / 2 + (g.lesCases().size() / 4)) + 1; cpt < g.lesCases().size() ; cpt++)
	    {
	    	JPanel lacase = new JPanel();
	    	GridLayout gl = new GridLayout(2, 1);
	    	JButton couleur = new JButton("");
	    	p.setColor(g.lesCases().get(cpt), couleur);
	    	couleur.setMinimumSize(new Dimension(10, 1));
	    	couleur.setMaximumSize(new Dimension(10, 1));
	    	String nom = g.lesCases().get(cpt).nom();
	    	String[] seq = nom.split(" ");
	    	nom = "<html><center>";
	    	int taille = 0;
	    	for (String s : seq)
	    	{
	    		taille += s.length();
	    		if (taille > 15)
	    		{
	    			nom += "<br>"+s;
	    			taille = s.length();
	    		}
	    		else
	    		{
	    			nom += " "+s;
	    		}
	    	}
	    	nom += "</html>";
	    	if (g.lesCases().get(cpt).propriete() != null)
	    	{
	    		couleur.setText(g.lesCases().get(cpt).propriete().prixAchat()+" F");
	    		if (couleur.getBackground().equals(Color.BLACK) || couleur.getBackground().equals(Color.BLUE))
	    		{
	    			couleur.setForeground(Color.WHITE);
	    		}
	    	}
	    	else if (g.lesCases().get(cpt).evenement() != null)
	    	{
	    		if (g.lesCases().get(cpt).evenement().type().equals("dépense"))
	    		{
	    			Depense d = (Depense)g.lesCases().get(cpt).evenement();
	    			couleur.setText(String.valueOf(d.somme())+" F");
	    		}
	    		else if (g.lesCases().get(cpt).evenement().type().equals("recette"))
	    		{
	    			Recette r = (Recette)g.lesCases().get(cpt).evenement();
	    			couleur.setText(String.valueOf(r.somme())+" F");
	    		}
	    		if (couleur.getBackground().equals(Color.BLACK) || couleur.getBackground().equals(Color.BLUE))
	    		{
	    			couleur.setForeground(Color.WHITE);
	    		}
	    	}
	    	for (Joueur j3 : g.lesJoueurs())
	    	{
	    		if (j3.position().numero() == g.lesCases().get(cpt).numero())
	    		{
	    			couleur.setText(couleur.getText()+" -> "+j3.numero());
	    			couleur.setFont(new Font("calibri", 10, 10));
	    		}
	    	}
	    	JLabel test = new JLabel(nom);
	    	test.setFont(new Font("calibri", 12, 12));
	    	lacase.add(couleur);
	    	lacase.add(test);
	    	test.setHorizontalAlignment(JLabel.CENTER);
	    	test.setVerticalAlignment(JLabel.CENTER);
	    	lacase.setMinimumSize(new Dimension(10, hauteurCase));
	    	lacase.setMaximumSize(new Dimension(10, hauteurCase));
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.weightx = 0.5;
		    c.ipady = 40;
		    c.gridx = k;
		    c.gridy = l - 1;
		    lacase.setLayout(gl);
		    pan.add(lacase, c);
		    lacase.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		    l--;
	    }
	    int y = 1;
	    for (Joueur j1 : g.lesJoueurs())
	    {
		    JLabel topo = new JLabel("");
		    String legende = "<html><center>";
	    	legende += j1.nom()+" = "+j1.numero()+"<br>";
		    legende += "</html>";
		    topo.setText(legende);
		    GridBagConstraints gbc = new GridBagConstraints();
		    gbc.weightx = 0.5;
		    gbc.fill = GridBagConstraints.HORIZONTAL;
		    gbc.ipady = 40;
		    gbc.ipadx = 40;
		    gbc.gridx = 5;
		    gbc.gridy = y;
		    topo.setMinimumSize(new Dimension(10, 10));
	    	topo.setMaximumSize(new Dimension(10, 10));
		    pan.add(topo, gbc);
		    y++;
	    }
	    fen.setContentPane(pan);
	    fen.pack();
	    JFrame.setDefaultLookAndFeelDecorated(true);
	    fen.setExtendedState(Frame.MAXIMIZED_BOTH);
	    fen.setVisible(true);
	}
	
	public void setColor(Case lacase, Component c)
	{
		if (lacase.propriete() != null)
    	{
    		if (lacase.propriete().groupe().nom().equals("bleu ciel"))
    		{
    			c.setBackground(Color.CYAN);
    		}
    		else if (lacase.propriete().groupe().nom().equals("bleu roi"))
    		{
    			c.setBackground(Color.BLUE);
    		}
    		else if (lacase.propriete().groupe().nom().equals("jaune"))
    		{
    			c.setBackground(Color.YELLOW);
    		}
    		else if (lacase.propriete().groupe().nom().equals("mauve"))
    		{
    			c.setBackground(Color.MAGENTA);
    		}
    		else if (lacase.propriete().groupe().nom().equals("orange"))
    		{
    			c.setBackground(Color.ORANGE);
    		}
    		else if (lacase.propriete().groupe().nom().equals("rouge"))
    		{
    			c.setBackground(Color.RED);
    		}
    		else if (lacase.propriete().groupe().nom().equals("vert"))
    		{
    			c.setBackground(Color.GREEN);
    		}
    		else if (lacase.propriete().groupe().nom().equals("violet"))
    		{
    			c.setBackground(Color.GRAY);
    		}
    		else if (lacase.propriete().groupe().nom().equals("gares"))
    		{
    			c.setBackground(Color.BLACK);
    		}
    		else if (lacase.propriete().groupe().nom().equals("compagnies"))
    		{
    			c.setBackground(Color.WHITE);
    		}
    	}
		/*else if (lacase.evenement() != null)
		{
			if (lacase.evenement().type() != null)
			{
				if (lacase.evenement().type().equals("chance"))
				{
					c.setBackground(Color.PINK);
				}
				else if (lacase.evenement().type().equals("CC"))
				{
					c.setBackground(Color.WHITE);
				}
			}
		}*/
	}
	
	public static void main(String[] args)
	{
		Test t = new Test();
		Game g = new Game();
		t.init(g);
		int tour = 1;
		while (JOptionPane.showConfirmDialog(null, "Jouer le tour "+tour+" ?") == JOptionPane.YES_OPTION)
		{
			JOptionPane.showMessageDialog(new JFrame(), "====================================\n========== Début du tour "+tour+"==========\n====================================");
			for (Joueur j : g.lesJoueurs())
			{
				if (j.elimine())
				{
					JOptionPane.showMessageDialog(new JFrame(), j.nom()+" est éliminé !");
				}
				else
				{
					g.jouerTour(j);
				}
				
				t.actualiser(g);
			}
			JOptionPane.showMessageDialog(new JFrame(), "====================================\n========== Fin du tour "+tour+"==========\n====================================");
			tour++;
		}
		JOptionPane.showMessageDialog(null, "Fin du jeu");
	}
}