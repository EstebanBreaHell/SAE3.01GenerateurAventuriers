package main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;

import java.util.Optional;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import ihm.Frame;
import ihm.sectioninit.PanelImageInfo;


public class Controleur 
{
    //private Metier metier;
    private Frame ihm;


    public Controleur()
    {
        //this.metier = new Metier(this);
        this.ihm = new Frame(this,"init");
    }

    /**
     * Convertie une image un icon
     * 
     * @param path : Repertoire de l'image à redimensionner et à mettre au bon format
     * @param longueur : Est défini en pixel
     * @param largueur : Est défini en pixel
     * @return : L'image redimentionner est dans le bon format pour l'affichage
     */
    public static Icon imageToIcon(String path,int longueur, int largueur)
    {
        return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(longueur, largueur, Image.SCALE_DEFAULT));
    }

    public void changerPanel(String nomPanel)
    {
        this.ihm.fermeFrame();

        this.ihm = new Frame(this, nomPanel);
    }

    public void panelSelectionner(PanelImageInfo panelSelectionner)
    {
        this.ihm.panelSelectionner(panelSelectionner);
    }

    public Dimension getDimensionEcran()
    {
        return this.ihm.getDimensionEcran();
    }

    public void fermeFrame()
    {
        this.ihm.fermeFrame();
    }

    public Font getFont(){return this.ihm.getDefautFont();}

    public String getExtension(String filename) {
        return Optional.ofNullable(filename).filter(f -> f.contains(".")).map(f -> f.substring(filename.lastIndexOf(".") + 1)).get();
	}

    /*
    public Color getCouleurArrete(Arete arete)
    {
        return arete.getCouleur();
    }
    */

    public void majPanelImporter()
    {
        this.ihm.majPanelImporter();
    }


    public static void main(String[] args)
    {
        new Controleur();
    }
}
