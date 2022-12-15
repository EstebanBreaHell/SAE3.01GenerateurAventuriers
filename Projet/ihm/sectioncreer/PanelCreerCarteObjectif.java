package ihm.sectioncreer;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import main.Controleur;
import metier.CarteObjectif;

public class PanelCreerCarteObjectif extends JPanel
{
	private Controleur ctrl;
	private JTextField txtNbPoint;
	private JList<String> lstNoeud;
	//private JList<CarteObjectif> lstHistorique;
	private JButton btnValider;


	public PanelCreerCarteObjectif(Controleur ctrl)
	{
		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());

		this.txtNbPoint = new JTextField();
		this.lstNoeud = this.ctrl.getLstHistorique();

		this.add(this.txtNbPoint,BorderLayout.NORTH);
		this.add(this.lstNoeud,BorderLayout.CENTER);
		
		
	}
}
