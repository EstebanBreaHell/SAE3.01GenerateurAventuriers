package ihm.sectioncreer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import main.Controleur;

public class PanelCreerCarteObjectif extends JPanel
{
	private Controleur ctrl;
	private JTextField txtNbPoint;
	private JList<String> lstNoeud;
	//private JList<CarteObjectif> lstHistorique;
	private JButton btnValider;


	public PanelCreerCarteObjectif(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());
		this.lstNoeud = new JList<String>();
		

		JLabel lblCommentaire = new JLabel("PARTIE DE WILLIAM");

		/**
		 * Positionnement des composants
		 */
		this.add(lblCommentaire, BorderLayout.CENTER);

		/**
		 * Activation des composants
		 */
	}
}
