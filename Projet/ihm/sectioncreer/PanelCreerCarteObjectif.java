package ihm.sectioncreer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.awt.GridLayout;

import main.Controleur;
import metier.Noeud;

public class PanelCreerCarteObjectif extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JTextField txtNbPoint;
	private PanelApercuFace panelApercuFace;
	private JList<String> lstNoeud;
	private JScrollPane scrollPaneNoeud;
	private JPanel panelLstNoeud;
	private JButton btnHistoriqueCarte;
	private JButton btnCreerCarte;
	private JPanel panelDispoArriere;
	private JLabel lblImageArriere;

	private JButton btnModifierMotif;
	private String[] repertoireUserData;

	private  void initRepertoireImporte()
	{
		try 	{Files.createDirectories(Paths.get("data_user"));} 
		catch (IOException e) 	{e.printStackTrace();}

		this.repertoireUserData = new File(Paths.get("date_user").toFile().getAbsolutePath()).list();
	}


	public PanelCreerCarteObjectif(Controleur ctrl)
	{
		this.initRepertoireImporte();
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());

		this.lstNoeud = new JList<String>();
		

		this.txtNbPoint = new JTextField(2);
		this.btnHistoriqueCarte = new JButton("Historique des cartes");
		this.btnCreerCarte = new JButton("Créer carte");
		this.lblImageArriere = new JLabel("Erreur");

		this.btnModifierMotif = new JButton("Modifier le motif");

		this.panelApercuFace = new PanelApercuFace(this.ctrl);
		this.panelDispoArriere = new JPanel(new BorderLayout(10,10));
		
		JPanel panelDispoListeApercu = new JPanel(new GridLayout(2,1));

		JPanel panelDispoListe = new JPanel(new BorderLayout());
		JPanel panelDispoGestionPoint = new JPanel(new BorderLayout(0,100));

		JPanel panelDispoApercu = new JPanel(new GridLayout(1,2,10,10));
	

		JPanel panelDispoBtnCreerHistorique = new JPanel(new GridLayout(2,1,5,5));

		/**
		 * Positionnement des composants
		 */

		/*Début de panelDispoListe */
		//panelDispoListe.add(new JLabel("Liste des noeuds existant sur la mappe",JLabel.CENTER),BorderLayout.NORTH);
		//panelDispoListe.add(this.lstNoeud,BorderLayout.CENTER);
		this.panelLstNoeud = new JPanel();
		this.panelLstNoeud.add(this.lstNoeud);
		this.panelLstNoeud.setBackground(Color.WHITE);
		this.scrollPaneNoeud = new JScrollPane(this.panelLstNoeud);
		panelDispoListe.add(this.scrollPaneNoeud);


			panelDispoGestionPoint.add(new JLabel("Nb points de la carte :",JLabel.CENTER),BorderLayout.WEST);
			panelDispoGestionPoint.add(this.txtNbPoint, BorderLayout.CENTER);
			JLabel lblPoints = new JLabel("points", JLabel.CENTER);
			panelDispoGestionPoint.add(lblPoints, BorderLayout.EAST);


		panelDispoListe.add(panelDispoGestionPoint,BorderLayout.SOUTH);
		
		panelDispoApercu.add(this.panelApercuFace);

			this.panelDispoArriere.add(new JLabel("Verso",JLabel.CENTER),BorderLayout.NORTH);
			this.setImageArriere("donnee\\non_definie.png");
			this.panelDispoArriere.add(this.btnModifierMotif,BorderLayout.SOUTH);

		panelDispoApercu.add(this.panelDispoArriere);
		/*Fin de panelDispoApercu */

		panelDispoBtnCreerHistorique.add(this.btnHistoriqueCarte);
		panelDispoBtnCreerHistorique.add(this.btnCreerCarte);

		/*Début de la disposition des panel Liste et apercu dans le panel centre*/
		panelDispoListeApercu.add(panelDispoListe);
		panelDispoListeApercu.add(panelDispoApercu);
		/*Fin de la disposition des panel */

		
		this.add(panelDispoListeApercu,BorderLayout.CENTER);
		this.add(panelDispoBtnCreerHistorique,BorderLayout.SOUTH);

		this.btnCreerCarte.setBackground(Color.WHITE);
		this.btnHistoriqueCarte.setBackground(Color.WHITE);
		this.btnModifierMotif.setBackground(Color.WHITE);

		/**
		 * Activation des composants
		 */

		this.btnHistoriqueCarte.addActionListener(this);
		this.btnCreerCarte.addActionListener(this);
		this.btnModifierMotif.addActionListener(this);

	}

	public void setImageArriere(String pathImg)
	{
		this.lblImageArriere = new JLabel(Controleur.imageToIcon(pathImg, 200, 100));
		this.panelDispoArriere.add(this.lblImageArriere);
		
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnModifierMotif)
		{
			FileNameExtensionFilter filtre = new FileNameExtensionFilter("format image(*.png; *.jpg; *.gif)", "png","jpg","gif");
			JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

			jFileChooser.setCurrentDirectory(new File("data_user"));
			jFileChooser.setAcceptAllFileFilterUsed(false);
			jFileChooser.setFileFilter(filtre);

			int res = jFileChooser.showOpenDialog(null);
			if(res == JFileChooser.APPROVE_OPTION)
			{
				File file = jFileChooser.getSelectedFile();
				
				try 	{Files.copy(file.toPath(), Paths.get("data_user/"+file.getName()));} 
				catch (IOException e1) {e1.printStackTrace();}

				this.panelDispoArriere.remove(this.lblImageArriere);
				this.setImageArriere("data_user/"+file.getName());		

			}
		}

	}

	

	public void majIHM()
	{
		List<JLabel> arratTmp = this.ctrl.getLstHistorique();


		
		// //Fait un setListData
		this.lstNoeud.setListData(arratTmp.stream().map(label -> label.getText()).toArray(String[]::new));
		//this.lstNoeud.setListData(this.ctrl.getLstHistorique());
	}
}
