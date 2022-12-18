package ihm.sectioncreer;

import javax.swing.Icon;
import javax.swing.JColorChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.colorchooser.DefaultColorSelectionModel;

import java.awt.BorderLayout;


import main.Controleur;

public class PanelCreer extends JPanel 
{
	private Controleur ctrl; 
	private PanelGraphique panelGraphique;
	private PanelGestionCreer panelGestionCreer;

	public PanelCreer(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		this.panelGraphique 	= new PanelGraphique(this.ctrl);
		this.panelGestionCreer 	= new PanelGestionCreer(this.ctrl);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
/*
		splitPane.setDividerLocation(1000);
		splitPane.setEnabled(false);
		*/

		
		
		splitPane.add(this.panelGraphique,JSplitPane.LEFT);
		splitPane.add(this.panelGestionCreer,JSplitPane.RIGHT);

		this.add(splitPane);

		/*
		this.add(this.panelGraphique,BorderLayout.CENTER);
		this.add(this.panelGestionCreer, BorderLayout.EAST);
		*/
		
	}

	public void supprimArete(int n)
	{
		this.panelGestionCreer.supprimArete(n);
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
