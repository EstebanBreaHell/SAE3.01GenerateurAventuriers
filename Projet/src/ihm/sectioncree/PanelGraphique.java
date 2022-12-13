package ihm.sectioncree;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.FileHandler;

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

		this.btnImportImg = new JButton("[IMPORTE UN IMAGE]");

		panelDispoBtn.add(new JLabel(" "),BorderLayout.NORTH);
		panelDispoBtn.add(this.btnImportImg,BorderLayout.CENTER);
		panelDispoBtn.add(new JLabel(" "),BorderLayout.SOUTH);

		this.add(panelDispoBtn);

		this.btnImportImg.addActionListener(this);
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

				if(this.ctrl.getExtension(file.getName()).get().equals("png") );
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
