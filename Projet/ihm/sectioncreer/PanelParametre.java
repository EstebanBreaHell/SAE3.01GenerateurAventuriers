package ihm.sectioncreer;


import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import main.Controleur;

public class PanelParametre extends JPanel implements ActionListener
{
	private JTextField txtNbJoueursMin;	
	private JTextField txtNbJoueursMax;
	private JTextField txtNbCartesJoueurs;	
	private JTextField txtNbCouleurs;
	private Controleur ctrl;
	private JButton btnValider;
	private JButton btnplus;
	private JButton btnmoins;
	private JButton btnplus2;
	private JButton btnmoins2;
	private JButton btnEditer;
	private JButton btnConfirmer;
	private JTextField btnCompter;
	private JTextField btnCompter2; 
	private JTextField txtNom;
	private JTextField txtNomMoyenDeTransport;
	private JDialog jd;


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

		JPanel panelDetail 		= new JPanel(new GridLayout(8,4,0,10));
		JPanel panelChoixCarte	= new JPanel(null);
		JPanel panelBas			= new JPanel(new GridLayout(1,3,10,10));
		JPanel panelTableau		= new JPanel(new BorderLayout());

		DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
		custom.setHorizontalAlignment(JLabel.CENTER);

		this.txtNbJoueursMin 		= new JTextField("1");
		this.txtNbJoueursMax 		= new JTextField();
		this.txtNbCartesJoueurs 	= new JTextField();
		this.txtNomMoyenDeTransport = new JTextField("voiture,vélo,wagon");
		this.btnCompter 			= new JTextField("0", JTextField.CENTER);
		this.btnCompter2 			= new JTextField("0");
		this.txtNbCouleurs			= new JTextField();
		this.txtNom					= new JTextField(("route,piste cyclable,rails"));
		this.btnValider 			= new JButton("Générer XML");
		this.btnplus 				= new JButton("+");
		this.btnplus2 				= new JButton("+");
		this.btnmoins 				= new JButton("-");
		this.btnmoins2 				= new JButton("-");
		this.btnEditer 				= new JButton("Importer image moyen de transport");
		this.btnConfirmer 			= new JButton("Confirmer");

		JLabel lblNbJoueursMin 			= new JLabel("Nb joueurs minimum : ");
		JLabel lblNbJoueursMax 			= new JLabel("Nb joueurs maximum : ");
		JLabel lblNbWagonsMax 			= new JLabel("Nb " + this.txtNomMoyenDeTransport.getText() + " de couleurs : ");
		JLabel lblNbWagonsMulti 		= new JLabel("Nb " + this.txtNomMoyenDeTransport.getText() + " multicolores : ");
		JLabel lblNbCartesJoueurs 		= new JLabel("Nb cartes / joueur : ");
		JLabel lblCarteWagon 			= new JLabel(new ImageIcon("donnee\\carteWagons\\carte.png"));
		JLabel lblCarteMulti			= new JLabel(new ImageIcon("donnee\\carteWagons\\carte9.png"));
		JLabel lblPoints     			= new JLabel("Barême des points : ");
		JLabel lblCouleur		 		= new JLabel("Nb couleurs : ");
		JLabel lblNom 					= new JLabel("Type arête :");
		JLabel lblNomMoyenDeTransport 	= new JLabel("Type moyen de transport :");

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
		this.btnCompter.setHorizontalAlignment(SwingConstants.CENTER);
		this.btnCompter2.setHorizontalAlignment(SwingConstants.CENTER);
		this.btnValider.setBackground(Color.WHITE);
		this.btnEditer.setBackground(Color.WHITE);
		this.txtNom.setForeground(Color.GRAY);
		this.txtNomMoyenDeTransport.setForeground(Color.GRAY);

		this.btnplus	.setBounds( 50,  30,  60,  20);
		this.btnCompter .setBounds( 50,  50,  60,  60);
		this.btnmoins	.setBounds( 50, 110,  60,  20);
		this.btnEditer	.setBounds(330,  85, 245,  25);
		this.btnplus2	.setBounds( 50,  200, 60,  20);
		this.btnCompter2.setBounds( 50,  220, 60,  60);
		this.btnmoins2	.setBounds( 50,  280, 60,  20);
		lblCarteMulti	.setBounds( 50,  220,250,  60);
		lblPoints		.setBounds(  0,  240,250, 250);
		lblNbWagonsMulti.setBounds(  0,   40,250, 250);
		lblCarteWagon	.setBounds(100,   50,250,  60);
		panelTableau	.setBounds( 50,  390,400, 117);
		jd				.setBounds(900,  300,500, 400); 	
		this.btnCompter2.setEditable(false);
		this.btnCompter	.setEditable(false);

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

