package metier;

import main.Controleur;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.Color;

 
import java.io.*;
import org.jdom2.*;
import org.jdom2.input.*;



public class Metier
{
    
    private Controleur ctrl;

	private ArrayList<Noeud> lstNoeud;
	private ArrayList<Arete> lstArete;
    private ArrayList<CarteObjectif> lstCarteObjectif;
    private ArrayList<CarteWagon> lstCarteWagon;

    private ArrayList<String> lstCouleurJoueur;
    private HashMap<String, Integer> hsmCouleurWagon;
    private HashMap<String, String> hsmImageWagon;
    private String versoCarteObjectif;
    private String versoCarteWagon;

    
	private int nbJoueurMax, nbJoueurMinDoubleArete , nbWagonDebutPartie ,nbWagonFinPartie , nbPointsPlusLongChemin ;

    public Metier( Controleur ctrl )
    {
        this.ctrl = ctrl;
		this.lstNoeud = new ArrayList<Noeud>();
		this.lstArete = new ArrayList<Arete>();
        this.lstCarteObjectif = new ArrayList<CarteObjectif>();
        this.lstCarteWagon = new ArrayList<CarteWagon>();


        this.lstCouleurJoueur = new ArrayList<String>();
        this.hsmCouleurWagon = new HashMap<String, Integer>();
        this.hsmImageWagon = new HashMap<String, String>();

        

		this.nbJoueurMax = 5;
		this.nbJoueurMinDoubleArete = 4;
        this.nbWagonDebutPartie = 45;
        this.nbWagonFinPartie = 2;
        this.nbPointsPlusLongChemin = 10;
    }



    public void setNbJoueurMinDoubleArete( int m )
    {
        this.nbJoueurMinDoubleArete = m ;
    }

    public void setNbJoueurMax( int m )
    {
        this.nbJoueurMax =  m ;
    }

    public void setNbWagonDebutPartie(int nbWagonDebutPartie) {
        this.nbWagonDebutPartie = nbWagonDebutPartie;
    }

    public void setNbWagonFinPartie(int nbWagonFinPartie) {
        this.nbWagonFinPartie = nbWagonFinPartie;
    }

    public void setNbPointsPlusLongChemin(int nbPointsPlusLongChemin) {
        this.nbPointsPlusLongChemin = nbPointsPlusLongChemin;
    }

    public void setLstCouleurJoueur(ArrayList<String> lstCouleurJoueur) {
        this.lstCouleurJoueur = lstCouleurJoueur;
    }
    
    public void setHsmCouleurWagon(HashMap<String, Integer> lstCouleurWagon) {
        this.hsmCouleurWagon = lstCouleurWagon;
    }

    public void setHsmImageWagon(HashMap<String, String> hsmImageWagon) {
        this.hsmImageWagon = hsmImageWagon;
    }

    public void setVersoCarteWagon(String versoCarteWagon){
        this.versoCarteWagon = versoCarteWagon;
    }




    public int getNbJoueurMax() {
        return nbJoueurMax;
    }

    public int getNbJoueurMinDoubleArete() {
        return nbJoueurMinDoubleArete;
    }

    public int getNbWagonDebutPartie() {
        return nbWagonDebutPartie;
    }

    public int getNbWagonFinPartie() {
        return nbWagonFinPartie;
    }

    public int getNbPointsPlusLongChemin() {
        return nbPointsPlusLongChemin;
    }

    public ArrayList<String> getLstCouleurJoueur() {
        return lstCouleurJoueur;
    }

    public HashMap<String, Integer> getHsmCouleurWagon() {
        return hsmCouleurWagon;
    }

    public HashMap<String, String> getHsmImageWagon() {
        return hsmImageWagon;
    }

    public String getVersoCarteWagon(){
        return this.versoCarteWagon;
    }

