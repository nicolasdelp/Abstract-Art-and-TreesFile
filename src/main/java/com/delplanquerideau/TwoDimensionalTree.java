package com.delplanquerideau;

import java.awt.*;
import java.util.Random;

/**
 * Class représentant une variante du 2d-arbres (TwoDimensionalTree)
 */
public class TwoDimensionalTree {

    private Node racine;
    private int largeurLigne;
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
        this.largeurLigne = largeurLigne;
        this.racine = new Node(chooseDivision(hauteur, largeur, seed, proportionCoupe), Color.WHITE, 0,largeur, 0, hauteur);
        // System.out.println(racine.getHeight());
        // System.out.println(racine.getWidth());
        // System.out.println(racine.getColor());
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
        Image img = new Image(this.racine.getWidth(), this.racine.getHeight());
        img.setRectangle(this.racine.getStartX(), this.racine.getEndX(), this.racine.getStartY(), this.racine.getEndY(), this.racine.getColor()); // On dessine la racine (un tableau blanc)

        Node node = this.racine;
        if(!node.getIsALeaf()){ // SI c'est pas une feuille
            DrawNode(img, node);
        } else{
            DrawLeaf(img, node);
        }
            // s = s + "(" + preorder(n.leftNode) + ")";
            //         s = s + "(" preorder(n.rightNode) + ")";
            //     }

        try {
            img.save("tableau.png");
        } catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Dessine les lignes grises pour les parents
     * @param img
     * @param node
     */
    public void DrawNode(Image img, Node node){
        int position = node.getCuttingDirection().getPosition();

        if(node.getCuttingDirection().getDirection() == Direction.Y){
            img.setRectangle(position-(largeurLigne/2), position+(largeurLigne/2), node.getStartY(), node.getEndY(), Color.GRAY);
        }else {
            img.setRectangle(node.getStartX(), node.getEndX(), position-(largeurLigne/2), position+(largeurLigne/2), Color.GRAY);
        }

    }

    /**
     * Dessine les rectangles de couleurs
     * @param img
     * @param node
     */
    public void DrawLeaf(Image img, Node node){
        img.setRectangle(node.getStartX(), node.getEndX(), node.getStartY(), node.getEndY(), node.getColor());
    }
    
    
    // public String preorder(Node n, String s){
    //     s = s + n.toString; 
    //     if(!n.isLeaf()){
    //         s = s + "(" + preorder(n.leftNode) + ")";
    //         s = s + "(" preorder(n.rightNode) + ")";
    //     } 
    //     return s;
    // }
}
