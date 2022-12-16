package ihm.sectioncreer;

import java.awt.BorderLayout;

import javax.swing.JList;
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
	private PanelDetail	panelDetail;

	public PanelGestionCreer(Controleur ctrl)
	{
		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());

		this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.panelCreerNoeud = new PanelCreerNoeud(this.ctrl);
		this.panelCreerArete = new PanelCreerArete(this.ctrl);
		this.panelCreerCarteObjectif = new PanelCreerCarteObjectif(this.ctrl);
		this.panelDetail	 = new PanelDetail(this.ctrl);

		this.tabbedPane.addTab("Créer Noeud",this.panelCreerNoeud);
		this.tabbedPane.addTab("Créer Arête", this.panelCreerArete);
		this.tabbedPane.addTab("Créer Carte Objectif",this.panelCreerCarteObjectif);
		this.tabbedPane.addTab("Paramètres", this.panelDetail);

		this.add(this.tabbedPane,BorderLayout.CENTER);
	}

	public void supprimArete(int n)
	{
		this.panelCreerArete.supprimArete(n);
	}

    public String getNomNoeudPanelCreer() { return this.panelCreerNoeud.getNomNoeudPanelCreer(); }

	public void afficherErreurPanelCreer(String text) { this.panelCreerNoeud.afficherErreurPanelCreer(text);}

	public void majIHM()
	{
		this.panelCreerNoeud.majIHM();
		this.panelCreerArete.majIHM();
	}

}
