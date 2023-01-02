/**
 * @author Lefort William, Decharrois Adrien, Brea-Hell Esteban
 * @version 1.0
 * @date 2019-03-20
 */

package ihm.sectioncreer;


import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import main.Controleur;

public class PanelParametre extends JPanel implements ActionListener
{
	private JTextField txtNbJoueursMinAreteDouble;
	private JTextField txtNbJoueursMax;
	private JTextField txtNbWagonDebutPartie;
	private JTextField txtNbWagonFinDePartie;
	private JTextField txtNbPlusLongChemain;

	private JLabel lblNbJoueursMinAreteDouble;
	private JLabel lblNbJoueursMax;
	private JLabel lblNbWagonDebutPartie;
	private JLabel lblNbWagonFinDePartie;
	private JLabel lblCouleurJoueur;
	
	private JButton btnValider;
	private JButton btnGenereXml;
	private JButton btnJdCouleur;
	private JButton btnValiderCouleurJoueur;
	private JButton btnAjouterCouleurJoueur;
	private JButton btnSupprimerCouleurJoueur;

	private JCheckBox chbPlusLongueRoute;


	private JDialog jdCouleur;
	private Container container;
	private JList<String> lstCouleurJoueur;
	private ArrayList<String> arrayCouleurJoueur;
	private JScrollPane scrollPaneCouleurJoueur;
	private JPanel panelLstCouleurJoueur;
	private JPanel panelCouleurBtn;




	private JPanel panelParametre;

	/*
	 * 
	 * le nombre maximum de joueurs pouvant jouer autour du plateau FAIT
	 * le nombre minimum de joueurs pour accéder aux voies doubles  FAIT 																																																																															
	 * le nombre de wagons attribués aux joueurs en début de partie FAIT																																									
	 * à partir de combien de wagons restant dans la main d’un joueur, la fin de partie est déclenchée. FAIT
	 * Faut encore reglé tout avec les couleur des joeuur FAIT
	 * 	Géré le cas de la route la plus grande FAIT																	
	 * 	TODO																																						
	 * 
	 * 
	 * Géré le nombre de points par taille de route :) 
	 * 
	 */

	private Controleur ctrl;



	private String[][] donnees = {
								{"1", "1",},
								{"2", "2",},
								{"3", "4",},
								{"4", "7",},
								{"5", "10",},
								{"6", "15",},};


	
	public PanelParametre(Controleur ctrl)
	{
		/**
		 * Création des éléments de la fenêtre
		 */
		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());
		this.panelParametre = new JPanel(new GridLayout(6,2, 15, 15));

		this.lstCouleurJoueur = new JList<String>();
		this.arrayCouleurJoueur = new ArrayList<String>();
		

		this.txtNbJoueursMinAreteDouble = new JTextField(""+this.ctrl.getNbJoueurMinDoubleArete());
		this.txtNbJoueursMax = new JTextField(""+this.ctrl.getNbJoueurMax());
		this.txtNbWagonDebutPartie = new JTextField(""+this.ctrl.getNbWagonDebutPartie());
		this.txtNbWagonFinDePartie = new JTextField(""+this.ctrl.getNbWagonFinPartie());
		this.btnJdCouleur= new JButton("Couleur Joueur");
		this.txtNbPlusLongChemain = new JTextField(""+this.ctrl.getNbPointsPlusLongChemin());


		this.lblNbJoueursMinAreteDouble = new JLabel("Nombre de joueurs minimum pour avoir une arete double");
		this.lblNbJoueursMax = new JLabel("Nombre de joueurs maximum");
		this.lblNbWagonDebutPartie = new JLabel("Nombre de wagon au debut de la partie");
		this.lblNbWagonFinDePartie = new JLabel("Nombre de wagon pour déclancé la fin de partie");
		this.lblCouleurJoueur = new JLabel("Couleur des joueurs");
		this.chbPlusLongueRoute = new JCheckBox("Règle du chemain le plus long");


		this.btnValider = new JButton("Valider");
		this.btnGenereXml = new JButton("Générer le fichier XML");


		this.btnValider.addActionListener(this);
		this.btnGenereXml.addActionListener(this);
		this.btnJdCouleur.addActionListener(this);
		this.chbPlusLongueRoute.addActionListener(this);


		this.panelParametre.add(this.lblNbJoueursMinAreteDouble);
		this.panelParametre.add(this.txtNbJoueursMinAreteDouble);

		this.panelParametre.add(this.lblNbJoueursMax);
		this.panelParametre.add(this.txtNbJoueursMax);

		this.panelParametre.add(this.lblNbWagonDebutPartie);
		this.panelParametre.add(this.txtNbWagonDebutPartie);

		this.panelParametre.add(this.lblNbWagonFinDePartie);
		this.panelParametre.add(this.txtNbWagonFinDePartie);

		this.panelParametre.add(this.chbPlusLongueRoute);
		this.panelParametre.add(this.txtNbPlusLongChemain);

