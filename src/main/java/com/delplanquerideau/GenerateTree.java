package com.delplanquerideau;

/**
 * Class permettant la génération de 2d-arbres
 */
public class GenerateTree {

    public TwoDimensionalTree generateRandomTree(int largeur, int hauteur, int nbFeuilles, double proportionCoupe, int minDimensionCoupe, double memeCouleurProb, int largeurLigne, long seed){
        return new TwoDimensionalTree(largeur, hauteur, nbFeuilles, proportionCoupe, minDimensionCoupe, memeCouleurProb, largeurLigne, seed);
    }
    
    public TwoDimensionalTree generateBetterRandomTree(){
        return null;
    }
}
