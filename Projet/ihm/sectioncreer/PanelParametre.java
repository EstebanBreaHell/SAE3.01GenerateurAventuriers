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
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;
import javax.swing.table.DefaultTableCellRenderer;

import main.Controleur;

public class PanelParametre extends JPanel implements ActionListener
{
	private JTextField txtNbJoueursMin;	
	private JTextField txtNbJoueursMax;
	private JTextField txtNbCartesJoueurs;	
	private JTextField txtNbJoueursMinAreteDouble;
	private JTextField txtNbPlusLongChemin;
	private JTextField txtNom;
	private JTextField txtNomMoyenDeTransport;
	private JTextField txtFinDePartie;
	private JButton btnValider;
	private JButton btnConfirmer;
	private JScrollPane scrollPane;

	private JCheckBox chbPlusLongueRoute;
	private JDialog jd;
	private JTable tableau;

	private static ArrayList<JButton> allButton = new ArrayList<JButton>();
	private static  ArrayList<String>  allCoul;


	private Controleur ctrl;

	private Object[][] donnees;
	private String[] entetes = {"Longueur de la route", "Points marqués"};
	
	public PanelParametre(Controleur ctrl)
	{
		/**
		 * Création des éléments de la fenêtre
		 */

		ArrayList<String> lstCouleurTest = new ArrayList<String>();
		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());
		this.donnees = this.ctrl.getpointsTaille();
		PanelParametre.allCoul = this.ctrl.getLstCouleurJoueur();
		JPanel panelDetail 		= new JPanel(new GridLayout(11,4,0,10));
		JPanel panelChoixCarte	= new JPanel(null);
		JPanel panelBas			= new JPanel(new GridLayout(1,3,10,10));
		JPanel panelTableau		= new JPanel(new BorderLayout());

		DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
		custom.setHorizontalAlignment(JLabel.CENTER);

		this.txtNbJoueursMin 			= new JTextField("1");
		this.txtNbJoueursMax 			= new JTextField(this.ctrl.getNbJoueurMax()+"");
		this.txtNbCartesJoueurs 		= new JTextField(this.ctrl.getNbWagonDebutPartie() + "");
		this.txtNomMoyenDeTransport		= new JTextField(this.ctrl.getNomMoyenDeTransport()+"");
		this.txtNom						= new JTextField(this.ctrl.getTxtRoute());
		this.txtFinDePartie				= new JTextField(this.ctrl.getNbWagonFinPartie() + "");
		this.btnValider 				= new JButton("Générer XML");
		this.btnConfirmer 				= new JButton("Confirmer");
		this.chbPlusLongueRoute 		= new JCheckBox("Chemin le plus long");
		this.txtNbJoueursMinAreteDouble = new JTextField(this.ctrl.getNbJoueurMinDoubleArete()+"");
		this.txtNbPlusLongChemin 		= new JTextField("" + this.ctrl.getNbPointsPlusLongChemin());

		JLabel lblNbJoueursMin 				= new JLabel("Nb joueurs minimum : ");
		JLabel lblNbJoueursMax 				= new JLabel("Nb joueurs maximum : ");
		JLabel lblNbCartesJoueurs 			= new JLabel("Nb cartes / joueur : ");
		JLabel lblPoints     				= new JLabel("Barême des points : ");
		JLabel lblNom 						= new JLabel("Type arête :");
		JLabel lblNomMoyenDeTransport 		= new JLabel("Type moyen de transport :");
		JLabel lblFinDePartie				= new JLabel("Arrêter la partie à : ");
		JLabel lblMoyenDeTransport			= new JLabel("  " + this.txtNomMoyenDeTransport.getText() + "...");
		JLabel lblNbJoueursMinAreteDouble	= new JLabel("Nb joueurs arête double : ");

		this.tableau = new JTable(donnees, entetes);

		
	
		/**
		 * Rendre les éléments du tableau des scores éditables
		 */
		for(int i = 0; i < tableau.getColumnCount(); i++)
		{
			this.tableau.getColumnModel().getColumn(i).setCellRenderer(custom);
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
		this.txtNom						.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.txtNomMoyenDeTransport		.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.txtFinDePartie				.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.txtNbJoueursMinAreteDouble	.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.txtNbPlusLongChemin		.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		lblPoints	.setBounds(  0, -100,250, 250);
		panelTableau.setBounds( 50,  150,400, 130);
		 

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
			this.txtNbPlusLongChemin.setText("0");
		}

