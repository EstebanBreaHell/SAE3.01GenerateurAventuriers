package ihm;

import main.Controleur;
import javax.swing.JFrame;

import ihm.sectioncree.PanelCree;

import java.awt.*;


public class Frame extends JFrame
{
    protected static Font POLICE_DEFAUT = new Font("Broadway",Font.BOLD,50);
    private Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();

    private Controleur ctrl;
    private PanelChoixMode panelChoixMode;
    private PanelCree panelCree;
    private int longueur, hauteur;

    public Frame(Controleur ctrl, String nomPanel)
    {
        
        this.ctrl = ctrl;
		this.longueur = this.tailleMoniteur.width  - (int) (this.tailleMoniteur.width*0.01) ;
		this.hauteur  = this.tailleMoniteur.height - (int) (this.tailleMoniteur.height*0.06);

        this.setTitle("Générateur de graphe");
        this.setSize(longueur, hauteur);
        this.setLayout(new BorderLayout());
        this.setLocation(0,0);

        this.changerPanel(nomPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(new MaBarreMenu(this,this.ctrl));

        this.setVisible(true);
    }

    public void changerPanel(String nomPanel)
    {

        switch (nomPanel) {
            case "cree":
                System.out.println("Bien recu pour crée");
                this.panelCree = new PanelCree(this.ctrl);
                this.add(this.panelCree);                
            break;

            case "init" : 
                System.out.println("Bien recu pour Init");
                this.panelChoixMode = new PanelChoixMode(this.ctrl);
                this.add(this.panelChoixMode);
            break;

            case "Import":
                System.out.println("Bien recu pour Import mais pas encore implémenté");
                this.panelChoixMode = new PanelChoixMode(this.ctrl);
                this.add(this.panelChoixMode);

            break;
        
        }
    }

    public void fermeFrame(){this.dispose();}
    public Font getDefautFont() {return Frame.POLICE_DEFAUT;}
    public Dimension getDimensionEcran() {return new Dimension(this.longueur,this.hauteur);}
}