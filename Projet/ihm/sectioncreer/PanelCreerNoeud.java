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
import java.awt.Dimension;

import main.Controleur;

public class PanelCreerNoeud extends JPanel implements ActionListener
{
	private JTextField txtNom;
	private JTextField txtPosX;
	private JTextField txtPosY;
	private JButton    btnSupprimer;
	private JButton    btnGenererNoeud;
	private JButton    btnGenererPrefait;
	private Controleur ctrl;

	private final String[] TAB_EXPLIQUATION_HISTORIQUE = {"|Noeud : a 		| Action : Ajout 		| numeroMouvement : 1	|",
														  "|Noeud : b 		| Action : Ajout 		| numeroMouvement : 2	|",
														  "|Arête : a-b 	| Action : Ajout 		| numeroMouvement : 3 	|",
														  "|Arête : a-b     | Action : Supp			| numeroMouvement : 4 	|",
														  "|Arête : a-b     | Action : Supp			| numeroMouvement : 4 	|",
														  "|Arête : a-b     | Action : Supp			| numeroMouvement : 4 	|",
														  "|Arête : a-b     | Action : Supp			| numeroMouvement : 4 	|",
														  "|Arête : a-b     | Action : Supp			| numeroMouvement : 4 	|",
														  "|Arête : a-b     | Action : Supp			| numeroMouvement : 4 	|",
														  "|Arête : a-b     | Action : Supp			| numeroMouvement : 4 	|", 
														  "|Arête : a-b     | Action : Supp			| numeroMouvement : 4 	|", 
														  "|Arête : a-b     | Action : Supp			| numeroMouvement : 4 	|", 
														  "|Arête : a-b     | Action : Supp			| numeroMouvement : 4 	|", };

	private JList<String>listHistorique;

	public PanelCreerNoeud(Controleur ctrl)
	{
		this.setLayout(new BorderLayout());
		this.ctrl = ctrl;
		JPanel panelCoordonnees 	= new JPanel(new GridLayout(5,3,10, 10));
		JPanel panelDispoHistorique = new JPanel(new BorderLayout(0,10));
		JPanel panelValidation		= new JPanel(new GridLayout(3,3, 10, 15));
		JLabel lblNom = new JLabel("Nom : ", JLabel.LEFT);
		JLabel lblPosX = new JLabel("Position X : ", JLabel.LEFT);
		JLabel lblPosY = new JLabel("Position Y : ", JLabel.LEFT);
		JLabel lblHistorique = new JLabel("Historique ", JLabel.CENTER);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

		this.txtNom = new JTextField();
		this.txtPosX = new JTextField();
		this.txtPosY = new JTextField();
		this.btnSupprimer = new JButton("Supprimer");
		this.btnGenererNoeud = new JButton("Générer noeud");
		this.btnGenererPrefait = new JButton("Générer noeud préfait");
		this.listHistorique  = new JList<String>(TAB_EXPLIQUATION_HISTORIQUE);


		this.listHistorique.setPreferredSize(new Dimension(100,400));
		this.listHistorique.setBorder(border);
		this.listHistorique.setBackground(Color.WHITE);
		this.btnSupprimer.setBackground(Color.WHITE);
		this.btnGenererNoeud.setBackground(Color.WHITE);
		this.btnGenererPrefait.setBackground(Color.WHITE);
		

		this.txtNom.setBorder(border);
		this.txtPosX.setBorder(border);
		this.txtPosY.setBorder(border);
		this.btnSupprimer.setBorder(border);
		this.btnGenererNoeud.setBorder(border);
		this.btnGenererPrefait.setBorder(border);
	
		panelCoordonnees.add(new JLabel());
		panelCoordonnees.add(new JLabel());
		panelCoordonnees.add(new JLabel());
		panelCoordonnees.add(lblNom);
		panelCoordonnees.add(this.txtNom);
		panelCoordonnees.add(new JLabel());
		panelCoordonnees.add(lblPosX);
		panelCoordonnees.add(this.txtPosX);
		panelCoordonnees.add(new JLabel());
		panelCoordonnees.add(lblPosY);
		panelCoordonnees.add(this.txtPosY);
		panelCoordonnees.add(new JLabel());
		panelCoordonnees.add(new JLabel());
		panelCoordonnees.add(new JLabel());
		panelCoordonnees.add(new JLabel());

		panelDispoHistorique.add(lblHistorique,BorderLayout.NORTH);
		panelDispoHistorique.add(new JPanel(), BorderLayout.WEST);
		panelDispoHistorique.add(new JPanel(), BorderLayout.EAST);
		panelDispoHistorique.add(this.listHistorique, BorderLayout.CENTER);

		panelValidation.add(new JLabel());
		panelValidation.add(this.btnGenererNoeud);
		panelValidation.add(new JLabel());
		panelValidation.add(new JLabel());
		panelValidation.add(this.btnGenererPrefait);
		panelValidation.add(new JLabel());
		panelValidation.add(new JLabel());
		panelValidation.add(this.btnSupprimer);
		panelValidation.add(new JLabel());


		this.add(panelCoordonnees, BorderLayout.NORTH);
		this.add(panelDispoHistorique, BorderLayout.CENTER);
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
