package ihm.sectioncreer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;

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
		this.add(new JLabel(Controleur.imageToIcon(this.ctrl.getImgPanel(), ALLBITS, ABORT)),BorderLayout.CENTER);
		this.add(this.btnVoirApercu,BorderLayout.SOUTH);

		this.btnVoirApercu.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnVoirApercu) System.out.println("ça marche");	
	}
}
