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

	public PanelGestionCreer(Controleur ctrl)
	{
		this.ctrl = ctrl; 

		this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.panelCreerNoeud = new PanelCreerNoeud(this.ctrl);
		this.panelCreerArete = new PanelCreerArete(this.ctrl);

		this.tabbedPane.addTab("Creer Noeud",this.panelCreerNoeud);
		this.tabbedPane.addTab("Creer Arete", this.panelCreerArete);

		this.add(this.tabbedPane);

	}
	
}
