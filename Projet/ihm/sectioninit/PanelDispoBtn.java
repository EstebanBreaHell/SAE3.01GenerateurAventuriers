package ihm.sectioninit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
import java.awt.Desktop;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import main.Controleur;

public class PanelDispoBtn extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JButton btnImporter;
	private JButton btnEditer;
	private JButton btnQuitter;


	public PanelDispoBtn(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		JPanel panelPtn = new JPanel(new GridLayout(3,1, 30, 30));
		
		Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
		int width  = (int)(double)(tailleMoniteur.getWidth ()/6  );
		int length = (int)(double)(tailleMoniteur.getHeight()/2.5);

		Border distanceBtn = BorderFactory.createLineBorder(Color.BLACK, 2);

		this.setBorder(BorderFactory.createEmptyBorder(length, width, width, length));

		this.btnImporter = new JButton("Importer");
		this.btnEditer   = new JButton("Editer");
		this.btnQuitter  = new JButton("Quitter");

		this.btnImporter.setBorder(distanceBtn);
		this.btnEditer.setBorder(distanceBtn);
		this.btnQuitter.setBorder(distanceBtn);

		this.btnImporter.setBackground(Color.WHITE);
		this.btnEditer.setBackground(Color.WHITE);
		this.btnQuitter.setBackground(Color.WHITE);

		panelPtn.add(this.btnImporter);
		panelPtn.add(this.btnEditer);
		panelPtn.add(this.btnQuitter);
		
		this.add(panelPtn);

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
			FileNameExtensionFilter filtre = new FileNameExtensionFilter("format image(*.png; *.jpg; *.gif)", "png","jpg","gif");
			JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

			jFileChooser.setAcceptAllFileFilterUsed(false);
			jFileChooser.setFileFilter(filtre);

			int res = jFileChooser.showOpenDialog(null);
			if(res == JFileChooser.APPROVE_OPTION)
			{
				File file = jFileChooser.getSelectedFile();
				
				try 	{Files.copy(file.toPath(), Paths.get("importe\\"+file.getName()));} 
				catch (IOException e1) {e1.printStackTrace();}
			}
		}

		/* Fermeture de l'application */
		if(e.getSource() == this.btnQuitter)
			System.exit(0);
		/*----------------------------*/
	}

}
