package ihm.sectioncreer;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import main.Controleur;

public class PanelGestionCreer extends JPanel
{
	private Controleur ctrl;

	private JTabbedPane tabbedPane;
	private PanelCreerNoeud panelCreerNoeud;
	private PanelCreerArete panelCreerArete;
	private PanelCreerCarteObjectif panelCreerCarteObjectif;
	private PanelParametre	panelParametre;
	private PanelCreerCarteWagon panelCreerCarteWagon;

	public PanelGestionCreer(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());

		this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.panelCreerNoeud = new PanelCreerNoeud(this.ctrl);
		this.panelCreerArete = new PanelCreerArete(this.ctrl);
		this.panelCreerCarteObjectif = new PanelCreerCarteObjectif(this.ctrl);
		this.panelCreerCarteWagon  = new PanelCreerCarteWagon(this.ctrl);
		this.panelParametre	 = new PanelParametre(this.ctrl);


		this.tabbedPane.addTab("Créer Noeud",this.panelCreerNoeud);
		this.tabbedPane.addTab("Créer Arête", this.panelCreerArete);
		this.tabbedPane.addTab("Créer Carte Objectif",this.panelCreerCarteObjectif);
		this.tabbedPane.addTab("Créer Carte Wagon",this.panelCreerCarteWagon);
		this.tabbedPane.addTab("Paramètres", this.panelParametre);

		/**
		 * Ajout des composants
		 */
		this.add(this.tabbedPane,BorderLayout.CENTER);
	}

	/**
	 * Supprimer une arête
	 * @param n
	 */
	public void supprimArete(int n)
	{
		this.panelCreerArete.supprimArete(n);
	}

	public List<JLabel> getLstHistorique()
	{
		return this.panelCreerNoeud.getListHistorique();
	}


	
	/**
	 * Retourne le nom du noeud du panel créer
	 * @return String
	 */
    public String getNomNoeudPanelCreer() { return this.panelCreerNoeud.getNomNoeudPanelCreer(); }

	/**
	 * Affiche le message d'erreur
	 * @param text
	 */
	public void afficherErreurPanelCreer(String text) { this.panelCreerNoeud.afficherErreurPanelCreer(text);}

	/**
	 * Met à jour les panels de création de noeud et d'arête
	 */
	public void majIHM()
	{
		this.panelCreerNoeud.majIHM();
		this.panelCreerArete.majIHM();
		this.panelCreerCarteWagon.majIHM();
		this.panelCreerCarteObjectif.majIHM();
	}

}
