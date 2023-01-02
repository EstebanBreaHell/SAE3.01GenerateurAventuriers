package ihm.sectioninit;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.*;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;


import main.Controleur;

public class PanelDispoBtn extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JButton btnImporter;
	private JButton btnEditer;
	private JButton btnImportXml;
	private JButton btnQuitter;

	public PanelDispoBtn(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		JPanel panelPtn = new JPanel(new GridLayout(4,1, 30, 30));
		
		Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
		int width  = (int)(double)(tailleMoniteur.getWidth ()/8  );
		int length = (int)(double)(tailleMoniteur.getHeight()/2.5);

		Border distanceBtn = BorderFactory.createLineBorder(Color.BLACK, 2);

		this.setBorder(BorderFactory.createEmptyBorder(length, width, width, length));

		this.btnImporter = new JButton("Importer");
		this.btnEditer   = new JButton("Editer");
		this.btnImportXml   = new JButton("Importer un xml");
		this.btnQuitter  = new JButton("Quitter");

		this.btnImporter.setBorder(distanceBtn);
		this.btnEditer.setBorder(distanceBtn);
		this.btnQuitter.setBorder(distanceBtn);
		this.btnImportXml.setBorder(distanceBtn);

		this.btnImporter.setBackground(Color.WHITE);
		this.btnEditer.setBackground(Color.WHITE);
		this.btnQuitter.setBackground(Color.WHITE);
		this.btnImportXml.setBackground(Color.WHITE);

		panelPtn.add(this.btnImporter);
		panelPtn.add(this.btnEditer);
		panelPtn.add(this.btnImportXml);
		panelPtn.add(this.btnQuitter);
		
		this.add(panelPtn);

		this.btnEditer.addActionListener(this);
		this.btnImporter.addActionListener(this);
		this.btnQuitter.addActionListener(this);	
		this.btnImportXml.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.btnEditer)
		{
			PanelImageInfo panelSelectionner = this.ctrl.getPanelSelectionner();

			if(this.ctrl.getPanelSelectionner() == null)
			{
				JOptionPane.showMessageDialog(this,"Merci de selectionner une image sur le panel de droite");
			}
			else
			{
				this.ctrl.imageToPanelGraphique("importe/"+panelSelectionner.getNomfichier());
				this.ctrl.changerPanel("editer");
			}
		}
			

		if(e.getSource() == this.btnImporter)
		{
			FileNameExtensionFilter filtre = new FileNameExtensionFilter("format image(*.png; *.jpg; *.gif)", "png","jpg","gif");
			JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

			jFileChooser.setAcceptAllFileFilterUsed(false);
			jFileChooser.setFileFilter(filtre);

			int res = jFileChooser.showOpenDialog(null);
			if(res == JFileChooser.APPROVE_OPTION)
			{
				File file = jFileChooser.getSelectedFile();
				
				
			}

			this.ctrl.majPanelImporter();
		}

		if(e.getSource() == this.btnImportXml)
		{
			/*
			FileNameExtensionFilter filtre = new FileNameExtensionFilter("format xml(*.xml)", "xml");
			JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

			jFileChooser.setAcceptAllFileFilterUsed(false);
			jFileChooser.setFileFilter(filtre);

			int res = jFileChooser.showOpenDialog(null);
			if(res == JFileChooser.APPROVE_OPTION)
			{
				File file = jFileChooser.getSelectedFile();
				
				try 	{Files.copy(file.toPath(), Paths.get("xml/"+file.getName()));} 
				catch (IOException e1) {e1.printStackTrace();}
			}

			this.ctrl.majPanelImporter();
			*/
			this.ctrl.lireXml();
			this.ctrl.changerPanel("editer");
		}

		/* Fermeture de l'application */
		if(e.getSource() == this.btnQuitter)
			System.exit(0);
		/*----------------------------*/
	}

}
