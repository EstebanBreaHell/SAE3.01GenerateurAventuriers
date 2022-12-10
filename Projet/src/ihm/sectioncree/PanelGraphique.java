package ihm.sectioncree;

import java.awt.*;
import javax.swing.*;

import main.Controleur;

public class PanelGraphique extends JPanel
{
	private Controleur ctrl;
	
	public PanelGraphique(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());
		
		this.setBackground(Color.RED);
	}
/*
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) getGraphics();
		
	}
	*/
	
	
}
