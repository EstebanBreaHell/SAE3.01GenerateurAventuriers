package main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;

import java.util.ArrayList;
import java.util.Optional;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;

import ihm.Frame;
import ihm.sectioninit.PanelImageInfo;
import metier.Arete;
import metier.Metier;
import metier.Noeud;
import ihm.sectioncreer.PanelGraphique;


public class Controleur 
{
    private Metier metier;
    private Frame ihm;


    public Controleur()
    {
        this.metier = new Metier(this);
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

    public ArrayList<Noeud> getLstNoeud()
    {
        return this.metier.getLstNoeud();
    }

    public ArrayList<Arete> getLstArete()
    {
        return this.metier.getLstArete();
    }
    public void setNbJoueurMin( int m )
    {
        this.metier.setNbJoueurMin(m);
    }

    public void setNbJoueurMax( int m )
    {
        this.metier.setNbJoueurMax(m);
    }

    public void setNbWagonsMax(int nbWagonsMax) {
        this.metier.setNbWagonsMax(nbWagonsMax);
    }

    public ArrayList<Noeud> getNoeudDispo(Noeud n)
    {
        return this.metier.getNoeudDispo(n);
    }

    public int getNbJoueurMax() {
        return this.metier.getNbJoueurMax();
    }

    public int getNbJoueurMin() {
        return this.metier.getNbJoueurMin();
    }

    public int getNbWagonsMax() {
        return this.metier.getNbWagonsMax();
    }

    public void addNoeud(String nom, int x, int y) { this.metier.creeNoeud(nom, x, y); }

    public void addArete(Noeud noeud1, Noeud noeud2,  String couleur, int longueur) { this.metier.creeArete(noeud1, noeud2, couleur, longueur ); }

    public String getNomNoeudPanelCreer() { return this.ihm.getNomNoeudPanelCreer(); }

    public void afficherErreurPanelCreer(String text) { this.ihm.afficherErreurPanelCreer(text);}


    public String getExtension(String filename) {
        return Optional.ofNullable(filename).filter(f -> f.contains(".")).map(f -> f.substring(filename.lastIndexOf(".") + 1)).get();
	}

    public PanelImageInfo getPanelSelectionner()
    {
        return this.ihm.getPanelSelectionner();
    }

    public void imageToPanelGraphique(String path)
    {
        this.ihm.imageToPanelGraphique(path);
    }

    /*
    public Color getCouleurArrete(Arete arete)
    {
        return arete.getCouleur();
    }
    */

    public void majIHM(){this.ihm.majIHM();}
    public void majPanelImporter(){this.ihm.majPanelImporter();}

    public void supprNoeud(int noeud)
    {
        this.metier.supprNoeud(noeud);
    }

    public static void main(String[] args)
    {
        new Controleur();
    }
}
