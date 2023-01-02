/**
 * @author Lefort William, Decharrois Adrien, Brea-Hell Esteban
 * @version 1.0
 * @date 2019-03-20
 */

package ihm.sectioncreer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color; 

import main.Controleur;
import metier.Arete;
import metier.Noeud;

public class PanelCreerArete extends JPanel implements ActionListener, ItemListener , MouseListener
{
	private Controleur ctrl;
	private PanelGraphique panelGraphique;
	private JList<String> listHistorique;
	private JTextField txtDistance;
	private JTextField txtDistanceUpdate;

	private Container container;
	private Container containerUpdate;
	private JButton   btnCouleur;
	private JButton   btnCouleurUpdate;

	private JButton btnConfirmer;
	private JComboBox<Noeud> comboNoeud1;
	private JComboBox<Noeud> comboNoeud2;

	private JButton btnSupprimer;
	private JButton btnGenererArete;
	private JButton btnGenererPrefait;
	private JPanel panelHisto;
	private JScrollPane scrollPaneHisto;

	private JCheckBox chbNeutre;

	private List<JLabel> lstLabel;

	private JDialog jd;

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
		this.panelHisto = new JPanel();
		this.panelHisto.setBackground(Color.WHITE);
		this.scrollPaneHisto = new JScrollPane(this.panelHisto);
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		JLabel lblHistorique = new JLabel("Historique", JLabel.CENTER);

		//Information contenue dans le panelHaut
		JLabel lblDistance = new JLabel("Distance : ", JLabel.LEFT);
		JLabel lblRelier   = new JLabel("Relier... ", JLabel.LEFT);
		JLabel lblVers	   = new JLabel("vers ", JLabel.CENTER);
		JLabel lblCouleur  = new JLabel("Sélectionnez couleur ", JLabel.LEFT);

		this.container = new Container();
		this.containerUpdate = new Container();
		this.container.setLayout(new FlowLayout());
		this.containerUpdate.setLayout(new FlowLayout());
		this.btnCouleur =new JButton("Palette de couleur");
		this.btnCouleurUpdate =new JButton("Palette de couleur");
		this.txtDistance = new JTextField("1");
		this.txtDistanceUpdate = new JTextField();

		
		this.comboNoeud1 = new JComboBox<Noeud>();
		this.comboNoeud2 = new JComboBox<Noeud>();

		this.chbNeutre = new JCheckBox("Couleur neutre", false);

		this.btnSupprimer = 	 new JButton("Supprimer"			 );
		this.btnGenererArete =	 new JButton("Générer arête"		 );
		this.btnGenererPrefait = new JButton("Générer arête préfaite");
		this.btnConfirmer      = new JButton("Confirmer"             );
		this.listHistorique = new JList<String>();
		this.panelHisto.add(this.listHistorique);

		/**
		 * Ajout de bordure et de couleur de fond
		 */

		this.txtDistanceUpdate.setHorizontalAlignment(JTextField.CENTER);
		this.txtDistance.setBorder(border);
		this.comboNoeud1.setBorder(border);
		this.comboNoeud2.setBorder(border);
		this.btnSupprimer.setBorder(border);
		this.listHistorique.setBorder(border);


		this.btnGenererArete.setBackground(Color.WHITE);
		this.btnGenererPrefait.setBackground(Color.WHITE);
		this.btnSupprimer.setBackground(Color.WHITE);
		this.btnCouleur.setBackground(Color.WHITE);
		this.btnCouleurUpdate.setBackground(Color.WHITE);
		this.btnConfirmer.setBackground(Color.WHITE);


		/* Ajout du bouton dans le container */
		this.container.add(this.btnCouleur);
		this.containerUpdate.add(this.btnCouleurUpdate);
		

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
		panelHaut.add(this.chbNeutre);


		panelDispoHistorique.add(lblHistorique, BorderLayout.NORTH);
		panelDispoHistorique.add(this.scrollPaneHisto, BorderLayout.CENTER);
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
		this.btnSupprimer		.addActionListener(this);
		this.btnGenererArete	.addActionListener(this);
		this.btnGenererPrefait	.addActionListener(this);
		this.btnCouleur			.addActionListener(this);
		this.btnCouleurUpdate	.addActionListener(this);
		this.comboNoeud1		.addItemListener(this);
		this.listHistorique		.addMouseListener(this);
		this.btnConfirmer		.addActionListener(this);
		this.chbNeutre			.addActionListener(this);

