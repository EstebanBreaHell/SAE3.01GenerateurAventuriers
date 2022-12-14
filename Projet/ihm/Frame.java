package ihm;


import javax.swing.JFrame;

import ihm.sectioncreer.PanelCreer;
import ihm.sectioninit.PanelImageInfo;
import ihm.sectioninit.PanelInit;

import main.Controleur;

import java.awt.*;


public class Frame extends JFrame
{
    protected static Font POLICE_DEFAUT = new Font("Broadway",Font.BOLD,50);
    private Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();

    private Controleur ctrl;

    private PanelInit   panelInit;
    private PanelCreer  panelCreer;

    private int longueur, hauteur;

    public Frame(Controleur ctrl, String nomPanel)
    {
        
        this.ctrl = ctrl;
		this.longueur = this.tailleMoniteur.width  - (int) (this.tailleMoniteur.width*0.01) ;
		this.hauteur  = this.tailleMoniteur.height - (int) (this.tailleMoniteur.height*0.06);

        this.setTitle("Générateur de plateau de jeu");
        this.setSize(longueur, hauteur);
        this.setLayout(new BorderLayout());
        this.setLocation(0,0);

        this.changerPanel(nomPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    public void changerPanel(String nomPanel)
    {

        switch (nomPanel) {
            case "editer":
                System.out.println("Bien recu pour editer");
                this.panelCreer = new PanelCreer(this.ctrl);
                this.add(this.panelCreer);                
            break;

            case "init" : 
                System.out.println("Bien recu pour Init");
                this.panelInit = new PanelInit(this.ctrl);
                this.add(this.panelInit);
            break;

            case "Import":
                System.out.println("Bien recu pour Import mais pas encore implémenté");
                this.panelInit = new PanelInit(this.ctrl);
                this.add(this.panelInit);

            break;
        
        }
    }

    public void fermeFrame(){this.dispose();}
    public Font getDefautFont() {return Frame.POLICE_DEFAUT;}
    public Dimension getDimensionEcran() {return new Dimension(this.longueur,this.hauteur);}
    public void panelSelectionner(PanelImageInfo panelSelectionner){ this.panelInit.panelSelectionner(panelSelectionner);}

    public void majIHM() { this.panelCreer.majIHM();  }
    public void majPanelImporter(){this.panelInit.majPanelImporter();}
}