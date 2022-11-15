package com.delplanquerideau;

/**
 * Class permettant la génération de 2d-arbres
 */
public class GenerateTree {

    /**
     * Génération d'un 2d-arbre selon la procédure énoncée
     * @param largeur
     * @param hauteur
     * @param nbFeuilles
     * @param proportionCoupe
     * @param minDimensionCoupe
     * @param memeCouleurProb
     * @param largeurLigne
     * @param seed
     * @return un 2d-arbre
     */
    public TwoDimensionalTree generateRandomTree(int largeur, int hauteur, int nbFeuilles, double proportionCoupe, int minDimensionCoupe, double memeCouleurProb, int largeurLigne, long seed){
        return new TwoDimensionalTree(largeur, hauteur, nbFeuilles, proportionCoupe, minDimensionCoupe, memeCouleurProb, largeurLigne, seed);
    }
    
    /**
     * Génération d'un 2d-arbre selon notre procédure
     * @return un 2d-arbre
     */
    public TwoDimensionalTree generateBetterRandomTree(){
        return null;
    }
}
