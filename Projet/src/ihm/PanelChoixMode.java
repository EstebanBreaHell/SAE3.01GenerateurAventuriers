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

	private JButton     btnImporter;
	private JButton     btnEditer;
	private JButton     btnQuitter;
	private JScrollBar  scrollBar;
	private JScrollPane scrollPane;

	public PanelChoixMode(Controleur ctrl)
	{
		Icon iconCree 	     = Controleur.imageToIcon("donnee\\chemin.png");
		JPanel panelDispoBtn = new JPanel(new GridLayout(3,1, 30, 30));
		JLabel lblTitre      = new JLabel("Images importées", JLabel.CENTER);
		Border border        = BorderFactory.createLineBorder(Color.BLACK, 2);

		Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
		int width  = (int)(double)(tailleMoniteur.getWidth ()/6  );
		int length = (int)(double)(tailleMoniteur.getHeight()/2.5);

		Border espacement = BorderFactory.createEmptyBorder(length, width, width, length);

		JPanel panelImages = new JPanel(new BorderLayout());

		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());
	
		panelImages.setBorder(border);

		this.btnImporter = new JButton("Importer");
		this.btnEditer   = new JButton("Editer",iconCree);
		this.btnQuitter  = new JButton("Quitter");

		this.btnImporter.setBorder(border);
		this.btnEditer  .setBorder(border);
		this.btnQuitter .setBorder(border);

		this.scrollBar  = new JScrollBar (JScrollBar.VERTICAL, 0, 10, 0, 100);
		this.scrollPane = new JScrollPane(this.scrollBar);

		panelDispoBtn.setBorder(espacement);

		panelDispoBtn.add(this.btnImporter);
		panelDispoBtn.add(this.btnEditer);
		panelDispoBtn.add(this.btnQuitter);

		JPanel panelTitre = new JPanel(new GridLayout(1,3));
		JPanel panelListe = new JPanel(new GridLayout(20,1));

		panelTitre.add(new JLabel());
		panelTitre.add(lblTitre);
		panelTitre.add(new JLabel());


		for(int i = 0; i < 20; i++)
		{
			panelListe.add(new JLabel("Image " + i, JLabel.CENTER));
		}
	
		panelImages.add(panelTitre, BorderLayout.NORTH);
		panelImages.add(panelListe, BorderLayout.CENTER);
		panelImages.add(this.scrollPane, BorderLayout.WEST);


		this.add(panelDispoBtn);
		this.add(panelImages,BorderLayout.EAST);

		this.btnEditer.addActionListener(this);
		this.btnImporter.addActionListener(this);
		this.btnQuitter.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.btnEditer)
		{
			this.ctrl.changerPanel("editer");
		}

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
		
		if(e.getSource() == this.btnQuitter)
		{
			System.exit(0);
		}

		
	}
	
}
