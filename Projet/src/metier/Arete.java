package metier;



public class Arete {

    private Noeud noeudDep;
    private Noeud noeudArr;

    private String couleur;
    private int   nbWagon;


    /**
    * Constructeur de la classe arrete
    * @param noeudDep la noeudDep de départ
    * @param noeudArr la noeudDep d'arrivée
    * @param couleur la couleur de l'arrete
    * @param nbWagon le nombre de nbWagon
    */


    //ON RAPPELLE QUE noeudDep 1 -----> noeudDep 2
    public Arete(Noeud noeudDep, Noeud noeudArr, String couleur, int nbWagon) {
        this.noeudDep   = noeudDep;
        this.noeudArr   = noeudArr;
        this.couleur    = couleur;
        this.nbWagon      = nbWagon;

        this.noeudDep.ajoutArete(this);
        this.noeudArr.ajoutArete(this);
    }
    public void supprArete() {
        this.noeudDep.supprArete(this);
        this.noeudArr.supprArete(this);

        
    }
    public Noeud getNoeud() {
        return this.noeudDep;
    }

    public Noeud getNoeud2() {
        return this.noeudArr;
    }

    public String getCouleur() {
        return this.couleur;
    }

    public int getWagon() {
        return this.nbWagon;
    }

    public void setNoeud(Noeud noeudDep) {
        this.noeudDep = noeudDep;
    }

    public void setNoeud2(Noeud noeudArr) {
        this.noeudArr = noeudArr;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setWagon(int nbWagon) {
        this.nbWagon = nbWagon;
    }

    


}