		ArrayList<Arete> aTmp = this.ctrl.getLstArete();
		for (Arete a : aTmp)
		{
			this.lstLabel.add(new JLabel("L'arête relie "    + a.getNoeudArr()  + " à " + a.getNoeudDep()
			 				+ " de couleur" + a.getCouleur() + " et de distance " + a.getWagon()));
			
		}
		this.listHistorique.setListData(this.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));


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

		this.txtDistanceUpdate.addKeyListener(new KeyAdapter() {
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
		else if(e.getSource() == this.btnCouleurUpdate)
		{
			Color initialcolor=Color.RED;
			Color color=JColorChooser.showDialog(this,"Choisissez une couleur d'arête",initialcolor);
			containerUpdate.setBackground(color);
			
			String couleur = "[r="+color.getRed()+",g="+color.getGreen()+",b="+color.getBlue()+"]";
			int n = this.listHistorique.getSelectedIndex();
			//String couleur = new Color(this.containerUpdate.getBackground().getRGB()).toString();
			this.ctrl.getLstArete().get(n).setCouleur(couleur);
			this.ctrl.majIHM();
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
			String couleur = "";
			
			if(this.chbNeutre.isSelected())
			{
				couleur = "Neutre";
			}
			else
			{
				Color coul = new Color(this.container.getBackground().getRGB());
				couleur = "[r="+coul.getRed()+",g="+coul.getGreen()+",b="+coul.getBlue()+"]";
			}
			this.ctrl.addArete( this.comboNoeud1.getItemAt(this.comboNoeud1.getSelectedIndex()),
					            this.comboNoeud2.getItemAt(this.comboNoeud2.getSelectedIndex()),
					            couleur,
								Integer.parseInt(this.txtDistance.getText())
					);
			couleur = "RGB "+couleur;
			this.lstLabel.add(new JLabel("L'arête relie "    + this.comboNoeud1.getSelectedItem()  + " à " + this.comboNoeud2.getSelectedItem()  
			 				+ " de couleur " + couleur + " et de distance " + this.txtDistance.getText()));
			
			//From javax.swing.plaf.ColorUIResource[r=238,g=238,b=238] 
			//to
			//java.awt.Color[r=238,g=238,b=238]
			/*------------------------------------*/
			
			
			
			
			this.listHistorique.setListData(this.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));
			/*------------------------------------*/

			this.panelGraphique.majIHM();
			
			this.txtDistance.setText("");
		}
		else if(e.getSource() == this.btnGenererPrefait)
		{
			int n = this.ctrl.getLstNoeud().size();
			n = (n*(n-1));
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
				if(this.ctrl.getNoeudDispo(noeud1).size() > 0)
					noeud2 = this.ctrl.getNoeudDispo(noeud1).get((int)(Math.random() * this.ctrl.getNoeudDispo(noeud1).size()));
				else
					noeud1 = lstNoeud.get((int)(Math.random() * lstNoeud.size()));
			}
			String randomCouleur = "[r=" + (int)(Math.random() * 255) + ",g=" + (int)(Math.random() * 255) + ",b=" + (int)(Math.random() * 255) + "]";
			int randomDistance =(int)(Math.random() * 8)+1;

			this.ctrl.addArete( noeud1,noeud2,randomCouleur,randomDistance);

			/* Ajout de l'arête dans l'historique */
			this.lstLabel.add(new JLabel("L'arête relie "    + noeud1  + " à " + noeud2   + " de couleur RGB " + 
			randomCouleur + " et de distance " + randomDistance));
			this.listHistorique.setListData(this.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));
			
			this.panelGraphique.majIHM();
		}
		if(e.getSource() == this.btnConfirmer)
		{

			int n = this.listHistorique.getSelectedIndex();
			this.ctrl.getLstArete().get(n).setWagon(Integer.parseInt(this.txtDistanceUpdate.getText()));
			this.listHistorique.setListData(this.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));
			this.ctrl.majIHM();
			jd.dispose();
		}
	}

	public void mouseClicked(MouseEvent e) {

		if(e.getClickCount() == 2)
		{
			this.jd = new JDialog();
			jd.setTitle("Modification des coordonnées");
			jd.setBounds(900, 300, 500, 200); 
			JPanel panelPopUp = new JPanel(new BorderLayout(5,5));
			JPanel panelCentre = new JPanel(new GridLayout(3,4,5,5));

			Arete a = this.ctrl.getLstArete().get(this.listHistorique.getSelectedIndex());
		
			this.txtDistanceUpdate.setText("" + a.getWagon());
			String c = a.getCouleur();
			if(c.equals("Neutre"))
			{
				this.containerUpdate.setBackground(new Color(228,228,228));

			}
			else
			{
				String[] tabCouleur = c.split(",");
				int r = Integer.parseInt(tabCouleur[0].substring(3));
				int g = Integer.parseInt(tabCouleur[1].substring(2));
				int b = Integer.parseInt(tabCouleur[2].substring(2,tabCouleur[2].length()-1));
				this.containerUpdate.setBackground(new Color(r,g,b));
			}

			for (int i = 1; i <= 4; i++)
			{
				panelCentre.add(new JLabel());
			}

			panelCentre.add(new JLabel());
			panelCentre.add(new JLabel("Nouvelle distance : "));
			panelCentre.add(this.txtDistanceUpdate);
			panelCentre.add(new JLabel());

			for (int i = 1; i <= 4; i++)
			{
				panelCentre.add(new JLabel());
			}
			panelPopUp.add(this.containerUpdate, BorderLayout.NORTH);
			panelPopUp.add(panelCentre, BorderLayout.CENTER);
			panelPopUp.add(this.btnConfirmer, BorderLayout.SOUTH);

			jd.add(panelPopUp);
			jd.setVisible(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
			

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
	}
}
