/**
 * @author Lefort William, Decharrois Adrien, Brea-Hell Esteban
 * @version 1.0
 * @date 2019-03-20
 */

package ihm.sectioninit;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import main.Controleur;

public class PanelImageImporter extends JPanel
{
	private Controleur ctrl;
	private JScrollPane scrollPane;
	private String[] repertoireImage;
	private PanelImageInfo panelSelectionner;

	private  void initRepertoireImporte()
	{
		try 	{Files.createDirectories(Paths.get("importe"));} 
		catch (IOException e) 	{e.printStackTrace();}

		this.repertoireImage = new File(Paths.get("importe").toFile().getAbsolutePath()).list();
	}

	public PanelImageImporter(Controleur ctrl)
	{		
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout(0,10));
		
		this.initRepertoireImporte();		

		PanelImageInfo[] tabPanelAffichageImporte = new PanelImageInfo[this.repertoireImage.length];
		JPanel panelDispoAffichage =new JPanel(new GridLayout(tabPanelAffichageImporte.length-1,1));

		for (int index = 0; index < tabPanelAffichageImporte.length; index++)
		{
			if(!this.repertoireImage[index].equals("imageCarte") &&
			!this.repertoireImage[index].equals("xml"))
			{
				tabPanelAffichageImporte[index] = new PanelImageInfo(this.ctrl,this.repertoireImage[index]);
	
				panelDispoAffichage.add(tabPanelAffichageImporte[index]);
			}
		}

		this.scrollPane = new JScrollPane(panelDispoAffichage, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		this.add(new JLabel("Liste des images importées",JLabel.CENTER),BorderLayout.NORTH);
		this.add(this.scrollPane,BorderLayout.CENTER);
	}

	/**
	 * Permets de stocker le panelSelectionner, si un panelSelectionner est deja présent alors l'ancien panel ce déselectionne est prend l'instance du nouveau panel en le selectionnant
	 * @param panelSelectionner
	 */
	public void panelSelectionner(PanelImageInfo panelSelectionner)
	{
		if(this.panelSelectionner == null)
		{
			this.panelSelectionner = panelSelectionner;
		}
		else
		{
			this.panelSelectionner.changerBordure(BorderFactory.createEmptyBorder());
			this.panelSelectionner.inverseEtatBtn();
			this.panelSelectionner = panelSelectionner;
		}

		this.panelSelectionner.changerBordure(BorderFactory.createLineBorder(Color.RED, 3));
		this.panelSelectionner.inverseEtatBtn();

	}

	public void paint(Graphics g)
	{
		super.paint(g);
	}
	
	public PanelImageInfo getPanelSelectionner()
	{
		return this.panelSelectionner;
	}

	public void majPanelImporter()
	{
		this.initRepertoireImporte();
		
		PanelImageInfo[] tabPanelAffichageImporte = new PanelImageInfo[this.repertoireImage.length];
		JPanel panelDispoAffichage =new JPanel(new GridLayout(tabPanelAffichageImporte.length-1,1));

		for (int index = 0; index < tabPanelAffichageImporte.length; index++)
		{
			if(!this.repertoireImage[index].equals("imageCarte") && 
			   !this.repertoireImage[index].equals("xml"       ))
			{
				tabPanelAffichageImporte[index] = new PanelImageInfo(this.ctrl,this.repertoireImage[index]);
				panelDispoAffichage.add(tabPanelAffichageImporte[index]);
		
			}
		}
		this.add(panelDispoAffichage);
		this.ctrl.changerPanel("init");
	}
}
