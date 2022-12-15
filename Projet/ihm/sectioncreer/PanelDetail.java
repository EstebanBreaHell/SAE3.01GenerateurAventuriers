package ihm.sectioncreer;


import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import main.Controleur;

public class PanelDetail extends JPanel implements ActionListener
{
	private JTextField txtNbJoueursMin;	
	private JTextField txtNbJoueursMax;
	private JTextField txtNbCartesJoueurs;
	private Controleur ctrl;
	private JButton btnValider;
	private JButton btnplus;
	private JButton btnmoins;
	private JButton btnplus2;
	private JButton btnmoins2;
	private JTextField btnCompter;
	private JTextField btnCompter2; 

	private Object[][] donnees = {
								{"1", "1",},
								{"2", "2",},
								{"3", "4",},
								{"4", "7",},
								{"5", "10",},
								{"6", "15",},
	};

	private String[] entetes = {"Longueur de la route", "Points marqués"};
	
	public PanelDetail(Controleur ctrl)
	{
		this.ctrl = ctrl; 
		this.setLayout(new BorderLayout());
		JLabel lblNbJoueursMin = new JLabel("Nombre de joueurs minimum : ");
		JLabel lblNbJoueursMax = new JLabel("Nombre de joueurs maximum : ");
		JLabel lblNbWagonsMax = new JLabel("Nombre de wagons de couleurs : ");
		JLabel lblNbWagonsMulti = new JLabel("Nombre de wagons multicolores : ");
		JLabel lblNbCartesJoueurs = new JLabel("Nombre de cartes par joueurs : ");
		JLabel lblCarteWagon = new JLabel(new ImageIcon("donnee\\carteWagons\\carte.png"));
		JLabel lblCarteMulti = new JLabel(new ImageIcon("donnee\\carteWagons\\carte9.png"));
		JLabel lblPoints     = new JLabel("Barême des points : ");
		JTable tableau = new JTable(donnees, entetes);

		DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
		custom.setHorizontalAlignment(JLabel.CENTER);

		this.txtNbJoueursMin = new JTextField();
		this.txtNbJoueursMax = new JTextField();
		this.txtNbCartesJoueurs = new JTextField();
		this.btnValider = new JButton("Valider");
		this.btnplus = new JButton("+");
		this.btnmoins = new JButton("-");
		this.btnplus2 = new JButton("+");
		this.btnmoins2 = new JButton("-");
		this.btnCompter = new JTextField("0", JTextField.CENTER);
		this.btnCompter2 = new JTextField("0");

		this.btnCompter.setHorizontalAlignment(SwingConstants.CENTER);
		this.btnCompter2.setHorizontalAlignment(SwingConstants.CENTER);
		this.btnValider.setBackground(Color.WHITE);
	
		for(int i = 0; i < tableau.getColumnCount(); i++)
		{
			tableau.getColumnModel().getColumn(i).setCellRenderer(custom);
		}

		JPanel panelDetail 		= new JPanel(new GridLayout(5,3,0,10));
		JPanel panelChoixCarte	= new JPanel(null);
		JPanel panelBas			= new JPanel(new GridLayout(1,3,10,10));
		JPanel panelTableau		= new JPanel(new BorderLayout());

		

		this.btnplus.setBounds(50, 30, 60, 20);
		this.btnCompter.setBounds(50, 50, 60, 60);
		this.btnCompter.setEditable(false);
		this.btnmoins.setBounds(50, 110, 60, 20);
		lblCarteWagon.setBounds(100, 50, 250, 60);

		lblNbWagonsMulti.setBounds(0, 40, 250, 250);

		this.btnplus2.setBounds(50, 200, 60, 20);
		this.btnCompter2.setBounds(50, 220, 60, 60);
		this.btnmoins2.setBounds(50, 280, 60, 20);
		this.btnCompter2.setEditable(false);
		lblCarteMulti.setBounds(50, 220, 250, 60);

		lblPoints.setBounds(0,240, 250, 250);

		panelTableau.setBounds(50, 390, 400, 117);
		


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

		panelChoixCarte.add(this.btnplus);
		panelChoixCarte.add(this.btnCompter);
		panelChoixCarte.add(this.btnmoins);
		panelChoixCarte.add(lblCarteWagon);

		panelChoixCarte.add(lblNbWagonsMulti);

		panelChoixCarte.add(this.btnplus2);
		panelChoixCarte.add(this.btnCompter2);
		panelChoixCarte.add(this.btnmoins2);
		panelChoixCarte.add(lblCarteMulti);
		panelChoixCarte.add(lblPoints);
		panelChoixCarte.add(panelTableau);

		panelTableau.add(tableau.getTableHeader(), BorderLayout.NORTH);
		panelTableau.add(tableau, BorderLayout.CENTER);

		panelBas.add(new JLabel());
		panelBas.add(this.btnValider);
		panelBas.add(new JLabel());

		this.add(panelDetail, BorderLayout.NORTH);
		this.add(panelChoixCarte, BorderLayout.CENTER);
		this.add(panelBas, BorderLayout.SOUTH);



		this.btnplus.addActionListener(this);
		this.btnmoins.addActionListener(this);
		this.btnplus2.addActionListener(this);
		this.btnmoins2.addActionListener(this);
		this.btnValider.addActionListener(this);


		this.txtNbJoueursMax.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		this.txtNbJoueursMin.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		this.txtNbCartesJoueurs.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnplus)
		{
			int nb = Integer.parseInt(this.btnCompter.getText());
			nb++;
			this.btnCompter.setText(Integer.toString(nb));
		}

		if(e.getSource() == this.btnplus2)
		{
			int nb = Integer.parseInt(this.btnCompter2.getText());
			nb++;
			this.btnCompter2.setText(Integer.toString(nb));
		}

		if(e.getSource() == this.btnmoins || e.getSource() == this.btnmoins2)
		{
			int nb = Integer.parseInt(this.btnCompter.getText());
			nb--;
			this.btnCompter.setText(Integer.toString(nb));

			if(nb < 0)
			{
				this.btnCompter.setText("0");
			}
		}

		if(e.getSource() == this.btnmoins2)
		{
			int nb = Integer.parseInt(this.btnCompter2.getText());
			nb--;
			this.btnCompter2.setText(Integer.toString(nb));

			if(nb < 0)
			{
				this.btnCompter2.setText("0");
			}
		}

		if(e.getSource() == this.btnValider)
		{
			if(this.txtNbCartesJoueurs.getText().isEmpty() || this.txtNbJoueursMax.getText().isEmpty() || this.txtNbJoueursMin.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
			}

			int nbJoueursMin = Integer.parseInt(this.txtNbJoueursMin.getText());
			int nbJoueursMax = Integer.parseInt(this.txtNbJoueursMax.getText());
			int nbCartesJoueurs = Integer.parseInt(this.txtNbCartesJoueurs.getText());
			int nbWagonsMax = Integer.parseInt(this.btnCompter.getText());
			int nbWagonsMulti = Integer.parseInt(this.btnCompter2.getText());

			if(nbJoueursMin > nbJoueursMax || nbJoueursMin <= 0 || nbJoueursMax <= 0 || nbCartesJoueurs <= 0 || nbWagonsMax <= 0 || nbWagonsMulti <= 0)
			{
				JOptionPane.showMessageDialog(this, "Erreur de saisie", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
