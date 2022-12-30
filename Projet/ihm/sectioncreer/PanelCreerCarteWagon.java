package ihm.sectioncreer;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import main.Controleur;
import metier.Arete;	

public class PanelCreerCarteWagon extends JPanel implements ActionListener , MouseListener
{
	private Controleur ctrl;
	private JList<String> lstCouleur;
	private ArrayList<String> lstCouleurTmp;
	private ArrayList<Arete> lstArete;
	private HashMap<String, Integer> hashCoulNbCarte;

	private JDialog jd;
	


	private JPanel panelLstCouleur;
	private JPanel panelJd;

	
	private JScrollPane scrollPaneCouleur;


	private JTextField txtNbCarte;
	private JButton btnModifierMotif;
	private JButton modifieRecto;
	private JButton btnConfirme;

	public PanelCreerCarteWagon(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());

		this.lstCouleur = new JList<String>();
		this.lstCouleurTmp = new ArrayList<String>();
		this.hashCoulNbCarte = new HashMap<String, Integer>();


		this.panelLstCouleur = new JPanel();
		this.panelLstCouleur.setBackground(Color.WHITE);
		
		this.lstArete = this.ctrl.getLstArete();
		this.panelLstCouleur.add(this.lstCouleur);

		this.scrollPaneCouleur = new JScrollPane(this.panelLstCouleur);

	


		for(Arete a : this.lstArete)
		{
			if(!this.lstCouleurTmp.contains(a.getCouleur()))
			{
				this.lstCouleurTmp.add(a.getCouleur());
				this.hashCoulNbCarte.put(a.getCouleur(), 0);
			}
		}

		//this.lstCouleur.setListData(this.lstCouleurTmp.toArray(new String[this.lstCouleurTmp.size()]));
		
		this.btnModifierMotif = new JButton("Modifier le Verso");

		this.lstCouleur.setBackground(Color.WHITE);
		this.lstCouleur.setForeground(Color.BLACK);
		this.lstCouleur.addMouseListener(this);
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
		{
			if(!this.lstCouleurTmp.contains(a.getCouleur()))
			{
				this.lstCouleurTmp.add(a.getCouleur());
				if(!this.hashCoulNbCarte.containsKey(a.getCouleur()))
					this.hashCoulNbCarte.put(a.getCouleur(), 0);
			}
		}
		this.lstCouleur.setListData(this.lstCouleurTmp.toArray(new String[this.lstCouleurTmp.size()]));
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnConfirme)
		{
			String couleur = this.lstCouleur.getSelectedValue();
			if(couleur != null)
			{
				this.hashCoulNbCarte.put(couleur, Integer.parseInt(this.txtNbCarte.getText()));
				this.jd.dispose();
			}
		}
	}
	public void mouseClicked(MouseEvent e) {

		if(e.getClickCount() == 2)
		{
			String couleur = this.lstCouleur.getSelectedValue();
			if(couleur != null)
			{
				this.jd = new JDialog();
				this.jd.setTitle("Modifier la carte de couleur");
				jd.setBounds(900, 300, 500, 400);
				this.panelJd = new JPanel();
				this.panelJd.setBackground(Color.WHITE);
				this.panelJd.setLayout(new GridLayout(2,2,12,12));
				this.txtNbCarte = new JTextField(""+ this.hashCoulNbCarte.get(couleur));


				
				


				this.modifieRecto = new JButton("Importe le recto");
				this.btnConfirme  = new JButton("Confirme");
				this.modifieRecto.addActionListener(this);
				this.btnConfirme.addActionListener(this);
				this.panelJd.add(new JLabel(couleur));
				this.panelJd.add(this.modifieRecto);
				this.panelJd.add(new JLabel("Nombre de carte"));
				this.panelJd.add(this.txtNbCarte);
				//this.panelJd.add(this.btnConfirme);

				this.jd.add(this.panelJd);
				this.jd.add(this.btnConfirme, BorderLayout.SOUTH);
				/*Print la hashmap*/
				System.out.println(couleur);
				System.out.println("-------------------");
				for(String key : this.hashCoulNbCarte.keySet())
				{
					System.out.println(key + " : " + this.hashCoulNbCarte.get(key));
				}
				
			

				this.jd.setVisible(true);

				//int nbCarte = this.hashCoulNbCarte.get(couleur);
				
				//this.hashCoulNbCarte.put(couleur, nbCarte);

				//this.ctrl.creerCarteWagon(couleur, nbCarte);
			}


		}
	}


	public void mousePressed(MouseEvent e) {}
	
	public void mouseReleased(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}

	

}
