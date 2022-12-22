package ihm.sectioncreer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color; 

import main.Controleur;
import metier.Noeud;

public class PanelCreerArete extends JPanel implements ActionListener, ItemListener
{
	private Controleur ctrl;
	private PanelGraphique panelGraphique;
	private JList<String> listHistorique;
	private JTextField txtDistance;

	private Container container;
	private JButton   btnCouleur;
	private JComboBox<Noeud> comboNoeud1;
	private JComboBox<Noeud> comboNoeud2;

	private JButton btnSupprimer;
	private JButton btnGenererArete;
	private JButton btnGenererPrefait;

	private JCheckBox chbDouble;

	private List<JLabel> lstLabel;

	public PanelCreerArete(Controleur ctrl)
	{

		/**
		 * Création des composants
		 */
		this.setLayout(new BorderLayout());
		this.ctrl = ctrl;
		this.lstLabel = new ArrayList<JLabel>();
		this.panelGraphique = new PanelGraphique(this.ctrl);

		JPanel panelHaut 	  		= new JPanel(new GridLayout(5,4,10,20));
		JPanel panelDispoHistorique = new JPanel(new BorderLayout(0,20));
		JPanel panelValidation		= new JPanel(new GridLayout(3,3, 10, 20));

		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		JLabel lblHistorique = new JLabel("Historique", JLabel.CENTER);

		//Information contenue dans le panelHaut
		JLabel lblDistance = new JLabel("Distance : ", JLabel.LEFT);
		JLabel lblRelier   = new JLabel("Relier... ", JLabel.LEFT);
		JLabel lblVers	   = new JLabel("vers ", JLabel.CENTER);
		JLabel lblCouleur  = new JLabel("Sélectionnez couleur ", JLabel.LEFT);
		//JCheckBox chbDouble = new JCheckBox("Double sens ", JLa);

		this.container = new Container();
		this.container.setLayout(new FlowLayout());
		this.btnCouleur =new JButton("Palette de couleur");
		this.txtDistance = new JTextField("1");
		
		this.comboNoeud1 = new JComboBox<Noeud>();
		this.comboNoeud2 = new JComboBox<Noeud>();
		this.chbDouble = new JCheckBox("Double sens", false);

		this.btnSupprimer = new JButton("Supprimer");
		this.btnGenererArete = new JButton("Générer arête");
		this.btnGenererPrefait = new JButton("Générer arête préfaite");
		this.listHistorique = new JList<String>();

		/**
		 * Ajout de bordure et de couleur de fond
		 */
		this.txtDistance.setBorder(border);
		this.comboNoeud1.setBorder(border);
		this.comboNoeud2.setBorder(border);
		this.btnSupprimer.setBorder(border);
		this.listHistorique.setBorder(border);


		this.btnGenererArete.setBackground(Color.WHITE);
		this.btnGenererPrefait.setBackground(Color.WHITE);
		this.btnSupprimer.setBackground(Color.WHITE);
		this.btnCouleur.setBackground(Color.WHITE);


		/* Ajout du bouton dans le container */
		this.container.add(this.btnCouleur);

		/*
		 * Positionnement des composants
		 * A optimiser par la suite
		 */
		
		panelHaut.add(lblCouleur);
		panelHaut.add(new JLabel());
		panelHaut.add(new JLabel());
		panelHaut.add(new JLabel());

		panelHaut.add(new JLabel());
		panelHaut.add(this.container);
		panelHaut.add(new JLabel());
		panelHaut.add(new JLabel());

		panelHaut.add(lblRelier); 
		panelHaut.add(new JLabel());
		panelHaut.add(new JLabel());
		panelHaut.add(new JLabel());

		panelHaut.add(new JLabel());
		panelHaut.add(this.comboNoeud1);
		panelHaut.add(lblVers);
		panelHaut.add(this.comboNoeud2);


		panelHaut.add(lblDistance);
		panelHaut.add(this.txtDistance);
		panelHaut.add(new JLabel());
		panelHaut.add(this.chbDouble);


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

		/**
		 * Activation des composants
		 */
		this.btnSupprimer.addActionListener(this);
		this.btnGenererArete.addActionListener(this);
		this.btnGenererPrefait.addActionListener(this);
		this.btnCouleur.addActionListener(this);
		this.comboNoeud1.addItemListener(this);

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

	/**
	 * Supprimer une arête de la liste
	 * @param n de type int
	 */
	public void supprimArete(int n)
	{
		this.lstLabel.remove(n);
		this.listHistorique.setListData(this.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));
		this.panelGraphique.majIHM();
	}

