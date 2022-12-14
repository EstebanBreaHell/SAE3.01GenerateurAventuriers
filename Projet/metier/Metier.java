package metier;

import main.Controleur;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;


public class Metier
{
    
    private Controleur ctrl;

	private ArrayList<Noeud> lstNoeud;
	private ArrayList<Arete> lstArete;

	private int nbJoueurMax, nbJoueurMin;

    public Metier( Controleur ctrl )
    {
        this.ctrl = ctrl;
		this.lstNoeud = new ArrayList<Noeud>();
		this.lstArete = new ArrayList<Arete>();

		this.nbJoueurMax = 0;
		this.nbJoueurMin = 0;
    }

    public void setNbJoueurMin( int m )
    {
        this.nbJoueurMin = m ;
    }

    public void setNbJoueurMax( int m )
    {
        this.nbJoueurMax =  m ;
    }

    public void ecrireXml()
    {
        try
        {
            PrintWriter pw = new PrintWriter( new FileOutputStream( "./sortie/carteTest.xml") );

            pw.println("<infos>");

            pw.println ( "\t<mappe>" );

            for( Noeud n : lstNoeud )
            {
                pw.println ( "\t\t<noeud nom=\"" + n.getNom() + "\">" );

                pw.println ( "\t\t\t<coordonees x=\"" + n.getX () + "\" y=\"" + n.getY () + "\"/>" );
                pw.println ( "\t\t\t<coordoneesNom x=\"" + n.getNomX() + "\" y=\"" + n.getNomY () + "\"/>" );

                pw.println ("\t\t</noeud>");

            }
            pw.println ( " \t</mappe>" );

            pw.println ( "\t<details>" );

            pw.println ( "\t\t<nbJoueurMin>" + this.nbJoueurMin + " </nbJoueurMin>" );
            pw.println ( "\t\t<nbJoueurMax>" + this.nbJoueurMax + " </nbJoueurMax>" );

            pw.println ( "\t</details>" );

            pw.println("</infos>");

            pw.close();
        }
        catch ( IOException e) {
            e.printStackTrace();
        }

    }

	public void creeNoeud( String nom, int x, int y )
	{
		Noeud n = new Noeud( nom, x, y, x, y-10 );
		lstNoeud.add( n );
	}

	//En partant sur la base que l'on utilise une liste déroulante pour la couleur ET pour les ville
	public void creeArete(Noeud n1 , Noeud n2 , Color c, int nbW )
	{
		Arete a = new Arete( n1, n2, c ,nbW);
		this.lstArete.add( a );
	}

	public ArrayList<Noeud> getLstNoeud()
	{
		return this.lstNoeud;
	}

	public ArrayList<Arete> getLstArete()
	{
		return this.lstArete;
	}

	public void supprArete( Arete a )
	{
		a.supprArete();
		this.lstArete.remove( a );
	}

	public void supprNoeud( Noeud n )
	{
		List<Arete> arrayListArretSupp = n.getArrayArete();
		for( Arete a : arrayListArretSupp )
			supprArete( a );
		
		this.lstNoeud.remove( n );
	}

    /* Méthode permettant de récupérer tous les noeuds disponibles ( qui n'ont pas d'aretes ) */
    public ArrayList<Noeud> getNoeudDispo(Noeud n) {
        ArrayList<Noeud> lstNoeudOccupe = new ArrayList<Noeud>();
        ArrayList<Noeud> lstNoeudDispo = new ArrayList<Noeud>();

        for (Arete a : n.getArrayArete())
            if(a.getNoeud() != n || a.getNoeud2() != n) {
                lstNoeudOccupe.add(a.getNoeud());
                lstNoeudOccupe.add(a.getNoeud2());
            }

        for (Noeud noeud : this.lstNoeud)
            if(!lstNoeudOccupe.contains(noeud))
                lstNoeudDispo.add(noeud);


        return lstNoeudDispo;
    }
    /*------------------------------------*/

    public void majIHM(){
        this.ctrl.majIHM();
    }
}