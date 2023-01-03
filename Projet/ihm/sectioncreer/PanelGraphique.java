/**
 * @author Lefort William, Decharrois Adrien, Brea-Hell Esteban
 * @version 1.0
 * @date 2019-03-20
 */

package ihm.sectioncreer;

import java.awt.*;
import java.awt.event.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import javax.swing.filechooser.FileSystemView;

import main.Controleur;
import metier.Arete;
import metier.Noeud;

public class PanelGraphique extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
	private Controleur ctrl;

	private static String  pathImg;

	private JButton btnImportImg;
	private JButton btnBackToMenu; 
	
	private Noeud     noeudActif;
	private boolean   nomActif;

	private Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
	private int       hauteurMoniteur = tailleMoniteur.height - (int) (tailleMoniteur.height*0.06);
	private int       largeurMoniteur = tailleMoniteur.height - (int) (tailleMoniteur.width *0.06);

	public PanelGraphique(Controleur ctrl)
	{
		/**
		 * Création des composants
		 */
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		JPanel panelBtn = new JPanel(new GridLayout(1,10));

		this.btnBackToMenu = new JButton("Retour au menu");
		this.btnBackToMenu.setBackground(Color.WHITE);
		this.nomActif = false;
		
		/**
		 * Boucle permettant de placer le bouton en haut à gauche du panel
		 */
		for(int i = 0; i < 6; i++)
			if(i == 0)
				panelBtn.add(this.btnBackToMenu);
			else
				panelBtn.add(new JLabel(""));
			
		/**
		 * Ajout des composants
		 */
		this.add(panelBtn, BorderLayout.NORTH);

		/**
		 * Activation des composants
		 */
		this.btnBackToMenu.addActionListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	public int getHauteurMoniteur() {
		return this.hauteurMoniteur;
	}

	public int getLargeurMoniteur() {
		return this.largeurMoniteur;
	}

	/**
	 * Méthode permettant de changer le chemin de l'image
	 * @param path
	 */
	public void imageToPanelGraphique(String path)
	{
		PanelGraphique.pathImg = path;
	}

	/**
	 * Méthode permettant de dessiner l'image
	 * @param g
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	
		Image img = null;

		try   {img = ImageIO.read( new File(PanelGraphique.pathImg));} 
		catch (IOException e) {e.printStackTrace();}

		g.drawImage(img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT),0,0, this);

	}

	/**
	 * Méthode permettant de dessiner les noeuds et les arêtes
	 * @param g
	 */
	public void paint(Graphics g)
	{
		super.paint(g);

		// Dessiner le texte	
		g.setFont(new Font("default", Font.BOLD, 12));
		ArrayList<Arete> areteDoubleDessine = new ArrayList();

		// draw les arete
		for (Arete a : this.ctrl.getLstArete()) {
			
			int nb = a.getWagon();
			int fromSize = 20;
			int toSize = 20;
			String c = a.getCouleur();

			Noeud from = a.getNoeudDep();
			Noeud to = a.getNoeudArr();

			if (to.getX() != 0 && to.getY() != 0) {
				if (from.getX() != 0 && from.getY() != 0) {

					int fromX = from.getX() + fromSize / 2;
					int fromY = from.getY() + fromSize / 2;

					int toX = to.getX() + toSize / 2;
					int toY = to.getY() + toSize / 2;

					if(a.getEstDouble() && !areteDoubleDessine.contains(a))
					{
						areteDoubleDessine.add(a);
						areteDoubleDessine.add(a.getAreteDouble());

						if((fromX > toX && toY < fromY) || (fromX < toX && toY > fromY) )
						{
							//Haut gauche
							//Bas droit
							drawArete(fromX+5, fromY-5, toX+5, toY-5, nb, c, g);
							drawArete(fromX-5, fromY+5, toX-5, toY+5, a.getAreteDouble().getWagon(), a.getAreteDouble().getCouleur(), g);
						}
						else
						{
							//Haut droit 
							//Bas gauche	
							drawArete(fromX+5, fromY+5, toX+5, toY+5, nb, c, g);
							drawArete(fromX-5, fromY-5, toX-5, toY-5, a.getAreteDouble().getWagon(), a.getAreteDouble().getCouleur(), g);
						}
					}
					else
					{
						if(!areteDoubleDessine.contains(a))
							drawArete(fromX, fromY, toX, toY, nb, c, g);
					}
				}
			}
		}

		// draw les noeuds
		for ( Noeud n : this.ctrl.getLstNoeud() )
			if ( n.getX() != 0 && n.getY() != 0)
				drawNoeud( n, g);
		
	}

	private void drawNoeud(Noeud noeud, Graphics g)
	{
		int size = 26;
		
		// draw la Noeud
		g.setColor(Color.BLACK);
		g.fillOval(noeud.getX(), noeud.getY(), size, size);
		g.drawOval(noeud.getX(), noeud.getY(), size, size);

		g.setColor(Color.WHITE);
		g.fillOval(noeud.getX()+size/4, noeud.getY()+size/4, size/2, size/2);

		// draw l'ID de la noeud
		g.setColor(Color.BLACK);
		String str = String.valueOf(noeud.getNom());
		g.drawRect(noeud.getNomX() + size/2 - g.getFontMetrics().stringWidth(str)/2, noeud.getNomY() - size, g.getFontMetrics().stringWidth(str), 20);
		g.setColor(Color.WHITE);
		g.fillRect(noeud.getNomX() + size/2 - g.getFontMetrics().stringWidth(str)/2, noeud.getNomY() - size, g.getFontMetrics().stringWidth(str), 22);


		g.setColor(Color.BLACK);
		g.drawString(str, noeud.getNomX() + size/2 - g.getFontMetrics().stringWidth(str)/2, noeud.getNomY() -10);

	}

	private void drawArete(int fromX, int fromY, int toX, int toY, int nbWagon, String c , Graphics g)
	{
		int posX = (fromX + toX) / 2;
		int posY = (fromY + toY) / 2;

		// draw la valeur de l'arete
		//System.out.print(c);
		//from string : "java.awt.Color[r=0,g=0,b=0]" to : 0,0,0
		
		//now remove "r=" and "g=" and "b="
		String[] rgb;
		if(!c.equals("Neutre"))
		{
			rgb = c.substring(1, c.length()-1).split(",");
			rgb[0] = rgb[0].substring(2);
			rgb[1] = rgb[1].substring(2);
			rgb[2] = rgb[2].substring(2);
		}
		else
		{
			rgb = new String[3];
			rgb[0] = "195";
			rgb[1] = "195";
			rgb[2] = "195";
		}


		g.setColor(new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2])));

		//on vas dessiner les arêtes découpées en fonction du nombre de wagon 
		for(int n= 0 ; n<=nbWagon-1; n++)
		{

			((Graphics2D) g).setStroke(new BasicStroke(15));
			g.setColor(Color.BLACK);
			g.drawLine(fromX + (toX-fromX)/nbWagon *n,
						fromY + (toY-fromY)/nbWagon *n,
					fromX + (toX-fromX)/nbWagon *(n+1),
					fromY + (toY-fromY)/nbWagon *(n+1));
				
			//Dessine les contours du trait en noir 
			((Graphics2D) g).setStroke(new BasicStroke(10));
			g.setColor(new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2])));
			g.drawLine(fromX + (toX-fromX)/nbWagon *n,
						fromY + (toY-fromY)/nbWagon *n,
					fromX + (toX-fromX)/nbWagon *(n+1),
					fromY + (toY-fromY)/nbWagon *(n+1));
		}
		//g.drawLine(fromX, fromY, toX, toY);
		((Graphics2D) g).setStroke(new BasicStroke(1));
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

				if(this.ctrl.getExtension(file.getName()).equals("png") );
				{
					Desktop desktop = Desktop.getDesktop();
					try {
						desktop.open(file);
					} catch (IOException e1) {e1.printStackTrace();}
				}
			}
		}

		if(e.getSource() == this.btnBackToMenu)
		{
			this.ctrl.resetGraph();
			this.ctrl.changerPanel("init");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String nom = this.ctrl.getNomNoeudPanelCreer();
		if(nom.equals(""))
		{
			return;
		}	
		else
		{
			this.ctrl.addNoeud(nom, e.getX(), e.getY());
			/* Ajout des noeuds dans l'historique */
			PanelCreerNoeud.lstLabel.add(new JLabel("Nom : " + nom + " | Position X : " + e.getX()  + " | Position Y : " + e.getY()));
			PanelCreerNoeud.listHistorique.setListData(PanelCreerNoeud.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));
			/*------------------------------------*/

			this.ctrl.majIHM();
		}
	}

	public void mouseDragged(MouseEvent e) {
		// Obtenez les coordonnées de la souris
		Dimension testTaille = this.getSize();
			//System.out.println("Taille : " + testTaille.getWidth() + " " + testTaille.getHeight());	

		double xMax = testTaille.getWidth() -26;
		double yMax = testTaille.getHeight() -26;

		if(this.noeudActif == null)
			return;

		if(!nomActif)
		{
			int x = e.getX();
			int y = e.getY();

			
			int nomX = this.noeudActif.getNomX()-(this.noeudActif.getX() - x);
			int nomY = this.noeudActif.getNomY()-(this.noeudActif.getY() - y);
			// Déplacez l'objet en utilisant les coordonnées de la souris

			if(x>=0 && x<=xMax)
				this.noeudActif.setX(x);
			
			if(y>=25 && y<=yMax)
				this.noeudActif.setY(y);

			if(nomX>=0 && nomX<=xMax)
				this.noeudActif.setNomX(nomX);
			
			if(nomY>=25 && nomY<=yMax)
				this.noeudActif.setNomY(nomY);
			
			
			//this.noeudActif.setNomX(x);
			//this.noeudActif.setNomY(y-10);
			this.repaint();
		}
		else
		{
			int x = e.getX();
			int y = e.getY();

			// Déplacez l'objet en utilisant les coordonnées de la souris
			if(x>=0 && x<=xMax)
				this.noeudActif.setNomX(x);
			
			if(y>=25 && y<=yMax)
				this.noeudActif.setNomY(y);
			
			//this.noeudActif.setNomX(x);
			//this.noeudActif.setNomY(y-10);
			this.repaint();
		}
		PanelCreerNoeud.lstLabel.get(this.ctrl.getPositionAreteNoeudAl(this.noeudActif)).setText("Nom : " + this.noeudActif.getNom() + " | Position X : " + this.noeudActif.getX()  + " | Position Y : " + this.noeudActif.getY());
		PanelCreerNoeud.listHistorique.setListData(PanelCreerNoeud.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));


	}
	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX()-15;
		int y = e.getY()-15;

		for(Noeud n : this.ctrl.getLstNoeud())
		{
			if(n.getX() >= x-15 && n.getX() <= x+15 && n.getY() >= y-15 && n.getY() <= y+15)
			{	
				this.noeudActif = n;
				return;
			}
			
			if (n.getNomX() >= x-35 && n.getNomX() <= x+35 && n.getNomY()-30 >= y-15 && n.getNomY()-30 <= y+15)
			{
				this.noeudActif = n;
				this.nomActif = true;
				return;
			}	
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.noeudActif = null;
		this.nomActif = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public void majIHM(){
		this.repaint();
		this.ctrl.majIHM();
	}
}