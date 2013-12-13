package monopoly.gui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import monopoly.jeu.*;

public class Plateau extends JFrame {
	private JPanel panel;
	public static void main(String [] args){
		Plateau p = new Plateau();
		p.pack();
		p.setVisible(true);
	}
	
	public Plateau(){
		super("Monopoly");
		Game g = new Game();
		int nbCasesparTour = g.lesCases().size()/4;
		int cpt = 0;
		int cardinalite = 0;
		
		JPanel middle = new JPanel();
		middle.setBackground(new Color(39,134,58));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
//		getContentPane().setLayout(new GridBagLayout());
//		GridBagConstraints gbc = new GridBagConstraints();
//		panel = new JPanel();
//		// On instancie le jeu
//		Iterator<Case> it = g.lesCases().iterator();
////		gbc.fill = GridBagConstraints.HORIZONTAL;
////		gbc.gridx=1;
////		gbc.gridy=1;
////		gbc.weightx=1.00;
//		this.add(middle,gbc);
//		for(int i = 0; i<g.lesCases().size(); i++){
////			if(i%nbCasesparTour==0)
//			gbc.fill = GridBagConstraints.CENTER;
//			gbc.gridx=i%nbCasesparTour;
//			System.out.println("Y : " + cardinalite + " i = " + i);
//			
//			gbc.gridy=cardinalite;
//			this.add(new JButton(g.lesCases().get(i).nom()),gbc);
//			if(i%nbCasesparTour==0){
//				cardinalite++;
//			}else if(cardinalite == 2 )
//			{
//
//				System.out.println("up");
//				cardinalite++;
//			}
//		}
		
//		gbc.fill = GridBagConstraints.LAST_LINE_START;
//		gbc.gridx=0;
//		gbc.gridy=0;
//		this.add(new JButton("Bas Gauche"),gbc);
//		gbc.fill = GridBagConstraints.PAGE_END;
//		gbc.gridx=1;
//		gbc.gridy=0;
//		this.add(new JButton("Bas Mid"),gbc);
//		gbc.fill = GridBagConstraints.LAST_LINE_END;
//		gbc.gridx=2;
//		gbc.gridy=0;
//		this.add(new JButton("Bas Droite"),gbc);
//		gbc.fill = GridBagConstraints.LINE_START;
//		gbc.gridx=0;
//		gbc.gridy=1;
//		this.add(new JButton("Mid Gauche"),gbc);
//		gbc.fill = GridBagConstraints.LINE_END;
//		gbc.gridx=2;
//		gbc.gridy=1;
//		this.add(new JButton("Mid Droit"),gbc);
//		gbc.fill = GridBagConstraints.FIRST_LINE_START;
//		gbc.gridx=0;
//		gbc.gridy=2;
//		this.add(new JButton("Top Gauche"),gbc);
//		gbc.fill = GridBagConstraints.PAGE_START;
//		gbc.gridx=1;
//		gbc.gridy=2;
//		this.add(new JButton("Top Mid"),gbc);
//		gbc.fill = GridBagConstraints.FIRST_LINE_END;
//		gbc.gridx=2;
//		gbc.gridy=2;
//		this.add(new JButton("Top Droite"),gbc);
		
		
		Panel north = new Panel(new GridLayout(1,0));
		Panel south = new Panel(new GridLayout(1,0));
		Panel east = new Panel(new GridLayout(0,1));
		Panel west = new Panel(new GridLayout(0,1));
		Panel center = new Panel(new FlowLayout());
		
		int size = g.lesCases().size();
		ArrayList<Case> haut = new ArrayList();
		ArrayList<Case> bas = new ArrayList();
		ArrayList<Case> gauche = new ArrayList();
		ArrayList<Case> droite = new ArrayList();
		
		Iterator<Case> it = g.lesCases().iterator();

		while(it.hasNext()){
			Case tmp = it.next();
			if(cardinalite==0){
				bas.add(tmp);
			}else if(cardinalite==1){
				gauche.add(tmp);
			}else if(cardinalite==2){
				haut.add(tmp);
			}else if(cardinalite==3){
				droite.add(tmp);
			}			
			cpt++;
			if(cpt==nbCasesparTour)
			{
				cardinalite++;
				cpt=0;
			}
		}
		
		for (int i = nbCasesparTour-1; i>=0;i--)
		{
			Panel p = new Panel();
			if(bas.get(i).get(i) != null)
				if(bas.get(i).get(i) != null){
				JPanel color = new JPanel();
				Color rgb;
				//get(i).propriete().groupe().nom()
				System.out.println(bas);
				switch (bas.get(i).propriete().groupe().nom()) {
				case "bleu ciel":
					rgb = new Color(0,178,238);
					break;
				case "bleu roi":
					rgb = new Color(16,78,139);
					break;
				case "jaune":
					rgb = new Color(255,255,0);
					break;
				case "mauve":
					rgb = new Color(122,55,139);
					break;
				case "orange":
					rgb = new Color(255,140,0);
					break;
				case "rouge":
					rgb = new Color(205,55,0);
					break;
				case "vert":
					rgb = new Color(0,100,0);
					break;				
				case "violet":
					rgb = new Color(255,0,255);
					break;
				case "gares":
					rgb = new Color(0,0,0);
					break;

				default:
					rgb = new Color(255,255,255);
					break;
				}
				color.setBackground(rgb);
				p.add(color);
			}
			
			
			JLabel nom = new JLabel(g.lesCases().get(i).nom());
			
		
			p.setLayout(new GridLayout(0,1));
			
			p.add(nom);
			
			south.add(p);
		}
		
		for (int i = nbCasesparTour-1; i!=-1;i--)
		{
			west.add(new JButton(gauche.get(i).nom()));
		}
		for (int i = 0; i<haut.size();i++)
		{
			north.add(new JButton(haut.get(i).nom()));
		}
		for (int i = 0; i<droite.size();i++)
		{
			east.add(new JButton(droite.get(i).nom()));
		}
		
		
		add(north,BorderLayout.NORTH);
		add(south,BorderLayout.SOUTH);
		add(east,BorderLayout.EAST);
		add(west,BorderLayout.WEST);
		add(middle,BorderLayout.CENTER);
		setBounds(400, 200, 800, 400);
		
	}
}