		panelDetail.add(lblNbWagonsMax);
		panelDetail.add(new JLabel());
		panelDetail.add(new JLabel());
		panelDetail.add(new JLabel());

		panelChoixCarte.add(this.btnplus);
		panelChoixCarte.add(this.btnCompter);
		panelChoixCarte.add(this.btnmoins);
		panelChoixCarte.add(lblCarteWagon);
		panelChoixCarte.add(this.btnEditer);

		panelChoixCarte.add(lblNbWagonsMulti);

		panelChoixCarte.add(this.btnplus2);
		panelChoixCarte.add(this.btnCompter2);
		panelChoixCarte.add(this.btnmoins2);
		panelChoixCarte.add(lblCarteMulti);
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
		this.btnplus.addActionListener(this);
		this.btnmoins.addActionListener(this);
		this.btnplus2.addActionListener(this);
		this.btnmoins2.addActionListener(this);
		this.btnValider.addActionListener(this);
		this.btnEditer.addActionListener(this);
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
				txtNom.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtNom.getText().equals(""))
					txtNom.setText("route,piste cyclable,rails");
			}
		});

		this.txtNomMoyenDeTransport.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNom.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtNom.getText().equals(""))
					txtNomMoyenDeTransport.setText("voiture,vélo,wagon");
			}
		});
	}

	/**
	 * Récupération des données
	 * @param e
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnplus)
		{
			int nb = Integer.parseInt(this.btnCompter.getText());
			nb++;
			this.btnCompter.setText(Integer.toString(nb));
		}

		if(e.getSource() == this.btnplus2)
		{
			int nb = Integer.parseInt(this.btnCompter2.getText());
			nb++;
			this.btnCompter2.setText(Integer.toString(nb));
		}

		if(e.getSource() == this.btnmoins || e.getSource() == this.btnmoins2)
		{
			int nb = Integer.parseInt(this.btnCompter.getText());
			nb--;
			this.btnCompter.setText(Integer.toString(nb));

			if(nb < 0)
			{
				this.btnCompter.setText("0");
			}
		}

		if(e.getSource() == this.btnmoins2)
		{
			int nb = Integer.parseInt(this.btnCompter2.getText());
			nb--;
			this.btnCompter2.setText(Integer.toString(nb));

			if(nb < 0)
			{
				this.btnCompter2.setText("0");
			}
		}

		if(e.getSource() == this.btnValider)
		{

			int nbJoueursMin = Integer.parseInt(this.txtNbJoueursMin.getText());
			int nbJoueursMax = Integer.parseInt(this.txtNbJoueursMax.getText());
			int nbCartesJoueurs = Integer.parseInt(this.txtNbCartesJoueurs.getText());
			int nbWagonsMax = Integer.parseInt(this.btnCompter.getText());
			int nbWagonsMulti = Integer.parseInt(this.btnCompter2.getText());


			if(this.txtNbCartesJoueurs.getText().isEmpty() || this.txtNbJoueursMax.getText().isEmpty() || this.txtNbJoueursMin.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
			}

			if(nbJoueursMin > nbJoueursMax || nbJoueursMin <= 0 || nbJoueursMax <= 0 || nbCartesJoueurs <= 0 || nbWagonsMax <= 0 || nbWagonsMulti <= 0)
			{
				JOptionPane.showMessageDialog(this, "Erreur de saisie", "Erreur", JOptionPane.ERROR_MESSAGE);
			}

			JPanel panelCouleur = new JPanel(new GridLayout(nbJoueursMax + 1, 2,5,5));
			for(int i = 1; i <= nbJoueursMax; i ++)
			{
				panelCouleur.add(new JLabel("Couleur joueur " + i + " : "));
				panelCouleur.add(new JTextField());
			}
			panelCouleur.add(btnConfirmer);
			
			jd.add(panelCouleur);
			jd.setVisible(true);
		}

		if(e.getSource() == btnConfirmer)
		{
			jd.dispose();
		}
		
		if(e.getSource() == this.btnEditer)
		{
			FileNameExtensionFilter filtre = new FileNameExtensionFilter("format image(*.png; *.jpg; *.gif)", "png","jpg","gif");
			JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

			jFileChooser.setAcceptAllFileFilterUsed(false);
			jFileChooser.setFileFilter(filtre);

			int res = jFileChooser.showOpenDialog(null);
			if(res == JFileChooser.APPROVE_OPTION)
			{
				File file = jFileChooser.getSelectedFile();
				
				try 	{Files.copy(file.toPath(), Paths.get("importe\\"+file.getName()));} 
				catch (IOException e1) {e1.printStackTrace();}
			}

			this.ctrl.majPanelImporter();
		}
	}
}