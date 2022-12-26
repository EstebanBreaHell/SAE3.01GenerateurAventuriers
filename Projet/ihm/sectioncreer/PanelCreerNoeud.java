package ihm.sectioncreer;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.NumberFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import main.Controleur;

public class PanelCreerNoeud extends JPanel implements ActionListener, MouseListener
{
	private Controleur ctrl;

	private JTextField txtNom;
	private JTextField txtPosX;
	private JTextField txtPosY;
	private JTextField txtNomModif;
	private JTextField txtPosXModif;
	private JTextField txtPosYModif;
	private JTextField txtPosXnom;
	private JTextField txtPosYnom;

	private JButton btnSupprimer;
	private JButton btnGenererNoeud;
	private JButton btnGenererPrefait;
	private JButton btnConfirmer;

	private JScrollPane scrollPane;

	public static List<JLabel> lstLabel;

	public static JList<String> listHistorique;

	private PanelGraphique panelGraphique;
	private JDialog jd;

	public PanelCreerNoeud(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());
		this.lstLabel = new ArrayList<JLabel>();
		this.panelGraphique = new PanelGraphique(this.ctrl);
		
		JPanel panelCoordonnees 	= new JPanel(new GridLayout(5,3,10, 10));
		JPanel panelDispoHistorique = new JPanel(new BorderLayout(0,20));
		JPanel panelValidation		= new JPanel(new GridLayout(3,3, 10, 20));

		JLabel lblNom  = new JLabel("Nom : ",        JLabel.LEFT);
		JLabel lblPosX = new JLabel("Position X : ", JLabel.LEFT);
		JLabel lblPosY = new JLabel("Position Y : ", JLabel.LEFT);
		JLabel lblHistorique = new JLabel("Historique ", JLabel.CENTER);

		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

		NumberFormat longformat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(longformat);
		numberFormatter.setValueClass(Long.class);
		numberFormatter.setAllowsInvalid(false);
		numberFormatter.setMinimum(0L);

		this.txtNom			= new JTextField();
		this.txtPosX		= new JFormattedTextField(longformat);
		this.txtPosY		= new JFormattedTextField(longformat);
		this.txtNomModif	= new JTextField();
		this.txtPosXModif	= new JTextField();
		this.txtPosYModif	= new JTextField();
		this.txtPosXnom		= new JTextField();
		this.txtPosYnom		= new JTextField();

		this.btnSupprimer      = new JButton("Supprimer"              );
		this.btnGenererNoeud   = new JButton("Générer noeud"          );
		this.btnGenererPrefait = new JButton("Générer noeud Aléatoire");
		this.btnConfirmer      = new JButton("Confirmer"              );

