package com.delplanquerideau;

import java.awt.*;

/**
 * Class représentant un noeud dans un 2d-arbres
 */


public class Node {

    private static int ID = 1;

    private int nodeID;
    private CuttingDirection cuttingDirection;
    private Color color;
    private int startX;
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
    public Node(CuttingDirection cuttingDirection, Color color, int startX, int endX, int startY, int endY){
        this.nodeID = ID++;
        this.cuttingDirection = cuttingDirection;
        this.color = color;
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
        this.isALeaf = true;
    }

    public int getID() {
        return this.nodeID;
    }

    public CuttingDirection getCuttingDirection(){
        return this.cuttingDirection;
    }

    public Color getColor(){
        return this.color;
    }

    public int getStartX() {
        return this.startX;
    }

    public int getEndX() {
        return this.endX;
    }

    public int getStartY() {
        return this.startY;
    }

    public int getEndY() {
        return this.endY;
    }

    public Node getRightNode(){
        return this.rightNode;
    }

    public Node getLeftNode(){
        return this.leftNode;
    }

    public int getHeight(){
        return this.endY-this.startY;
    }

    public int getWidth(){
        return this.endX-this.startX;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
        this.isALeaf = false;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
        this.isALeaf = false;
    }

    public boolean getIsALeaf(){
        return this.isALeaf;
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