    public void ecrireXml()
    {
        ArrayList<String> lslTmpCouleurWagon = new ArrayList<String>();
        for( Arete a : this.lstArete )
        {
            lslTmpCouleurWagon.add(a.getCouleur());
        }
        try
        {
            PrintWriter pw = new PrintWriter( new FileOutputStream( "./data_user/carteTest.xml") );
            pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
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

                pw.println ( "\t\t\t<noeudArr>" + this.lstNoeud.indexOf(a.getNoeudDep()) + "</noeudArr>" );
                pw.println ( "\t\t\t<noeudDep>" + this.lstNoeud.indexOf(a.getNoeudArr())+ "</noeudDep>" );
                pw.println ( "\t\t\t<couleur>" + a.getCouleur() + "</couleur>" );
               // pw.println ( "\t\t<couleurRGB>" + a.getCouleur().getRGB() + " </couleurRGB>" );
                pw.println ( "\t\t\t<wagons>" + a.getWagon() + "</wagons>" );

                pw.println ("\t\t</arete>");

            }

            /* 
            for( CarteObjectif co : lstCarteObjectif )
            {
                pw.println ( "\t\t<carteObjectif>" );

                pw.println ( "\t\t\t<noeudArr>"+ co.getNoeudDep().getNom() + "</noeudArr>" );
                pw.println ( "\t\t\t<noeudDep>"+ co.getNoeudArr().getNom() + "</noeudDep>" );
                pw.println ( "\t\t\t<points>"+ co.getNbPoints() + "</points>" );

                pw.println ("\t\t</carteObjectif>");

            }
            */

            for(String c : this.hsmCouleurWagon.keySet())
            {
                if(lslTmpCouleurWagon.contains(c) || c.equals("Joker"))
                {
                    pw.println ( "\t\t<carteWagon>" );

                    pw.println ( "\t\t\t<couleur>" + c + "</couleur>" );
                    pw.println ( "\t\t\t<nombre>" + this.hsmCouleurWagon.get(c) + "</nombre>" );
                    pw.println ( "\t\t\t<recto>" + this.hsmImageWagon.get(c) + "</recto>" );


                    pw.println ("\t\t</carteWagon>");
                }
            }

            /* 
            for( CarteWagon cw : lstCarteWagon )
            {
                pw.println ( "\t\t<carteWagon>" );

                pw.println ( "\t\t\t<couleur>" + cw.getCouleur() + "</couleur>" );

                pw.println ("\t\t</carteWagon>");

            }

            */
           

            pw.println ( "\t<details>" );

            pw.println ( "\t\t<nbJoueurMinDoubleArete>"+ this.nbJoueurMinDoubleArete + "</nbJoueurMinDoubleArete>" );
            pw.println ( "\t\t<nbJoueurMax>"+ this.nbJoueurMax + "</nbJoueurMax>" );
            pw.println ( "\t\t<nbWagonDebutPartie>"+ this.nbWagonDebutPartie + "</nbWagonDebutPartie>" );
            pw.println ( "\t\t<nbWagonFinPartie>"+ this.nbWagonFinPartie + "</nbWagonFinPartie>" );
            pw.println ( "\t\t<nbPointsPlusLongChemin>"+ this.nbPointsPlusLongChemin + "</nbPointsPlusLongChemin>" );
            pw.println ( "\t\t<versoCarteWagon>"+ this.versoCarteWagon + "</versoCarteWagon>" );
            pw.println ( "\t\t<image>"+ this.ctrl.getPathImg() + "</image>" );
            //getPathImg

            pw.println ( "\t</details>" );
            pw.println ( "\t</mappe>" );
            pw.println("</infos>");

            pw.close();
        }
        catch ( IOException e) {
            e.printStackTrace();
        }

    }

    
    public void lireXml()
    {
        org.jdom2.Document document;
        Element racine;


        SAXBuilder sxb = new SAXBuilder();
        try {
            // On crée un nouveau document JDOM avec en argument le
            //fichier XML
            // Le parsing est terminé
            document = sxb.build(new File( "donnee/xml/carteTest.xml" ));
        } catch (Exception e)
        {
            
            System.out.println("ça crash");
            return;
        }

        this.lstNoeud = new ArrayList<Noeud>();
		this.lstArete = new ArrayList<Arete>();
        this.lstCarteObjectif = new ArrayList<CarteObjectif>();
        this.lstCarteWagon = new ArrayList<CarteWagon>();
        this.hsmCouleurWagon = new HashMap<String, Integer>();
        this.hsmImageWagon = new HashMap<String, String>();

        racine = document.getRootElement();

        System.out.println("Test1");

        // List listVilles = racine.getChildren("noeud");
        // Iterator i = listVilles.iterator();
        List<Element> lstNoeud = racine.getChildren ( "mappe" ).get(0).getChildren("noeud");
        List<Element> lstArete = racine.getChildren ( "mappe" ).get(0).getChildren("arete");
        //List<Element> lstObjectif = racine.getChildren ( "mappe" ).get(0).getChildren("carteObjectif");
        List<Element> lstWagon = racine.getChildren ( "mappe" ).get(0).getChildren("carteWagon");
        List<Element> lstInformation = racine.getChildren ( "mappe" ).get(0).getChildren("details");


        
        System.out.println("Test2");
        for(Element courant : lstNoeud) {
            
            String nomVille = courant.getAttributeValue("nom");
            int x = Integer.parseInt(courant.getChild("coordonees").getAttributeValue("x"));
            int y = Integer.parseInt(courant.getChild("coordonees").getAttributeValue("y"));
            int nomX = Integer.parseInt(courant.getChild("coordoneesNom").getAttributeValue("x"));
            int nomY = Integer.parseInt(courant.getChild("coordoneesNom").getAttributeValue("y"));

            //System.out.println("Noeud : " + nomVille + " x : " + x + " y : " + y + " nomX : "+ nomX + " nomY : "+ nomY);

            this.creeNoeud(nomVille, x, y, nomX, nomY);
        }

        for(Element w : lstWagon)
        {
            String c = w.getChild("couleur").getText();
            
            this.hsmCouleurWagon.put(c, Integer.parseInt(w.getChild("nombre").getText()));
            this.hsmImageWagon.put(c, w.getChild("recto").getText());


            //this.creeCarteWagon(new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2])));
            
        }

        for(Element a : lstArete)
        {
            String nomVille1 = a.getChild("noeudArr").getText ();
            String nomVille2 = a.getChild("noeudDep").getText ();
            String couleur = a.getChild("couleur").getText();
            int nbW = Integer.parseInt(a.getChild("wagons").getText());

            //System.out.println("Arete : " + nomVille1 + " " + nomVille2 + " " + couleur + " " + nbW + " " + estDouble);
            Noeud n1 = this.lstNoeud.get(Integer.parseInt(nomVille1));
            Noeud n2 = this.lstNoeud.get(Integer.parseInt(nomVille2));
            this.creeArete(n1, n2, couleur,nbW);
        }

        /* 
        for(Element o : lstObjectif)
        {
            String nomVille1 = o.getChild("noeudArr").getText ();
            String nomVille2 = o.getChild("noeudDep").getText ();
            int points = Integer.parseInt(o.getChild("points").getText());

           // System.out.println("Objectif : " + nomVille1 + " " + nomVille2 + " " + points);


            this.creeCarteObjectif(this.getNoeud(nomVille1), this.getNoeud(nomVille2), points);
        }
        */
        
        for(Element d : lstInformation)
        {
            this.nbJoueurMinDoubleArete = Integer.parseInt(d.getChild("nbJoueurMinDoubleArete").getText());
            this.nbJoueurMax = Integer.parseInt(d.getChild("nbJoueurMax").getText());
            this.nbWagonDebutPartie = Integer.parseInt(d.getChild("nbWagonDebutPartie").getText());
            this.nbWagonFinPartie = Integer.parseInt(d.getChild("nbWagonFinPartie").getText());
            this.nbPointsPlusLongChemin = Integer.parseInt(d.getChild("nbPointsPlusLongChemin").getText());
            System.out.println("Image : "+ d.getChild("image").getText());
            this.ctrl.imageToPanelGraphique(d.getChild("image").getText());
        }




        
    

    }
    
    public void creeNoeud(String nom, int x, int y, int nomX , int nomY)
	{
		Noeud n = new Noeud( nom, x, y, nomX, nomY);
		lstNoeud.add( n );
	}

    public void creeNoeud(String nom, int x, int y )
	{
		Noeud n = new Noeud( nom, x, y, x-15, y+15 );
		lstNoeud.add( n );
	}

	//En partant sur la base que l'on utilise une liste déroulante pour la couleur ET pour les ville
	public void creeArete(Noeud n1 , Noeud n2 , String c, int nbW )
	{
		Arete a = new Arete( n1, n2, c ,nbW);
        for (Arete b : lstArete)
        {
            if ((b.getNoeudArr().equals(n1) && b.getNoeudDep().equals(n2))||
                (b.getNoeudArr().equals(n2) && b.getNoeudDep().equals(n1)))
            {
                b.setEstDouble(true);
                a.setEstDouble(true);
                b.setAreteDouble(a);
                a.setAreteDouble(b);
            }
        }

		this.lstArete.add( a );

        
    
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

        if(a.getEstDouble())
        {
            for (Arete b : lstArete)
            {
                if ((b.getNoeudArr().equals(a.getNoeudArr()) && b.getNoeudDep().equals(a.getNoeudDep()))||
                    (b.getNoeudArr().equals(a.getNoeudDep()) && b.getNoeudDep().equals(a.getNoeudArr())))
                {
                    b.setEstDouble(false);
                }
            }
        }
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
            System.out.print(this.lstArete.indexOf(arrayListArretSupp.get(0)));
            this.ctrl.supprimArete(this.lstArete.indexOf(arrayListArretSupp.get(0)));
            supprArete(arrayListArretSupp.get(0));
        }

        lstNoeud.remove(n);
    }

    /* Méthode permettant de récupérer tous les noeuds disponibles ( qui n'ont pas d'aretes ) */
	/* A refaire car pue la merde */
    public ArrayList<Noeud> getNoeudDispo(Noeud n) 
	{
        ArrayList<Noeud> lstNoeudOccupe = new ArrayList<Noeud>();
        ArrayList<Noeud> lstNoeudDispo = new ArrayList<Noeud>();

        for (Arete a : n.getArrayArete()) 
		{
            /*
            if((!a.getNoeudDep().equals(n) || a.getEstDouble() )&& !lstNoeudOccupe.contains(a.getNoeudDep()) )
				lstNoeudOccupe.add(a.getNoeudDep());
			if((!a.getNoeudArr().equals(n) || a.getEstDouble() )&& !lstNoeudOccupe.contains(a.getNoeudArr()))
				lstNoeudOccupe.add(a.getNoeudArr());
            */
            if(a.getEstDouble())
            {
                lstNoeudOccupe.add(a.getNoeudDep());
                lstNoeudOccupe.add(a.getNoeudArr());
            }
		}


        for (Noeud noeud : this.lstNoeud)
            if(!lstNoeudOccupe.contains(noeud) && !noeud.equals(n))
                lstNoeudDispo.add(noeud);

        return lstNoeudDispo;
    }

	public int getXNomVille(int index)
	{
		return this.lstNoeud.get(index).getNomX();
	}
    /*------------------------------------*/

    public void majIHM()
	{
        this.ctrl.majIHM();
    }

    public Noeud getNoeud(String nom) {
        for (Noeud n : this.lstNoeud)
            if (n.getNom().equals(nom))
                return n;

        return null;
    }
}