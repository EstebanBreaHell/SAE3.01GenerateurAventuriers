package ihm.sectioncreer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.filechooser.FileSystemView;


import main.Controleur;
import metier.Arete;
import metier.Noeud;


public class PanelGraphique extends JPanel implements ActionListener, MouseListener
{
	private Controleur ctrl;
	private JButton btnImportImg;
	private JButton btnBackToMenu; 
	private static String  pathImg;

	private boolean premierClic;

	public PanelGraphique(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		JPanel panelBtn = new JPanel(new GridLayout(1,10));

		Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = tailleMoniteur.height - (int) (tailleMoniteur.height*0.06);

		this.btnBackToMenu = new JButton("Retour au menu");
		this.btnBackToMenu.setBackground(Color.WHITE);

		for(int i = 0; i < 6; i++)
			if(i == 0)
				panelBtn.add(this.btnBackToMenu);
			else
				panelBtn.add(new JLabel(""));
			
	
		
		this.add(panelBtn, BorderLayout.NORTH);
		this.addMouseListener(this);

		this.btnBackToMenu.addActionListener(this);
	}

	public void imageToPanelGraphique(String path)
	{
		PanelGraphique.pathImg = path;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		while(PanelGraphique.pathImg == null)
			System.out.println("erreur");

		Image img = null;

		try   {img = ImageIO.read( new File(PanelGraphique.pathImg));} 
		catch (IOException e) {e.printStackTrace();}
		g.drawImage(img, 0,0, this);


	}


	public void paint(Graphics g)
	{
		super.paint(g);
		g.setFont(new Font("default", Font.BOLD, 16));

		// draw les arete
		for (Arete a : this.ctrl.getLstArete()) {

			int nb = a.getWagon();
			int fromSize = 20;
			int toSize = 20;

			Noeud from = a.getNoeudDep();
			Noeud to = a.getNoeudArr();

			if (to.getX() != 0 && to.getY() != 0) {
				if (from.getX() != 0 && from.getY() != 0) {

					int fromX = from.getX() + fromSize / 2;
					int fromY = from.getY() + fromSize / 2;

					int toX = to.getX() + toSize / 2;
					int toY = to.getY() + toSize / 2;

					drawArete(fromX, fromY, toX, toY, nb, g);

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
		int size = 20;

		// draw la Noeud
		g.setColor(Color.BLACK);
		g.fillOval(noeud.getX(), noeud.getY(), size, size);
		g.drawOval(noeud.getX(), noeud.getY(), size, size);

		// draw l'ID de la noeud
		String str = String.valueOf(noeud.getNom());
		g.drawString(str, noeud.getX() + size/2 - g.getFontMetrics().stringWidth(str)/2, noeud.getY() - 22);
	}

	private void drawArete(int fromX, int fromY, int toX, int toY, int nbWagon, Graphics g)
	{
		int posX = (fromX + toX) / 2;
		int posY = (fromY + toY) / 2;

		// draw la valeur de l'arete
		g.setColor(new Color(195, 195, 195));
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
			this.ctrl.changerPanel("init");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String nom = this.ctrl.getNomNoeudPanelCreer();
		if(nom.equals(""))
			this.ctrl.afficherErreurPanelCreer("Il faut entrer un nom");
		else
		{
			this.ctrl.addNoeud(nom, e.getX(), e.getY());
			this.majIHM();
			this.ctrl.majIHM();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

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