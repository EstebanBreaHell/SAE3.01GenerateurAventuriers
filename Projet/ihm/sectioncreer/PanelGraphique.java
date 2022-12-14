package ihm.sectioncreer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

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

	private boolean premierClic;

	public PanelGraphique(Controleur ctrl)
	{
		this.ctrl = ctrl;

		Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur  = tailleMoniteur.height - (int) (tailleMoniteur.height*0.06);

		JPanel panelDispoBtn = new JPanel(new BorderLayout(0,hauteur/6));

		this.setLayout(new BorderLayout());

		this.add(panelDispoBtn);
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		g.setFont(new Font("default", Font.BOLD, 16));

		if(this.premierClic) {
			// draw les arete
			for (Arete a : this.ctrl.getLstArete())
			{

				int nb = a.getWagon();
				int fromSize = 20;
				int toSize = 20;

				Noeud from = a.getNoeud();
				Noeud to =   a.getNoeud2();

				if(to.getX() != 0 && to.getY() != 0) {
					if(from.getX() != 0 && from.getY() != 0) {

						int fromX = from.getX() + fromSize/2;
						int fromY = from.getY() + fromSize/2;

						int toX = to.getX() + toSize/2;
						int toY = to.getY() + toSize/2;

						drawArete(fromX, fromY, toX, toY, nb, g);

					}
				}
			}

			// draw les noeuds
			for ( Noeud n : this.ctrl.getLstNoeud() ) {
				if ( n.getX() != 0 && n.getY() != 0)
					drawNoeud( n , g);
			}
		}

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
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//this.ctrl.setPos( e.getX(), e.getY()), this.ctrl.getIdCuve() );
		this.premierClic = true;
		this.ctrl.majIHM();
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
}