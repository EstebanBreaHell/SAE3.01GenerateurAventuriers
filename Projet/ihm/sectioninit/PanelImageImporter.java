package ihm.sectioninit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import main.Controleur;

public class PanelImageImporter extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JScrollPane scrollPane;
	private static String[] repertoireImage;
	private PanelImageInfo panelSelectionner;

	private static void initRepertoireImporte()
	{
		try 	{Files.createDirectories(Paths.get("importe"));} 
		catch (IOException e) 	{e.printStackTrace();}

		PanelImageImporter.repertoireImage = new File(Paths.get("importe").toFile().getAbsolutePath()).list();
	}

	public PanelImageImporter(Controleur ctrl)
	{		
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());
		
		PanelImageImporter.initRepertoireImporte();		

		PanelImageInfo[] tabPanelAffichageImporte = new PanelImageInfo[PanelImageImporter.repertoireImage.length];
		JPanel panelDispoAffichage =new JPanel(new GridLayout(tabPanelAffichageImporte.length,1));

		for (int index = 0; index < tabPanelAffichageImporte.length; index++)
		{
			tabPanelAffichageImporte[index] = new PanelImageInfo(this.ctrl,PanelImageImporter.repertoireImage[index]);

			panelDispoAffichage.add(tabPanelAffichageImporte[index]);
		}

		this.scrollPane = new JScrollPane(panelDispoAffichage, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		this.add(this.scrollPane,BorderLayout.CENTER);
	}

	/**
	 * Permets de stocker le panelSelectionner, si un panelSelectionner est deja présent alors l'ancien panel ce déselectionne est prend l'instance du nouveau panel en le selectionnant
	 * @param panelSelectionner
	 */
	public void panelSelectionner(PanelImageInfo panelSelectionner)
	{
		if(this.panelSelectionner == null)
			this.panelSelectionner = panelSelectionner;
		else
		{
			this.panelSelectionner.inverseEtatBtn();
			this.panelSelectionner = panelSelectionner;
		}

		this.panelSelectionner.inverseEtatBtn();

	}


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
}
