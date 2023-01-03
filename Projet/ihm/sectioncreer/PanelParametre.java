/**
 * @author Lefort William, Decharrois Adrien, Brea-Hell Esteban
 * @version 1.0
 * @date 2019-03-20
 */

package ihm.sectioncreer;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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

	private static ArrayList<JButton> allButton = new ArrayList<JButton>();
	private static  String[] allCoul;
	private static int indexBtn = -1;


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
		this.chbPlusLongueRoute 		= new JCheckBox("Chemin le plus long");
		this.txtNbJoueursMinAreteDouble = new JTextField();
		this.txtNbPlusLongChemin 		= new JTextField("" + this.ctrl.getNbPointsPlusLongChemin());

		JLabel lblNbJoueursMin 				= new JLabel("Nb joueurs minimum : ");
		JLabel lblNbJoueursMax 				= new JLabel("Nb joueurs maximum : ");
		JLabel lblNbCartesJoueurs 			= new JLabel("Nb cartes / joueur : ");
		JLabel lblPoints     				= new JLabel("Barême des points : ");
		JLabel lblCouleur		 			= new JLabel("Nb couleurs : ");
		JLabel lblNom 						= new JLabel("Type arête :");
		JLabel lblNomMoyenDeTransport 		= new JLabel("Type moyen de transport :");
		JLabel lblFinDePartie				= new JLabel("Arrêter la partie à : ");
		JLabel lblMoyenDeTransport			= new JLabel("  " + this.txtNomMoyenDeTransport.getText() + "...");
		JLabel lblNbJoueursMinAreteDouble	= new JLabel("Nb joueurs arête double : ");

		JTable tableau = new JTable(donnees, entetes);

		this.jd = new JDialog();
		jd.setTitle("Choix des couleurs pour chaque joueur");
		this.jd.setLayout(new BorderLayout());
	
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
		this.chbPlusLongueRoute.setSelected(true);
		this.txtNomMoyenDeTransport.setForeground(Color.GRAY);

		this.txtNbCartesJoueurs			.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.txtNbJoueursMin			.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.txtNbJoueursMax			.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.txtNbCouleurs				.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.txtNom						.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.txtNomMoyenDeTransport		.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.txtFinDePartie				.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.txtNbJoueursMinAreteDouble	.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.txtNbPlusLongChemin		.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		lblPoints	.setBounds(  0, -100,250, 250);
		panelTableau.setBounds( 50,  150,400, 120);
		this.jd		.setBounds(900,  300,500, 400); 

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
		this.chbPlusLongueRoute.addActionListener(this);

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

		this.txtFinDePartie.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});

		this.txtNbPlusLongChemin.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});

		txtNbJoueursMinAreteDouble.addKeyListener(new KeyAdapter() {
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

	/**
	 * Récupération des données
	 * @param e
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(this.chbPlusLongueRoute.isSelected())
		{
			this.txtNbPlusLongChemin.setEnabled(true);
		}
		else 
		{
			this.txtNbPlusLongChemin.setEnabled(false);
		}

		if(e.getSource() == this.btnValider)
		{

			if(this.txtNbCartesJoueurs.getText().isEmpty() || this.txtNbJoueursMax.getText().isEmpty() || 
			   this.txtNbJoueursMin.getText().isEmpty()    || this.txtNom.getText().isEmpty()		   || 
			   this.txtNomMoyenDeTransport.getText().isEmpty() || this.txtNbCouleurs.getText().isEmpty() || this.txtFinDePartie.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				int nbJoueursMin = Integer.parseInt(this.txtNbJoueursMin.getText());
				int nbJoueursMax = Integer.parseInt(this.txtNbJoueursMax.getText());
				int nbCartesJoueurs = Integer.parseInt(this.txtNbCartesJoueurs.getText());
				int nbFinDePartie = Integer.parseInt(this.txtFinDePartie.getText());

				
				this.ctrl.setNbJoueurMax(Integer.parseInt(this.txtNbJoueursMax.getText()));
				this.ctrl.setNbJoueurMinDoubleArete(Integer.parseInt(this.txtNbJoueursMinAreteDouble.getText()));
				this.ctrl.setNbWagonDebutPartie(Integer.parseInt(this.txtNbCartesJoueurs.getText()));
				this.ctrl.setNbWagonFinPartie(nbFinDePartie);
				this.ctrl.setNbPointsPlusLongChemin(Integer.parseInt(this.txtNbPlusLongChemin.getText()));

				if(nbJoueursMin > nbJoueursMax || nbJoueursMin <= 0 || nbJoueursMax <= 0 || nbCartesJoueurs <= 0 || nbFinDePartie < 0)
				{
					JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
				} 

				JPanel panelCouleur = new JPanel(new GridLayout(nbJoueursMax + 1, 2,5,5));
				JPanel panelConfirmer = new JPanel(new GridLayout(1,3,5,5));

				
				PanelParametre.allCoul = new String[nbJoueursMax];

				for(int i = 1; i <= nbJoueursMax; i ++)
				{
					JButton btnTmp = new JButton("Choisir Couleur ");
					PanelParametre.allButton.add(btnTmp);
					panelCouleur.add(new JLabel("Couleur joueur " + i + " : "));
					panelCouleur.add(btnTmp);
					btnTmp.addActionListener(this);
					if(PanelParametre.allCoul[i-1] != null)
					{
						btnTmp.setBackground(Color.decode(PanelParametre.allCoul[i-1]));
					}
					else
					{
						btnTmp.setBackground(Color.WHITE);
					}
					


					
				}
				panelConfirmer.add(new JLabel());
				panelConfirmer.add(this.btnConfirmer);
				this.btnConfirmer.setBackground(Color.WHITE);
				panelConfirmer.add(new JLabel());
				
				this.jd.add(panelCouleur, BorderLayout.NORTH);
				this.jd.add(panelConfirmer, BorderLayout.SOUTH);
				this.jd.setVisible(true);
				this.ctrl.ecrireXml();
			}
		}

		if(e.getSource() == btnConfirmer)
		{
			this.jd.dispose();
		}

		if(PanelParametre.allButton.contains(e.getSource()))
		{
			JButton btnTmp = (JButton) e.getSource();
			int index = PanelParametre.allButton.indexOf(btnTmp);
			Color couleur = JColorChooser.showDialog(this, "Choisir une couleur", Color.WHITE);
			btnTmp.setBackground(couleur);
			//From java.awt.Color[r=xx,g=xx,b=xxx]
			//to [r=xx,g=xx,b=xxx]

			JDialog jdColor = new JDialog();
			JPanel panelBtn = new JPanel();

			//jdColor.add
			jdColor.add(panelBtn);
			this.jd.setVisible(true);


			String c = couleur.toString().substring(14,couleur.toString().length()-1);
			PanelParametre.allCoul[index] = (c);



		}
	}
}
