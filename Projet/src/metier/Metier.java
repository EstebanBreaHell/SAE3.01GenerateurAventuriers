package metier;

import main.Controleur;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Metier
{
    
    private Controleur ctrl;

	private List<Noeud> lstNoeud;
	private List<Arrete> lstArrete;

	private int nbJoueurMax, nbJoueurMin;

    public Metier( Controleur ctrl )
    {
        this.ctrl = ctrl;
		this.lstNoeud = new ArrayList<Noeud>();
		this.lstArrete = new ArrayList<Arrete>();

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
	public void creeArrete(Noeud n1 , Noeud n2 , Color c, int nbW )
	{
		Arrete a = new Arrete( n1, n2, c ,nbW);

		this.lstArrete.add( a );
	}
	public ArrayList<Noeud> getLstNoeud()
	{
		return (ArrayList<Noeud>) this.lstNoeud;
	}

	public ArrayList<Arrete> getLstArrete()
	{
		return (ArrayList<Arrete>) this.lstArrete;
	}


	
	public void supprArrete( Arrete a )
	{
		a.removeArrete();
		this.lstArrete.remove( a );
	}

	public void supprNoeud( Noeud n )
	{
		List<Arrete> arayListArretSupp = n.getArrayArrete();

		for( Arrete a : arayListArretSupp )
		{
			supprArrete( a );
		}
		
		this.lstNoeud.remove( n );
	}







}