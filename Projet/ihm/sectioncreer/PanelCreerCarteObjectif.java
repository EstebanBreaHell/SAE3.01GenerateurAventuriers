/**
 * @author Lefort William, Decharrois Adrien, Brea-Hell Esteban
 * @version 1.0
 * @date 2019-03-20
 */

package ihm.sectioncreer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
	private JList<String> lstNoeud1;
	private JList<String> lstNoeud2;
	private JScrollPane scrollPaneNoeud;
	private JPanel panelLstNoeud;
	private JButton btnHistoriqueCarte;
	private JButton btnCreerCarte;
	private JButton btnInformation;
	private JPanel panelDispoArriere;
	private JLabel lblImageArriere;

	private Noeud noeud1, noeud2;
	private int nbPoint;

	private JButton btnModifierMotif;

	private  void initRepertoireCarteObjectif()
	{
		try 	{Files.createDirectories(Paths.get("donnee/carteObjectif"));} 
		catch (IOException e) 	{e.printStackTrace();}
	}

	private String[] getPathCartObjectifCreer()
	{
		String[] tmp = new File(Paths.get("donnee/carteObjectif/"+this.ctrl.getPathImg().substring(8)).toFile().getAbsolutePath()).list();
		
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

		this.noeud1 = null;
		this.noeud2 = null;
		this.nbPoint = 0;

		this.lstNoeud1 = new JList<String>();
		this.lstNoeud2 = new JList<String>();

		this.lstNoeud1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.lstNoeud2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	
		this.lblImageArriere = new JLabel(Controleur.imageToIcon("data_user/non_definie.png", 200, 100));

		this.txtNbPoint = new JTextField(2);
		this.txtNbPoint.setText(0 + "");
		this.txtNbPoint.setHorizontalAlignment(JTextField.CENTER);
			
		this.btnHistoriqueCarte = new JButton("Historique des cartes créées");
		this.btnCreerCarte = new JButton("Créer carte");
		this.lblImageArriere = new JLabel("Erreur");

		this.btnModifierMotif = new JButton("Importer une carte verso");

		this.panelDispoArriere = new JPanel(new BorderLayout(10,10));
		this.panelApercuFace = new PanelApercuFace(this.ctrl);
		
		JPanel panelDispoListeApercu = new JPanel(new GridLayout(2,1));
		JPanel panelDispoListe = new JPanel(new BorderLayout());
		JPanel panelDispoApercu = new JPanel(new GridLayout(1,2,10,10));
		JPanel panelDispoBtnCreerHistorique = new JPanel(new GridLayout(2,1,5,5));

		/**
		 * Positionnement des composants
		 */

		/*Début de panelDispoListe */
		this.panelLstNoeud = new JPanel(new GridLayout(1,2));
		this.panelLstNoeud.add(this.lstNoeud1);
		this.panelLstNoeud.add(this.lstNoeud2);
		this.panelLstNoeud.setBackground(Color.WHITE);
		this.scrollPaneNoeud = new JScrollPane(this.panelLstNoeud);
		panelDispoListe.add(this.scrollPaneNoeud);
		
		JPanel panelTest = new JPanel(new GridLayout(2,3,5,5));
		
		this.btnInformation = new JButton("Notice d'utilisation");
		this.btnInformation.setBackground(Color.WHITE);
		this.btnInformation.addActionListener(this);


		panelTest.add(new JLabel());
		panelTest.add(this.btnInformation);
		panelTest.add(new JLabel());

		panelTest.add(new JLabel("Nb points de la carte :",JLabel.CENTER));
		panelTest.add(this.txtNbPoint);
		panelTest.add(new JLabel("points", JLabel.LEFT));

		panelDispoListe.add(panelTest,BorderLayout.SOUTH);
		
		panelDispoApercu.add(this.panelApercuFace);

			this.panelDispoArriere.add(new JLabel("Verso",JLabel.CENTER),BorderLayout.NORTH);
			this.setImageArriere("data_user/non_definie.png");
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
		this.lstNoeud1			.addMouseListener(new InputSourie());
		this.lstNoeud2			.addMouseListener(new InputSourie());


		this.txtNbPoint.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) 
				{
					e.consume();
				}
			}
		});
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
		if(e.getSource() == this.txtNbPoint){
			this.panelApercuFace.setNbPoint(Integer.parseInt(this.txtNbPoint.getText()));
			this.nbPoint = Integer.parseInt(this.txtNbPoint.getText());
		}

		if(e.getSource() == this.btnCreerCarte)
		{
						
			try 	{Files.createDirectories(Paths.get("donnee/carteObjectif/"+this.ctrl.getPathImg().substring(8)));} 
			catch (IOException a) 	{a.printStackTrace();}

			File fileImg = new File("donnee/carteObjectif/"+this.ctrl.getPathImg().substring(8) + "/carteObjectifN°"+ PanelCreerCarteObjectif.nbCarteObjectifCreer++ + ".png");
			
			try		{ImageIO.write(this.ctrl.createImage(this.panelApercuFace.getPanelGraphiqueFace()), "PNG", fileImg);} 
			catch 	(IOException e1) {e1.printStackTrace();}
			if(this.noeud1 == null || this.noeud2 == null)
			{
				JOptionPane.showMessageDialog(null, "Veuillez selectionner deux noeuds differents", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.ctrl.creerCarteObjectif(this.noeud1, this.noeud2, this.nbPoint);
			

		
		}

		if(e.getSource() == this.btnHistoriqueCarte)
		{
			JDialog popUpHistoirque = new JDialog();
			String[] repertoireCarteObjectif = this.getPathCartObjectifCreer();
			
			popUpHistoirque.setSize(600,400);
			popUpHistoirque.setResizable(false);

			popUpHistoirque.setLayout(new BorderLayout());
			popUpHistoirque.setTitle("Historique des cartes objectif de " + this.ctrl.getPathImg().substring(8));


			if(repertoireCarteObjectif == null)	popUpHistoirque.add(new JLabel("Le dossier carte objectif de" + this.ctrl.getPathImg().substring(8) + " est vide"),JLabel.CENTER);
			else
			{
				JPanel panelDispoZoom = new JPanel(new GridLayout(this.ctrl.getLstCarteObjectif().size(),1));

				for(int index = 0; index < this.ctrl.getLstCarteObjectif().size(); index++)
				{
					panelDispoZoom.add(new BtnZoomCarte("donnee/carteObjectif/" + this.ctrl.getPathImg().substring(8) + "/" + repertoireCarteObjectif[index],index));	
				}
				
				popUpHistoirque.add(new JScrollPane(panelDispoZoom),BorderLayout.CENTER);
			}
			
			popUpHistoirque.setVisible(true);
		}

		if(e.getSource() == this.btnInformation)
		{
			JDialog popUpInformation = new JDialog();
			popUpInformation.setSize(600,400);
			popUpInformation.setResizable(false);
			popUpInformation.setBounds(110,200,600,400);
			popUpInformation.setTitle("Information sur la création de carte objectif");

			JPanel panelInformation = new JPanel(new GridLayout(4,1,10,10));
			panelInformation.add(new JLabel("- Appuyez entrer après la saisie du nombre de point pour valider"));
			panelInformation.add(new JLabel("- Pour créer une carte objectif, selectionnez un noeud en double cliquant sur la liste 1, puis sur la miniature, faites un clique gauche"));
			panelInformation.add(new JLabel("- Pour relier le chemin, selectionnez un noeud en double cliquant sur la liste 2, puis sur la miniature, faites un clique gauche"));
			panelInformation.add(new JLabel("- Pour importer une carte verso, cliquez sur le bouton importer"));


			popUpInformation.add(panelInformation,BorderLayout.CENTER);
			popUpInformation.setVisible(true);
		}
	}

	
	public void majIHM()
	{
		List<JLabel> arratTmp = this.ctrl.getLstHistorique();

		this.lstNoeud1.setListData(arratTmp.stream().map(label -> label.getText()).toArray(String[]::new));
		this.lstNoeud2.setListData(arratTmp.stream().map(label -> label.getText()).toArray(String[]::new));
	}

	public class BtnZoomCarte extends JPanel implements ActionListener
	{
		private JButton btnZoom;
		private JButton btnSuppr;
		private String pathImg;
		private int indexCarteObjectif;

		public BtnZoomCarte(String pathImg, int indexcarteObjectif)
		{			
			this.pathImg = pathImg;
			this.indexCarteObjectif = indexcarteObjectif;
			this.btnZoom = new JButton(new ImageIcon(this.pathImg));
			this.btnZoom.setBackground(Color.WHITE);

			this.btnSuppr = new JButton("Supprimer");

			this.setLayout(new BorderLayout());
			this.add(new JLabel(this.pathImg.substring(20)),BorderLayout.NORTH);
			this.add(this.btnZoom,BorderLayout.CENTER);
			this.add(this.btnSuppr,BorderLayout.SOUTH);

			this.setBackground(Color.WHITE);

			this.btnZoom.addActionListener(this);
			this.btnSuppr.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == this.btnZoom)
			{
				Icon icon = new ImageIcon(this.pathImg);
				JDialog popZoom = new JDialog();

				popZoom.setSize(icon.getIconWidth(),icon.getIconHeight());
				popZoom.setTitle("Zoom sur " + this.pathImg.substring(20));
				popZoom.setResizable(false);

				popZoom.add(new JLabel(icon));

				popZoom.setVisible(true);
			}

			if(e.getSource() == this.btnSuppr)
			{
				PanelCreerCarteObjectif.this.ctrl.supprCarteObjectif(this.indexCarteObjectif);
				System.out.println("supprimé");
				this.majIHM();
			}
		}

		private void majIHM()
		{
			this.setVisible(false);
		}
	}

	public class InputSourie extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			if(e.getClickCount() == 2)
			{
				Noeud tmpNoeud;

				if(e.getSource() == PanelCreerCarteObjectif.this.lstNoeud1)
				{
					
					tmpNoeud = PanelCreerCarteObjectif.this.ctrl.getLstNoeud().get(PanelCreerCarteObjectif.this.lstNoeud1.getSelectedIndex());

					if(!tmpNoeud.equals(PanelCreerCarteObjectif.this.noeud2))
					{
						PanelCreerCarteObjectif.this.noeud1 = tmpNoeud;
						PanelCreerCarteObjectif.this.panelApercuFace.setNoeud1(1000, 1000, tmpNoeud.getNom());
					}
					else{ JOptionPane.showMessageDialog(null, "Erreur : Vous ne pouvez pas choisir le même noeud","Attention",1);}
				}

				if(e.getSource() == PanelCreerCarteObjectif.this.lstNoeud2)
				{
					tmpNoeud = PanelCreerCarteObjectif.this.ctrl.getLstNoeud().get(PanelCreerCarteObjectif.this.lstNoeud2.getSelectedIndex());

					if(!tmpNoeud.equals(PanelCreerCarteObjectif.this.noeud1))
					{
						PanelCreerCarteObjectif.this.noeud2 = tmpNoeud;
						PanelCreerCarteObjectif.this.panelApercuFace.setNoeud2(1000, 1000, tmpNoeud.getNom());
					}
					else{ JOptionPane.showMessageDialog(null, "Erreur : Vous ne pouvez pas choisir le même noeud","Attention",1);}
				}

			}
		}
	}
}