	/**
	 * Gérer une action avec les boutons
	 * @param e de type ActionEvent
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnCouleur)
		{
			Color initialcolor=Color.RED;
			Color color=JColorChooser.showDialog(this,"Choisissez une couleur d'arête",initialcolor);
			container.setBackground(color);
		}

		if(e.getSource() == this.btnSupprimer)
		{
			if(this.listHistorique.getSelectedIndex() == -1)
			{
				JOptionPane.showMessageDialog(this, "Veuillez sélectionner une arête à supprimer", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.lstLabel.remove(this.listHistorique.getSelectedIndex());
			this.ctrl.supprArete(this.listHistorique.getSelectedIndex());
			this.listHistorique.setListData(this.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));
			this.panelGraphique.majIHM();
		}
		else if(e.getSource() == this.btnGenererArete)
		{
			/* Vérifier que les champs sont remplis */
			// Convertir la distance en entier
			if(this.txtDistance.getText().isEmpty() || Integer.parseInt(this.txtDistance.getText()) < 1 || Integer.parseInt(this.txtDistance.getText()) > 100)
			{
				JOptionPane.showMessageDialog(this, "La distance doit être comprise entre 1 et 100", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}
			/*--------------------------------------*/

			// Ajout de l'arête dans l'historique 
			this.lstLabel.add(new JLabel("L'arête relie "    + this.comboNoeud1.getSelectedItem()  + " à " + this.comboNoeud2.getSelectedItem()  
			 				+ " de couleur" + this.container.getBackground() + " et de distance " + this.txtDistance.getText()));

			this.ctrl.addArete( this.comboNoeud1.getItemAt(this.comboNoeud1.getSelectedIndex()),
					            this.comboNoeud2.getItemAt(this.comboNoeud2.getSelectedIndex()),
					            this.container.getBackground().toString(),
								Integer.parseInt(this.txtDistance.getText()),this.chbDouble.isSelected() 
					);
			
			this.listHistorique.setListData(this.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));
			/*------------------------------------*/

			this.panelGraphique.majIHM();
			
			this.txtDistance.setText("");
		}
		else if(e.getSource() == this.btnGenererPrefait)
		{

			//java.awt.Color[r=0,g=0,b=255]
			int n = this.ctrl.getLstNoeud().size();
			n = (n*(n-1))/2;
			if(this.ctrl.getLstArete().size() == n)
			{
				JOptionPane.showMessageDialog(this, "Tous les noeuds sont reliés", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}
			ArrayList<Noeud> lstNoeud = this.ctrl.getLstNoeud(); 
			if(lstNoeud.size() < 2)
			{
				JOptionPane.showMessageDialog(this, "Il faut au moins 2 noeuds disponibles pour générer une arête", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}

			Noeud noeud1 = lstNoeud.get((int)(Math.random() * lstNoeud.size()));

			Noeud noeud2 = null;
			while(noeud2 == null)
			{
				if(this.ctrl.getNoeudDispo(noeud1).size()>0)
					noeud2 = this.ctrl.getNoeudDispo(noeud1).get((int)(Math.random() * this.ctrl.getNoeudDispo(noeud1).size()));
				else
					noeud1 = lstNoeud.get((int)(Math.random() * lstNoeud.size()));
			}
			String randomCouleur = "java.awt.Color[r=" + (int)(Math.random() * 255) + ",g=" + (int)(Math.random() * 255) + ",b=" + (int)(Math.random() * 255) + "]";
			int randomDistance =(int)(Math.random() * 8)+1;

			this.ctrl.addArete( noeud1,noeud2,randomCouleur,randomDistance, Math.random() < 0.5 ? true : false);

			/* Ajout de l'arête dans l'historique */
			this.lstLabel.add(new JLabel("L'arête relie  "    + noeud1  + " à " + noeud2   +
			" de couleur RBG " + randomCouleur + " et de distance " + randomDistance));
			this.listHistorique.setListData(this.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));
			this.panelGraphique.majIHM();
		}
	}

	/**
	 * Permet de voir si l'état du combobox est modifié
	 * @param e de type ItemEvent
	 */
	public void itemStateChanged(ItemEvent e) 
    { 
		this.comboNoeud2.removeAllItems();
		Noeud n = this.comboNoeud1.getItemAt(this.comboNoeud1.getSelectedIndex());
		
		if(n == null)
			return;

		for(Noeud noeud : this.ctrl.getNoeudDispo(n))
			this.comboNoeud2.addItem(noeud);        
    } 

	/**
	 * Permet de mettre à jour l'IHM
	 */
	public void majIHM()
	{
		this.comboNoeud1.removeAllItems();
		for(Noeud noeud : this.ctrl.getLstNoeud())
			this.comboNoeud1.addItem(noeud);
		
		//this.comboNoeud1.addItem(n);
		
		/*
		 * getNoeudDispo
		 */
		//this.comboNoeud2.addItem(ctrl.getNoeudDispo(n));
		
	}
}
