package com.delplanquerideau;

import java.awt.*;

/**
 * Class représentant un noeud dans un 2d-arbres
 */
public class Node {
    private CuttingDirection cuttingDirection;
    private Color color;
    private int startX; //TODO : classe Zone/Region ? qui calcule le weight?
    private int endX;
    private int startY;
    private int endY;
    private Node leftNode;
    private Node rightNode;
    private boolean isALeaf = false;

    private double weight;

    /**
     * Constructeur d'un noeud dans un arbre
     * @param cuttingDirection direction de coupe
     * @param color couleur du noeud
     * @param startX pixel de départ en X
     * @param endX pixel de fin en X
     * @param startY pixel de départ en Y
     * @param endY pixel de fin en Y
     * @param leftNode noeud enfant à gauche
     * @param rightNode noeud enfant à droite
     */
    public Node(CuttingDirection cuttingDirection, Color color, int startX, int endX, int startY, int endY, Node leftNode, Node rightNode){
        this.cuttingDirection = cuttingDirection;
        this.color = color;
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        if(this.leftNode == null && this.rightNode == null){
            this.isALeaf = true;
        }
    }

    public CuttingDirection getCuttingDirection(){
        return this.cuttingDirection;
    }

    public Color getColor(){
        return this.color;
    }

    public int getStartX() {
        return startX;
    }

    public int getEndX() {
        return endX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndY() {
        return endY;
    }

    public Node getRightNode(){
        return rightNode;
    }

    public Node getLeftNode(){
        return leftNode;
    }

    public int getHeight(){
        return endY-startY;
    }

    public int getWidth(){
        return endX-startX;
    }
    
    /**
     * Calcul le poids d'une feuille
     * @param w la largeur
     * @param h la hauteur
     * @return le poids (double)
     */
    public double weight(){
        int w = this.endX - this.startX;
        int h = this.endY - this.startY;
        
        this.weight = (w*h)/(Math.pow((w+h), 1.5));
        return this.weight;
    }
}
