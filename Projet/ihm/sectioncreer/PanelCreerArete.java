package ihm.sectioncreer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color; 


import main.Controleur;
import metier.Noeud;

public class PanelCreerArete extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JComboBox<String> comboCouleur;
	private JComboBox  comboNoeud1;
	private JComboBox  comboNoeud2;

	private JTextField txtDistance;

	private JButton btnSupprimer;
	private JButton btnGenererArete;
	private JButton btnGenererPrefait;

	private List<JLabel> lstLabel;

	private JList<String>listHistorique;

	private PanelGraphique panelGraphique;

	public PanelCreerArete(Controleur ctrl)
	{
		this.setLayout(new BorderLayout());
		this.ctrl = ctrl;
		this.lstLabel = new ArrayList<JLabel>();
		this.panelGraphique = new PanelGraphique(this.ctrl);

		JPanel panelHaut 	  		= new JPanel(new GridLayout(6,3,0,15));
		JPanel panelDispoHistorique = new JPanel(new BorderLayout(0,25));
		JPanel panelValidation		= new JPanel(new GridLayout(3,3, 10, 20));
		JLabel lblCouleur  = new JLabel("Couleur : ", JLabel.LEFT);
		JLabel lblDistance = new JLabel("Distance : ", JLabel.LEFT);
		JLabel lblRelier   = new JLabel("Relier... ", JLabel.LEFT);
		JLabel lblVers	   = new JLabel("vers ", JLabel.CENTER);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		JLabel lblHistorique = new JLabel("Historique", JLabel.CENTER);


		NumberFormat longformat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(longformat);
		numberFormatter.setValueClass(Long.class);
		numberFormatter.setAllowsInvalid(false);
		numberFormatter.setMinimum(0L);

		this.comboCouleur = new JComboBox<String>(
			new String[] 
			{
				"Rouge", 
				"Vert", 
				"Bleu",	
				"Jaune",
				"Noir",
				"Blanc",
				"Rose", 
				"Orange",
				"Gris"
			});

		this.txtDistance = new JFormattedTextField(longformat);		
		this.comboNoeud1 = new JComboBox();
		this.comboNoeud2 = new JComboBox();
		this.btnSupprimer = new JButton("Supprimer");
		this.btnGenererArete = new JButton("Générer arête");
		this.btnGenererPrefait = new JButton("Générer arête préfaite");
		this.listHistorique = new JList<String>();


		this.listHistorique.setPreferredSize(new Dimension(0, 550));
		this.comboCouleur.setBorder(border);
		this.txtDistance.setBorder(border);
		this.comboNoeud1.setBorder(border);
		this.comboNoeud2.setBorder(border);
		this.btnSupprimer.setBorder(border);
		this.listHistorique.setBorder(border);

		this.btnGenererArete.setBackground(Color.WHITE);
		this.btnGenererPrefait.setBackground(Color.WHITE);
		this.btnSupprimer.setBackground(Color.WHITE);

		panelHaut.add(new JLabel());
		panelHaut.add(new JLabel());
		panelHaut.add(new JLabel());
		panelHaut.add(lblCouleur);
		panelHaut.add(this.comboCouleur);
		panelHaut.add(new JLabel());
		panelHaut.add(lblRelier); 
		panelHaut.add(new JLabel());
		panelHaut.add(new JLabel());
		panelHaut.add(this.comboNoeud1);
		panelHaut.add(lblVers);
		panelHaut.add(this.comboNoeud2);
		panelHaut.add(lblDistance);
		panelHaut.add(this.txtDistance);
		panelHaut.add(new JLabel());

		panelDispoHistorique.add(lblHistorique, BorderLayout.NORTH);
		panelDispoHistorique.add(this.listHistorique, BorderLayout.CENTER);
		panelDispoHistorique.add(new JPanel(), BorderLayout.SOUTH);
		panelDispoHistorique.add(new JPanel(), BorderLayout.EAST);
		panelDispoHistorique.add(new JPanel(), BorderLayout.WEST);

		panelValidation.add(new JLabel());
		panelValidation.add(this.btnGenererArete);
		panelValidation.add(new JLabel());
		panelValidation.add(new JLabel());
		panelValidation.add(this.btnGenererPrefait);
		panelValidation.add(new JLabel());
		panelValidation.add(new JLabel());
		panelValidation.add(this.btnSupprimer);
		panelValidation.add(new JLabel());


		this.add(panelHaut, BorderLayout.NORTH);
		this.add(panelDispoHistorique, BorderLayout.CENTER);
		this.add(panelValidation, BorderLayout.SOUTH);


		this.btnSupprimer.addActionListener(this);
		this.btnGenererArete.addActionListener(this);
		this.btnGenererPrefait.addActionListener(this);

		// Empêcher l'utilisateur de rentrer autre chose qu'un nombre dans les champs de texte
		this.txtDistance.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		

	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnSupprimer)
		{
			this.lstLabel.remove(this.listHistorique.getSelectedIndex());
			this.listHistorique.setListData(this.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));
		}
		else if(e.getSource() == this.btnGenererArete)
		{
			/* Vérifier que les champs sont remplis */
			if(this.txtDistance.getText().isEmpty() || this.txtDistance.getText().equals("0"))
			{
				JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}
			/*--------------------------------------*/

			/* Ajout de l'arête dans l'historique */
			this.lstLabel.add(new JLabel(
			"Arête de "    + this.comboNoeud1.getSelectedItem()  + " à " + this.comboNoeud2.getSelectedItem()   +
				" de Couleur " + this.comboCouleur.getSelectedItem() + " de distance " + this.txtDistance.getText())
			);

			this.listHistorique.setListData(this.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));
			/*------------------------------------*/

			this.panelGraphique.majIHM();

			this.txtDistance.setText("");
			//this.comboCouleur.setText("");
		}/*
		else if(e.getSource() == this.btnGenererPrefait)
		{
			String[] couleurs = {"rouge", "vert", "bleu", "jaune", "noir", "blanc", "violet", "marron"};
			String randomCouleur = couleurs[(int)(Math.random() * couleurs.length)];
			String randomDistance = String.valueOf((int)(Math.random() * 1000));

			this.lstLabel.add(new JLabel("Couleur : " + randomCouleur + " | Distance : " + randomDistance));
			this.listHistorique.setListData(this.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));
		}*/
	}

	public void majIHM()
	{
		Noeud n = this.ctrl.getLstNoeud().get(this.ctrl.getLstNoeud().size()-1);

		this.comboNoeud1.addItem(n.getNom());
		this.comboNoeud2.addItem(n.getNom());

	}
}