		this.panelParametre.add(this.lblCouleurJoueur);
		this.panelParametre.add(this.btnJdCouleur);

		this.txtNbPlusLongChemain.setEnabled(false);

		this.add(this.panelParametre, BorderLayout.CENTER);
		this.add(this.btnValider, BorderLayout.SOUTH);
		this.add(this.btnGenereXml, BorderLayout.NORTH);
		
	}

	public void actionPerformed(ActionEvent e)
	{
		if(this.chbPlusLongueRoute.isSelected())
		{
			this.txtNbPlusLongChemain.setEnabled(true);
		}

		else 
		{
			this.txtNbPlusLongChemain.setEnabled(false);
		}
		if(e.getSource() == this.btnValider)
		{
			this.ctrl.setNbJoueurMax(Integer.parseInt(this.txtNbJoueursMax.getText()));
			this.ctrl.setNbJoueurMinDoubleArete(Integer.parseInt(this.txtNbJoueursMinAreteDouble.getText()));
			this.ctrl.setNbWagonDebutPartie(Integer.parseInt(this.txtNbWagonDebutPartie.getText()));
			this.ctrl.setNbWagonFinPartie(Integer.parseInt(this.txtNbWagonFinDePartie.getText()));
			this.ctrl.setNbPointsPlusLongChemin(Integer.parseInt(this.txtNbPlusLongChemain.getText()));
			this.ctrl.setLstCouleurJoueur(this.arrayCouleurJoueur);

		// 
		}
		else if(e.getSource() == this.btnGenereXml)
		{
			//this.ctrl.genererXml();
		}

		else if(e.getSource() == this.btnJdCouleur)
		{
			this.jdCouleur = new JDialog();
			this.panelLstCouleurJoueur = new JPanel();
			this.panelCouleurBtn = new JPanel(new GridLayout(1,2));
		

			this.scrollPaneCouleurJoueur = new JScrollPane(this.panelLstCouleurJoueur);
			this.btnValiderCouleurJoueur = new JButton("Valider");
			this.btnAjouterCouleurJoueur = new JButton("Ajouté");
			this.btnSupprimerCouleurJoueur = new JButton("Supprimer");

			this.panelCouleurBtn.add(this.btnSupprimerCouleurJoueur);
			this.container = new Container();
			this.container.setLayout(new FlowLayout());
			this.container.add(this.btnAjouterCouleurJoueur);

			this.panelCouleurBtn.add(this.container);
			



			this.btnValiderCouleurJoueur.addActionListener(this);
			this.btnAjouterCouleurJoueur.addActionListener(this);
			this.btnSupprimerCouleurJoueur.addActionListener(this);
			

			
			this.lstCouleurJoueur.setListData(this.arrayCouleurJoueur.toArray(new String[this.arrayCouleurJoueur.size()]));
			this.panelLstCouleurJoueur.add(this.lstCouleurJoueur);

			this.jdCouleur.setTitle("Couleur des joueurs");
			this.jdCouleur.setSize(500, 500);
			this.jdCouleur.setLocationRelativeTo(null);
			
			this.jdCouleur.setLayout(new BorderLayout());
			this.jdCouleur.add(this.panelCouleurBtn, BorderLayout.NORTH);
			this.jdCouleur.add(this.scrollPaneCouleurJoueur, BorderLayout.CENTER);

			this.jdCouleur.add(this.btnValiderCouleurJoueur, BorderLayout.SOUTH);
			this.jdCouleur.setVisible(true);
		}
		
		else if(e.getSource() == this.btnValiderCouleurJoueur)
		{
			this.jdCouleur.dispose();
		}
		
		


		else if(e.getSource() == this.btnAjouterCouleurJoueur)
		{
			Color initialcolor=Color.RED;
			Color color=JColorChooser.showDialog(this,"Choisissez une couleur d'arête",initialcolor);

			container.setBackground(color);
			String sColor = color.toString();

			sColor = sColor.substring(sColor.indexOf("[") + 1, sColor.indexOf("]"));

			if(!this.arrayCouleurJoueur.contains(sColor))
				this.arrayCouleurJoueur.add(sColor);
			this.lstCouleurJoueur.setListData(this.arrayCouleurJoueur.toArray(new String[this.arrayCouleurJoueur.size()]));
			this.jdCouleur.setVisible(false);
			this.jdCouleur.setVisible(true);
		
		}

		else if(e.getSource() == this.btnSupprimerCouleurJoueur)
		{
			if (this.lstCouleurJoueur.getSelectedIndex() != -1)
			{
				this.arrayCouleurJoueur.remove(this.lstCouleurJoueur.getSelectedIndex());
				this.lstCouleurJoueur.setListData(this.arrayCouleurJoueur.toArray(new String[this.arrayCouleurJoueur.size()]));
		
			}
		}
	}




	
}
