package ihm.sectioncreer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.Color;
import java.awt.Dimension;

import main.Controleur;

public class PanelCreerArete extends JPanel implements ActionListener
{
	private JTextField txtCouleur;
	private JComboBox  comboNoeud1;
	private JComboBox  comboNoeud2; 
	private JTextField txtDistance;
	private JButton    btnSupprimer;
	private JButton    btnGenererArete;
	private JButton    btnGenererPrefait;
	private Controleur ctrl;

	private final String[] TAB_EXPLIQUATION_HISTORIQUE = {"|Noeud : a 		| Action : Ajout 		| numeroMouvement : 1	|",
														  "|Noeud : b 		| Action : Ajout 		| numeroMouvement : 2	|",
													      "|Arête : a-b 	| Action : Ajout 		| numeroMouvement : 3 	|",
														  "|Arête : a-b     | Action : Supp			| numeroMouvement : 4 	|",
														  "|Arête : a-b     | Action : Supp			| numeroMouvement : 4 	|",
														  "|Arête : a-b     | Action : Supp			| numeroMouvement : 4 	|",
														  "|Arête : a-b     | Action : Supp			| numeroMouvement : 4 	|",
														  "|Arête : a-b     | Action : Supp			| numeroMouvement : 4 	|",
														  "|Arête : a-b     | Action : Supp			| numeroMouvement : 4 	|",
														  "|Arête : a-b     | Action : Supp			| numeroMouvement : 4 	|", 
														  "|Arête : a-b     | Action : Supp			| numeroMouvement : 4 	|", 
														  "|Arête : a-b     | Action : Supp			| numeroMouvement : 4 	|", 
														  "|Arête : a-b     | Action : Supp			| numeroMouvement : 4 	|",};

	private JList<String>listHistorique;

	public PanelCreerArete(Controleur ctrl)
	{
		this.setLayout(new BorderLayout());
		this.ctrl = ctrl;

		JPanel panelHaut 	  		= new JPanel(new GridLayout(6,3, 10, 10));
		JPanel panelDispoHistorique = new JPanel(new BorderLayout(0,20));
		JPanel panelValidation		= new JPanel(new GridLayout(3,3, 10, 15));
		JLabel lblCouleur  = new JLabel("Couleur : ", JLabel.LEFT);
		JLabel lblDistance = new JLabel("Distance : ", JLabel.LEFT);
		JLabel lblRelier   = new JLabel("Relier... ", JLabel.LEFT);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

		this.txtCouleur = new JTextField();
		this.txtDistance = new JTextField();
		this.comboNoeud1 = new JComboBox();
		this.comboNoeud2 = new JComboBox();
		this.btnSupprimer = new JButton("Supprimer");
		this.btnGenererArete = new JButton("Générer arête");
		this.btnGenererPrefait = new JButton("Générer arête préfaite");
		this.listHistorique = new JList<String>(TAB_EXPLIQUATION_HISTORIQUE);


		this.listHistorique.setPreferredSize(new Dimension(0, 550));
		this.txtCouleur.setBorder(border);
		this.txtDistance.setBorder(border);
		this.comboNoeud1.setBorder(border);
		this.comboNoeud2.setBorder(border);
		this.btnSupprimer.setBorder(border);
		this.listHistorique.setBorder(border);


		
		panelHaut.add(new JLabel());
		panelHaut.add(new JLabel());
		panelHaut.add(new JLabel());
		panelHaut.add(lblCouleur);
		panelHaut.add(this.txtCouleur);
		panelHaut.add(new JLabel());
		panelHaut.add(lblRelier); 
		panelHaut.add(new JLabel());
		panelHaut.add(new JLabel());
		panelHaut.add(this.comboNoeud1);
		panelHaut.add(new JLabel());
		panelHaut.add(new JLabel());
		panelHaut.add(this.comboNoeud2);
		panelHaut.add(new JLabel());
		panelHaut.add(new JLabel());
		panelHaut.add(lblDistance);
		panelHaut.add(this.txtDistance);
		panelHaut.add(new JLabel());

		panelDispoHistorique.add(new JLabel("Historique : "), BorderLayout.NORTH);
		panelDispoHistorique.add(this.listHistorique, BorderLayout.CENTER);

		panelValidation.add(this.btnGenererArete);
		panelValidation.add(this.btnGenererPrefait);

		this.add(panelHaut, BorderLayout.NORTH);
		this.add(panelDispoHistorique, BorderLayout.CENTER);
		this.add(panelValidation, BorderLayout.SOUTH);

	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnSupprimer)
		{
			System.out.println("Suppression");
		}
		else if(e.getSource() == this.btnGenererArete)
		{
			System.out.println("Génération d'une arête");
		}
		else if(e.getSource() == this.btnGenererPrefait)
		{
			System.out.println("Génération d'une arête préfaite");
		}
	}
}
