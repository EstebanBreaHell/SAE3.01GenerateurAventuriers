/**
 * @author Lefort William, Decharrois Adrien, Brea-Hell Esteban
 * @version 1.0
 * @date 2019-03-20
 */

package ihm.sectioncreer;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import main.Controleur;

public class PanelParametre extends JPanel implements ActionListener
{
	private JTextField txtNbJoueursMin;	
	private JTextField txtNbJoueursMax;
	private JTextField txtNbCartesJoueurs;	
	private JTextField txtNbCouleurs;
	private JTextField txtNbJoueursMinAreteDouble;
	private JTextField txtNbPlusLongChemin;
	private JTextField txtNom;
	private JTextField txtNomMoyenDeTransport;
	private JTextField txtFinDePartie;
	private JButton btnValider;
	private JButton btnConfirmer;

	private JCheckBox chbPlusLongueRoute;
	private JDialog jd;

	private Controleur ctrl;

	private Object[][] donnees = {
								{"1", "1",},
								{"2", "2",},
								{"3", "4",},
								{"4", "7",},
								{"5", "10",},
								{"6", "15",},};

	private String[] entetes = {"Longueur de la route", "Points marqués"};
	
	public PanelParametre(Controleur ctrl)
	{
		/**
		 * Création des éléments de la fenêtre
		 */
		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());

		JPanel panelDetail 		= new JPanel(new GridLayout(11,4,0,10));
		JPanel panelChoixCarte	= new JPanel(null);
		JPanel panelBas			= new JPanel(new GridLayout(1,3,10,10));
		JPanel panelTableau		= new JPanel(new BorderLayout());

		DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
		custom.setHorizontalAlignment(JLabel.CENTER);

		this.txtNbJoueursMin 			= new JTextField("1");
		this.txtNbJoueursMax 			= new JTextField();
		this.txtNbCartesJoueurs 		= new JTextField("1");
		this.txtNomMoyenDeTransport		= new JTextField("voiture,vélo,wagon");
		this.txtNbCouleurs				= new JTextField("1");
		this.txtNom						= new JTextField(("route,piste cyclable,rails"));
		this.txtFinDePartie				= new JTextField();
		this.btnValider 				= new JButton("Générer XML");
		this.btnConfirmer 				= new JButton("Confirmer");
		this.chbPlusLongueRoute 		= new JCheckBox("Règle chemin le plus long");
		this.txtNbJoueursMinAreteDouble = new JTextField();
		this.txtNbPlusLongChemin 		= new JTextField(" " + this.ctrl.getNbPointsPlusLongChemin());

		JLabel lblNbJoueursMin 				= new JLabel("Nb joueurs minimum : ");
		JLabel lblNbJoueursMax 				= new JLabel("Nb joueurs maximum : ");
		JLabel lblNbCartesJoueurs 			= new JLabel("Nb cartes / joueur : ");
		JLabel lblPoints     				= new JLabel("Barême des points : ");
		JLabel lblCouleur		 			= new JLabel("Nb couleurs : ");
		JLabel lblNom 						= new JLabel("Type arête :");
		JLabel lblNomMoyenDeTransport 		= new JLabel("Type moyen de transport :");
		JLabel lblFinDePartie				= new JLabel("Arrêter la partie à : ");
		JLabel lblMoyenDeTransport			= new JLabel("  " + this.txtNomMoyenDeTransport.getText() + "...");
		JLabel lblNbJoueursMinAreteDouble	= new JLabel("Nb joueurs minimum pour arête double : ");

		JTable tableau = new JTable(donnees, entetes);

		this.jd = new JDialog();
		jd.setTitle("Choix des couleurs pour chaque joueur");
	
		/**
		 * Rendre les éléments du tableau des scores éditables
		 */
		for(int i = 0; i < tableau.getColumnCount(); i++)
		{
			tableau.getColumnModel().getColumn(i).setCellRenderer(custom);
		}

		/**
		 * Ajout d'une bordure et d'une couleur aux éléments
		 * Utilisation de setBounds() pour les positionner manuellement 
		 */ 
		this.btnValider.setBackground(Color.WHITE);
		this.txtNom.setForeground(Color.GRAY);
		this.txtNomMoyenDeTransport.setForeground(Color.GRAY);

