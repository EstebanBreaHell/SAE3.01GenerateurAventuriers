package ihm.sectioncreer;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

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
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		
		splitPane.add(this.panelGraphique,JSplitPane.LEFT);
		splitPane.add(this.panelGestionCreer,JSplitPane.RIGHT);

		this.add(splitPane);

		/*
		this.add(this.panelGraphique,BorderLayout.CENTER);
		this.add(this.panelGestionCreer, BorderLayout.EAST);
		*/
		
	}

	public void imageToPanelGraphique(String path)
	{
		this.panelGraphique.imageToPanelGraphique(path);
	}

	public String getNomNoeudPanelCreer() { return this.panelGestionCreer.getNomNoeudPanelCreer(); }

	public void afficherErreurPanelCreer(String text) { this.panelGestionCreer.afficherErreurPanelCreer(text);}


	public void majIHM(){
		this.panelGestionCreer.majIHM();
		this.panelGraphique.repaint();
	}


}
