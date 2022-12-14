package metier;

import java.util.ArrayList;

public class Noeud
{
    private String nom;
    private int posX;
    private int posY;
    private int nomX;
    private int nomY;

    private ArrayList<Arete> alArrete;

    /**
     * Constructeur de la classe noeud
     * @param nom  le nom de la noeud
     * @param posX la coordonnée posX de la Noeud
     * @param posY la coordonnée posY de la noeud
     */
    public Noeud(String nom, int posX, int posY, int nomX, int nomY)
    {
        this.nom  = nom;
        this.posX = posX;
        this.posY = posY;
        this.nomX = nomX;
        this.nomY = nomY;

        this.alArrete = new ArrayList<Arete>();
    }

    /**
     * Ajoute les arrete qui appartienne aux noeud
     * @param arrete
     */
    public void ajoutArete(Arete arrete){
        this.alArrete.add(arrete);
    }
    /**
     * Supprime une 
     * @param arrete
     */
    public void supprArete(Arete arrete){
        this.alArrete.remove(arrete);
    }

    public ArrayList<Arete> getArrayArete(){
        return this.alArrete;
    }
    /**
        * Retourne le nom de la noeud
        * @return le nom de la noeud
     */
    public String getNom()
    {
        return this.nom;
    }
    /**
        * Retourne la coordonnée posX de la noeud
        * @return la coordonnée posX de la noeud
     */
    public int getX()
    {
        return this.posX;
    }
    /**
        * Retourne la coordonnée posY de la noeud
        * @return la coordonnée posY de la noeud
     */
    public int getY()
    {
        return this.posY;
    }
    /**
        * Retourne la coordonnée posX du nom de la noeud
        * @return la coordonnée posX du nom de la noeud
     */
    public int getNomX()
    {
        return this.nomX;
    }
    /**
        * Retourne la coordonnée posY du nom de la noeud
        * @return la coordonnée posY du nom de la noeud
     */
    public int getNomY()
    {
        return this.nomY;
    }


    /**
        *Retourne l'objet sous forme String
        * @return l'objet sous forme String
     */
    @Override
    public String toString()
    {
        return this.nom + " (" + posX + "," + posY + ")";
    }
}