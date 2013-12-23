import monopoly.evenements.*;
import monopoly.jeu.*;
import monopoly.proprietes.*;
import java.awt.*;
import java.util.*;

import javax.swing.*;

public class Test
{
	public Test()
	{
		
	}
	
	public void init()
	{
		int hauteurCase = 30;
		Game g = new Game();
		Test p = new Test();
		JFrame fen = new JFrame();
		fen.setTitle("test gbl");
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
	    	nom = "<html>";
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
	    	nom = "<html>";
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
	    	lacase.setMinimumSize(new Dimension(10, hauteurCase));
	    	lacase.setMaximumSize(new Dimension(10, hauteurCase));
		    c.fill = GridBagConstraints.BOTH;
		    c.weightx = 0.5;
		    c.ipady = 40;
		    c.gridx = i - 1;
		    c.gridy = j;
		    lacase.setLayout(gl);
		    pan.add(lacase, c);
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
	    	nom = "<html>";
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
	    	lacase.setMinimumSize(new Dimension(10, hauteurCase));
	    	lacase.setMaximumSize(new Dimension(10, hauteurCase));
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.weightx = 0.5;
		    c.ipady = 40;
		    c.gridx = k - 1;
		    c.gridy = j;
		    lacase.setLayout(gl);
		    pan.add(lacase, c);
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
	    	nom = "<html>";
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
	    	lacase.setMinimumSize(new Dimension(10, hauteurCase));
	    	lacase.setMaximumSize(new Dimension(10, hauteurCase));
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.weightx = 0.5;
		    c.ipady = 40;
		    c.gridx = k;
		    c.gridy = l - 1;
		    lacase.setLayout(gl);
		    pan.add(lacase, c);
		    l--;
	    }
	    
	    fen.setContentPane(pan);
	    fen.pack();
	    fen.setVisible(true);
	    
		//g.play();
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
		
	}
}