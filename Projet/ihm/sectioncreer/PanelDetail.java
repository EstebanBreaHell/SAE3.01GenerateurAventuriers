package ihm.sectioncreer;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import main.Controleur;

public class PanelDetail extends JPanel implements ActionListener
{
	private JTextField txtNbJoueursMin;	
	private JTextField txtNbJoueursMax;
	private JTextField txtNbWagonsMax;
	private JTextField txtNbCartesJoueurs;
	private Controleur ctrl;


	public PanelDetail(Controleur ctrl)
	{
		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());
		JLabel lblNbJoueursMin = new JLabel("Nombre de joueurs minimum : ");
		JLabel lblNbJoueursMax = new JLabel("Nombre de joueurs maximum : ");
		JLabel lblNbWagonsMax = new JLabel("Nombre de wagons maximum : ");
		JLabel lblNbCartesJoueurs = new JLabel("Nombre de cartes par joueurs : ");

		this.txtNbJoueursMin = new JTextField();
		this.txtNbJoueursMax = new JTextField();
		this.txtNbWagonsMax = new JTextField();
		this.txtNbCartesJoueurs = new JTextField();

		JPanel panelDetail 		= new JPanel(new GridLayout(3,2));
		JPanel panelChoixCarte	= new JPanel(new GridLayout(4,4, 10, 10));
		JPanel panelBas			= new JPanel(new GridLayout(1,2));

		for(int i = 0; i < 16; i++)
		{
			JButton btn = new JButton("" + i);
			JLabel lbl = new JLabel("" + i);
			btn.addActionListener(this);
			panelChoixCarte.add(btn);
			panelChoixCarte.add(lbl);
		}

		panelDetail.add(lblNbJoueursMin);
		panelDetail.add(this.txtNbJoueursMin);
		panelDetail.add(lblNbJoueursMax);
		panelDetail.add(this.txtNbJoueursMax);
		panelDetail.add(lblNbWagonsMax);
		panelDetail.add(this.txtNbWagonsMax);

		panelBas.add(lblNbCartesJoueurs);
		panelBas.add(this.txtNbCartesJoueurs);

		this.add(panelDetail, BorderLayout.NORTH);
		this.add(panelChoixCarte, BorderLayout.CENTER);
		this.add(panelBas, BorderLayout.SOUTH);
		
	}

	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub

	}
}
