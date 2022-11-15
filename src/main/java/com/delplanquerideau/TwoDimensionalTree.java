package com.delplanquerideau;

import java.util.Random;

/**
 * Class représentant une variante du 2d-arbres (TwoDimensionalTree)
 */
public class TwoDimensionalTree {

    private int actualNbFeuilles = 0;

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
    public TwoDimensionalTree(int largeur, int hauteur, int nbFeuilles, double proportionCoupe, int minDimensionCoupe, double memeCouleurProb, int largeurLigne, long seed){
        Node racine = new Node(chooseDivision(hauteur, largeur, seed, proportionCoupe), Colors.WHITE, hauteur, nbFeuilles, minDimensionCoupe, largeurLigne, null, null);
        System.out.println(racine.getHeight());
        System.out.println(racine.getWidth());
        // while(actualNbFeuilles < nbFeuilles){

        // }
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
    private CuttingDirection chooseDivision(int h, int w, long seed, double proportionCoupe){
        double probaX = w/(w+h);
        // double probaY = 1 - probaX;
        Random r = new Random(seed);
        double randomDouble = r.nextDouble();
        int startCut;
        int endCut;

        if(randomDouble < probaX){
            startCut = (int)(w*proportionCoupe);
            endCut = (int)(w*(1-proportionCoupe));
            
            int cut = r.nextInt((endCut - startCut) + 1) + startCut;
            
            return new CuttingDirection(Direction.X, cut);
        }else {
            startCut = (int)(h*proportionCoupe);
            endCut = (int)(h*(1-proportionCoupe));

            int cut = r.nextInt((endCut - startCut) + 1) + startCut;

            return new CuttingDirection(Direction.Y, cut);
        }
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
