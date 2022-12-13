package ihm;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;

import java.awt.*;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import main.Controleur;

public class PanelChoixMode extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JButton btnImporter;
	private JButton btnEditer;
	private JButton btnQuitter;
	private JScrollPane scrollPane;

	public PanelChoixMode(Controleur ctrl)
	{
		this.ctrl = ctrl;

		Icon iconCree 	     = Controleur.imageToIcon("Projet\\donnee\\chemin.png");
		JPanel panelDispoBtn = new JPanel(new GridLayout(3,1, 30, 30));
		JLabel lblTitre      = new JLabel("Images importées", JLabel.CENTER);
		Border border        = BorderFactory.createLineBorder(Color.BLACK, 2);

		/* Gestion en fonction de la résolution des écrans */
		Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
		int width  = (int)(double)(tailleMoniteur.getWidth ()/6  );
		int length = (int)(double)(tailleMoniteur.getHeight()/2.5);
		Border espacement = BorderFactory.createEmptyBorder(length, width, width, length);
		/*-------------------------------------------------*/

		JPanel panelImages = new JPanel(new BorderLayout());

		this.setLayout(new BorderLayout());
	
		panelImages.setBorder(border);

		/* Création des boutons */
		this.btnImporter = new JButton("Importer");
		this.btnEditer   = new JButton("Editer",iconCree);
		this.btnQuitter  = new JButton("Quitter");
		/*----------------------*/

		/* Ajout des boutons */
		this.btnImporter.setBorder(border);
		this.btnEditer.setBorder(border);
		this.btnQuitter.setBorder(border);

		this.btnImporter.setBackground(Color.WHITE);
		this.btnEditer.setBackground(Color.WHITE);
		this.btnQuitter.setBackground(Color.WHITE);
		
		JPanel tmp = new JPanel(new GridLayout(70,1));	
		JPanel panelListe[]  = new JPanel[70];


		panelDispoBtn.setBorder(espacement);
		panelDispoBtn.add(this.btnImporter);
		panelDispoBtn.add(this.btnEditer);
		panelDispoBtn.add(this.btnQuitter);

	

		for(int i = 0; i < 70; i++)
		{
			panelListe[i] = new JPanel(new BorderLayout());
			panelListe[i].add(new JLabel("Image n" + i,JLabel.CENTER),BorderLayout.NORTH);
			panelListe[i].add(new JButton(iconCree),BorderLayout.CENTER);
			panelImages.add(panelListe[i]);

			tmp.add(panelListe[i]);

		}

		this.scrollPane = new JScrollPane(tmp, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


		this.add(panelDispoBtn);
		this.add(this.scrollPane,BorderLayout.EAST);

		this.btnEditer.addActionListener(this);
		this.btnImporter.addActionListener(this);
		this.btnQuitter.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.btnEditer)
			this.ctrl.changerPanel("editer");

		if(e.getSource() == this.btnImporter)
		{
			JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

			int res = jFileChooser.showOpenDialog(null);
			if(res == JFileChooser.APPROVE_OPTION)
			{
				File file = jFileChooser.getSelectedFile();

				if(this.ctrl.getExtension(file.getName()).get().equals("png") );
				{
					Desktop desktop = Desktop.getDesktop();
					try {
						desktop.open(file);
					} catch (IOException e1) { e1.printStackTrace();}
				}
			}
		}

		/* Fermeture de l'application */
		if(e.getSource() == this.btnQuitter)
			System.exit(0);
		/*----------------------------*/
	}


}
