package ihm;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;


import main.Controleur;

public class PanelChoixMode extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JButton btnImport;
	private JButton btnCree;
	private JButton btnTester;

	public PanelChoixMode(Controleur ctrl)
	{
		Icon iconCree 	= Controleur.imageToIcon("Projet\\src\\donnee\\creer.png");
		Icon iconImport = Controleur.imageToIcon("Projet\\src\\donnee\\importer.png");

		this.ctrl = ctrl;
		Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur  = tailleMoniteur.height - (int) (tailleMoniteur.height*0.06);

		this.setLayout(new BorderLayout(hauteur/4,0));
		
	
		JPanel panelDispoBtn = new JPanel(new GridLayout(3,1,0,50));

		this.btnImport = new JButton("Import",iconImport);
		this.btnImport.setFont(Frame.POLICE_DEFAUT);

		this.btnCree = new JButton("Créer",iconCree);
		this.btnCree.setFont(Frame.POLICE_DEFAUT);

		this.btnTester = new JButton("Tester");
		this.btnTester.setFont(Frame.POLICE_DEFAUT);
	
		panelDispoBtn.add(this.btnImport);
		panelDispoBtn.add(this.btnCree);
		panelDispoBtn.add(this.btnTester);
		
		this.add(panelDispoBtn,BorderLayout.CENTER);
		this.add(new JLabel(" "),BorderLayout.EAST);
		this.add(new JLabel(" "),BorderLayout.WEST);
		this.add(new JLabel(" "),BorderLayout.NORTH);
		this.add(new JLabel(" "),BorderLayout.SOUTH);

		this.btnCree.addActionListener(this);



	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.btnCree)
		{
			this.ctrl.changerPanel("cree");
		}

		if(e.getSource() == this.btnImport)
		{

		}
		
		if(e.getSource() == this.btnTester)
		{

		}

		
	}
	
}
