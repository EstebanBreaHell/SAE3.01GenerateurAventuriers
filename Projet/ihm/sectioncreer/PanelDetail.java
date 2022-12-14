package ihm.sectioncreer;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;



import main.Controleur;

public class PanelDetail extends JPanel implements ActionListener
{
	private JTextField txtNbJoueursMin;	
	private JTextField txtNbJoueursMax;
	private JTextField txtNbCartesJoueurs;
	private Controleur ctrl;
	
	public PanelDetail(Controleur ctrl)
	{
		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());
		JLabel lblNbJoueursMin = new JLabel("Nombre de joueurs minimum : ");
		JLabel lblNbJoueursMax = new JLabel("Nombre de joueurs maximum : ");
		JLabel lblNbWagonsMax = new JLabel("Choisissez un nombre de wagons");
		JLabel lblNbCartesJoueurs = new JLabel("Nombre de cartes par joueurs : ");



		this.txtNbJoueursMin = new JTextField();
		this.txtNbJoueursMax = new JTextField();
		this.txtNbCartesJoueurs = new JTextField();

		JPanel panelDetail 		= new JPanel(new GridLayout(5,3,0,10));
		JPanel panelChoixCarte	= new JPanel(new GridLayout(4,4, 0,0));
		for(int i = 0; i < 8; i++)
		{
			JButton btn = new JButton("" + i);
			JLabel lbl = new JLabel("" + i);
			btn.addActionListener(this);
			panelChoixCarte.add(btn);
			panelChoixCarte.add(lbl);
		}


		panelDetail.add(new JLabel());
		panelDetail.add(new JLabel());
		panelDetail.add(new JLabel());
		panelDetail.add(lblNbJoueursMin);
		panelDetail.add(this.txtNbJoueursMin);
		panelDetail.add(new JLabel());
		panelDetail.add(lblNbJoueursMax);
		panelDetail.add(this.txtNbJoueursMax);
		panelDetail.add(new JLabel());
		panelDetail.add(lblNbCartesJoueurs);
		panelDetail.add(this.txtNbCartesJoueurs);
		panelDetail.add(new JLabel());
		panelDetail.add(lblNbWagonsMax);

		this.add(panelDetail, BorderLayout.NORTH);
		this.add(panelChoixCarte, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
	}
}
