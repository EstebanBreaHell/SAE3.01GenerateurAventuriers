package ihm.sectioncreer;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;

import main.Controleur;

public class PanelCreer extends JPanel 
{
	private Controleur ctrl; 
	private PanelGraphique panelGraphique;
	private PanelGestionCreer panelGestionCreer;

	public PanelCreer(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		this.panelGraphique 	= new PanelGraphique(this.ctrl);
		this.panelGestionCreer 	= new PanelGestionCreer(this.ctrl);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		/*
		splitPane.setDividerLocation(1000);
		splitPane.setEnabled(false);
		*/

		/**
		 * Positionnement des composants
		 */
		splitPane.add(this.panelGraphique,JSplitPane.LEFT);
		splitPane.add(this.panelGestionCreer,JSplitPane.RIGHT);

		this.add(splitPane);

		/*
		this.add(this.panelGraphique,BorderLayout.CENTER);
		this.add(this.panelGestionCreer, BorderLayout.EAST);
		*/
		
	}

	/**
	 * Permet de supprimer une arête du panelGestion
	 * @param n
	 */
	public void supprimArete(int n)
	{
		this.panelGestionCreer.supprimArete(n);
	}
	
	/**
	 * Change le chemin de l'image
	 * @param path
	 */
	public void imageToPanelGraphique(String path)
	{
		this.panelGraphique.imageToPanelGraphique(path);
	}

	/**
	 * Récupère le nom du noeud du panel créer
	 * @return String
	 */
	public String getNomNoeudPanelCreer() { return this.panelGestionCreer.getNomNoeudPanelCreer(); }

	/**
	 * Affiche la popup d'erreur selon le texte passé en paramètre
	 * @param text
	 */
	public void afficherErreurPanelCreer(String text) { this.panelGestionCreer.afficherErreurPanelCreer(text);}


	/**
	 * Etablis une mise à jour de l'ihm
	 */
	public void majIHM()
	{
		this.panelGestionCreer.majIHM();
		this.panelGraphique.repaint();
	}

	public String getImgPanel(){return this.panelGraphique.getImgPanel();}


}
