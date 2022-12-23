package ihm.sectioncreer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

import main.Controleur;

public class PanelCreerCarteObjectif extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JTextField txtNbPoint;
	private JList<String> lstNoeud;
	private JButton btnHistoriqueCarte;
	private JButton btnCreerCarte;
	private JButton btnVoirApercu;
	private JButton btnModifierMotif;

	public PanelCreerCarteObjectif(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
	
		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());

		this.lstNoeud = new JList<String>();
		this.txtNbPoint = new JTextField(2);
		this.btnHistoriqueCarte = new JButton("Historique des cartes");
		this.btnCreerCarte = new JButton("Créer carte");
		this.btnVoirApercu = new JButton("Voir aperçu");
		this.btnModifierMotif = new JButton("Modifier le motif");
		
		JPanel panelDispoListeApercu = new JPanel(new GridLayout(2,1));

		JPanel panelDispoListe = new JPanel(new BorderLayout());
		JPanel panelDispoGestionPoint = new JPanel(new BorderLayout(0,100));

		JPanel panelDispoApercu = new JPanel(new GridLayout(1,2,10,10));
		JPanel panelDispoFace = new JPanel(new BorderLayout());
		JPanel panelDispoArriere = new JPanel(new BorderLayout(10,10));

		JPanel panelDispoBtnCreerHistorique = new JPanel(new GridLayout(2,1,5,5));

		/**
		 * Positionnement des composants
		 */
		

		/*Début de panelDispoListe */
		panelDispoListe.add(new JLabel("Liste des noeuds existant sur la mappe",JLabel.CENTER),BorderLayout.NORTH);
		panelDispoListe.add(this.lstNoeud,BorderLayout.CENTER);
	
			panelDispoGestionPoint.add(new JLabel("Nb points de la carte :",JLabel.CENTER),BorderLayout.WEST);
			panelDispoGestionPoint.add(this.txtNbPoint, BorderLayout.CENTER);
			JLabel lblPoints = new JLabel("points", JLabel.CENTER);
			panelDispoGestionPoint.add(lblPoints, BorderLayout.EAST);


		panelDispoListe.add(panelDispoGestionPoint,BorderLayout.SOUTH);
		/*Fin de panelDispoListe */

		/*Début de panelDispoApercu */
			panelDispoFace.add(new JLabel("Recto",JLabel.CENTER),BorderLayout.NORTH);
			panelDispoFace.add(new JLabel(Controleur.imageToIcon("importe\\a_changer.png", 200, 200)),BorderLayout.CENTER);
			panelDispoFace.add(this.btnVoirApercu,BorderLayout.SOUTH);

		panelDispoApercu.add(panelDispoFace);

			panelDispoArriere.add(new JLabel("Verso",JLabel.CENTER),BorderLayout.NORTH);
			panelDispoArriere.add(new JLabel(Controleur.imageToIcon("importe\\FortniteMappe.png", 200, 200)),BorderLayout.CENTER);
			panelDispoArriere.add(this.btnModifierMotif,BorderLayout.SOUTH);

		panelDispoApercu.add(panelDispoArriere);
		/*Fin de panelDispoApercu */

		panelDispoBtnCreerHistorique.add(this.btnHistoriqueCarte);
		panelDispoBtnCreerHistorique.add(this.btnCreerCarte);

		/*Début de la disposition des panel Liste et apercu dans le panel centre*/
		panelDispoListeApercu.add(panelDispoListe);
		panelDispoListeApercu.add(panelDispoApercu);
		/*Fin de la disposition des panel */

		
		this.add(panelDispoListeApercu,BorderLayout.CENTER);
		this.add(panelDispoBtnCreerHistorique,BorderLayout.SOUTH);

		this.btnCreerCarte.setBackground(Color.WHITE);
		this.btnHistoriqueCarte.setBackground(Color.WHITE);
		this.btnVoirApercu.setBackground(Color.WHITE);
		this.btnModifierMotif.setBackground(Color.WHITE);

		/**
		 * Activation des composants
		 */

		this.btnHistoriqueCarte.addActionListener(this);
		this.btnCreerCarte.addActionListener(this);
		this.btnVoirApercu.addActionListener(this);
		this.btnModifierMotif.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e)
	{
	}
}
