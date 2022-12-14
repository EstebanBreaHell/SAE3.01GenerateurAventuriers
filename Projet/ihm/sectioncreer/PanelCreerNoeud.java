package ihm.sectioncreer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.Color;

import main.Controleur;

public class PanelCreerNoeud extends JPanel implements ActionListener
{
	private JTextField txtNom;
	private JTextField txtPosX;
	private JTextField txtPosY;
	private JButton    btnSupprimer;
	private JButton    btnGenererNoeud;
	private JButton    btnGenererPrefait;

	private final String[] TAB_EXPLIQUATION_HISTORIQUE = {"|Noeud : a 		| Action : Ajout 		| numeroMouvement : 1	|",
														  "|Noeud : b 		| Action : Ajout 		| numeroMouvement : 2	|",
														  "|Arête : a-b 	| Action : Ajout 		| numeroMouvement : 3 	|",
														  "|Arête : a-b     | Action : Supp			| numeroMouvement : 4 	|"};

	private JList<String>listHistorique;

	public PanelCreerNoeud(Controleur ctrl)
	{
		this.setLayout(new BorderLayout());
		JPanel panelCoordonnees 	= new JPanel(new GridLayout(3,2, 10, 10));
		JPanel panelSupprimer 		= new JPanel(new GridLayout(1,3, 10, 10));
		JPanel panelDispoHistorique = new JPanel(new BorderLayout());
		JPanel panelValidation		= new JPanel(new GridLayout(2,1, 10, 10));
		JLabel lblNom = new JLabel("Nom : ", JLabel.LEFT);
		JLabel lblPosX = new JLabel("Position X : ", JLabel.LEFT);
		JLabel lblPosY = new JLabel("Position Y : ", JLabel.LEFT);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);


		this.txtNom = new JTextField();
		this.txtPosX = new JTextField();
		this.txtPosY = new JTextField();
		this.btnSupprimer = new JButton("Supprimer");
		this.btnGenererNoeud = new JButton("Générer un noeud : ");
		this.btnGenererPrefait = new JButton("Générer un noeud préfait : ");
		this.listHistorique  = new JList<String>(TAB_EXPLIQUATION_HISTORIQUE);


		this.listHistorique.setBorder(border);
		this.listHistorique.setBackground(Color.WHITE);
		this.txtNom.setBorder(border);
		this.txtPosX.setBorder(border);
		this.txtPosY.setBorder(border);
		this.btnSupprimer.setBorder(border);
		this.btnGenererNoeud.setBorder(border);
		this.btnGenererPrefait.setBorder(border);
	
		panelCoordonnees.add(lblNom);
		panelCoordonnees.add(this.txtNom);
		panelCoordonnees.add(lblPosX);
		panelCoordonnees.add(this.txtPosX);
		panelCoordonnees.add(lblPosY);
		panelCoordonnees.add(this.txtPosY);

		
		panelSupprimer.add(new JLabel());
		panelSupprimer.add(this.btnSupprimer);
		panelSupprimer.add(new JLabel());

		panelDispoHistorique.add(new JLabel("Historique",JLabel.CENTER),BorderLayout.NORTH);
		panelDispoHistorique.add(this.listHistorique);

		panelValidation.add(this.btnGenererNoeud);
		panelValidation.add(this.btnGenererPrefait);


		this.add(panelCoordonnees, BorderLayout.NORTH);
		this.add(panelSupprimer, BorderLayout.CENTER);
		this.add(panelDispoHistorique);
		this.add(panelValidation, BorderLayout.SOUTH);


		this.btnSupprimer.addActionListener(this);
		this.btnGenererNoeud.addActionListener(this);
		this.btnGenererPrefait.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnSupprimer)
		{
			this.setVisible(false);
		}

		if(e.getSource() == this.btnGenererNoeud)
		{
			System.out.println("Générer un noeud");
		}

		if(e.getSource() == this.btnGenererPrefait)
		{
			System.out.println("Générer un noeud préfait");
		}
	}
}
