package ihm.sectioncreer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import main.Controleur;

public class PanelCreerCarteObjectif extends JPanel
{
	private Controleur ctrl;
	private JTextField txtNbPoint;
	private JList<String> lstNoeud;
	private JButton btnHistoriqueCarte;
	private JButton btnCreerCarte;

	public PanelCreerCarteObjectif(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
	
		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());

		this.lstNoeud = new JList<String>();
		this.txtNbPoint = new JTextField(2);
		this.btnHistoriqueCarte = new JButton("Historique des carte");
		this.btnCreerCarte = new JButton("Créer carte");
		
		JPanel panelDispoListeApercu = new JPanel(new GridLayout(2,1));

		JPanel panelDispoListe = new JPanel(new BorderLayout());
		JPanel panelDispoGestionPoint = new JPanel(new BorderLayout(100,0));

		JPanel panelDispoApercu = new JPanel(new GridLayout(1,2));
		JPanel panelDispoFace = new JPanel(new BorderLayout());
		JPanel panelDispoArriere = new JPanel(new BorderLayout());

		JPanel panelDispoBtnCreerHistorique = new JPanel(new GridLayout(2,1));

		/**
		 * Positionnement des composants
		 */
		

		/*Début de panelDispoListe */
		panelDispoListe.add(new JLabel("Liste des noeud existant sur la mappe",JLabel.CENTER),BorderLayout.NORTH);
		panelDispoListe.add(this.lstNoeud,BorderLayout.CENTER);
	
			panelDispoGestionPoint.add(new JLabel("nombre de point de la carte :",JLabel.CENTER),BorderLayout.WEST);
			panelDispoGestionPoint.add(this.txtNbPoint, BorderLayout.CENTER);
			panelDispoGestionPoint.add(new JLabel("point"),BorderLayout.EAST);

		panelDispoListe.add(panelDispoGestionPoint,BorderLayout.SOUTH);
		/*Fin de panelDispoListe */

		/*Début de panelDispoApercu */
			panelDispoFace.add(new JLabel("Carte de face",JLabel.CENTER),BorderLayout.NORTH);
			panelDispoFace.add(new JLabel(Controleur.imageToIcon("importe\\a_changer.png", 200, 200)),BorderLayout.CENTER);
			panelDispoFace.add(new JButton("voir apercu"),BorderLayout.SOUTH);

		panelDispoApercu.add(panelDispoFace);

			panelDispoArriere.add(new JLabel("Carte de derrier",JLabel.CENTER),BorderLayout.NORTH);
			panelDispoArriere.add(new JLabel(Controleur.imageToIcon("importe\\FortniteMappe.png", 200, 200)),BorderLayout.CENTER);
			panelDispoArriere.add(new JButton("Modifier motif"),BorderLayout.SOUTH);

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

		/**
		 * Activation des composants
		 */
	}
}
