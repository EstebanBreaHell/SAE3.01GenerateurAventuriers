package ihm.sectioncreer;

import javax.swing.JPanel;
import java.awt.BorderLayout;


import main.Controleur;

public class PanelCreer extends JPanel 
{
	private Controleur ctrl; 
	private PanelGraphique panelGraphique;
	private PanelGestionCreer panelGestionCreer;
	private PanelDetail panelDetail;

	public PanelCreer(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		this.panelGraphique 	= new PanelGraphique(this.ctrl);
		this.panelGestionCreer 	= new PanelGestionCreer(this.ctrl);
		
		this.add(this.panelGraphique,BorderLayout.CENTER);
		this.add(this.panelGestionCreer, BorderLayout.EAST);
		
	}
}
