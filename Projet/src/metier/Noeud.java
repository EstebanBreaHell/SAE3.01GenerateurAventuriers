public class Noeud
{
    private String nom;
    private int x;
    private int y;

    /**
        * Constructeur de la classe noeud
        * @param nom le nom de la noeud
        * @param x la coordonnée x de la Noeud
        * @param y la coordonnée y de la noeud
     */
    public Noeud(String nom, int x, int y)
    {
        this.nom = nom;
        this.x = x;
        this.y = y;
    }

    /**
        * Retourne le nom de la noeud
        * @return le nom de la noeud
     */
    public String getNom()
    {
        return nom;
    }
    /**
        * Retourne la coordonnée x de la noeud
        * @return la coordonnée x de la noeud
     */
    public int getX()
    {
        return x;
    }
    /**
        * Retourne la coordonnée y de la noeud
        * @return la coordonnée y de la noeud
     */
    public int getY()
    {
        return y;
    }

    /**
        *Retourne l'objet sous forme String
        * @return l'objet sous forme String
     */
    @override
    public String toString()
    {
        return nom + " (" + x + "," + y + ")";
    }
}