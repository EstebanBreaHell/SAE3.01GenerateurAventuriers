package ihm.sectioncreer;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import main.Controleur;

public class PanelGestionCreer extends JPanel
{
	private Controleur ctrl;

	private JTabbedPane tabbedPane;
	private PanelCreerNoeud panelCreerNoeud;
	private PanelCreerArete panelCreerArete;
	private PanelDetail	panelDetail;

	public PanelGestionCreer(Controleur ctrl)
	{
		this.ctrl = ctrl; 

		this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.panelCreerNoeud = new PanelCreerNoeud(this.ctrl);
		this.panelCreerArete = new PanelCreerArete(this.ctrl);
		this.panelDetail	 = new PanelDetail(this.ctrl);

		this.tabbedPane.addTab("Créer Noeud",this.panelCreerNoeud);
		this.tabbedPane.addTab("Créer Arete", this.panelCreerArete);
		this.tabbedPane.addTab("Détail", this.panelDetail);

		this.add(this.tabbedPane);

	}
	
}
