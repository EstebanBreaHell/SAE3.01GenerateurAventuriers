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

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Controleur;

public class PanelApercuFace extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JButton btnVoirApercu;

	public PanelApercuFace(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		this.btnVoirApercu = new JButton("Voir aperçu");
		this.btnVoirApercu.setBackground(Color.WHITE);
	
		this.add(new JLabel("Recto",JLabel.CENTER),BorderLayout.NORTH);

		//this.add(new JLabel(Controleur.imageToIcon(this.ctrl.getPathImg(), 200, 200)),BorderLayout.CENTER);
		
		
		this.add(this.btnVoirApercu,BorderLayout.SOUTH);

		this.btnVoirApercu.addActionListener(this);
	}

	public void paintComponent(Graphics g)
	{
		Image img = null;

		Graphics2D g2d = (Graphics2D) g;

		try   {img = ImageIO.read( new File(this.ctrl.getPathImg()));} 
		catch (IOException e) {e.printStackTrace();}

		g.drawImage(img.getScaledInstance(200,100, Image.SCALE_DEFAULT),this.getWidth()/4,this.getHeight()/4, this);
		
		g2d.setColor(Color.WHITE);
		g2d.setStroke(new BasicStroke(5.0f));
		g2d.drawRect(this.getWidth()/4,this.getHeight()/4,200,100);

		g2d.fillArc(this.getWidth()/4 + 175,this.getHeight()/4-25,50,50,180,90);
		g2d.setColor(Color.BLACK);

		//Test Affichage
		g2d.drawString("12", this.getWidth()/4 + 185,this.getHeight()/4+15);

		g2d.fillOval(this.getWidth()/4+50, this.getHeight()/4+50, 10, 10);
		
		g2d.setColor(Color.white);
		g2d.fillRect(this.getWidth()/4+10, this.getHeight()/4+30, 40, 15);
		g2d.setColor(Color.BLACK);
		g2d.drawString("noeud1", this.getWidth()/4+10, this.getHeight()/4+40);


	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnVoirApercu) System.out.println("ça marche");	
	}
}
