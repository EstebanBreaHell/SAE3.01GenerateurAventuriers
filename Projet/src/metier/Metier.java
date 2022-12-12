package metier;

import main.Controleur;

public class Metier
{
    
    public Controleur ctrl;

    public Metier( Controleur ctrl )
    {
        this.ctrl = ctrl;
    }

    public void ecrireXml()
	{
		try
		{
			PrintWriter pw = new PrintWriter ( new FileOutputStream( "/sortie/DepartementV3.xml") );
			String regAct = ensDept.get ( 0 ).getNomRegion ();

			pw.println ( "<liste>" );
			pw.println ( "\t<region nom=\"" + regAct + "\">" );

			for( Dept d : ensDept )
			{
				if( ! regAct.equals ( d.getNomRegion () ))
				{
					pw.println ("</region>");
					regAct = d.getNomRegion ();
					pw.println ( "\t<region nom=\"" + regAct + "\">" );

				}
				if( regAct.equals ( d.getNomRegion () ))
				{
					pw.println ( "\t\t<dept numero=\"" + d.getNumero () + "\">" );
					pw.println ( "\t\t\t<coordonees x=\"" + d.getX () + "\" y=\"" + d.getY () + "\"/>" );
					pw.println ( "\t\t\t<nomDept>" + d.getNomDept () + " </nomDept>" );
					pw.println ( "\t\t\t<prefecture>" + d.getNomPrefecture () + " </prefecture>" );
					pw.println ( "\t\t\t<population>" + d.getPopulation () + " </population>" );
					pw.println ( "\t\t\t<superficie>" + d.getSuperficie () + " </superficie>" );
					pw.println ("\t\t</dept>");
				}

			}
			pw.println ("</region>");
			pw.println ( " </liste>" );
			pw.close();

		}
		catch ( IOException e) {
			e.printStackTrace();
		}


	}


}