		this.txtNbCartesJoueurs.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.txtNbJoueursMin.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.txtNbJoueursMax.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.txtNbCouleurs.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.txtNom.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.txtNomMoyenDeTransport.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.txtFinDePartie.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.txtNbJoueursMinAreteDouble.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.txtNbPlusLongChemin.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		lblPoints			.setBounds(  0,  -100,250, 250);
		panelTableau		.setBounds( 50,  100,400, 117);
		this.jd				.setBounds(900,  300,500, 400); 

		/**
		 * Ajout des composants dans chaque panel
		 */
		for(int i = 1; i <= 4; i++)
			panelDetail.add(new JLabel());

		panelDetail.add(lblNbJoueursMin);
		panelDetail.add(new JLabel());
		panelDetail.add(this.txtNbJoueursMin);
		panelDetail.add(new JLabel());

		panelDetail.add(lblNbJoueursMax);
		panelDetail.add(new JLabel());
		panelDetail.add(this.txtNbJoueursMax);
		panelDetail.add(new JLabel());

		panelDetail.add(lblNbCartesJoueurs);
		panelDetail.add(new JLabel());
		panelDetail.add(this.txtNbCartesJoueurs);
		panelDetail.add(new JLabel());

		panelDetail.add(lblCouleur);
		panelDetail.add(new JLabel());
		panelDetail.add(this.txtNbCouleurs);
		panelDetail.add(new JLabel());
	
		panelDetail.add(lblNom);
		panelDetail.add(new JLabel());
		panelDetail.add(this.txtNom);
		panelDetail.add(new JLabel());

		panelDetail.add(lblNomMoyenDeTransport);
		panelDetail.add(new JLabel());
		panelDetail.add(this.txtNomMoyenDeTransport);
		panelDetail.add(new JLabel());

		panelDetail.add(lblFinDePartie);
		panelDetail.add(new JLabel());
		panelDetail.add(this.txtFinDePartie);
		panelDetail.add(lblMoyenDeTransport);

		panelDetail.add(lblNbJoueursMinAreteDouble);
		panelDetail.add(new JLabel());
		panelDetail.add(this.txtNbJoueursMinAreteDouble);
		panelDetail.add(new JLabel());

		panelDetail.add(this.chbPlusLongueRoute);
		panelDetail.add(new JLabel());
		panelDetail.add(this.txtNbPlusLongChemin);

		panelChoixCarte.add(lblPoints);
		panelChoixCarte.add(panelTableau);
		panelTableau.add(tableau.getTableHeader(), BorderLayout.NORTH);
		panelTableau.add(tableau, BorderLayout.CENTER);

		panelBas.add(new JLabel());
		panelBas.add(this.btnValider);
		panelBas.add(new JLabel());

		this.add(panelDetail, BorderLayout.NORTH);
		this.add(panelChoixCarte, BorderLayout.CENTER);
		this.add(panelBas, BorderLayout.SOUTH);

		/**
		 * Activation des composants
		 */
		this.btnValider.addActionListener(this);
		this.btnConfirmer.addActionListener(this);