		if(e.getSource() == this.btnValider)
		{
			this.jd = new JDialog();
			this.jd.setTitle("Choix des couleurs pour chaque joueur");
			this.jd.setLayout(new BorderLayout());
			this.jd		.setBounds(900,  300,500, 400);
			if(this.txtNbCartesJoueurs.getText().isEmpty() || this.txtNbJoueursMax.getText().isEmpty() || 
			   this.txtNbJoueursMin.getText().isEmpty()    || this.txtNom.getText().isEmpty()		   || 
			   this.txtNomMoyenDeTransport.getText().isEmpty() || this.txtFinDePartie.getText().isEmpty())
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
				this.ctrl.setNomMoyenDeTransport(this.txtNomMoyenDeTransport.getText());
				this.ctrl.setTxtRoute(this.txtNom.getText());


				

				if(nbJoueursMin > nbJoueursMax || nbJoueursMin <= 0 || nbJoueursMax <= 0 || nbCartesJoueurs <= 0 || nbFinDePartie < 0)
				{
					JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
				} 

				//Regarde les modification qui on était apporté au JTable 
				
				String[][] tmpNbPoints = new String[tableau.getRowCount()][tableau.getColumnCount()];
				//Récupération des données du JTable nommé tableau
				for(int i = 0; i < tableau.getRowCount(); i++)
				{
					//Récupération des données de la ligne i
					for(int j = 0; j < tableau.getColumnCount(); j++)
					{
						//Récupération des données de la colonne j
						if(tableau.getValueAt(i, j) != null)
						{
							tmpNbPoints[i][j] = (String) tableau.getValueAt(i, j);
							
						}
						else
						{
							tmpNbPoints[i][j] = "0";
						}
					}
				}

				this.ctrl.setPointsTaille(tmpNbPoints);
				PanelParametre.allCoul = this.ctrl.getLstCouleurJoueur();

				JPanel panelCouleur = new JPanel(new GridLayout(nbJoueursMax + 1, 2,5,5));
				JPanel panelConfirmer = new JPanel(new GridLayout(1,3,5,5));

				
				for(int i = 1; i <= nbJoueursMax; i ++)
				{
					JButton btnTmp = new JButton("Choisir Couleur ");
					PanelParametre.allButton.add(btnTmp);
					panelCouleur.add(new JLabel("Couleur joueur " + i + " : "));
					panelCouleur.add(btnTmp);
					btnTmp.addActionListener(this);
					this.scrollPane = new JScrollPane(panelCouleur);



					if(PanelParametre.allCoul.size()< nbJoueursMax)
					{
						PanelParametre.allCoul.add(null);
					}
					
					if(PanelParametre.allCoul.get(i-1) != null)
					{
						String tmp = PanelParametre.allCoul.get(i-1);
						//tmp = [r=255,g=125;b=0]
						int r= Integer.parseInt(tmp.split(",")[0].substring(3));

						int g= Integer.parseInt(tmp.split(",")[1].substring(2));
						int b =Integer.parseInt(tmp.split(",")[2].substring(2, tmp.split(",")[2].length()-1));
						btnTmp.setBackground(new Color(r,g,b));
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
				
				this.jd.add(scrollPane);
				this.jd.add(panelConfirmer, BorderLayout.SOUTH);
				this.jd.setVisible(true);
				this.ctrl.ecrireXml();
			}
		}

		if(e.getSource() == btnConfirmer)
		{
			this.jd.dispose();
			this.ctrl.ecrireXml();
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


			String c = couleur.toString().substring(14,couleur.toString().length());

			PanelParametre.allCoul.set(index, c);
			
			System.out.println("------------");
			
			this.ctrl.setLstCouleurJoueur(PanelParametre.allCoul);
			



		}
		//this.ctrl.setLstCouleurJoueur(PanelParametre.allCoul);
	}
}
