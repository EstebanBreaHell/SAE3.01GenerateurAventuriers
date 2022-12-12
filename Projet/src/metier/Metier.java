package metier;

import main.Controleur;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Metier
{
    
    private Controleur ctrl;

	private List<Noeud> lstNoeud;

    public Metier( Controleur ctrl )
    {
        this.ctrl = ctrl;
		this.lstNoeud = new ArrayList<Noeud>();
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




}