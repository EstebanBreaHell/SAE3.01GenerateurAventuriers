package ihm;


import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JList;

import ihm.sectioncreer.PanelCreer;
import ihm.sectioninit.PanelImageInfo;
import ihm.sectioninit.PanelInit;

import main.Controleur;

import java.awt.*;


public class Frame extends JFrame
{
    private Controleur ctrl;
    
    protected static Font POLICE_DEFAUT = new Font("Broadway",Font.BOLD,50);
    private Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();

    

    private PanelInit   panelInit;
    private PanelCreer  panelCreer;

    private int longueur, hauteur;

    public Frame(Controleur ctrl, String nomPanel)
    {
        this.ctrl = ctrl;
        
        this.panelInit = new PanelInit(this.ctrl);
        this.panelCreer = new PanelCreer(this.ctrl);

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
    public void supprimArete(int n)
	{
        this.panelCreer.supprimArete(n);
    }

    public void changerPanel(String nomPanel)
    {

        switch (nomPanel) {
            case "editer":
                this.panelCreer = new PanelCreer(this.ctrl);
                this.add(this.panelCreer);                
            break;

            case "init" : 
                this.panelInit = new PanelInit(this.ctrl);
                this.add(this.panelInit);
            break;

            case "Import":
                this.panelInit = new PanelInit(this.ctrl);
                this.add(this.panelInit);

            break;
        
        }
    }

    
    public void fermeFrame(){this.dispose();}
    public Font getDefautFont() {return Frame.POLICE_DEFAUT;}
    public Dimension getDimensionEcran() {return new Dimension(this.longueur,this.hauteur);}
    public void panelSelectionner(PanelImageInfo panelSelectionner){ this.panelInit.panelSelectionner(panelSelectionner);}

    public void imageToPanelGraphique(String path)
    {
        this.panelCreer.imageToPanelGraphique(path);
    }

    public PanelImageInfo getPanelSelectionner()
    {
        return this.panelInit.getPanelSelectionner();
    }
    

    public void majIHM() { this.panelCreer.majIHM();  }

    public String getNomNoeudPanelCreer() { return this.panelCreer.getNomNoeudPanelCreer();}

    public void afficherErreurPanelCreer(String text) { this.panelCreer.afficherErreurPanelCreer(text);}
    public void majPanelImporter(){this.panelInit.majPanelImporter();}


}