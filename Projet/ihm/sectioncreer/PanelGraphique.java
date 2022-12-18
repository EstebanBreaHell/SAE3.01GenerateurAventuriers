package ihm.sectioncreer;

import java.awt.*;
import java.awt.event.*;

import java.io.File;
import java.io.IOException;

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
	private JButton btnImportImg;
	private JButton btnBackToMenu; 
	private static String  pathImg;
	private Noeud noeudActif;
	private boolean premierClic;
	private boolean nomActif;

	public PanelGraphique(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		JPanel panelBtn = new JPanel(new GridLayout(1,10));

		Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = tailleMoniteur.height - (int) (tailleMoniteur.height*0.06);

		this.btnBackToMenu = new JButton("Retour au menu");
		this.btnBackToMenu.setBackground(Color.WHITE);
		this.nomActif = false;
		for(int i = 0; i < 6; i++)
			if(i == 0)
				panelBtn.add(this.btnBackToMenu);
			else
				panelBtn.add(new JLabel(""));
			
		this.add(panelBtn, BorderLayout.NORTH);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.btnBackToMenu.addActionListener(this);
	}

	public void imageToPanelGraphique(String path)
	{
		PanelGraphique.pathImg = path;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	
		Image img = null;

		try   {img = ImageIO.read( new File(PanelGraphique.pathImg));} 
		catch (IOException e) {e.printStackTrace();}

		g.drawImage(img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT),0,0, this);

	}

	public void paint(Graphics g)
	{
		super.paint(g);

		// Dessiner le texte
		g.setFont(new Font("default", Font.BOLD, 12));

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

					drawArete(fromX, fromY, toX, toY, nb, c , g);

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

		// draw l'ID de la noeud
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
		String[] rgb = c.substring(15, c.length()-1).split(",");
		//now remove "r=" and "g=" and "b="
		rgb[0] = rgb[0].substring(2);
		rgb[1] = rgb[1].substring(2);
		rgb[2] = rgb[2].substring(2);


		g.setColor(new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2])));
		//g.setColor(new Color(195, 195, 195));
		g.drawString(String.valueOf(nbWagon), posX + 5, posY + 5);

		// change la largeur de la ligne et la draw
		((Graphics2D) g).setStroke(new BasicStroke(nbWagon/2));
		g.drawLine(fromX, fromY, toX, toY);
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
		{	//this.ctrl.afficherErreurPanelCreer("Il faut entrer un nom");
			return;
		}	
		else
		{
			this.ctrl.addNoeud(nom, e.getX(), e.getY());
			/* Ajout des noeuds dans l'historique */
			PanelCreerNoeud.lstLabel.add(new JLabel("Nom : " + nom + " | Pos X : " + e.getX()  + " | Pos Y : " + e.getY()));
			PanelCreerNoeud.listHistorique.setListData(PanelCreerNoeud.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));
			/*------------------------------------*/

			this.ctrl.majIHM();
		}
	}


	public void mouseDragged(MouseEvent e) {
		// Obtenez les coordonnées de la souris
		if(this.noeudActif != null && !nomActif)
		{
			int x = e.getX();
			int y = e.getY();

			// Déplacez l'objet en utilisant les coordonnées de la souris
			this.noeudActif.setX(x);
			this.noeudActif.setY(y);
			//this.noeudActif.setNomX(x);
			//this.noeudActif.setNomY(y-10);
			this.repaint();
		}
		if(this.noeudActif != null && nomActif)
		{
			int x = e.getX();
			int y = e.getY();

			// Déplacez l'objet en utilisant les coordonnées de la souris
			this.noeudActif.setNomX(x);
			this.noeudActif.setNomY(y);
			//this.noeudActif.setNomX(x);
			//this.noeudActif.setNomY(y-10);
			this.repaint();
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX()-15;
		int y = e.getY()-15;

		System.out.println("x : " + x + " | y : " + y);
		for(Noeud n : this.ctrl.getLstNoeud())
		{
			System.out.println("x : " + n.getX() + " | y : " + n.getY());
			if(n.getX() >= x-15 && n.getX() <= x+15 && n.getY() >= y-15 && n.getY() <= y+15)
			{	
				System.out.println("Noeud trouvé");
				this.noeudActif = n;
				return;
			}
			
			System.out.println("NomX : " + n.getNomX() + " | NomY : " + n.getNomY());
			if (n.getNomX() >= x-15 && n.getNomX() <= x+15 && n.getNomY()-30 >= y-15 && n.getNomY()-30 <= y+15)
			{
				System.out.println("Noeud trouvé");
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