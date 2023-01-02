/**
 * @author Lefort William, Decharrois Adrien, Brea-Hell Esteban
 * @version 1.0
 * @date 2019-03-20
 */

package ihm.sectioncreer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
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
import java.awt.event.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.awt.GridLayout;

import main.Controleur;
import metier.Noeud;

public class PanelCreerCarteObjectif extends JPanel implements ActionListener
{
	private static int nbCarteObjectifCreer = 0;
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

	private  void initRepertoireCarteObjectif()
	{
		try 	{Files.createDirectories(Paths.get("donnee\\carteObjectif"));} 
		catch (IOException e) 	{e.printStackTrace();}
	}

	private String[] getPathCartObjectifCreer()
	{
		String[] tmp = new File(Paths.get("donnee\\carteObjectif\\"+this.ctrl.getPathImg().substring(8)).toFile().getAbsolutePath()).list();
		
		return tmp;
	}

	public PanelCreerCarteObjectif(Controleur ctrl)
	{
		this.initRepertoireCarteObjectif();
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());

		this.lstNoeud = new JList<String>();
		
	
		this.lblImageArriere = new JLabel(Controleur.imageToIcon("data_user\\non_definie.png", 200, 100));

		this.txtNbPoint = new JTextField(2);
		this.txtNbPoint.setText(0 +"");
			
		this.btnHistoriqueCarte = new JButton("Historique des cartes");
		this.btnCreerCarte = new JButton("Créer carte");
		this.lblImageArriere = new JLabel("Erreur");

		this.btnModifierMotif = new JButton("Modifier le motif");

		this.panelDispoArriere = new JPanel(new BorderLayout(10,10));
		this.panelApercuFace = new PanelApercuFace(this.ctrl);
		
		JPanel panelDispoListeApercu = new JPanel(new GridLayout(2,1));

		JPanel panelDispoListe = new JPanel(new BorderLayout());
		JPanel panelDispoGestionPoint = new JPanel(new BorderLayout(0,100));

		JPanel panelDispoApercu = new JPanel(new GridLayout(1,2,10,10));
	
		JPanel panelDispoBtnCreerHistorique = new JPanel(new GridLayout(2,1,5,5));

		/**
		 * Positionnement des composants
		 */

		/*Début de panelDispoListe */
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
			this.setImageArriere("data_user\\non_definie.png");
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

		this.btnHistoriqueCarte	.addActionListener(this);
		this.btnCreerCarte		.addActionListener(this);
		this.btnModifierMotif	.addActionListener(this);
		this.txtNbPoint			.addActionListener(this);
		this.lstNoeud			.addMouseListener(new InputSourie());
	}

	public void setImageArriere(String pathImg)
	{
		this.remove(this.lblImageArriere);
		this.lblImageArriere = new JLabel(Controleur.imageToIcon(pathImg, 200, 100));
		this.panelDispoArriere.add(this.lblImageArriere,BorderLayout.CENTER);
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
				
				this.majIHM();
			}
		}

		/**
		 * Definie le nombre de point sur la carte
		 */
		if(e.getSource() == this.txtNbPoint){this.panelApercuFace.setNbPoint(Integer.parseInt(this.txtNbPoint.getText()));}

		if(e.getSource() == this.btnCreerCarte)
		{
						
			try 	{Files.createDirectories(Paths.get("donnee\\carteObjectif\\"+this.ctrl.getPathImg().substring(8)));} 
			catch (IOException a) 	{a.printStackTrace();}

			File fileImg = new File("donnee\\carteObjectif\\"+this.ctrl.getPathImg().substring(8) + "\\carteObjectifN°"+ PanelCreerCarteObjectif.nbCarteObjectifCreer++ + ".png");
			
			try		{ImageIO.write(this.ctrl.createImage(this.panelApercuFace.getPanelGraphiqueFace()), "PNG", fileImg);} 
			catch 	(IOException e1) {e1.printStackTrace();}
		}

		if(e.getSource() == this.btnHistoriqueCarte)
		{
			JDialog popUpHistoirque = new JDialog();
			String[] repertoireCarteObjectif = this.getPathCartObjectifCreer();

			popUpHistoirque.setTitle("Histoirique des carte objectif de " + this.ctrl.getPathImg().substring(8));
			if(repertoireCarteObjectif == null)	popUpHistoirque.add(new JLabel("Le dossier carte objectif de" + this.ctrl.getPathImg().substring(8) + " est vide"),JLabel.CENTER);
			else
			{
				popUpHistoirque.setLayout(new GridLayout(1,repertoireCarteObjectif.length));
				for(int index = 0; index < repertoireCarteObjectif.length; index++)
				{
					popUpHistoirque.add(new JLabel(Controleur.imageToIcon("donnee\\carteObjectif\\"+this.ctrl.getPathImg().substring(8) + "\\" + repertoireCarteObjectif[index], 200, 100)));
				}
			}
			popUpHistoirque.pack();
			popUpHistoirque.setVisible(true);


		}
	}

	
	public void majIHM()
	{
		List<JLabel> arratTmp = this.ctrl.getLstHistorique();

		this.lstNoeud.setListData(arratTmp.stream().map(label -> label.getText()).toArray(String[]::new));
	}



	public class InputSourie extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			if(e.getClickCount() == 2)
			{
				Noeud tmpNoeud;
				tmpNoeud = PanelCreerCarteObjectif.this.ctrl.getLstNoeud().get(PanelCreerCarteObjectif.this.lstNoeud.getSelectedIndex());
				
				PanelCreerCarteObjectif.this.panelApercuFace.setNoeud1(tmpNoeud.getNomX()+15, tmpNoeud.getNomY(), tmpNoeud.getNom());
			}
		}
	}
}
