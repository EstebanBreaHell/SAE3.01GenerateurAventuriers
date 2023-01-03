/**
 * @author Lefort William, Decharrois Adrien, Brea-Hell Esteban
 * @version 1.0
 * @date 2019-03-20
 */

package ihm.sectioncreer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.BasicStroke;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;



import main.Controleur;
import metier.Noeud;

public class PanelApercuFace extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JButton btnVoirApercu;
	private PanelGraphiqueFace panelGraphiqueFace;

	public PanelApercuFace(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		this.panelGraphiqueFace = new PanelGraphiqueFace(this.ctrl.getPathImg(), 0, 0, "", 0, 0, "",0);

		this.btnVoirApercu = new JButton("Voir aperçu");
		this.btnVoirApercu.setBackground(Color.WHITE);
	
		this.add(new JLabel("Recto",JLabel.CENTER),BorderLayout.NORTH);
		this.add(this.panelGraphiqueFace,BorderLayout.CENTER);
		this.add(this.btnVoirApercu,BorderLayout.SOUTH);

		this.btnVoirApercu.addActionListener(this);
	}

	public void setNoeud1(int x, int y , String nom){this.panelGraphiqueFace.setNoeud1(x, y, nom);}
	public void setNoeud2(int x, int y , String nom){this.panelGraphiqueFace.setNoeud2(x, y, nom);}
	public void creerCarte(Noeud noeudDep, Noeud noeudArr){this.panelGraphiqueFace.creerCarte(noeudDep,noeudArr);}

	public PanelGraphiqueFace getPanelGraphiqueFace(){return this.panelGraphiqueFace;}

	public void setNbPoint(int nbPoint) {this.panelGraphiqueFace.setNbPoint(nbPoint);}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnVoirApercu)
		{
			JDialog popupApercu = new JDialog();

			popupApercu.setTitle("Apercu carte Face");
			popupApercu.setBounds(100, 100, (int)(this.getWidth()*1.5),(int)(this.getHeight()*1.5));
		
			popupApercu.add(new JLabel( new ImageIcon(this.ctrl.createImage(this.getPanelGraphiqueFace()).getScaledInstance(this.getWidth()*2,this.getHeight()*2, Image.SCALE_DEFAULT))));
			popupApercu.setBackground(Color.BLACK);

			popupApercu.setResizable(false);
			popupApercu.setVisible(true);

		}	
	}

	public class PanelGraphiqueFace extends JPanel
	{
		private static String pathImg;
		private int x1, x2, y1, y2, nbPoint;
		private String nomNoeud1, nomNoeud2; 
	
		public PanelGraphiqueFace(String pathImg, int x1, int y1, String nomNoeud1, int x2, int y2, String nomNoeud2, int nbPoint)
		{
			PanelGraphiqueFace.pathImg = pathImg;
			this.setLayout(new BorderLayout());

			this.x1 = x1;
			this.x2 = x2; 
			this.y1 = y1; 
			this.y2 = y2; 

			this.nomNoeud1 = nomNoeud1; 
			this.nomNoeud2 = nomNoeud2;

			this.nbPoint = nbPoint;

			this.addMouseListener(new InputSouris());
		}

		public void setNbPoint(int nbPoint)
		{
			this.nbPoint = nbPoint;
			this.majIhm();
		}

		public void setNoeud1(int x, int y, String nom)
		{
			this.x1 = x;
			this.y1 = y;
			this.nomNoeud1 = nom;

			this.majIhm();
		}

		public void setNoeud2(int x, int y, String nom)
		{
			this.x2 = x;
			this.y2 = y;
			this.nomNoeud2 = nom;

			this.majIhm();
		}
		
		public void creerCarte(Noeud noeudDep, Noeud noeudArr)
		{
			PanelApercuFace.this.ctrl.creerCarteObjectif(noeudDep, noeudArr, this.nbPoint);
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Image img = null;

			Graphics2D g2d = (Graphics2D) g;
	
			try   {img = ImageIO.read(new File(PanelGraphiqueFace.pathImg));} 
			catch (IOException e) {e.printStackTrace();}

		

			g.drawImage(img.getScaledInstance(200,100, Image.SCALE_DEFAULT),this.getWidth()/4,this.getHeight()/3, this);
			
			g2d.setColor(Color.WHITE);
			g2d.setStroke(new BasicStroke(5.0f));
			g2d.drawRect(this.getWidth()/4,this.getHeight()/3,200,100);
	
			/*Dessiner le nombre de point de la carte  */
			g2d.fillArc(this.getWidth()/4 + 175,this.getHeight()/3-25,50,50,180,90);
			g2d.setColor(Color.BLACK);
			g2d.drawString(this.nbPoint + "", this.getWidth()/4 + 185,this.getHeight()/3+15);
			
			/*arete */
			g2d.setColor(Color.red);
			g2d.drawLine(this.x1 +5, this.y1 + 5, this.x2 + 5, this.y2 + 5);

			/*Noeud 1 */
			g2d.setColor(Color.black);
			g2d.fillOval(this.x1 ,this.y1, 10, 10);
			g2d.setColor(Color.white);
			g2d.fillRect(this.x1 ,this.y1 -20, this.nomNoeud2.toCharArray().length * 5 , 15);
			g2d.setColor(Color.BLACK);
			g2d.drawString(this.nomNoeud1, this.x1,this.y1 + 10);

			/*Noeud 2 */
			g2d.fillOval(this.x2 ,this.y2  , 10, 10);
			g2d.setColor(Color.white);
			g2d.fillRect(this.x2 ,this.y2 -20, this.nomNoeud2.toCharArray().length *5 , 15);
			g2d.setColor(Color.BLACK);
			g2d.drawString(this.nomNoeud2, this.x2,this.y2 - 10);

			
	
			

		}

		public class InputSouris extends MouseAdapter
		{
			public void mouseClicked(MouseEvent e)
			{
				if(e.getButton() == MouseEvent.BUTTON1) 
					PanelApercuFace.this.setNoeud1(e.getX(), e.getY(), PanelGraphiqueFace.this.nomNoeud1);
				
				if(e.getButton() == MouseEvent.BUTTON3 )
					PanelApercuFace.this.setNoeud2(e.getX(), e.getY(), PanelGraphiqueFace.this.nomNoeud2);
			}
			
		}

		public void majIhm()
		{
			
			super.repaint();
		}
	}	
}
