package ihm.sectioncreer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Desktop;

import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import main.Controleur;

public class PanelGraphique extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JButton btnImportImg;

	public PanelGraphique(Controleur ctrl)
	{
		this.ctrl = ctrl;

		Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur  = tailleMoniteur.height - (int) (tailleMoniteur.height*0.06);

		JPanel panelDispoBtn = new JPanel(new BorderLayout(0,hauteur/6));

		this.setLayout(new BorderLayout());

		this.add(panelDispoBtn);
	}

	public void paint(Graphics g)
	{
		super.paint(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.btnImportImg)
		{
			JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

			int res = jFileChooser.showOpenDialog(null);

			if(res == JFileChooser.APPROVE_OPTION)
			{
				File file = jFileChooser.getSelectedFile();

				if(this.ctrl.getExtension(file.getName()).equals("png") );
				{
					Desktop desktop = Desktop.getDesktop();
					try {
						desktop.open(file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
	}	
}