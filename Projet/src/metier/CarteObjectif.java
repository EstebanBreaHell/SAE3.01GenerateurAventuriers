package metier;

import java.awt.*;

public class CarteObjectif {

    private Noeud noeud1;
    private Noeud noeud2;
    private int nbPoints;


    public CarteObjectif(Noeud noeud1, Noeud noeud2, int nbPoints) {
        this.noeud1 = noeud1;
        this.noeud2 = noeud2;
        this.nbPoints = nbPoints;
    }

    public Noeud getNoeud1() {
        return this.noeud1;
    }

    public Noeud getNoeud2() {
        return this.noeud2;
    }

    public int getNbPoints() {
        return this.nbPoints;
    }


}
