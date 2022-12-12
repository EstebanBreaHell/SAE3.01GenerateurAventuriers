package metier;

import java.awt.*;

public class Arrete {

    private Noeud ville;
    private Noeud ville2;

    private Color couleur;
    private int   wagon;


    /**
    * Constructeur de la classe arrete
    * @param ville la ville de départ
    * @param ville2 la ville d'arrivée
    * @param couleur la couleur de l'arrete
    * @param wagon le nombre de wagon
    */

    public Arrete(Noeud ville, Noeud ville2, Color couleur, int wagon) {
        this.ville = ville;
        this.ville2 = ville2;
        this.couleur = couleur;
        this.wagon = wagon;

        this.ville .addArrete(this);
        this.ville2.addArrete(this);
    }

    public Noeud getVille() {
        return this.ville;
    }

    public Noeud getVille2() {
        return this.ville2;
    }

    public Color getCouleur() {
        return this.couleur;
    }

    public int getWagon() {
        return this.wagon;
    }

    public void setVille(Noeud ville) {
        this.ville = ville;
    }

    public void setVille2(Noeud ville2) {
        this.ville2 = ville2;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public void setWagon(int wagon) {
        this.wagon = wagon;
    }


}
