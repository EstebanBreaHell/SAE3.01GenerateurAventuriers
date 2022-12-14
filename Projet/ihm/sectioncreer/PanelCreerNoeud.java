package ihm.sectioncreer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.ArrayList;
import java.util.List; 

import main.Controleur;

public class PanelCreerNoeud extends JPanel implements ActionListener, ListSelectionListener
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

		this.txtNom = new JTextField();
		this.txtPosX = new JTextField();
		this.txtPosY = new JTextField();
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

		//Si on selectionne une ligne de la JList, on peut la supprimer avec btnSupprimer
		this.listHistorique.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					if (listHistorique.getSelectedIndex() == -1) {
						btnSupprimer.setEnabled(false);
					} else {
						btnSupprimer.setEnabled(true);
					}
				}
			}
		});

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
			this.setVisible(false);
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
			System.out.println("Générer un noeud préfait");
		}
	}

}
