package metier;

import java.util.ArrayList;

public class Noeud
{
    private String nom;
    private int x;
    private int y;
    private int nomX;
    private int nomY;

    private ArrayList<Arrete> alArrete;

    /**
        * Constructeur de la classe noeud
        * @param nom le nom de la noeud
        * @param x la coordonnée x de la Noeud
        * @param y la coordonnée y de la noeud
     */
    public Noeud(String nom, int x, int y, int nomX, int nomY)
    {
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.nomX = nomX;
        this.nomY = nomY;

        this.alArrete = new ArrayList<Arrete>();
    }

    public void addArrete(Arrete arrete){
        this.alArrete.add(arrete);
    }

    public void removeArrete(Arrete arrete){
        this.alArrete.remove(arrete);
    }

    public ArrayList<Arrete> getArrayArrete(){
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
        * Retourne la coordonnée x de la noeud
        * @return la coordonnée x de la noeud
     */
    public int getX()
    {
        return this.x;
    }
    /**
        * Retourne la coordonnée y de la noeud
        * @return la coordonnée y de la noeud
     */
    public int getY()
    {
        return this.y;
    }
    /**
        * Retourne la coordonnée x du nom de la noeud
        * @return la coordonnée x du nom de la noeud
     */
    public int getNomX()
    {
        return this.nomX;
    }
    /**
        * Retourne la coordonnée y du nom de la noeud
        * @return la coordonnée y du nom de la noeud
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
        return this.nom + " (" + x + "," + y + ")";
    }
}