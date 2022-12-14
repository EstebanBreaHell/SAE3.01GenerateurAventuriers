package ihm;

import javax.swing.*;

import main.Controleur;

import java.awt.event.*;

public class MaBarreMenu extends JMenuBar implements ActionListener
{
	private Controleur ctrl;

	private JFrame frame;

	private JMenuItem menuiFichierOuvrir;
	private JMenuItem menuiFichierFermer;
	private JMenuItem menuiFichierEnregistrer;
	private JMenuItem menuiFichierEnregistrerSous;
	private JMenuItem menuiFichierQuitter;

	private JMenuItem menuiEditionAnnuler;
	private JMenuItem menuiEditionRefaire;
	private JMenuItem menuiEditionCopier;
	private JMenuItem menuiEditionColler;
	private JMenuItem menuiEditionCouper;

	public MaBarreMenu(JFrame f,Controleur ctrl)
	{
		this.ctrl = ctrl;
		// l'ensemble du menu
	
		this.frame = f; 
		// un element de la barre de menu
		JMenu menuFichier = new JMenu("Fichier");
			  menuFichier.setMnemonic('F');

		JMenu menuEdition = new JMenu("Edition");
			  menuEdition.setMnemonic('E');


		// les items du menu fichier
		this.menuiFichierOuvrir				= new JMenuItem ("Ouvrir" );
		this.menuiFichierOuvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));

		this.menuiFichierFermer				= new JMenuItem ("Fermer");
		this.menuiFichierFermer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));

		this.menuiFichierEnregistrer		= new JMenuItem("Enregistrer");
		this.menuiFichierEnregistrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

		this.menuiFichierEnregistrerSous	= new JMenuItem("Enregistrer sous");
		this.menuiFichierEnregistrerSous.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));		

		this.menuiFichierQuitter			= new JMenuItem ("Quitter");
		this.menuiFichierQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,InputEvent.ALT_DOWN_MASK));

		this.menuiEditionAnnuler			= new JMenuItem("Annuler");
		this.menuiEditionAnnuler.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));

		this.menuiEditionRefaire 			= new JMenuItem("Refaire");
		this.menuiEditionRefaire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));

		this.menuiEditionCopier  			= new JMenuItem("Copier");
		this.menuiEditionCopier.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));

		this.menuiEditionColler  			= new JMenuItem("Coller");
		this.menuiEditionColler.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));

		this.menuiEditionCouper  			= new JMenuItem("Couper");
		this.menuiEditionCouper.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
		
		// ajouts des items au menu correspondant
		menuFichier.add( this.menuiFichierOuvrir );
		menuFichier.add( this.menuiFichierFermer );

		menuFichier.addSeparator();

		menuFichier.add( this.menuiFichierEnregistrer);
		menuFichier.add( this.menuiFichierEnregistrerSous);
		menuFichier.addSeparator();
		menuFichier.add( this.menuiFichierQuitter);

		menuEdition.add( this.menuiEditionAnnuler);
		menuEdition.add( this.menuiEditionRefaire);
		menuEdition.addSeparator();
		menuEdition.add( this.menuiEditionCopier);
		menuEdition.add( this.menuiEditionColler);
		menuEdition.add( this.menuiEditionCouper);


		// ajout du menu 'Fichier' a la barre de menu
		this.add( menuFichier );
		this.add( menuEdition );


		/*-------------------------------*/
		/* positionnement des composants */
		/*-------------------------------*/

		/*-------------------------------*/
		/* Activation des composants     */
		/*-------------------------------*/
		this.menuiFichierOuvrir			.addActionListener ( this );
		this.menuiFichierFermer			.addActionListener ( this );
		this.menuiFichierEnregistrer	.addActionListener ( this );
		this.menuiFichierEnregistrerSous.addActionListener ( this );

		this.menuiFichierQuitter		.addActionListener ( this );
		this.menuiEditionAnnuler		.addActionListener ( this );
		this.menuiEditionRefaire		.addActionListener ( this );
		this.menuiEditionCopier			.addActionListener ( this );
		this.menuiEditionColler			.addActionListener ( this );
		this.menuiEditionCouper			.addActionListener ( this );

		this.setVisible( true );
	}

	public void actionPerformed ( ActionEvent e )
	{
		if(e.getSource() instanceof JMenuItem )
		{
			String info = ((JMenuItem) e.getSource()).getText();
			switch (info) 
			{
				case "Refaire":	this.ctrl.changerPanel("init");break;
				case "Quitter" : this.ctrl.fermeFrame();
			}
		}
	}
}

