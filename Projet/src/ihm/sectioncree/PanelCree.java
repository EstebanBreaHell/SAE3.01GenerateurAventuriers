package ihm.sectioncree;

import javax.swing.*;

import ihm.Frame;

import java.awt.BorderLayout;

import main.Controleur;

public class PanelCree extends JPanel 
{
	private Controleur ctrl;
    private PanelInformation panelInformation;
    private PanelGraphique panelGraphique;

	public PanelCree(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());

        this.panelInformation = new PanelInformation(this.ctrl);
        this.panelGraphique =   new PanelGraphique(this.ctrl);

        this.add(panelInformation,BorderLayout.EAST);
        this.add(panelGraphique,BorderLayout.CENTER);
        


    }
}
