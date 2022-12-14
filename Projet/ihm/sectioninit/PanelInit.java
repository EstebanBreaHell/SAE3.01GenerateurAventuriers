package ihm.sectioninit;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import main.Controleur;

public class PanelInit extends JPanel
{
	private Controleur ctrl;
	private PanelDispoBtn panelDispoBtn;
	private PanelImageImporter panelImageImporter;

	public PanelInit(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		this.panelDispoBtn = new PanelDispoBtn(this.ctrl);
		this.panelImageImporter = new PanelImageImporter(this.ctrl);
		
		this.add(this.panelDispoBtn,BorderLayout.CENTER);
		this.add(this.panelImageImporter,BorderLayout.EAST);
	}

	public void panelSelectionner(PanelImageInfo panelSelectionner){this.panelImageImporter.panelSelectionner(panelSelectionner);}
	public void majPanelImporter(){this.panelImageImporter.majPanelImporter();}

	public PanelImageInfo getPanelSelectionner()
	{
		return this.panelImageImporter.getPanelSelectionner();
	}
	
}
