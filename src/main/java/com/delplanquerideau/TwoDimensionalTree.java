package com.delplanquerideau;

/**
 * Class représentant une variante du 2d-arbres (TwoDimensionalTree)
 */
public class TwoDimensionalTree {

    /**
     * Constructeur d'un 2d-arbre
     * @param largeur
     * @param hauteur
     * @param nbFeuilles
     * @param proportionCoupe
     * @param minDimensionCoupe
     * @param memeCouleurProb
     * @param largeurLigne
     */
    public TwoDimensionalTree(int largeur, int hauteur, int nbFeuilles, float proportionCoupe, int minDimensionCoupe, float memeCouleurProb, int largeurLigne){
        
    }

    /**
     * Choix de la feuille à diviser
     * @return la feuille à diviser
     */
    private Node chooseLeaf(){
        return null;
    }

    /**
     * Choix du sens de la coupe
     * @return le sens de coupe
     */
    private CuttingDirection chooseDivision(){
        return null;
    }

    /**
     * Choix de la couleur de la nouvelle feuille créée
     * @return la couleur
     */
    private Colors chooseColor(){
        return null;
    }

    /**
     * Génère le tableau au format PNG à partir d'un arbre
     */
    public void toImage(){
        
    }
}
