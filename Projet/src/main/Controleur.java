package main;

import ihm.*;
//import metier.*;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Font;

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

    public static void main(String[] args)
    {
        new Controleur();
    }
}
