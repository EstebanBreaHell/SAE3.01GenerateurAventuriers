package ihm.sectioncreer;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import main.Controleur;
import metier.Arete;	

public class PanelCreerCarteWagon extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JList<String> lstCouleur;
	private ArrayList<String> lstCouleurTmp;
	private ArrayList<Arete> lstArete;
	private JPanel panelLstCouleur;

	
	private JScrollPane scrollPaneCouleur;



	private JButton btnModifierMotif;

	public PanelCreerCarteWagon(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());

		this.lstCouleur = new JList<String>();
		this.lstCouleurTmp = new ArrayList<String>();
		this.panelLstCouleur = new JPanel();
		this.panelLstCouleur.setBackground(Color.WHITE);
		
		this.lstArete = this.ctrl.getLstArete();
		this.panelLstCouleur.add(this.lstCouleur);

		this.scrollPaneCouleur = new JScrollPane(this.panelLstCouleur);

	


		for(Arete a : this.lstArete)
			if(!this.lstCouleurTmp.contains(a.getCouleur()))
				this.lstCouleurTmp.add(a.getCouleur());

		//this.lstCouleur.setListData(this.lstCouleurTmp.toArray(new String[this.lstCouleurTmp.size()]));
		
		this.btnModifierMotif = new JButton("Modifier le Verso");

		this.lstCouleur.setBackground(Color.WHITE);
		this.lstCouleur.setForeground(Color.BLACK);
		/**
		 * Ajout des composants
		 */
		this.add(this.btnModifierMotif, BorderLayout.NORTH);
		this.add(this.scrollPaneCouleur, BorderLayout.CENTER);
		
		


		


		

	}

	public void majIHM()
	{
		this.lstArete = this.ctrl.getLstArete();
		this.lstCouleurTmp.clear();
		for(Arete a : this.lstArete)
			if(!this.lstCouleurTmp.contains(a.getCouleur()))
				this.lstCouleurTmp.add(a.getCouleur());

		this.lstCouleur.setListData(this.lstCouleurTmp.toArray(new String[this.lstCouleurTmp.size()]));
	}
	public void actionPerformed(ActionEvent e)
	{
	}
}
