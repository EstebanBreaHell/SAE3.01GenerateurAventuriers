package ihm.sectioncreer;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.NumberFormatter;

import java.util.ArrayList;
import java.util.List; 

import main.Controleur;

public class PanelCreerNoeud extends JPanel implements ActionListener
{
	private JTextField txtNom;
	private JTextField txtPosX;
	private JTextField txtPosY;
	private JButton    btnSupprimer;
	private JButton    btnGenererNoeud;
	private JButton    btnGenererPrefait;
	private List<JLabel> lstLabel;
	private Controleur ctrl;

	private JList<String> listHistorique;

	public PanelCreerNoeud(Controleur ctrl)
	{
		this.setLayout(new BorderLayout());
		this.ctrl = ctrl;
		this.lstLabel = new ArrayList<JLabel>();
		
		JPanel panelCoordonnees 	= new JPanel(new GridLayout(5,3,10, 10));
		JPanel panelDispoHistorique = new JPanel(new BorderLayout(0,20));
		JPanel panelValidation		= new JPanel(new GridLayout(3,3, 10, 20));
		JLabel lblNom = new JLabel("Nom : ", JLabel.LEFT);
		JLabel lblPosX = new JLabel("Position X : ", JLabel.LEFT);
		JLabel lblPosY = new JLabel("Position Y : ", JLabel.LEFT);
		JLabel lblHistorique = new JLabel("Historique ", JLabel.CENTER);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

		NumberFormat longformat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(longformat);
		numberFormatter.setValueClass(Long.class);
		numberFormatter.setAllowsInvalid(false);
		numberFormatter.setMinimum(0L);

		this.txtNom = new JTextField();
		this.txtPosX = new JFormattedTextField(longformat);
		this.txtPosY = new JFormattedTextField(longformat);
		this.btnSupprimer = new JButton("Supprimer");
		this.btnGenererNoeud = new JButton("Générer noeud");
		this.btnGenererPrefait = new JButton("Générer noeud préfait");
		this.listHistorique  = new JList<String>();



		this.listHistorique.setPreferredSize(new Dimension(0,550));
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

		panelDispoHistorique.add(lblHistorique,BorderLayout.NORTH);
		panelDispoHistorique.add(new JPanel(), BorderLayout.WEST);
		panelDispoHistorique.add(new JPanel(), BorderLayout.EAST);
		panelDispoHistorique.add(this.listHistorique, BorderLayout.CENTER);
		panelDispoHistorique.add(new JPanel(), BorderLayout.SOUTH);
		

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


		// Empêcher l'utilisateur de rentrer autre chose qu'un nombre dans les champs de texte
		this.txtPosX.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		this.txtPosY.addKeyListener(new KeyAdapter() {
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

		if(e.getSource() == this.btnSupprimer)
		{
			this.lstLabel.remove(this.listHistorique.getSelectedIndex());
			this.listHistorique.setListData(this.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));
		}

		if(e.getSource() == this.btnGenererNoeud)
		{

			if(this.txtPosX.getText().isEmpty() || this.txtNom.getText().isEmpty() || this.txtPosY.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}

			this.lstLabel.add(new JLabel("Nom : " + this.txtNom.getText() + " | Pos X : " + this.txtPosX.getText() + " | Pos Y : " + this.txtPosY.getText()));
			this.listHistorique.setListData(this.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));

			this.txtNom.setText("");
			this.txtPosX.setText("");
			this.txtPosY.setText("");
		}

		if(e.getSource() == this.btnGenererPrefait)
		{
			String randomNom = (char) (Math.random() * 26 + 'a') + "";
			String randomPosX = String.valueOf((int)(Math.random() * 1000));
			String randomPosY = String.valueOf((int)(Math.random() * 1000));

			this.lstLabel.add(new JLabel("Nom : " + randomNom + " | Pos X : " + randomPosX + " | Pos Y : " + randomPosY));
			this.listHistorique.setListData(this.lstLabel.stream().map(label -> label.getText()).toArray(String[]::new));
		}
	}
}
