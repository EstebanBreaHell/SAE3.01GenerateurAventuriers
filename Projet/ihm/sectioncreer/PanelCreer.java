/**
 * @author Lefort William, Decharrois Adrien, Brea-Hell Esteban
 * @version 1.0
 * @date 2019-03-20
 */

package ihm.sectioncreer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.util.List;

import main.Controleur;

public class PanelCreer extends JPanel 
{
	private Controleur ctrl; 
	private PanelGraphique    panelGraphique;
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

		/**
		 * Positionnement des composants
		 */
		splitPane.add(this.panelGraphique,JSplitPane.LEFT);
		splitPane.add(this.panelGestionCreer,JSplitPane.RIGHT);

		this.add(splitPane);		
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
	 * Permet de renvoyer la taille de la fenêtre PanelCreer
	 * @return
	 */
	public int getTaillePanelCreer() { return this.getWidth(); }

	public List<JLabel> getLstHistorique() { return this.panelGestionCreer.getLstHistorique(); }
	
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
}