		/**
		 * Gestion des évènements, à optimiser
		 */
		this.txtNbJoueursMax.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});

		this.txtNbJoueursMin.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});

		this.txtNbCartesJoueurs.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});

		this.txtNbCouleurs.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});

		this.txtNom.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(!(txtNom.getText().isEmpty()))
				{
					txtNom.getText();
				}

				if(txtNom.getText().equals("route,piste cyclable,rails"))
				{
					txtNom.setText("");
					txtNom.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {}
		});

		this.txtNomMoyenDeTransport.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(!(txtNomMoyenDeTransport.getText().isEmpty()))
				{
					txtNomMoyenDeTransport.getText();
				}

				if(txtNomMoyenDeTransport.getText().equals("voiture,vélo,wagon"))
				{
					txtNomMoyenDeTransport.setText("");
					txtNomMoyenDeTransport.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {}
		});
	}

	public void actionPerformed(ActionEvent e )
	{
		
	}
	/*
	public void actionPerformed(ActionEvent e)
	{
		if(this.chbPlusLongueRoute.isSelected())
		{
			this.txtNbPlusLongChemain.setEnabled(true);
		}

		else 
		{
			this.txtNbPlusLongChemain.setEnabled(false);
		}
		if(e.getSource() == this.btnValider)
		{
			this.ctrl.setNbJoueurMax(Integer.parseInt(this.txtNbJoueursMax.getText()));
			this.ctrl.setNbJoueurMinDoubleArete(Integer.parseInt(this.txtNbJoueursMinAreteDouble.getText()));
			this.ctrl.setNbWagonDebutPartie(Integer.parseInt(this.txtNbWagonDebutPartie.getText()));
			this.ctrl.setNbWagonFinPartie(Integer.parseInt(this.txtNbWagonFinDePartie.getText()));
			this.ctrl.setNbPointsPlusLongChemin(Integer.parseInt(this.txtNbPlusLongChemain.getText()));
			this.ctrl.setLstCouleurJoueur(this.arrayCouleurJoueur);

		// 
		}
		else if(e.getSource() == this.btnGenereXml)
		{
			this.ctrl.ecrireXml();
		}

		else if(e.getSource() == this.btnJdCouleur)
		{
			this.jdCouleur = new JDialog();
			this.panelLstCouleurJoueur = new JPanel();
			this.panelCouleurBtn = new JPanel(new GridLayout(1,2));
		

			this.scrollPaneCouleurJoueur = new JScrollPane(this.panelLstCouleurJoueur);
			this.btnValiderCouleurJoueur = new JButton("Valider");
			this.btnAjouterCouleurJoueur = new JButton("Ajouté");
			this.btnSupprimerCouleurJoueur = new JButton("Supprimer");

			this.panelCouleurBtn.add(this.btnSupprimerCouleurJoueur);
			this.container = new Container();
			this.container.setLayout(new FlowLayout());
			this.container.add(this.btnAjouterCouleurJoueur);

			this.panelCouleurBtn.add(this.container);
			



			this.btnValiderCouleurJoueur.addActionListener(this);
			this.btnAjouterCouleurJoueur.addActionListener(this);
			this.btnSupprimerCouleurJoueur.addActionListener(this);
			

			
			this.lstCouleurJoueur.setListData(this.arrayCouleurJoueur.toArray(new String[this.arrayCouleurJoueur.size()]));
			this.panelLstCouleurJoueur.add(this.lstCouleurJoueur);

			this.jdCouleur.setTitle("Couleur des joueurs");
			this.jdCouleur.setSize(500, 500);
			this.jdCouleur.setLocationRelativeTo(null);
			
			this.jdCouleur.setLayout(new BorderLayout());
			this.jdCouleur.add(this.panelCouleurBtn, BorderLayout.NORTH);
			this.jdCouleur.add(this.scrollPaneCouleurJoueur, BorderLayout.CENTER);

			this.jdCouleur.add(this.btnValiderCouleurJoueur, BorderLayout.SOUTH);
			this.jdCouleur.setVisible(true);
		}
		
		else if(e.getSource() == this.btnValiderCouleurJoueur)
		{
			this.jdCouleur.dispose();
		}
		
		


		else if(e.getSource() == this.btnAjouterCouleurJoueur)
		{
			Color initialcolor=Color.RED;
			Color color=JColorChooser.showDialog(this,"Choisissez une couleur d'arête",initialcolor);

			container.setBackground(color);
			String sColor = color.toString();

			sColor = sColor.substring(sColor.indexOf("[") + 1, sColor.indexOf("]"));

			if(!this.arrayCouleurJoueur.contains(sColor))
				this.arrayCouleurJoueur.add(sColor);
			this.lstCouleurJoueur.setListData(this.arrayCouleurJoueur.toArray(new String[this.arrayCouleurJoueur.size()]));
			this.jdCouleur.setVisible(false);
			this.jdCouleur.setVisible(true);
		
		}

		else if(e.getSource() == this.btnSupprimerCouleurJoueur)
		{
			if (this.lstCouleurJoueur.getSelectedIndex() != -1)
			{
				this.arrayCouleurJoueur.remove(this.lstCouleurJoueur.getSelectedIndex());
				this.lstCouleurJoueur.setListData(this.arrayCouleurJoueur.toArray(new String[this.arrayCouleurJoueur.size()]));
		
			}
		}
	}




	*/
}
