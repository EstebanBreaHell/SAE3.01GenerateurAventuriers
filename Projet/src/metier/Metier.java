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

	public boolean creeArrete(String noeud1 , String noeud2 , String couleur, int wagon )
	{
		Noeud n1 = null;
		Noeud n2 = null;

		//TODO Faut regarder pour géré la couleur (Vus que les couleur sont déterminé de base dans le jeu on peux faire une liste déroulante dans l'ihm)
		Color c = Color.BLACK;
		

		/*
		*Vus que le joueur nous donne juste les Nom en string on vas regarder si ils éxiste dans la liste des noeud
		*TODO car j'ai la flemme peux être voir pour faire une liste déroulante dans l'ihm
		*/
		for( Noeud n : lstNoeud )
		{
			if( n.getNom().equals( noeud1 ) )
				n1 = n;
			if( n.getNom().equals( noeud2 ) )
				n2 = n;
		}

		if( n1 != null && n2 != null )
		{
			Arrete a = new Arrete( n1, n2, c ,wagon);
			lstArrete.add( a );

			return true;
		}
		else
			return false;
	}





}