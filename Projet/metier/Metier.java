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
    private ArrayList<CarteObjectif> lstCarteObjectif;
    private ArrayList<CarteWagon> lstCarteWagon;

	private int nbJoueurMax, nbJoueurMin, nbWagonsMax;

    public Metier( Controleur ctrl )
    {
        this.ctrl = ctrl;
		this.lstNoeud = new ArrayList<Noeud>();
		this.lstArete = new ArrayList<Arete>();
        this.lstCarteObjectif = new ArrayList<CarteObjectif>();
        this.lstCarteWagon = new ArrayList<CarteWagon>();

		this.nbJoueurMax = 0;
		this.nbJoueurMin = 0;
        this.nbWagonsMax = 0;
    }

    public void setNbJoueurMin( int m )
    {
        this.nbJoueurMin = m ;
    }

    public void setNbJoueurMax( int m )
    {
        this.nbJoueurMax =  m ;
    }

    public void setNbWagonsMax(int nbWagonsMax) {
        this.nbWagonsMax = nbWagonsMax;
    }

    public int getNbJoueurMax() {
        return nbJoueurMax;
    }

    public int getNbJoueurMin() {
        return nbJoueurMin;
    }

    public int getNbWagonsMax() {
        return nbWagonsMax;
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
            for( Arete a : lstArete )
            {
                pw.println ( "\t\t<arete>" );

                pw.println ( "\t\t<noeudArr>" + a.getNoeudDep().getNom() + " </noeudArr>" );
                pw.println ( "\t\t<noeudDep>" + a.getNoeudArr().getNom() + " </noeudDep>" );
               // pw.println ( "\t\t<couleurRGB>" + a.getCouleur().getRGB() + " </couleurRGB>" );
                pw.println ( "\t\t<wagons>" + a.getWagon() + " </wagons>" );

                pw.println ("\t\t</arete>");

            }

            for( CarteObjectif co : lstCarteObjectif )
            {
                pw.println ( "\t\t<carteObjectif>" );

                pw.println ( "\t\t<noeudArr>" + co.getNoeudDep().getNom() + " </noeudArr>" );
                pw.println ( "\t\t<noeudDep>" + co.getNoeudArr().getNom() + " </noeudDep>" );
                pw.println ( "\t\t<points>" + co.getNbPoints() + " </points>" );

                pw.println ("\t\t</carteObjectif>");

            }

            for( CarteWagon cw : lstCarteWagon )
            {
                pw.println ( "\t\t<carteWagon>" );

                pw.println ( "\t\t<couleur>" + cw.getCouleur() + " </couleur>" );

                pw.println ("\t\t</carteWagon>");

            }
            pw.println ( " \t</mappe>" );

            pw.println ( "\t<details>" );

            pw.println ( "\t\t<nbJoueurMin>" + this.nbJoueurMin + " </nbJoueurMin>" );
            pw.println ( "\t\t<nbJoueurMax>" + this.nbJoueurMax + " </nbJoueurMax>" );
            pw.println ( "\t\t<nbWagonsMax>" + this.nbWagonsMax + " </nbWagonsMax>" );

            pw.println ( "\t</details>" );

            pw.println("</infos>");

            pw.close();
        }
        catch ( IOException e) {
            e.printStackTrace();
        }

    }

    public void creeNoeud(String nom, int x, int y )
	{
		Noeud n = new Noeud( nom, x, y, x, y-10 );
		lstNoeud.add( n );
	}

	//En partant sur la base que l'on utilise une liste déroulante pour la couleur ET pour les ville
	public void creeArete(Noeud n1 , Noeud n2 , String c, int nbW )
	{
		Arete a = new Arete( n1, n2, c ,nbW);

        for(Arete a2 : lstArete ){
            System.out.println(a2.toString());
        }
		this.lstArete.add( a );

        for(Arete a2 : lstArete ){
            System.out.println(a2.toString());
        }
	}

    public void creeCarteObjectif( Noeud noeudDep, Noeud noeudArr, int nbW )
    {
        CarteObjectif co = new CarteObjectif( noeudDep, noeudArr, nbW );
        this.lstCarteObjectif.add( co );
    }

    public void creeCarteWagon( Color c )
    {
        CarteWagon cw = new CarteWagon( c );
        this.lstCarteWagon.add( cw );
    }

	public ArrayList<Noeud> getLstNoeud()
	{
		return this.lstNoeud;
	}

	public ArrayList<Arete> getLstArete()
	{
		return this.lstArete;
	}

	public void supprArete( int n )
	{
        Arete a = this.lstArete.get( n );
		a.supprArete();
		this.lstArete.remove( a );
	}

    public void supprArete( Arete a )
	{
		a.supprArete();
		this.lstArete.remove( a );
	}

	public void supprNoeud( int n ) {
        //Méthode pour supprimer le n indiqué en paramètre
        List<Arete> arrayListArretSupp = this.lstNoeud.get(n).getArrayArete();

        while(arrayListArretSupp.size() != 0)
        {
            supprArete(arrayListArretSupp.get(0));
        }

        lstNoeud.remove(n);

        for (Noeud no : lstNoeud) {
            System.out.println(no.toString());
        }
    }

    /* Méthode permettant de récupérer tous les noeuds disponibles ( qui n'ont pas d'aretes ) */
	/* A refaire car pue la merde */
    public ArrayList<Noeud> getNoeudDispo(Noeud n) 
	{
        ArrayList<Noeud> lstNoeudOccupe = new ArrayList<Noeud>();
        ArrayList<Noeud> lstNoeudDispo = new ArrayList<Noeud>();

        for (Arete a : n.getArrayArete())
		{
            if(!a.getNoeudDep().equals(n) && !lstNoeudOccupe.contains(a.getNoeudDep()))
				lstNoeudOccupe.add(a.getNoeudDep());
			if(!a.getNoeudArr().equals(n) && !lstNoeudOccupe.contains(a.getNoeudArr()))
				lstNoeudOccupe.add(a.getNoeudArr());
		}


        for (Noeud noeud : this.lstNoeud)
            if(!lstNoeudOccupe.contains(noeud) && !noeud.equals(n))
                lstNoeudDispo.add(noeud);

        return lstNoeudDispo;
    }
    /*------------------------------------*/

    public void majIHM()
	{
        this.ctrl.majIHM();
    }
}