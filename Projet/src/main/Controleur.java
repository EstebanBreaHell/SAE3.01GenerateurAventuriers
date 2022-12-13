package main;

import ihm.*;
//import metier.*;
import metier.Arete;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.util.Optional;
import java.awt.Font;
import java.awt.Color;

public class Controleur 
{
    //private Metier metier;
    private Frame ihm;


    public Controleur()
    {
        //this.metier = new Metier(this);
        this.ihm = new Frame(this,"init");
    }

    public static Icon imageToIcon(String path)
    {
        return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
    }

    public void changerPanel(String nomPanel)
    {
        this.ihm.fermeFrame();

        this.ihm = new Frame(this, nomPanel);
    }

    public Dimension getDimensionEcran()
    {
        return this.ihm.getDimensionEcran();
    }

    public void fermeFrame()
    {
        this.ihm.fermeFrame();
    }

    public Font getFont(){return this.ihm.getDefautFont();}

    public Optional<String> getExtension(String filename) {
		return Optional.ofNullable(filename)
		  .filter(f -> f.contains("."))
		  .map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}

    public Color getCouleurArrete(Arete arete)
    {
        return null; 
    }


    public static void main(String[] args)
    {
        new Controleur();
    }
}
