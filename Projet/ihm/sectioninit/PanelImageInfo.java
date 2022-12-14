package ihm.sectioninit;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.*;


import main.Controleur;

public class PanelImageInfo extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JButton btnImage;
	private Icon icon;

	//Temporaire pour tester le btn
	private String nomFichier;

	public PanelImageInfo(Controleur ctrl,String nomFichier)
	{
		this.ctrl = ctrl;

		this.nomFichier = nomFichier;
		this.setLayout(new BorderLayout());

		this.icon= Controleur.imageToIcon("importe\\" + nomFichier,200,100);

		this.btnImage = new JButton();
		this.btnImage.setIcon(icon);
		this.btnImage.setRolloverIcon(this.icon);
		this.btnImage.setRolloverEnabled(true);
		this.btnImage.setFocusPainted(false);
		this.btnImage.setBorderPainted(false);

		this.add(new JLabel(nomFichier,JLabel.CENTER),BorderLayout.NORTH);
		this.add(this.btnImage,BorderLayout.CENTER);

		this.btnImage.addActionListener(this);
	}

	public void inverseEtatBtn(){this.btnImage.setEnabled(!this.btnImage.isEnabled());}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.btnImage)
		{
			System.out.println(this.nomFichier);
			this.ctrl.panelSelectionner(this);
		}
		
	}
	
}