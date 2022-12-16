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

	private List<JLabel> lstLabel;

	public PanelCreerArete(Controleur ctrl)
	{
		this.setLayout(new BorderLayout());
		this.ctrl = ctrl;
		this.lstLabel = new ArrayList<JLabel>();
		this.panelGraphique = new PanelGraphique(this.ctrl);

		JPanel panelHaut 	  		= new JPanel(new GridLayout(6,4,10,10));
		JPanel panelDispoHistorique = new JPanel(new BorderLayout(0,20));
		JPanel panelValidation		= new JPanel(new GridLayout(3,3, 10, 20));

		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		JLabel lblHistorique = new JLabel("Historique", JLabel.CENTER);

		NumberFormat longformat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(longformat);
		numberFormatter.setValueClass(Long.class);
		numberFormatter.setAllowsInvalid(false);
		numberFormatter.setMinimum(0L);



		//Information contenue dans le panelHaut
		JLabel lblDistance = new JLabel("Distance : ", JLabel.LEFT);
		JLabel lblRelier   = new JLabel("Relier... ", JLabel.LEFT);
		JLabel lblVers	   = new JLabel("vers ", JLabel.CENTER);
		JLabel lblCouleur  = new JLabel("Couleur : ", JLabel.LEFT);

		this.container = new Container();
		this.container.setLayout(new FlowLayout());
		this.btnCouleur =new JButton("Couleur de l'arête");
		this.txtDistance = new JFormattedTextField(longformat);	
		

		
		this.comboNoeud1 = new JComboBox<Noeud>();
		this.comboNoeud1.addItemListener(this);
		this.comboNoeud2 = new JComboBox<Noeud>();

		this.btnSupprimer = new JButton("Supprimer");
		this.btnGenererArete = new JButton("Générer arête");
		this.btnGenererPrefait = new JButton("Générer arête préfaite");
		this.listHistorique = new JList<String>();

		this.txtDistance.setBorder(border);
		this.comboNoeud1.setBorder(border);
		this.comboNoeud2.setBorder(border);
		this.btnSupprimer.setBorder(border);
		this.listHistorique.setBorder(border);


		this.btnGenererArete.setBackground(Color.WHITE);
		this.btnGenererPrefait.setBackground(Color.WHITE);
		this.btnSupprimer.setBackground(Color.WHITE);

		this.container.add(this.btnCouleur);

		/* A optimiser par la suite */
		panelHaut.add(new JLabel());
		panelHaut.add(new JLabel());
		panelHaut.add(new JLabel());
		panelHaut.add(new JLabel());

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
		this.btnCouleur.addActionListener(this);

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

	public void supprimArete(int n)
	{
		this.lstLabel.remove(n);
		this.listHistorique.setListData(this.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));
		this.panelGraphique.majIHM();
	}
	public void actionPerformed(ActionEvent e)
	{

		if(e.getSource() == this.btnCouleur)
		{
			Color initialcolor=Color.RED;
			Color color=JColorChooser.showDialog(this,"Select a color",initialcolor);
			container.setBackground(color);
		}

		if(e.getSource() == this.btnSupprimer)
		{

			this.lstLabel.remove(this.listHistorique.getSelectedIndex());
			this.ctrl.supprArete(this.listHistorique.getSelectedIndex());
			this.listHistorique.setListData(this.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));
			this.panelGraphique.majIHM();
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

			// Ajout de l'arête dans l'historique 
			this.lstLabel.add(new JLabel(
			"Arête de "    + this.comboNoeud1.getSelectedItem()  + " à " + this.comboNoeud2.getSelectedItem()   +
				" de Couleur " + this.container.getBackground() + " de distance " + this.txtDistance.getText())
			);

			this.ctrl.addArete( this.comboNoeud1.getItemAt(this.comboNoeud1.getSelectedIndex()),
					            this.comboNoeud2.getItemAt(this.comboNoeud2.getSelectedIndex()),
					            this.container.getBackground().toString(),
								Integer.parseInt(this.txtDistance.getText()) 
					);

			this.listHistorique.setListData(this.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));
			/*------------------------------------*/

			this.panelGraphique.majIHM();

			this.txtDistance.setText("");
		}
		else if(e.getSource() == this.btnGenererPrefait)
		{
			String[] couleurs = {"Rouge", "Vert", "Bleu", "Jaune", "Noir", "Blanc", "Violet", "Marron"};
			String randomCouleur = couleurs[(int)(Math.random() * couleurs.length)];
			String randomDistance = String.valueOf((int)(Math.random() * 1000));

			this.ctrl.addArete( this.comboNoeud1.getItemAt(this.comboNoeud1.getSelectedIndex()),
					this.comboNoeud2.getItemAt(this.comboNoeud2.getSelectedIndex()),
					randomCouleur,
					Integer.parseInt(randomDistance)
					);

			/* Ajout de l'arête dans l'historique */
			this.lstLabel.add(new JLabel("Couleur : " + randomCouleur + " | Distance : " + randomDistance));
			this.listHistorique.setListData(this.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));
			/*------------------------------------*/
		}
	}
	public void itemStateChanged(ItemEvent e) 
    { 
        // si l'état du combobox est modifiée 
		this.comboNoeud2.removeAllItems();

		Noeud n = this.comboNoeud1.getItemAt(this.comboNoeud1.getSelectedIndex());
		
		if(n == null)
			return;
		for(Noeud noeud : this.ctrl.getNoeudDispo(n))
		{
			this.comboNoeud2.addItem(noeud);
		} 
        
    } 



	public void majIHM()
	{
		this.comboNoeud1.removeAllItems();
		for(Noeud noeud : this.ctrl.getLstNoeud())
		{
			this.comboNoeud1.addItem(noeud);
		} 
		

			//this.comboNoeud1.addItem(n);
		
		/*
		 * getNoeudDispo
		 */
		//this.comboNoeud2.addItem(ctrl.getNoeudDispo(n));
		
	}
}
