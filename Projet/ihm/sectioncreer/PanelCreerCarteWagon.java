/**
 * @author Lefort William, Decharrois Adrien, Brea-Hell Esteban
 * @version 1.0
 * @date 2019-03-20
 */

package ihm.sectioncreer;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import ihm.sectioninit.PanelImageInfo;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

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
	private HashMap<String, String> hashCoulImage;
	private String[] repertoireImage;

	private JDialog jd;

	private JDialog jdImporte;
	
	private JPanel panelLstCouleur;
	private JPanel panelBouton;
	private JPanel panelJd;
	private JPanel panelJdImporte;

	private JScrollPane scrollPaneCouleur;

	private JTextField txtNbCarte;
	private JLabel lblNomRecto;
	private JLabel lblNomVerso;
	private String nomVerso;
	private JButton btnModifierMotif;
	private JButton modifieRecto;
	private JButton btnConfirme;

	private JScrollPane scrollPaneImporte;
	private JButton btnImporte;
	private JButton btnValidImporteRecto;
	private JButton btnValidImporteVerso;

	public PanelCreerCarteWagon(Controleur ctrl)
	{
		
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());

		this.lstCouleur = new JList<String>();
		this.lstCouleurTmp = new ArrayList<String>();
		this.hashCoulNbCarte = this.ctrl.getLstCouleurWagon();
		//this.hashCoulNbCarte.put("Joker",0);
		this.hashCoulImage = this.ctrl.getHsmImageWagon();
		

		for(String s : this.hashCoulNbCarte.keySet())
			this.lstCouleurTmp.add(s);
			
		this.nomVerso = "Aucun verso pour le moment.";
		this.lblNomVerso = new JLabel(this.nomVerso);


		this.panelLstCouleur = new JPanel();
		this.panelLstCouleur.setBackground(Color.WHITE);
		
		this.lstArete = this.ctrl.getLstArete();
		this.panelLstCouleur.add(this.lstCouleur);

		this.scrollPaneCouleur = new JScrollPane(this.panelLstCouleur);

		if(!this.lstCouleurTmp.contains("Joker"))
			this.lstCouleurTmp.add("Joker");
		for(Arete a : this.lstArete)
		{
			if(!this.lstCouleurTmp.contains(a.getCouleur()) && !a.getCouleur().equals("Neutre") )
			{
				this.lstCouleurTmp.add(a.getCouleur());
				this.hashCoulNbCarte.put(a.getCouleur(), 0);
				this.hashCoulImage.put(a.getCouleur(), null);
			}
		}

		this.lstCouleur.setListData(this.lstCouleurTmp.toArray(new String[this.lstCouleurTmp.size()]));

		this.btnModifierMotif = new JButton("Modifier le verso");
		this.btnModifierMotif.setBackground(Color.WHITE);

		this.btnModifierMotif.addActionListener(this);
		this.lstCouleur.setBackground(Color.WHITE);
		this.lstCouleur.setForeground(Color.BLACK);
		this.lstCouleur.addMouseListener(this);

		/**
		 * Ajout des composants
		 */
		this.panelBouton = new JPanel(new GridLayout(2,3,2,10));

		this.panelBouton.add(new JLabel());
		this.panelBouton.add(this.lblNomVerso);
		this.panelBouton.add(new JLabel());
		this.panelBouton.add(new JLabel());
		this.panelBouton.add(this.btnModifierMotif);
		this.panelBouton.add(new JLabel());

		this.add(this.panelBouton      , BorderLayout.NORTH);
		this.add(this.scrollPaneCouleur, BorderLayout.CENTER);
	}

	public void majIHM()
	{
		this.lstArete = this.ctrl.getLstArete();
		this.lstCouleurTmp.clear();
		this.lstCouleurTmp.add("Joker");
		for(Arete a : this.lstArete)
		{
			if(!this.lstCouleurTmp.contains(a.getCouleur()) && !a.getCouleur().equals("Neutre"))
			{
				this.lstCouleurTmp.add(a.getCouleur());
				if(!this.hashCoulNbCarte.containsKey(a.getCouleur()))
				{
					this.hashCoulNbCarte.put(a.getCouleur(), 0);
					this.hashCoulImage.put(a.getCouleur(), null);
				}
			}
		}
		this.ctrl.setHsmCouleurWagon(this.hashCoulNbCarte);
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
				this.ctrl.setHsmCouleurWagon(this.hashCoulNbCarte);
				this.jd.dispose();
			}
			else
			{
				System.exit(0);
			}
		}

		if(e.getSource() == this.modifieRecto)
		{
			this.btnImporte = new JButton("Importer image");
			this.btnValidImporteRecto = new JButton("Choisir image");
			this.btnImporte.setBackground(Color.WHITE);
			this.btnValidImporteRecto.setBackground(Color.WHITE);

			this.btnImporte.addActionListener(this);
			this.btnValidImporteRecto.addActionListener(this);
			
			this.jdImporte = new JDialog();
			this.jdImporte.setTitle("Modifier la carte de couleur");	
			this.jdImporte.setBounds(900, 300, 500, 400);

			this.panelJdImporte = new JPanel();

			try 
			{
				Files.createDirectories(Paths.get("importe/imageCarte"));
			} 
			catch (IOException ee) 	{ee.printStackTrace();}

			this.repertoireImage = new File(Paths.get("importe/imageCarte").toFile().getAbsolutePath()).list();

			PanelImageInfo[] tabPanelAffichageImporte = new PanelImageInfo[this.repertoireImage.length];
			JPanel panelDispoAffichage =new JPanel(new GridLayout(tabPanelAffichageImporte.length,1));

			for (int index = 0; index < tabPanelAffichageImporte.length; index++)
			{
				tabPanelAffichageImporte[index] = new PanelImageInfo(this.ctrl,"imageCarte/"+this.repertoireImage[index]);
				panelDispoAffichage.add(tabPanelAffichageImporte[index]);
			}

			this.scrollPaneImporte = new JScrollPane(panelDispoAffichage, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

			this.jdImporte.add(new JLabel("Liste des images de Carte importées",JLabel.CENTER),BorderLayout.NORTH);
			this.jdImporte.add(this.scrollPaneImporte);
			this.jdImporte.add(this.btnValidImporteRecto,BorderLayout.SOUTH);
			this.jdImporte.add(this.btnImporte,BorderLayout.NORTH);
			this.jdImporte.setVisible(true);
		}

		if(e.getSource() == this.btnModifierMotif)
		{
			JPanel panelBtnHaut = new JPanel(new GridLayout(1,3,10,10));
			JPanel panelBtnBas = new JPanel(new GridLayout(1,3,10,10));

			this.btnImporte = new JButton("Importer image");
			this.btnValidImporteVerso = new JButton("Choisir image");

			this.btnImporte.setBackground(Color.WHITE);
			this.btnValidImporteVerso.setBackground(Color.WHITE);

			this.btnImporte.addActionListener(this);
			this.btnValidImporteVerso.addActionListener(this);
			this.jdImporte = new JDialog();
			this.jdImporte.setTitle("Modifier le verso de la carte");	
			this.jdImporte.setBounds(900, 300, 500, 400);

			try 
			{
				Files.createDirectories(Paths.get("importe/imageCarte"));
			}  catch (IOException ee) 	{ee.printStackTrace();}

			this.repertoireImage = new File(Paths.get("importe/imageCarte").toFile().getAbsolutePath()).list();

			PanelImageInfo[] tabPanelAffichageImporte = new PanelImageInfo[this.repertoireImage.length];
			JPanel panelDispoAffichage =new JPanel(new GridLayout(tabPanelAffichageImporte.length,1));

			for (int index = 0; index < tabPanelAffichageImporte.length; index++)
			{
				tabPanelAffichageImporte[index] = new PanelImageInfo(this.ctrl,"imageCarte/"+this.repertoireImage[index]);
				panelDispoAffichage.add(tabPanelAffichageImporte[index]);
			}

			this.scrollPaneImporte = new JScrollPane(panelDispoAffichage, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

			panelBtnHaut.add(new JLabel());
			panelBtnHaut.add(this.btnImporte);
			panelBtnHaut.add(new JLabel());

			panelBtnBas.add(new JLabel());
			panelBtnBas.add(this.btnValidImporteVerso);
			panelBtnBas.add(new JLabel());

			this.jdImporte.add(new JLabel("Liste des images de carte importées",JLabel.CENTER),BorderLayout.NORTH);
			this.jdImporte.add(this.scrollPaneImporte);
			this.jdImporte.add(panelBtnHaut,BorderLayout.NORTH);
			this.jdImporte.add(panelBtnBas,BorderLayout.SOUTH);
			this.jdImporte.setVisible(true);
		}

		if(e.getSource() == btnValidImporteVerso)
		{
			this.nomVerso = this.ctrl.getPanelSelectionner().getNomfichier();
			this.lblNomVerso.setText(this.nomVerso);
			this.ctrl.setVersoCarteWagon(this.nomVerso);
			this.jdImporte.dispose();
		}

		if(e.getSource() == this.btnValidImporteRecto)
		{
			this.hashCoulImage.put(this.lstCouleur.getSelectedValue(),this.ctrl.getPanelSelectionner().getNomfichier());
			this.lblNomRecto.setText("Recto : "+this.ctrl.getPanelSelectionner().getNomfichier());
			this.ctrl.setHsmImageWagon(this.hashCoulImage);
			this.jdImporte.dispose();
		}
		if(e.getSource() == this.btnImporte)
		{
			FileNameExtensionFilter filtre = new FileNameExtensionFilter("format image(*.png; *.jpg; *.gif)", "png","jpg","gif");
			JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

			jFileChooser.setAcceptAllFileFilterUsed(false);
			jFileChooser.setFileFilter(filtre);

			int res = jFileChooser.showOpenDialog(null);
			if(res == JFileChooser.APPROVE_OPTION)
			{
				File file = jFileChooser.getSelectedFile();
				
				try 	{Files.copy(file.toPath(), Paths.get("importe/imageCarte/"+file.getName()));} 
				catch (IOException e1) {e1.printStackTrace();}
			}
		}
	}

	public void mouseClicked(MouseEvent e) {

		if(e.getClickCount() == 2)
		{
			String couleur = this.lstCouleur.getSelectedValue();
			if(couleur != null)
			{
				String recto = this.hashCoulImage.get(couleur);
				JButton btnCouleur = new JButton();
				JLabel lblNbCarte = new JLabel("Nb cartes : ");

				this.jd = new JDialog();
				this.jd.setTitle("Modifier la carte joker");
				this.jd.setBounds(900, 300, 500, 400);

				this.panelJd = new JPanel(new GridLayout(6,5,5,5));
				this.txtNbCarte = new JTextField();

				btnCouleur.setBackground(Color.WHITE);
				
				if(! couleur.equals("Joker"))
				{
					String[] tabCouleur = couleur.split(",");
					int r = Integer.parseInt(tabCouleur[0].substring(3));
					int g = Integer.parseInt(tabCouleur[1].substring(2));
					int b = Integer.parseInt(tabCouleur[2].substring(2,tabCouleur[2].length()-1));
					btnCouleur.setBackground(new Color(r,g,b));
				}

				this.modifieRecto = new JButton("Importer");
				this.btnConfirme  = new JButton("Confirmer");

				this.modifieRecto.setBackground(Color.WHITE);
				this.btnConfirme.setBackground(Color.WHITE);

				this.lblNomRecto = new JLabel("Recto : ");

				for(int i = 1; i <= 5; i++)
				{
					this.panelJd.add(new JLabel());
				}

				this.panelJd.add(new JLabel());
				this.panelJd.add(new JLabel(couleur));
				this.panelJd.add(new JLabel());
				this.panelJd.add(btnCouleur);
				this.panelJd.add(new JLabel());
				
				this.panelJd.add(new JLabel());
				this.panelJd.add(this.lblNomRecto);
				this.panelJd.add(new JLabel());
				this.panelJd.add(this.modifieRecto);
				this.panelJd.add(new JLabel());

				this.panelJd.add(new JLabel());
				this.panelJd.add(lblNbCarte);
				this.panelJd.add(new JLabel());
				this.panelJd.add(this.txtNbCarte);
				this.panelJd.add(new JLabel());

				for(int i = 1; i <= 5; i++)
				{
					this.panelJd.add(new JLabel());
				}

				this.panelJd.add(new JLabel());
				this.panelJd.add(new JLabel());
				this.panelJd.add(this.btnConfirme);

				this.jd.add(this.panelJd);
			
				this.modifieRecto.addActionListener(this);
				this.btnConfirme.addActionListener(this);

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
