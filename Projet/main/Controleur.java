package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import ihm.Frame;
import ihm.sectioninit.PanelImageInfo;
import metier.Arete;
import metier.Metier;
import metier.Noeud;


public class Controleur 
{
    private Metier metier;
    private Frame ihm;

    private String pathImg;
    

    public Controleur()
    {
        this.metier = new Metier(this);
        this.ihm = new Frame(this,"init");
    }

    public void resetGraph()
    {
        this.metier = new Metier(this);
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
    public void supprimArete(int n)
	{
        this.ihm.supprimArete(n);
    }

    public int getPositionAreteNoeudAl(Noeud n )
    {
        return this.metier.getLstNoeud().indexOf(n);
    }

    public void panelSelectionner(PanelImageInfo panelSelectionner)
    {
        this.ihm.panelSelectionner(panelSelectionner);
    }

    public List<JLabel> getLstHistorique()
    {
        return this.ihm.getLstHistorique();
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


    public void setNbJoueurMinDoubleArete( int m )
    {
        this.metier.setNbJoueurMinDoubleArete(m);
    }

    public void setNbJoueurMax( int m )
    {
        this.metier.setNbJoueurMax(m);
    }

    public void setNbWagonDebutPartie(int nbWagonsDebut) {
        this.metier.setNbWagonDebutPartie(nbWagonsDebut);
    }

    public void setNbWagonFinPartie(int nbWagonsFin) {
        this.metier.setNbWagonFinPartie(nbWagonsFin);
    }

    public void setNbPointsPlusLongChemin(int nbPoints){
        this.metier.setNbPointsPlusLongChemin(nbPoints);
    }


    public ArrayList<Noeud> getNoeudDispo(Noeud n)
    {
        return this.metier.getNoeudDispo(n);
    }

    public int getNbJoueurMax() {
        return this.metier.getNbJoueurMax();
    }

    public int getNbJoueurMinDoubleArete() {
        return this.metier.getNbJoueurMinDoubleArete();
    }

    public int getNbWagonDebutPartie() {
        return this.metier.getNbWagonDebutPartie();
    }

    public int getNbWagonFinPartie() {
        return this.metier.getNbWagonFinPartie();
    }

    public int getNbPointsPlusLongChemin() {
        return this.metier.getNbPointsPlusLongChemin();
    }

    public void addNoeud(String nom, int x, int y) { this.metier.creeNoeud(nom, x, y); }

    public void addArete(Noeud noeud1, Noeud noeud2,  String couleur, int longueur) { this.metier.creeArete(noeud1, noeud2, couleur, longueur ); }

    public String getNomNoeudPanelCreer() { return this.ihm.getNomNoeudPanelCreer(); }

    public void afficherErreurPanelCreer(String text) { this.ihm.afficherErreurPanelCreer(text);}




    public String getExtension(String filename) {
        return Optional.ofNullable(filename).filter(f -> f.contains(".")).map(f -> f.substring(filename.lastIndexOf(".") + 1)).get();
	}

	public int getXNomVille(int index)
	{
		return this.metier.getXNomVille(index);
	}

    public PanelImageInfo getPanelSelectionner()
    {
        return this.ihm.getPanelSelectionner();
    }

    public void imageToPanelGraphique(String path)
    {
        this.ihm.imageToPanelGraphique(path);
        this.pathImg = path;
    }

    public String getPathImg()
    {
        return this.pathImg;
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

    public BufferedImage createImage(JPanel panel) {

        int w = panel.getWidth();
        int h = panel.getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panel.paint(g);
        g.dispose();
        return bi;
    }


    public void supprArete(int arete)
    {
        this.metier.supprArete(arete);
    }

    public static void main(String[] args)
    {
        new Controleur();
    }

    public Noeud getNoeud(String nom) {
        return this.metier.getNoeud(nom);
    }
}
