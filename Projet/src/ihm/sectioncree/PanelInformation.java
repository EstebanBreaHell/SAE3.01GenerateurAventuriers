package ihm.sectioncree;

import main.Controleur;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;

import java.awt.*;

public class PanelInformation extends JPanel
{
	private Controleur 	ctrl;

	private final String[] TAB_EXPLIQUATION_HISTORIQUE = {"|Ville : a 		| Action : Ajout 		| numeroMouvement : 1	|",
														  "|Ville : b 		| Action : Ajout 		| numeroMouvement : 2	|",
														  "|Chemain : a-b 	| Action : Ajout 		| numeroMouvement : 3 	|",
														  "|Chemain : a-b   | Action : Supp			| numeroMouvement : 4 	|"};
	private JButton btnInfoGraph;
	private JButton	btnGenereVille;
	private JButton	btnGenereChemin;
	private JButton	btnSupp;
	private JButton	btnGenereGraph;

	private JList<String>listHistorique;

	public PanelInformation(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		//Font fontDefault = this.ctrl.getFont();
		JPanel panelDispoBtn        = new JPanel(new GridLayout(4,1,0,20));
		JPanel panelDispoHistorique = new JPanel(new BorderLayout());

		Icon iconGomme       = Controleur.imageToIcon("./gomme.png"			);
		Icon iconGenere      = Controleur.imageToIcon("./genere.png"		);
		Icon iconInformation = Controleur.imageToIcon("./information.png"	);
		Icon iconVille 	     = Controleur.imageToIcon("./ville.png"			);
		Icon iconChemin      = Controleur.imageToIcon("./chemin.png"		);

		this.btnInfoGraph    = new JButton("Information graphe",iconInformation);
		this.btnGenereVille  = new JButton("Généré ville",iconVille);
		this.btnGenereChemin = new JButton("Généré chemin",iconChemin);
 		this.btnSupp 		 = new JButton("Supprimer",iconGomme);
		this.btnGenereGraph  = new JButton("Généré graphe",iconGenere);
		this.listHistorique  = new JList<String>(TAB_EXPLIQUATION_HISTORIQUE);

		panelDispoBtn.add(this.btnInfoGraph);
		panelDispoBtn.add(this.btnGenereVille);
		panelDispoBtn.add(this.btnGenereChemin);
		panelDispoBtn.add(this.btnSupp);

		this.add(panelDispoBtn,BorderLayout.NORTH);

		panelDispoHistorique.add(new JLabel("Historique",JLabel.CENTER),BorderLayout.NORTH);
		panelDispoHistorique.add(this.listHistorique);

		this.add(panelDispoHistorique,BorderLayout.CENTER);
		this.add(this.btnGenereGraph, BorderLayout.SOUTH);
	}

}