		this.listHistorique = new JList<String>();
		this.scrollPane = new JScrollPane(this.listHistorique, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		this.jd = new JDialog();
		jd.setTitle("Modification des coordonnées");
		jd.setBounds(900, 300, 500, 400); 

		/**
		 * Mise en place d'une bordure et d'une couleur de fond aux différents éléments
		 * Placement manuel grâce à la méthode setBounds
		 */
		 
		this.listHistorique.setPreferredSize(new Dimension(0,550));
		this.listHistorique.setBorder(border);
		this.listHistorique.setBackground(Color.WHITE);

		this.btnSupprimer     .setBackground(Color.WHITE);
		this.btnGenererNoeud  .setBackground(Color.WHITE);
		this.btnGenererPrefait.setBackground(Color.WHITE);

		this.txtNom .setBorder(border);
		this.txtPosX.setBorder(border);
		this.txtPosY.setBorder(border);
		this.btnSupprimer.setBorder(border);
		this.btnGenererNoeud.setBorder(border);
		this.btnGenererPrefait.setBorder(border);


		/**
		 * Ajout des composants dans différents panels
		 */
		panelCoordonnees.add(new JLabel());
		panelCoordonnees.add(new JLabel());
		panelCoordonnees.add(new JLabel());
		panelCoordonnees.add(lblNom);
		panelCoordonnees.add(this.txtNom);
		panelCoordonnees.add(new JLabel());
		panelCoordonnees.add(lblPosX);
		panelCoordonnees.add(this.txtPosX);
		panelCoordonnees.add(new JLabel());
		panelCoordonnees.add(lblPosY);
		panelCoordonnees.add(this.txtPosY);
		panelCoordonnees.add(new JLabel());

		panelDispoHistorique.add(lblHistorique,BorderLayout.NORTH);
		panelDispoHistorique.add(new JPanel(), BorderLayout.WEST);
		panelDispoHistorique.add(new JPanel(), BorderLayout.EAST);
		panelDispoHistorique.add(this.scrollPane, BorderLayout.CENTER);
		panelDispoHistorique.add(new JPanel(), BorderLayout.SOUTH);

		panelValidation.add(new JLabel());
		panelValidation.add(this.btnGenererNoeud);
		panelValidation.add(new JLabel());
		panelValidation.add(new JLabel());
		panelValidation.add(this.btnGenererPrefait);
		panelValidation.add(new JLabel());
		panelValidation.add(new JLabel());
		panelValidation.add(this.btnSupprimer);
		panelValidation.add(new JLabel());

		this.add(panelCoordonnees, BorderLayout.NORTH);
		this.add(panelDispoHistorique, BorderLayout.CENTER);
		this.add(panelValidation, BorderLayout.SOUTH);


		/**
		 * Activation des composants
		 */
		this.btnSupprimer.addActionListener(this);
		this.btnGenererNoeud.addActionListener(this);
		this.btnGenererPrefait.addActionListener(this);
		this.btnConfirmer.addActionListener(this);
		this.listHistorique.addMouseListener(this);

		
		/**
		 * Gestion des évènements clavier, on ne peut entrer que des chiffres dans les champs de texte
		 * à optimiser
		 */
		this.txtPosX.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) 
				{
					e.consume();
				}
			}
		});

		this.txtPosY.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) 
				{
					e.consume();
				}
			}
		});

		
		this.txtPosXModif.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) 
				{
					e.consume();
				}
			}
		});

		
		this.txtPosYModif.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) 
				{
					e.consume();
				}
			}
		});
	}
	

	/**
	 * Mise en place des actions des boutons
	 * @param n
	 */
	public void actionPerformed(ActionEvent e)
	{	
		if(e.getSource() == this.btnSupprimer)
		{
			int n = PanelCreerNoeud.listHistorique.getSelectedIndex();

			this.ctrl.supprNoeud(n);
			PanelCreerNoeud.lstLabel.remove(n);
			this.panelGraphique.majIHM();
			PanelCreerNoeud.listHistorique.setListData(PanelCreerNoeud.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));

		}

		if(e.getSource() == this.btnGenererNoeud)
		{
			/* Vérification si tous les champs à préciser sont remplis */
			if(this.txtPosX.getText().isEmpty() || this.txtNom.getText().isEmpty() || this.txtPosY.getText().isEmpty())
			{
				afficherErreurPanelCreer("Tous les champs sont obligatoires");
				return;
			}
			/*---------------------------------------------------------*/

			this.ctrl.addNoeud(this.txtNom.getText(),Integer.parseInt(this.txtPosX.getText()), Integer.parseInt(this.txtPosY.getText()));

			/* Ajout du noeud ajouté dans l'histoirque */
			this.lstLabel.add(new JLabel("Nom : " + this.txtNom.getText() + " | Pos X : " + this.txtPosX.getText() + " | Pos Y : " + this.txtPosY.getText()));
			this.listHistorique.setListData(this.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));
			/*-----------------------------------------*/

			this.ctrl.majIHM();
		}

		if(e.getSource() == this.btnGenererPrefait)
		{
			Random random = new Random();
			String randomNom = "Nouvelle ville";
			int randomPosX = random.nextInt(750) + 50;
			int randomPosY = random.nextInt(600) + 50;

			this.lstLabel.add(new JLabel("Nom : " + randomNom + " | Pos X : " + randomPosX + " | Pos Y : " + randomPosY));
			this.listHistorique.setListData(this.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));

			this.ctrl.addNoeud(randomNom,randomPosX, randomPosY);

			this.ctrl.majIHM();
		}

		if(e.getSource() == this.btnConfirmer)
		{
			//PanelCreerNoeud.listHistorique.getSelectedIndex()
			int n = PanelCreerNoeud.listHistorique.getSelectedIndex();
			this.ctrl.getLstNoeud().get(n).setNom(this.txtNomModif.getText());
			this.ctrl.getLstNoeud().get(n).setPosX(Integer.parseInt(this.txtPosXModif.getText()));
			this.ctrl.getLstNoeud().get(n).setPosY(Integer.parseInt(this.txtPosYModif.getText()));
			this.ctrl.getLstNoeud().get(n).setNomX(Integer.parseInt(this.txtPosXnom.getText()));
			this.ctrl.getLstNoeud().get(n).setNomY(Integer.parseInt(this.txtPosYnom.getText()));


			PanelCreerNoeud.lstLabel.get(n).setText("Nom : " + this.txtNomModif.getText() + " | Pos X : " + this.txtPosXModif.getText() + " | Pos Y : " + this.txtPosYModif.getText());
			//this.lstLabel.add(new JLabel("Nom : " + this.txtNomModif.getText() + " | Pos X : " + this.txtPosXModif.getText() + " | Pos Y : " + this.txtPosYModif.getText()));
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
			jd.setBounds(900, 300, 500, 400); 
			JPanel panelPopUp = new JPanel(new GridLayout(6,2,12,12));
			//this.txtNomModif.setText(this.lstLabel.get(PanelCreerNoeud.listHistorique.getSelectedIndex()).getText().split(" | ")[2] + " " + this.lstLabel.get(PanelCreerNoeud.listHistorique.getSelectedIndex()).getText().split(" | ")[3]);
			//this.txtPosXModif.setText(this.lstLabel.get(PanelCreerNoeud.listHistorique.getSelectedIndex()).getText().split(" | ")[8]);
			//this.txtPosYModif.setText(this.lstLabel.get(PanelCreerNoeud.listHistorique.getSelectedIndex()).getText().split(" | ")[13]);
			//this.txtPosXnom.setText(/* Récupérer le X du labelNom */);
			//this.txtPosYnom.setText(/* Récupérer le Y du labelNom */);

			this.txtNomModif.setText(this.ctrl.getLstNoeud().get(PanelCreerNoeud.listHistorique.getSelectedIndex()).getNom());
			this.txtPosXModif.setText(String.valueOf(this.ctrl.getLstNoeud().get(PanelCreerNoeud.listHistorique.getSelectedIndex()).getX()));
			this.txtPosYModif.setText(String.valueOf(this.ctrl.getLstNoeud().get(PanelCreerNoeud.listHistorique.getSelectedIndex()).getY()));
			this.txtPosXnom.setText(String.valueOf(this.ctrl.getLstNoeud().get(PanelCreerNoeud.listHistorique.getSelectedIndex()).getX()));
			this.txtPosYnom.setText(String.valueOf(this.ctrl.getLstNoeud().get(PanelCreerNoeud.listHistorique.getSelectedIndex()).getY()));

			panelPopUp.add(new JLabel("Nom : "));
			panelPopUp.add(this.txtNomModif);

			panelPopUp.add(new JLabel("Position X ville : "));
			panelPopUp.add(this.txtPosXModif);

			panelPopUp.add(new JLabel("Position Y ville : "));
			panelPopUp.add(this.txtPosYModif);

			panelPopUp.add(new JLabel("Position X nom :")); 
			panelPopUp.add(this.txtPosXnom);

			panelPopUp.add(new JLabel("Position Y nom :"));
			panelPopUp.add(this.txtPosYnom);

			panelPopUp.add(this.btnConfirmer);
			//panelPopUp.add(new JLabel());

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

	public String getNomNoeudPanelCreer() { return this.txtNom.getText(); }

	public void afficherErreurPanelCreer(String text) { JOptionPane.showMessageDialog(this, text, "Erreur", JOptionPane.ERROR_MESSAGE);}

	
	public void majIHM() { 	this.txtNom.setText("");
							this.txtPosX.setText("");
							this.txtPosY.setText(""); }
}
