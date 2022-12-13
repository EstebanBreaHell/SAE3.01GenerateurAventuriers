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

    public Metier( Controleur ctrl )
    {
        this.ctrl = ctrl;
		this.lstNoeud = new ArrayList<Noeud>();
		this.lstArrete = new ArrayList<Arrete>();
    }

    public void ecrireXml()
	{
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream( "./sortie/carteTest.xml") );

			pw.println ( "<mappe>" );

			for( Noeud n : lstNoeud )
			{
				pw.println ( "\t<noeud nom=\"" + n.getNom() + "\">" );


				pw.println ("</noeud>");

			}
			pw.println ( " </mappe>" );
			pw.close();

		}
		catch ( IOException e) {
			e.printStackTrace();
		}

	}

	public void creeNoeud( String nom, int x, int y )
	{
		Noeud n = new Noeud( nom, x, y );
		lstNoeud.add( n );
	}

	

	//En partant sur la base que l'on utilise une liste déroulante pour la couleur ET pour les ville
	public void creeArrete(Noeud noeud1 , Noeud noeud2 , Color couleur, int wagon )
	{
		Color c = couleur;

		Arrete a = new Arrete( noeud1, noeud2, c ,wagon);

		lstArrete.add( a );
	}
	public ArrayList<Noeud> getLstNoeud()
	{
		return (ArrayList<Noeud>) lstNoeud;
	}

	public ArrayList<Arrete> getLstArrete()
	{
		return (ArrayList<Arrete>) lstArrete;
	}


	
	public void suppArrete( Arrete a )
	{
		a.removeArrete();
		lstArrete.remove( a );
	}

	public void suppNoeud( Noeud n )
	{
		List<Arrete> arayListArretSupp = n.getArrayArrete();

		for( Arrete a : arayListArretSupp )
		{
			suppArrete( a );
		}
		
		lstNoeud.remove( n );
	}







}